package recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import recipe.model.RecipeBean;


@WebServlet("/Recipe/AdminShowDeleteRecipe")
public class AdminShowDeleteRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminShowDeleteRecipe() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int deleteId = Integer.parseInt(request.getParameter("id"));
		int index=0;
		HttpSession session = request.getSession();
		ArrayList<RecipeBean> recipeList = (ArrayList<RecipeBean>) session.getAttribute("lists");
		
		for (RecipeBean recipeBean : recipeList) {
			if (recipeBean.getId() == deleteId) {
				session.setAttribute("recipe", recipeBean);
				break;
			}
			index++;
		}
		
		ArrayList<String> imgList =  new ArrayList<String>();
		for (RecipeBean imgBean : recipeList) {
			 String base64 = Base64.getEncoder().encodeToString(imgBean.getPhoto());
			imgList.add(base64);
		}
		request.getSession(true).setAttribute("imgList", imgList);
		
		String url= String.format("./AdminDeleteConfirm.jsp?id=%s&index=%d", deleteId, index);
		request.getRequestDispatcher(url).forward(request, response);
	}


}
