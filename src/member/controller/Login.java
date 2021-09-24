package member.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import member.bean.Member;
import member.dao.MemberDAO;
import member.dao.MemberDAOFactory;


/**
 * Servlet implementation class User
 */

@WebServlet(urlPatterns = { "/Member/Login" }, initParams = {
		@WebInitParam(name = "SUCCESS", value = "../Home"),
		@WebInitParam(name = "ERROR", value = "./loginForm.html")}
)
	
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String SUCCESS_VIEW;
	private String ERROR_VIEW;
	// 從cookie獲取已登入的使用者名稱
	private String userName = "";
	private boolean isAlreadyLogin = false;
	
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
		System.out.println("\nuser: "+userName);
		System.out.println("isAlreadyLogin: "+isAlreadyLogin);

		if (isAlreadyLogin) {
			// 有登入紀錄 導向歡迎頁面
			response.sendRedirect(SUCCESS_VIEW);
		} else {
			// 沒有登入紀錄 導向登入頁面
			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
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
					// 使全站(localhost下所有子路徑)都可以存取此路徑的cookie 而不只是原本限制的/Member底下
					cookie.setPath("/");
//					session.setAttribute("user", cookie.getValue());
					// 單位:秒
					cookie.setMaxAge(30 * 60); //30分鐘內有效
					response.addCookie(cookie);
					
					isAlreadyLogin = true;
					response.sendRedirect(SUCCESS_VIEW);
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
	
	private void CheckLogin(Cookie[] cookies) {
		if (cookies != null) { // 有登入紀錄
			for (Cookie cookie : cookies) {
				if (cookie.getName() == "user") {
					isAlreadyLogin = true;
					userName = cookie.getName();
					System.out.println("CookieName: "+cookie.getName());
					System.out.println("CookieValue: "+cookie.getValue());
					break;
				}
			}
		} 
//		else { // 沒登入或者登入時限已到(cookie過期)
//			
//		}
	}
}
