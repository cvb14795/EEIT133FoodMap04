package cf.cvb14795.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cf.cvb14795.member.service.IMemberService;

@Controller
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes({"user", "isAdmin"})
public class IndexController  {
	
	IMemberService mService;

	@Autowired
	public IndexController(IMemberService mService) {
		this.mService = mService;
	}
	
	public IndexController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@GetMapping({"/"})
	private String index() {
		System.out.println("使用者未登入，將跳轉至根目錄");
		return "index";
	}
	
	@GetMapping({"/Home"})
	private String home(
			Model model,
			@CookieValue("user") String user) {
		model.addAttribute("user", user);
		model.addAttribute("isAdmin", mService.isAdmin(user));
		System.out.println("使用者已登入，將跳轉至首頁");
		return "index";
	}
}
