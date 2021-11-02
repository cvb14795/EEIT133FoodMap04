package cf.cvb14795.Coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;
import cf.cvb14795.Coupon.model.service.IQuestionnaireService;

@Controller
@RequestMapping("/Coupon")
public class HomeController {
	private final static String PREFIX = "Coupon/";
	IQuestionnaireService qService;

	@Autowired
	public HomeController(IQuestionnaireService qService) {
		super();
		this.qService = qService;
	}
	
	@GetMapping({"/frontpage", "/", "default.htm"})
	public String frontpage(Model model) {
//		return PREFIX+"frontpage";
		return PREFIX+"frontpage-final";
	}
	
	
	@GetMapping("/questionnaire")
	public String Questionnaire(Model model) {
		model.addAttribute("title", "防疫問卷調查");
		return PREFIX+"questionnaire";
	}
	
	@GetMapping("/admin")
	public String AdminAction(Model model) {
		return PREFIX+"admin";
	}
	
	
	
	
	
	
	
	

}
