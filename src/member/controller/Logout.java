package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Member/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		// 登出 清空session
		session.invalidate();
		// 清空使用者名稱
		Cookie cookie = new Cookie("user", "");
		// cookie是屬於某個path及domain的 因此也要設定path才會使該名稱的cookie被刪除 
		// 而domain皆預設為該網域及其子網域 所以不用動
		cookie.setPath("/");
//		session.setAttribute("user", cookie.getValue());
		// 使cookie立即失效
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		request.getRequestDispatcher("./goodbyeUser.jsp").forward(request, response);
	}
}
