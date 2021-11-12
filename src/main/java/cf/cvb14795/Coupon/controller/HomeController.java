package cf.cvb14795.Coupon.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cf.cvb14795.Coupon.model.service.IQuestionnaireService;
import cf.cvb14795.member.service.IMemberService;

@Controller
@RequestMapping("/Coupon")
@SessionAttributes({"user", "isAdmin"})
public class HomeController {
	private final static String PREFIX = "Coupon/";
	IQuestionnaireService qService;
	IMemberService mService;
	HttpServletRequest request;


	
	@Autowired
	public HomeController(IQuestionnaireService qService, IMemberService mService, HttpServletRequest request) {
		this.qService = qService;
		this.mService = mService;
		this.request = request;
	}


	@GetMapping({"/frontpage", "/", "default.htm"})
	public String frontpage(Model model, 
			HttpServletResponse response) {	
		
		try {
			
			HttpSession session = request.getSession();
			String user = (String) session.getAttribute("user");			
			
			System.out.println("user:"+user);
			Cookie cookie = null;
			if (mService.isAdmin(user)) {
				cookie = new Cookie("identity", "admin");
			} else {
				cookie = new Cookie("identity", "guest");
			}
			
			response.addCookie(cookie);
		} catch (Exception e) { 
			return PREFIX+"frontpage-final";
		}
			
		return PREFIX+"frontpage-final";

	
	}
	
	
	@GetMapping("/questionnaire")
	public String Questionnaire(Model model) {
		model.addAttribute("title", "防疫問卷調查");
		return PREFIX+"questionnaire";
	}
	
	@GetMapping("/admin")
	public String AdminAction(Model model, 
			IMemberService mService, HttpServletRequest request) {
		String page = "notAdmin";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie:cookies) {
			if ("admin".equals(cookie.getValue())) {
				page = "admin";
			}
		}
				
		return PREFIX+page;
	}
	
	@GetMapping("/adminforCoupon")
	public String CouponAction(Model model) {
		return PREFIX+"adminforCoupon";
	}
	
	
	
	
	
	

}
