package cf.cvb14795.Food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Food")
public class BaseController {
	@RequestMapping("/Fooddex")
	public String home() {
		return "Food/Fooddex";
	}
}
