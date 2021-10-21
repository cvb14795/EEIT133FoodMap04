package cf.cvb14795.recipe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/Recipe")
@SessionAttributes({"user", "isAdmin"})
public class AdminHomePageController {

	public AdminHomePageController() {
	}

	// ==============連結首頁==============
	@GetMapping("/admin")
	public String adminRecipeListPage(
			@ModelAttribute("user") String userAccount,
			@ModelAttribute("isAdmin") boolean isAdmin,
			HttpServletRequest request, 
			Model model) {
		return "Recipe/AdminStartingPage";
	}

}
