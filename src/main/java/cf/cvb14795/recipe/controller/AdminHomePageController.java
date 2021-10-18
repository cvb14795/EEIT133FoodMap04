package cf.cvb14795.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cf.cvb14795.member.dao.IMemberService;

@Controller
@RequestMapping("/Recipe")
public class AdminHomePageController {

	IMemberService mService;

	public AdminHomePageController() {
	}

	@Autowired
	public AdminHomePageController(IMemberService mService) {
		this.mService = mService;
	}

	// ==============連結首頁==============
	@GetMapping("admin")
	public String adminRecipeListPage() {
		return "Recipe/AdminStartingPage";
	}

}
