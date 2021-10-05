<<<<<<< HEAD
package controller;
=======
package recipe.controller;
>>>>>>> 1aa4bb469a768fe0d0a7474056f00cb62caa2ea0

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

<<<<<<< HEAD
import model.RecipeBeanDao;
import util.HibernateUtil;

@WebServlet("/AdminDeleteRecipeServlet")
=======
import recipe.model.RecipeBeanDao;
import util.hibernate.HibernateUtil;

@WebServlet("/Recipe/AdminDeleteRecipeServlet")
>>>>>>> 1aa4bb469a768fe0d0a7474056f00cb62caa2ea0
public class AdminDeleteRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDeleteRecipeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deleteAction(request,response);
	}

	private void deleteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		RecipeBeanDao rDao = new RecipeBeanDao(session);
		rDao.deleteById(Integer.parseInt(request.getParameter("id")));
//		RecipeBean query = session.get(RecipeBean.class, Integer.parseInt(request.getParameter("id")));
//		session.delete(query);
		System.out.println("已刪除資料");
		request.getRequestDispatcher("./OK.jsp").forward(request, response);
	}

}
