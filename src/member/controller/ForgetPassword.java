package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import member.bean.Member;
import member.dao.MemberService;
import util.gmail.Mail;
import util.hibernate.HibernateUtil;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/Member/ForgetPassword")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	System.out.println(request.getParameter("method"));
    	if (request.getParameter("method") == "reset") {
			out.write("重設密碼(測試中)");
		} else {
			request.getRequestDispatcher("./forgetPassword.html").forward(request, response);			
		}
    	
    	
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/** Authorizes the installed application to access user's protected data. */
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		MemberService mService = new MemberService(session);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String userAccount = request.getParameter("account");
		// 收件者(用戶輸入之email)
		String recipientEmail = request.getParameter("email");
		// 郵件主旨
		String subject = "FoodMap美食地圖——重設密碼認證信";
		// 郵件內容
		String text = "您好，" + userAccount + "！ <br/>"+ 
				"<a href='http://localhost:8080/FoodMap04/Member/ForgetPassword?method=reset'>請點擊以下連結修改您的密碼</a>";
		
			System.out.println("正在確認註冊時信箱是否正確...");
			Member m = mService.selectMemberByAccount(userAccount);
			boolean isAccountCorrect = m.getAccount().equals(userAccount);
			boolean isEmailCorrect = m.getEmail().equals(recipientEmail);
			if (isAccountCorrect && isEmailCorrect) {
				// 寄信
				Mail.SendGmail("me", recipientEmail, subject, text);
				System.out.println("送出成功");
			} else {
				// 找不到該用戶
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
	}
    

}
