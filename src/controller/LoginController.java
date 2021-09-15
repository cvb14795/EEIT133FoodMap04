package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import member.Member;
import member.MemberDAO;
import member.MemberDAOFactory;

/**
 * Servlet implementation class User
 */

@WebServlet(urlPatterns = { "/Login" }, initParams = {
		@WebInitParam(name = "SUCCESS", value = "./HelloUser.jsp"),
		@WebInitParam(name = "ERROR", value = "./loginForm.html")}
)
	
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String SUCCESS_VIEW;
	private String ERROR_VIEW;
	// 從cookie獲取已登入的使用者名稱
	private String userName = "";
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		SUCCESS_VIEW = this.getInitParameter("SUCCESS");
		ERROR_VIEW = this.getInitParameter("ERROR");
	}
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
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
		
		// 確認登入紀錄
		Cookie[] cookies = request.getCookies();
		// 使用者是否已有登入紀錄(cookie)
		boolean isAlreadyLogin = CheckLogin(cookies);
		
		request.setAttribute("user", userName);
		request.setAttribute("isAlreadyLogin", isAlreadyLogin);

		if (isAlreadyLogin) {
			// 有登入紀錄 導向歡迎頁面
			request.getRequestDispatcher("./HelloUser.jsp").forward(request, response);
		} else {
			// 沒有登入紀錄 導向登入頁面
			request.getRequestDispatcher("./loginForm.html").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MemberDAO mDAO = MemberDAOFactory.getMemberDAO();

		try {
			/* ========== 開啟連線 ========== */
			mDAO.createConn();

			userName = request.getParameter("account");
			Member m = mDAO.findAllByAccount(userName);
			String userPassword = request.getParameter("password");
			System.out.println("正在驗證使用者:"+ userName +"的登入...");
			if (m != null) {
				// 撈資料庫傳回的加密後密碼
				String hashPassword = m.getPassword();
				boolean checkpw = BCrypt.checkpw(userPassword, hashPassword);

				mDAO.closeConn();
				/* ========== 關閉連線(將連線還回連線池) ========== */

				if (checkpw) {
					Cookie cookie = new Cookie("user", userName);
					request.setAttribute("user", userName);
					// 單位:秒
					cookie.setMaxAge(30 * 60); //30分鐘內有效
					response.addCookie(cookie);
					request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
				} else {
					// 密碼錯誤
					//改成JSON回傳 前端login接收並alert
					System.out.println("密碼錯誤!");
					request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
				}
				
			} else {
				// 找不到此帳號
				//改成JSON回傳 前端login接收並alert
				System.out.println("無此帳號!");
				request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}

	}
	
	private boolean CheckLogin(Cookie[] cookies) {
		boolean isAlreadyLogin = false;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName() == "users") {
					isAlreadyLogin = true;
					userName = cookie.getName();
				}
				System.out.println("CookieName: "+cookie.getName());
				System.out.println("CookieValue: "+cookie.getValue());
			}
		}
		return isAlreadyLogin;
	}
}
