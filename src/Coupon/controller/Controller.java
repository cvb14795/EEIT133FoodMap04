package Coupon.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Coupon.model.QuestionnaireBean;
import Coupon.model.QuestionnaireDAO;
import Coupon.model.QuestionnaireDAOFactor;

/**
 * Servlet implementation class Check
 */
@WebServlet("/Coupon/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String RECORD = "./records";
	private final String SUCCESS_PATH = "send_success.jsp";
	private final String ERROR_PATH = "send_error.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String phone = request.getParameter("phone");
		String foreign = request.getParameter("foreign");
		String move = request.getParameter("move");
		String family = request.getParameter("family");
		String vaccine = request.getParameter("vaccine");
		String fever = request.getParameter("fever");
		
		QuestionnaireDAO questionnaireDAOFactor = QuestionnaireDAOFactor.getQuestionnaireDAO();
			
		Path userhome = Paths.get(RECORD);
		System.out.println("Record檔路徑: "+userhome);
		String path;
//		if (!(checkProfile(userhome, id))) {
		// 測試 不使用record
		if(true) {
			path = SUCCESS_PATH;
//			createUser(userhome, id);
			try {
				questionnaireDAOFactor.createConn(); // 開啟資料庫連線
				
				//設置Bean
				QuestionnaireBean qbean = new QuestionnaireBean();
				qbean.setName(name);
				qbean.setId(id);
				qbean.setBirth(birth);
				qbean.setGender(gender);
				qbean.setPhone(phone);
				qbean.setForeign(foreign);
				qbean.setMove(move);
				qbean.setFamily(family);
				qbean.setVaccine(vaccine);
				qbean.setFever(fever);
				qbean.setLabel("0");     //是否發放優惠劵
				questionnaireDAOFactor.addNewData(qbean);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					questionnaireDAOFactor.closeConn();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else {
			
			path = ERROR_PATH;
			
		}
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	
	// 將身分證字號寫入profile
		private void createUser(Path userhome, String id) throws IOException {
			Path profile = userhome.resolve("profile");
			try (BufferedWriter writer = Files.newBufferedWriter(profile, StandardOpenOption.APPEND, StandardOpenOption.CREATE)){
				writer.write("\n"+id);
				
			}
		}
	
	// 檢查是否填過問卷
	private boolean checkProfile(Path userhome, String id) throws IOException {
		boolean adj = false;
		String existId;
		Path profile = userhome.resolve("profile");
		try (BufferedReader reader = Files.newBufferedReader(profile)){
			while ((existId=reader.readLine())!=null) {
				if (existId.equals(id)) {
					adj =  true;
					break;
				} else {
					adj = false;
				}
			}
		}
		
		return adj;
		
	}
	
	



}
