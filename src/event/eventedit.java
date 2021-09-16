package event;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet implementation class eventedit
 */
@WebServlet("/Event/eventedit")
public class eventedit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public eventedit() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");  
		out.println("<h1>Update Event</h1>");
		String sid = request.getParameter("id");
		//int id = Integer.parseInt(sid);
		eventtest1 e = eventdao.getEventByName(sid);
		out.print("<form action='./eventedit2' method='post'>");
		out.print("<table>");
		//out.print("<tr><td></td><td><input type='hidden' name='id' value='" + e.getId() + "'/></td></tr>");
		out.print("<tr><td>Name:</td><td><input type='text' name='name' value='" + e.getName() + "'/></td></tr>");
		out.print("<tr><td>People Number:</td><td><input type='text' name='people_num' value='" + e.getpeople_num() + "'/></td></tr>");
		out.print("<tr><td>Time:</td><td><input type='text' name='time' value='" + e.gettime() + "'/></td></tr>");
		out.print("<tr><td>Content:</td><td><input type='text' name='content' value='" + e.getcontent()
				+ "'/></td></tr>");
		
		
		out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");
		out.print("</table>");
		out.print("</form>");

		out.close();
//		eventdao.update(e);  
//        response.sendRedirect("./eventread");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
