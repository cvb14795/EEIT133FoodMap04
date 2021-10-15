package cf.cvb14795.recipe.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cf.cvb14795.recipe.model.RecipeBean;
import cf.cvb14795.recipe.model.RecipeBeanDao;
import util.hibernate.HibernateUtil;

@MultipartConfig
@WebServlet("/Recipe/AdminInsertRecipeServlet")
public class AdminInsertRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminInsertRecipeServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		if (request.getParameter("submit") != null) {
			gotoSubmit(request, response);
		} else if (request.getParameter("confirm") != null) {
			gotoConfirm(request, response);
		}
		
	}

	private void gotoSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("123");
		String name = request.getParameter("name").trim();
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
			bos.write(photo, 0, length);
		}
		// write bytes to bos
		photo = bos.toByteArray();

		RecipeBean recipe = new RecipeBean(name, category, food1, food2, food3, food4, sauce1, sauce2, sauce3, photo);
		request.getSession(true).setAttribute("recipe", recipe);
		String base64 = Base64.getEncoder().encodeToString(recipe.getPhoto());
		request.getSession(true).setAttribute("base64Img", base64);
		request.getRequestDispatcher("./AdminInsertConfirm.jsp").forward(request, response);
	}

	private void gotoConfirm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		RecipeBeanDao rDao = new RecipeBeanDao(session);
		RecipeBean r = (RecipeBean) request.getSession(true).getAttribute("recipe");
		rDao.insert(r);
		System.out.println("已新增資料");
		request.getRequestDispatcher("./OK.jsp").forward(request, response);
	}
}
