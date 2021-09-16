package Food.AddData;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Food.bean.MapData;
import Food.controller.MapDataDAO;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Food/AddData")
public class AddData extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddData() {
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
		  
		  
		  //response.setHeader("content-type","text/html;charset=UTF-8");
		  //response.setContentType("text/html;charset=UTF8");
		  PrintWriter out = response.getWriter();
		  String title = "新增結果";
		  
		 // String param1 = new String(request.getParameter("param1").getBytes("iso-8859-1"), "UTF-8");
		 // String param2 = new String(request.getParameter("param2").getBytes("iso-8859-1"), "UTF-8");
		 // String param3 = new String(request.getParameter("param3").getBytes("iso-8859-1"), "UTF-8");
		  
		  String param1 = request.getParameter("name");
		  String param2 = request.getParameter("map");
		  String param3 = request.getParameter("number");
		  String param4 = request.getParameter("XY");
		  String param5 = request.getParameter("checkiso");
		  String imgName = request.getParameter("imgName");
		  
		  
		  MapDataDAO mDAO = new MapDataDAO();		  
		  MapData mapData= new MapData(param1,param2,param3,param4,param5,imgName);
		  request.setAttribute("mapData", mapData);		  
		  
		  try {
			mDAO.createConn();
			mDAO.addMapData(mapData);
			mDAO.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	  
		request.getRequestDispatcher("./AddDataResult.jsp").forward(request, response);
	}
	
}
