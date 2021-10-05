package event;

import java.io.IOException;  
import java.io.PrintWriter;  
import java.util.List;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

/**
 * Servlet implementation class eventread
 */
@WebServlet("/Event/eventread")
public class eventread extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eventread() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        out.println("<a href='./eventtest2.html'>Add New Event</a>");  
        out.println("<h1>Events List</h1>");  
          
        List<eventtest1> list=eventdao.getAlleventtest1();  
          
        out.print("<table border='1' width='100%'");  
        out.print("<tr><th>Id</th><th>Name</th><th>People Number</th><th>Time</th><th>Content</th><th>Edit</th><th>Delete</th></tr>");  
        int fakeID = 1;
        for(eventtest1 e:list){  
        	out.print("<tr><td>"+fakeID+"</td><td>"+e.getName()+"</td><td>"+e.getpeople_num()+"</td><td>"+e.gettime()+"</td><td>"+e.getcontent()+"</td><td><a href='./eventedit?id="+e.getName()+"'>edit</a></td><td><a href='./eventdelete?id="+e.getName()+"'>delete</a></td></tr>");  
        	fakeID += 1;
        }  
        out.print("</table>");  
          
        out.close();  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
