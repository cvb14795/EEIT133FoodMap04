package cf.cvb14795.recipe.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cf.cvb14795.recipe.model.AdminRecipeBean;
import cf.cvb14795.recipe.model.MyFavoritesBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;
import cf.cvb14795.recipe.service.IMyFavoriteService;
import cf.cvb14795.recipe.service.IUserRecipeService;

@Controller
@RequestMapping("/Recipe")
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes({"user", "isAdmin"})
public class UserHomePageController {
	
	IUserRecipeService uRecipeService;
	IAdminRecipeService aRecipeService;
	IMyFavoriteService favoritesService;
	
	@Autowired
	public UserHomePageController(IUserRecipeService uRecipeService, IAdminRecipeService aRecipeService,IMyFavoriteService favoritesService) {
		this.uRecipeService = uRecipeService;
		this.aRecipeService = aRecipeService;
		this.favoritesService = favoritesService;
	}

	// ==============使用者查詢官方食譜==============
	@GetMapping("/user")
	public String userRecipeListPage(
			@ModelAttribute("user") String userAccount,
			@ModelAttribute("isAdmin") boolean isAdmin,
			HttpServletRequest request, 
			Model model) {
		
		List<AdminRecipeBean> lists = aRecipeService.selectAll();
		List<String> imgList = new ArrayList<String>();
		List<MyFavoritesBean> favList = favoritesService.findAll();
		for (AdminRecipeBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getPhoto());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);
		model.addAttribute("favList", favList);

		return "Recipe/UserViewAdminRecipe2";
	}
	
}
