package Event;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class eventjava
 */
@WebServlet("/Event/eventjava")
public class eventjava extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public eventjava() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("eventtest2.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub 
		
		if(request.getParameter("eason")!=null){
			test(request, response);
		}
		
	}
	
	public void test(HttpServletRequest request, HttpServletResponse response) {
		DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    
	    

	     
	      //建立Context Object,連到JNDI Server
	      try {
	    	  
			ctxt = new InitialContext();
			 //使用JNDI API找到DataSource
		      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/TeamProject");
		     
		      //向DataSource要Connection
		      conn = ds.getConnection();

		      //建立Database Access Object,負責Table的Access
		      eventdao deptDAO = new eventdao();

		      //如果要編碼值為UTF-8
		     request.setCharacterEncoding("UTF-8");

//			doGet(request, response);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			String name = request.getParameter("name");
			String content = request.getParameter("content");
			String time = request.getParameter("time");
			int people_num = Integer.parseInt(request.getParameter("people_num"));
			
			
			eventtest1 e = new eventtest1();
			e.setName(name);
			e.setcontent(content);
			e.settime(time);
			e.setpeople_num(people_num);

			int status = eventdao.save(e);
			if (status > 0) {
				out.print("<p>Record saved successfully!</p>");
				request.getRequestDispatcher("./eventtest2.html").include(request, response);
			} else {
				out.println("Sorry! unable to save record");
			}

			out.close();
		} catch (NamingException | SQLException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	     
	}
	}
