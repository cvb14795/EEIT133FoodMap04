package Coupon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Coupon.model.QuestionnaireBean;
import Coupon.model.QuestionnaireDAO;
import util.gmail.Mail;
import util.hibernate.HibernateUtil;



/**
 * Servlet implementation class AdministratorController
 */
@WebServlet("/Coupon/administratorcontroller")
public class AdministratorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PATH;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministratorController() {
        super();
    }
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		String action = request.getParameter("action");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		
		if (action.equals("R")){
			try {
				
				QuestionnaireDAO qDAO = new QuestionnaireDAO(session);
				
				PATH = "view.jsp";  
				List<QuestionnaireBean> vaccine = qDAO.QueryDataByVaccine();
				request.setAttribute("vaccine", vaccine);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				request.getRequestDispatcher(PATH).forward(request, response);
			}
			
		}else if (action.equals("U")) {
				try {
					
					QuestionnaireDAO qDAO = new QuestionnaireDAO(session);
					
					PATH = "view2.jsp";
					List<QuestionnaireBean> sendUsersCoupons = qDAO.SendUsersCoupons();
//					Mail.SendGmail(action, action, action, action);
					
					for(QuestionnaireBean b:sendUsersCoupons) {
						System.out.println(b.getId());
					}
					
					request.setAttribute("sendUsersCoupons", sendUsersCoupons);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					request.getRequestDispatcher(PATH).forward(request, response);
				}
				
		}else if (action.equals("B")) {
			try {
				
				QuestionnaireDAO qDAO = new QuestionnaireDAO(session);
				
				PATH = "view3.jsp";
				List<QuestionnaireBean> revokeUsersCoupons = qDAO.revokeUsersCoupons();
				
				request.setAttribute("revokeUsersCoupons", revokeUsersCoupons);
				
			} catch (Exception e) {			
				e.printStackTrace();
			} finally {
				request.getRequestDispatcher(PATH).forward(request, response);
			}
		}
	}

}
