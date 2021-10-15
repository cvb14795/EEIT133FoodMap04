package cf.cvb14795.recipe.controller;

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

import cf.cvb14795.recipe.model.UserRecipeBean;
import util.hibernate.HibernateUtil;

@WebServlet("/Recipe/UserViewMembersRecipe")
public class UserViewMembersRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserViewMembersRecipe() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("submit") != null) {
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session session = factory.getCurrentSession();
			
			Query<UserRecipeBean> query = session.createQuery("From UserRecipeBean",UserRecipeBean.class);
			List<UserRecipeBean> lists = query.list();
			
			ArrayList<String> imgList = new ArrayList<String>();
			for (UserRecipeBean urBean : lists) {
				String base64 = Base64.getEncoder().encodeToString(urBean.getPhoto());
				imgList.add(base64);
			}
			request.getSession(true).setAttribute("imgList", imgList);
			request.getSession(true).setAttribute("lists", lists);
			request.getRequestDispatcher("./UserViewMembersRecipe.jsp").forward(request, response);
			
		}
		
	}

}
