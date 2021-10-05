<<<<<<< HEAD:src/recipe/controller/AdminShowEditRecipe.java
package controller;
=======
package recipe.controller;
>>>>>>> 1aa4bb469a768fe0d0a7474056f00cb62caa2ea0:src/recipe/servelet/crud/Edit.java

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

<<<<<<< HEAD:src/recipe/controller/AdminShowEditRecipe.java
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.RecipeBean;
import util.HibernateUtil;

@WebServlet("/AdminShowEditRecipe")
=======
import recipe.model.RecipeBean;

@WebServlet("/Recipe/AdminShowEditRecipe")
>>>>>>> 1aa4bb469a768fe0d0a7474056f00cb62caa2ea0:src/recipe/servelet/crud/Edit.java
public class AdminShowEditRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminShowEditRecipe() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int editId = Integer.parseInt(request.getParameter("id"));
		int index=0;
		
		HttpSession session = request.getSession();
		ArrayList<RecipeBean> recipeList = (ArrayList<RecipeBean>) session.getAttribute("lists");

		for (RecipeBean recipeBean : recipeList) {
			System.out.println("index now is: "+ index);
			if (recipeBean.getId() == editId) {
				session.setAttribute("recipe", recipeBean);
				break;
			}
			index++;
		}
		System.out.println();
		
		ArrayList<String> imgList =  new ArrayList<String>();
		for (RecipeBean imgBean : recipeList) {
			 String base64 = Base64.getEncoder().encodeToString(imgBean.getPhoto());
			imgList.add(base64);
		}
		request.getSession(true).setAttribute("imgList", imgList);	
		
		String url = String.format("./AdminEditRecipe.jsp?id=%s&index=%d", editId, index);
		request.getRequestDispatcher(url).forward(request, response);
	
	}

}
