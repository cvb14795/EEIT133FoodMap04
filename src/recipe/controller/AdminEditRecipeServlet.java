package recipe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import recipe.model.RecipeBeanDao;
import util.hibernate.HibernateUtil;

@WebServlet("/Recipe/AdminEditRecipeServlet")
public class AdminEditRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditRecipeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		editAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		editAction(request, response);
	}

	private void editAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		RecipeBeanDao rDao = new RecipeBeanDao(session);
		rDao.update(Integer.parseInt(request.getParameter("id")), request.getParameter("name"),
				request.getParameter("category"), request.getParameter("food1"), request.getParameter("food2"),
				request.getParameter("food3"), request.getParameter("food4"), request.getParameter("sauce1"),
				request.getParameter("sauce2"), request.getParameter("sauce3"));
//		RecipeBean query = session.get(RecipeBean.class, Integer.parseInt(request.getParameter("id")));
//		query.setName(request.getParameter("name"));
//		query.setCategory(request.getParameter("category"));
//		query.setFood1(request.getParameter("food1"));
//		query.setFood2(request.getParameter("food2"));
//		query.setFood3(request.getParameter("food3"));
//		query.setFood4(request.getParameter("food4"));
//		query.setSauce1(request.getParameter("sauce1"));
//		query.setSauce2(request.getParameter("sauce2"));
//		query.setSauce3(request.getParameter("sauce3"));
//		session.update(query);
		System.out.println("已更新資料");
		request.getRequestDispatcher("./OK.jsp").forward(request, response);
	}

}
