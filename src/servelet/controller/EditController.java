package servelet.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import bean.RecipeBean;
import servelet.RecipeDAO;

@WebServlet("/EditController")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       
    public EditController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 呼叫DAO的update 對資料庫資料進行修改
		
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		
		InitialContext ctxt = null;
		DataSource ds = null;
		Connection conn = null;
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int id = Integer.parseInt(request.getParameter("id")); 
		String name = request.getParameter("name");	
		String category = request.getParameter("category");	
		String food1 = request.getParameter("food1");	
		String food2 = request.getParameter("food2");	
		String food3 = request.getParameter("food3");	
		String food4 = request.getParameter("food4");	
		String sauce1 = request.getParameter("sauce1");	
		String sauce2 = request.getParameter("sauce2");	
		String sauce3 = request.getParameter("sauce3");
		RecipeBean updateBean = new RecipeBean(id, name,category,food1,food2,food3, food4,sauce1,sauce2,sauce3,null);
		
		RecipeDAO editDAO = new RecipeDAO(conn);
		editDAO.updateData(updateBean);
		System.out.println("已更新資料");
		request.getSession(true).invalidate();
		request.getRequestDispatcher("./Success.jsp").forward(request, response);
		
//		response.getWriter().println("已更新資料"); // 顯示在網頁上
		
//		request.getRequestDispatcher("./Edit.jsp").forward(request, response);
	
	
	}

}
