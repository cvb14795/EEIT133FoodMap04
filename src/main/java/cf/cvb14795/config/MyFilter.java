package cf.cvb14795.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyFilter implements Filter {
	// 網站根目錄
	final private static String BASE_URL = "/FoodMap04";

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		/* 待修改 白名單要寫在其他檔案或方法裡面 */
		// 連進的網址非首頁且使用者尚未登入時 跳轉至登入畫面
		// 若連進首頁則正常顯示

		boolean isWhiteList1 = request.getRequestURI().startsWith(BASE_URL + "/");
		boolean isWhiteList2 = request.getRequestURI().startsWith(BASE_URL + "/Member");

		// 非首頁也非member頁面或已登出(沒有session) 表示需要跳轉到登入畫面
		if (isWhiteList1 || isWhiteList2) {
			HttpSession session = request.getSession();
			String user = (String) session.getAttribute("user");
			if (user == null) {
				session.setAttribute("user", "");
				session.setAttribute("isAdmin", false);
				user = "";
			} else {
				chain.doFilter(servletRequest, servletResponse);
			}
		} else {
			response.sendRedirect(BASE_URL + "/Member/Login");
		}
	}
}
