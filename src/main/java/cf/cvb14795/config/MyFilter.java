package cf.cvb14795.config;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyFilter implements Filter {
	Map<URL, Boolean> whiteList = new HashMap<>();
	boolean redirect = false;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		whiteList.put(new URL(request.getContextPath()+"/FoodMap04/"), Boolean.FALSE);
		whiteList.put(new URL(request.getContextPath()+"/FoodMap04/Member"), Boolean.FALSE);
		whiteList.put(new URL(request.getContextPath()+"/css/"), Boolean.FALSE);
		whiteList.put(new URL(request.getContextPath()+"/js/"), Boolean.FALSE);
		whiteList.put(new URL(request.getContextPath()+"/image/"), Boolean.FALSE);
		whiteList.put(new URL(request.getContextPath()+"/test/"), Boolean.FALSE);
		whiteList.put(new URL(request.getContextPath()+"/static/"), Boolean.FALSE);
		
		/* 待修改 白名單要寫在其他檔案或方法裡面 */
		// 連進的網址非首頁且使用者尚未登入時 跳轉至登入畫面
		// 若連進首頁則正常顯示

		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
//			System.out.println("=====filter=====\nuser:"+user);

		// 曾登入但已經登出 或尚未登入
		if (user == null) {
			System.out.println("*****Not Login*****");
			session.setAttribute("user", "");
			session.setAttribute("isAdmin", false);
			user = "";
			redirect = true;
		}
		
		for (Map.Entry<String, Boolean> me : whiteList.entrySet()) {
			whiteList.replace(me.getKey(), request.getRequestURI().startsWith(me.getKey()));
		}
		if (whiteList.containsValue(true)) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		} else {
			// 若未登入則存取非首頁亦非member路徑下之網站 則重新導向至登入網址
			redirect = true;
		}

		if (redirect) {
			// 非首頁也非member頁面或已登出(沒有session) 表示需要跳轉到登入畫面
			System.out.println("=====redirect to login=====");
			// 發送重新導向至登入頁面請求
			response.sendRedirect(request.getContextPath() + "/Member/Login");
			redirect = false;
			return;
		} else {
			chain.doFilter(servletRequest, servletResponse);
		}
	}
}
