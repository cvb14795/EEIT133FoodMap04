package Food.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Food.model.MapData;
import Food.model.MapDataDAO;
import util.hibernate.HibernateUtil;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Food/DelData")
public class DelData extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DelData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  request.setCharacterEncoding("UTF-8");  //setup response character encoding type
		  
		  response.setContentType("text/html");   //setup response content type
		  response.setCharacterEncoding("UTF-8"); //setup response character encoding type
		  

		  String param1 = request.getParameter("name");

		  SessionFactory factory = HibernateUtil.getSessionFactory();
		  Session session = factory.getCurrentSession();
		  
		  MapDataDAO mDAO = new MapDataDAO(session);		  		  
		  List<MapData> mapData = mDAO.findByName(param1);
		  request.setAttribute("mapData", mapData.get(0));	
		  mDAO.deleteMapDataByname(param1);
		  request.getRequestDispatcher("./DelDataResult.jsp").forward(request, response);
//		  try {
//			mDAO.createConn();
//			
////			//先搜尋NAME對應的mapData
//			
//			
//			//有結果後刪除該NAME對應的資料
//			
//			
//			mDAO.closeConn();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
	  
		  
	}
}
