package user.recipe.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import recipe.bean.UserRecipeBean;
import recipe.servelet.UserRecipeDAO;

@MultipartConfig
@WebServlet("/Recipe/UserInsertRecipe")
public class UserInsertRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public UserInsertRecipe() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server

		if (request.getParameter("submit") != null) {
			gotoSubmit(request, response);
		} else if (request.getParameter("confirm") != null) {
			try {
				gotoConfirm(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void gotoSubmit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName").trim();
		String foodName = request.getParameter("foodName").trim();
		String category = request.getParameter("category").trim();
		String food1 = request.getParameter("food1").trim();
		String food2 = request.getParameter("food2").trim();
		String food3 = request.getParameter("food3").trim();
		String food4 = request.getParameter("food4").trim();
		String sauce1 = request.getParameter("sauce1").trim();
		String sauce2 = request.getParameter("sauce2").trim();
		String sauce3 = request.getParameter("sauce3").trim();
		Part photoPart;
		byte[] photo = new byte[1024];
		photoPart = request.getPart("photo");

		InputStream is = photoPart.getInputStream();
		// read bytes from bos
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		int length;
		while ((length = is.read(photo)) != -1) {
			// 從緩衝區讀取buffer裡面0~lenght -1 的位置
			bos.write(photo, 0, length);
		}
		// write bytes to bos
		photo = bos.toByteArray();

		UserRecipeBean uRecipe = new UserRecipeBean(userName, foodName, category, food1, food2, food3, food4, sauce1,
				sauce2, sauce3, photo);
		request.getSession(true).setAttribute("recipe", uRecipe);
		String base64 = Base64.getEncoder().encodeToString(uRecipe.getPhoto());
		request.getSession(true).setAttribute("base64Img", base64);
		request.getRequestDispatcher("./user/UserConfirm.jsp").forward(request, response);

	}

	// 確認之後送進資料庫(新增)
	public void gotoConfirm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		UserRecipeDAO uRecipeDAO = new UserRecipeDAO();
		uRecipeDAO.createConn();
		UserRecipeBean uR = (UserRecipeBean) request.getSession(true).getAttribute("recipe");
		if (uRecipeDAO.insertData(uR)) {
			request.getSession(true).invalidate();
			request.getRequestDispatcher("./user/UserSuccess.jsp").forward(request, response);
		}
		uRecipeDAO.closeConn();

	}

}
