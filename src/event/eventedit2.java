package event;

import java.io.IOException;
import java.io.PrintWriter;  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class eventedit2
 */
@WebServlet("/Event/eventedit2")
public class eventedit2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public eventedit2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html"); 
		PrintWriter out=response.getWriter();  
		String sid=request.getParameter("id");  
        //int id=Integer.parseInt(sid);  
        String name=request.getParameter("name");  
        String content=request.getParameter("content");  
        String time=request.getParameter("time");  
        int people_num = Integer.parseInt(request.getParameter("people_num"));
          
        eventtest1 e= new eventtest1 (people_num,name,content,time);  
        //e.setId(id);  
        e.setName(name);
		e.setcontent(content);
		e.settime(time);
		e.setpeople_num(people_num);
		
        int status=eventdao.update(e);  
        if(status>0){  
//            response.sendRedirect("./ViewServlet");  
            response.sendRedirect("./eventtest2.html");  
        }else{  
            out.println("Sorry! unable to update record");  
        }  
          
        out.close();  
    }  
	}


