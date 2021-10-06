package recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import recipe.model.RecipeBean;
import util.hibernate.HibernateUtil;

@WebServlet("/Recipe/AdminViewRecipeServlet")
public class AdminViewRecipeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (request.getParameter("submit") != null) {
			response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();

			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.getCurrentSession();

			Query<RecipeBean> query = session.createQuery("from RecipeBean", RecipeBean.class);
			List<RecipeBean> lists = query.list();

			ArrayList<String> imgList = new ArrayList<String>();
			for (RecipeBean rBean : lists) {
				String base64 = Base64.getEncoder().encodeToString(rBean.getPhoto());
				imgList.add(base64);
			}
			request.getSession(true).setAttribute("imgList", imgList);
			request.getSession(true).setAttribute("lists", lists);
			request.getRequestDispatcher("./AdminViewRecipe.jsp").forward(request, response);
//		for (RecipeBean rBean : lists) {
//			out.write(rBean.getId() + "<br/>");
//			out.write(rBean.getName() + "<br/>");
//			out.write(rBean.getCategory() + "<br/>");
//			out.write(rBean.getFood1() + "<br/>");
//			out.write(rBean.getFood2() + "<br/>");
//			out.write(rBean.getFood3() + "<br/>");
//			out.write(rBean.getFood4() + "<br/>");
//			out.write(rBean.getSauce1() + "<br/>");
//			out.write(rBean.getSauce2() + "<br/>");
//			out.write(rBean.getSauce3() + "<br/>");
//			String base64 = Base64.getEncoder().encodeToString(rBean.getPhoto());
//			String src = "data:image/jpg;base64,"+base64;
//			String formatStr = String.format("<img src='%s' width='100' height='100'> <br/>" , src);
//			out.write(formatStr);
//		}
//		out.close();

		}else if(request.getParameter("confirm") != null) {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.getCurrentSession();

			Query<RecipeBean> query = session.createQuery("from RecipeBean", RecipeBean.class);
			List<RecipeBean> lists = query.list();

			ArrayList<String> imgList = new ArrayList<String>();
			for (RecipeBean rBean : lists) {
				String base64 = Base64.getEncoder().encodeToString(rBean.getPhoto());
				imgList.add(base64);
			}
			request.getSession(true).setAttribute("imgList", imgList);
			request.getSession(true).setAttribute("lists", lists);
			request.getRequestDispatcher("./UserViewAdminRecipe.jsp").forward(request, response);
		}
	}

}
