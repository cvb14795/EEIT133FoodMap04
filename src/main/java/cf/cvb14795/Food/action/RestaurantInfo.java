package cf.cvb14795.Food.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cf.cvb14795.Food.model.MapData;
import cf.cvb14795.Food.model.MapDataDAO;
import util.hibernate.HibernateUtil;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		MapDataDAO mDAO = new MapDataDAO(session);
		String mapName = request.getParameter("mapName");
		System.out.println("\n查詢店家名稱: " + mapName);
		List<MapData> mapData = mDAO.findByName(mapName);
		request.setAttribute("mapData", mapData.get(0));
		request.getRequestDispatcher("./RestaurantInfo.jsp").forward(request, response);
//		try {
//			mDAO.createConn();
//			mDAO.closeConn();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
