package user.recipe.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.bean.RecipeBean;
import recipe.servelet.RecipeDAO;

@WebServlet("/Recipe/UserViewAdminRecipe")
public class UserViewAdminRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public UserViewAdminRecipe() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);

		try {
			if (request.getParameter("submit") != null) {
				RecipeDAO selectDAO = new RecipeDAO();
				selectDAO.createConn();
				ArrayList<RecipeBean> recipeList = selectDAO.findAllRecipe();
				selectDAO.closeConn();
				ArrayList<String> imgList = new ArrayList<String>();
				for (RecipeBean imgBean : recipeList) {
					String base64 = Base64.getEncoder().encodeToString(imgBean.getPhoto());
					imgList.add(base64);
				}
				request.getSession(true).setAttribute("imgList", imgList);
				System.out.println("資料庫共" + imgList.size() + "筆");

				HttpSession session = request.getSession(true);
				session.setAttribute("recipeList", recipeList); // 抓多筆資料
				request.getRequestDispatcher("./user/UserViewAdminRecipe.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
