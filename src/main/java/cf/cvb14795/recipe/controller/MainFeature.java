package recipe.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import member.dao.MemberService;
import recipe.model.RecipeBeanDao;
import util.MemberStatus;
import util.hibernate.HibernateUtil;

/**
 * Servlet implementation class MainFeature
 */
@WebServlet(name = "Recipe/main", urlPatterns = { "/Recipe/main" })
public class MainFeature extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainFeature() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		String nextPage = "./UserStartingPage.jsp";
		
		MemberStatus status = new MemberStatus(request.getCookies());
		String userAccount = status.getCurrentUserAccount();
		httpSession.setAttribute("user", userAccount);
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session hibernateSession = factory.getCurrentSession();
		MemberService mService = new MemberService(hibernateSession);
		// 若沒有登入(沒有帳號)時則跳轉到登入畫面
		httpSession.setAttribute("isAdmin", mService.isAdmin(userAccount));
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
