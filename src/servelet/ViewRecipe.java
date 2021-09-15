package servelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.RecipeBean;

@WebServlet("/ViewRecipe")

public class ViewRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
	
       
    public ViewRecipe() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		
//		PrintWriter out = response.getWriter();
		
		InitialContext ctxt = null;
		DataSource ds = null;
		Connection conn = null;
		
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/EmployeeDB");
			conn = ds.getConnection();
			ArrayList<RecipeBean> recipeList  = new ArrayList<RecipeBean>();
			
			String sqlStr = "select * from recipe";
			PreparedStatement preState = conn.prepareStatement(sqlStr);
			ResultSet rs = preState.executeQuery();
//			request.setAttribute("rs", rs);
			while (rs.next()) {
				RecipeBean recipe = new RecipeBean(
						rs.getInt("id"),
						rs.getString("Name"),
						rs.getString("Category"), 
						rs.getString("Food1"), 
						rs.getString("Food2"), 
						rs.getString("Food3"), 
						rs.getString("Food4"), 
						rs.getString("Sauce1"), 
						rs.getString("Sauce2"), 
						rs.getString("Sauce3"), 
						rs.getBytes("Photo"));
				recipeList.add(recipe);
			}
//			System.out.println(recipeList.toString());
			ArrayList<String> imgList =  new ArrayList<String>();
			for (RecipeBean imgBean : recipeList) {
				 String base64 = Base64.getEncoder().encodeToString(imgBean.getPhoto());
				imgList.add(base64);
			}
			request.getSession(true).setAttribute("imgList", imgList);		
			System.out.println("資料庫共"+ imgList.size() +"筆");
			
//			request.setAttribute("recipe",recipeList.get(0)); // 抓一筆資料
			HttpSession session = request.getSession(true);
			session.setAttribute("recipeList",recipeList); // 抓多筆資料
			request.getRequestDispatcher("./ViewRecipe.jsp").forward(request, response);
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	}

}
