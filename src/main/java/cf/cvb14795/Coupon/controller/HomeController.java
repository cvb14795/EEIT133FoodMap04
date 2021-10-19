package cf.cvb14795.Coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cf.cvb14795.Coupon.model.service.IQuestionnaireService;

@Controller
public class HomeController {
	
	IQuestionnaireService qService;

	@Autowired
	public HomeController(IQuestionnaireService qService) {
		super();
		this.qService = qService;
	}
	
	@GetMapping({"/frontpage", "/", "default.htm"})
	public String  frontpage() {
		return "frontpage";
		
	}
	
	
	@GetMapping("/questionnaire")
	public String Questionnaire(Model model) {
		model.addAttribute("title", "防疫問卷調查");
		
		return "questionnaire";
	}
	
	@GetMapping("/admin")
	public String AdminAction(Model model) {
		return "admin";
	}
	
	
	
	
	
	
	
	

}
