package cf.cvb14795.Food.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/Food")
@SessionAttributes({"user", "isAdmin"})
public class AdminHomeController {

	public AdminHomeController() {
		
	}
	
	@RequestMapping("/admin")
	public String home(
			@ModelAttribute("user") String userAccount,
			@ModelAttribute("isAdmin") boolean isAdmin,
			HttpServletRequest request, 
			Model model) {
		return "Food/AdminStartingPage";
	}
	
}
