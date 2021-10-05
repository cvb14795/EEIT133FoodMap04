package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import member.bean.Member;
import member.dao.MemberService;
import util.hibernate.HibernateUtil;

/**
 * Servlet implementation class User
 */

@WebServlet(urlPatterns = { "/Member/Login" },
			initParams = {
					@WebInitParam(name = "loginPage", value = "./loginForm.html"),
			}
)

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String loginPage;
	// 從cookie獲取已登入的使用者名稱
	private String userName = "";
	private boolean isAlreadyLogin = false;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		loginPage = this.getInitParameter("loginPage");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession(true);

		// 確認登入紀錄
		Cookie[] cookies = request.getCookies();
		// 使用者是否已有登入紀錄(cookie)
		CheckLogin(cookies);

		session.setAttribute("user", userName);
		System.out.println("\nuser: " + userName);
		System.out.println("isAlreadyLogin: " + isAlreadyLogin);

		if (!isAlreadyLogin) {
			// 沒有登入紀錄 導向登入頁面
			request.getRequestDispatcher(loginPage).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		MemberService mService = new MemberService(session);

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		userName = request.getParameter("account");
//			Member m = mDAO.findAllByAccount(userName);
		Member m = mService.selectMemberByAccount(userName);
		String userPassword = request.getParameter("password");
		System.out.println("正在驗證使用者:" + userName + "的登入...");
		if (m != null) {
			// 撈資料庫傳回的加密後密碼
			String hashPassword = m.getPassword();
			boolean checkpw = BCrypt.checkpw(userPassword, hashPassword);

			if (checkpw) {
				Cookie cookie = new Cookie("user", userName);
				// 使全站(localhost下所有子路徑)都可以存取此路徑的cookie 而不只是原本限制的/Member底下
				cookie.setPath("/");
//					session.setAttribute("user", cookie.getValue());
				/* ==========不設置maxAge 則默認為-1（cookie直到關閉瀏覽器才會消失）=========== */
//					cookie.setMaxAge(30 * 60); //30分鐘內有效
				/* ====================== */
				response.addCookie(cookie);

				isAlreadyLogin = true;
			} else {
				// 密碼錯誤
				// 改成JSON回傳 前端login接收並alert
				System.out.println("密碼錯誤!");
//					request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} else {
			// 找不到此帳號
			// 改成JSON回傳 前端login接收並alert
			System.out.println("無此帳號!");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}

	private void CheckLogin(Cookie[] cookies) {
		if (cookies != null) { // 有登入紀錄
			for (Cookie cookie : cookies) {
				if (cookie.getName() == "user") {
					isAlreadyLogin = true;
					userName = cookie.getName();
					System.out.println("CookieName: " + cookie.getName());
					System.out.println("CookieValue: " + cookie.getValue());
					break;
				} else {
					isAlreadyLogin = false;
				}
			}
		}
	}
}
