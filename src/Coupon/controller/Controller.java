package Coupon.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Coupon.model.QuestionnaireBean;
import Coupon.model.QuestionnaireDAO;
import util.hibernate.HibernateUtil;

/**
 * Servlet implementation class controller
 */
@WebServlet(name = "Controller", 
			urlPatterns = {"/Coupon/controller"},
			initParams = {
			// 設定初始參數
					@WebInitParam(name="SUCCESS", value="send_success.jsp"),//成功路徑
					@WebInitParam(name="ERROR", value="send_error.jsp")   //失敗路徑
			})

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String SUCCESS_PATH;
	private String ERROR_PATH;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
    }
    
    @Override
    public void init() throws ServletException {
    	// 取得初始參數
		SUCCESS_PATH = getInitParameter("SUCCESS");
		ERROR_PATH = getInitParameter("ERROR");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processAction(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processAction(request, response);
    }
	
	protected void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		String abroad = request.getParameter("foreign");
		String moving = request.getParameter("move");
		String family = request.getParameter("family");
		String vaccine = request.getParameter("vaccine");
		String fever = request.getParameter("fever");
		String label = "0";
		String path = null;
		
	
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		//設置Java Bean
		QuestionnaireBean qBean = new QuestionnaireBean(id, name, gender, birth, phone, 
				abroad, moving, family, vaccine, fever, label);
		
		
		try {
			QuestionnaireDAO qDAO = new QuestionnaireDAO(session);
			qDAO.addNewData(qBean);
			path = SUCCESS_PATH;	
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("帳號重複 已註冊過!");
			path = ERROR_PATH;
		} finally {
			request.getRequestDispatcher(path).forward(request, response);
		}
		
		
	}

}
