package cf.cvb14795.recipe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cf.cvb14795.recipe.service.IAdminRecipeService;

@Controller
@RequestMapping("/Recipe")
@SessionAttributes({ "user", "isAdmin" })
public class AdminHomePageController {

	@Autowired
	IAdminRecipeService aRecipeService;

	public AdminHomePageController() {
	}

	// ==============連結首頁==============
	@GetMapping("/admin")
	public String adminRecipeListPage(
			@ModelAttribute("user") String userAccount,
			@ModelAttribute("isAdmin") boolean isAdmin,
			HttpServletRequest request, 
			Model model) {
		
		String categoryArray[] = new String[] {"飯類", "麵類", "肉類", "湯類", "小菜類", "炸物類"};
		String cate = "";
		for (String c : categoryArray) {
			Long countByCategory = aRecipeService.countByCategory(c);
			
			cate += countByCategory + ",";
		}
		model.addAttribute("category", cate);
		
		return "Recipe/AdminStartingPage";
	}

}
