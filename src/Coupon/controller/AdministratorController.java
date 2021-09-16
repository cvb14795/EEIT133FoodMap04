package Coupon.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Coupon.model.QuestionnaireBean;
import Coupon.model.QuestionnaireDAO;
import Coupon.model.QuestionnaireDAOFactor;

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
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String action = request.getParameter("action");
		QuestionnaireDAO questionnaireDAOFactor = QuestionnaireDAOFactor.getQuestionnaireDAO();
		
		if (action.equals("R")){
			try {
				PATH = "view.jsp";
				questionnaireDAOFactor.createConn();
				List<QuestionnaireBean> vaccine = questionnaireDAOFactor.QueryDataByVaccine();
				
				request.setAttribute("vaccine", vaccine);
				request.getRequestDispatcher(PATH).forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					questionnaireDAOFactor.closeConn();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if (action.equals("U")) {
				try {
					PATH = "view2.jsp";
					questionnaireDAOFactor.createConn();
					List<QuestionnaireBean> sendCouponsUsers = questionnaireDAOFactor.SendCouponsUsers();
					
					request.setAttribute("sendCouponsUsers", sendCouponsUsers);
					request.getRequestDispatcher(PATH).forward(request, response);
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						questionnaireDAOFactor.closeConn();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
		}else if (action.equals("B")) {
			try {
				PATH = "view3.jsp";
				questionnaireDAOFactor.createConn();
				List<QuestionnaireBean> revokeCouponsUsers = questionnaireDAOFactor.revokeCouponsUsers();
				
				request.setAttribute("revokeCouponsUsers", revokeCouponsUsers);
				request.getRequestDispatcher(PATH).forward(request, response);
			} catch (Exception e) {			
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				try {
					questionnaireDAOFactor.closeConn();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
