package Food.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Food.model.MapData;
import Food.model.MapDataDAO;
import Food.util.HibernateUtil;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Food/Revise")
public class Revise extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Revise() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8"); // setup response character encoding type

		response.setContentType("text/html"); // setup response content type
		response.setCharacterEncoding("UTF-8"); // setup response character encoding type

		// response.setHeader("content-type","text/html;charset=UTF-8");
		// response.setContentType("text/html;charset=UTF8");
		PrintWriter out = response.getWriter();
		String title = "修改結果";

		// String param1 = new
		// String(request.getParameter("param1").getBytes("iso-8859-1"), "UTF-8");
		// String param2 = new
		// String(request.getParameter("param2").getBytes("iso-8859-1"), "UTF-8");
		// String param3 = new
		// String(request.getParameter("param3").getBytes("iso-8859-1"), "UTF-8");

		String param1 = request.getParameter("name");
		String param2 = request.getParameter("map");
		String param3 = request.getParameter("number");
		String param4 = request.getParameter("XY");
		String param5 = request.getParameter("checkiso");
		String imgName = request.getParameter("imgName");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		
		MapDataDAO mDAO = new MapDataDAO(session);
		MapData mapData = new MapData(param1, param2, param3, param4, param5, imgName);
		request.setAttribute("mapData", mapData);
		mDAO.updateName(param1, mapData);
		
		StringBuilder sb = new StringBuilder();
		sb.append("servlet: ");
		sb.append("\nparam1: " + param1);
		sb.append("\nparam2: " + param2);
		sb.append("\nparam3: " + param3);
		sb.append("\nparam4: " + param4);
		sb.append("\nparam5: " + param5);
		sb.append("\nimgName: " + imgName);
		System.out.println(sb.toString());
		
		request.getRequestDispatcher("./ReviseResult.jsp").forward(request, response);
//		  try {
//			  mDAO.createConn();
//			mDAO.updateName(mapData);
//			mDAO.closeConn();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	  
		
	}

}
