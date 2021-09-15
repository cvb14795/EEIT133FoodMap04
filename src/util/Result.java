package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Result
 */
@WebServlet(description = "後端回應JSON", urlPatterns = { "/Result" })
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String jsonString = gson.toJson(request.getAttribute("member"));
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonString);
		System.out.println(jsonString);
		
//		JsonArray jArray= new JsonArray();
//		jArray.add("1");
//		jArray.add("2");
//		jArray.add("3");
		
		out.flush();
	}

}
