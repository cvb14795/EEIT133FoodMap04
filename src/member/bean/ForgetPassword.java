package member.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dao.MemberDAO;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/ForgetPassword")
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
    	
    	request.getRequestDispatcher("./forgetPassword.jsp").forward(request, response);
    	
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userAccount = request.getParameter("account");
		String email = request.getParameter("email");
		
		MemberDAO mDAO = new MemberDAO();
		
		try {
			mDAO.createConn();
			
			if (mDAO.findAllByAccount(userAccount) != null) {
				Properties  props = new Properties (); 
				props.put("mail.imap.ssl.enable", "true"); // required for Gmail
				props.put("mail.imap.auth.mechanisms", "XOAUTH2");
				Session session = Session.getInstance(props);
				Store store = session.getStore("imap");
//				store.connect("imap.gmail.com", userAccount, oauth2_access_token);
			} else {

			}
			
			mDAO.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    

}
