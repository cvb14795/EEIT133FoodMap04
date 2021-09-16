package Food.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Food.bean.MapData;

/**
 * Servlet implementation class RestaurantInfo
 */
@WebServlet("/Food/RestaurantInfo")
public class RestaurantInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		MapDataDAO mDAO = MapDataDAOFactory.getMapDataDAO();
		try {
			mDAO.createConn();
			String mapName = request.getParameter("mapName");
			System.out.println("\n查詢店家名稱: "+mapName);
			MapData mapData = mDAO.findByName(mapName);
			request.setAttribute("mapData", mapData);
			
			mDAO.closeConn();
		
			request.getRequestDispatcher("./RestaurantInfo.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
	}

}
