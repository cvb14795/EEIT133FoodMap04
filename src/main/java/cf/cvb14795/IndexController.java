package cf.cvb14795;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.service.IMemberService;
import util.MemberStatus;

@Controller
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes({ "user", "isAdmin" })
public class IndexController {

	IMemberService mService;

	@Autowired
	public IndexController(IMemberService mService) {
		this.mService = mService;
	}

	public IndexController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GetMapping({ "/" })
	private String index(@ModelAttribute String user, HttpServletRequest request) {
		MemberStatus status = new MemberStatus(request.getCookies());
		if (!status.getLoginStatus()) {
			System.out.println("使用者未登入，將跳轉至根目錄");
			return "/HomePage/User/index";
		} else {
			return "redirect:/Home";
		}
	}

	@GetMapping({ "/Home" })
	private String home(Model model, @CookieValue("user") String user) {
		Optional<Member> member = mService.selectMemberByAccount(user); 	
		if (member.isPresent()) {
			boolean isAdmin = member.get().isAdmin(); 	
			String status = "使用者"; 
			String nextPage = "/HomePage/User/index"; 
			
			model.addAttribute("user", user);
			model.addAttribute("isAdmin", isAdmin);
			
			if (isAdmin) {
				status = "管理者";
				nextPage = "redirect:/HomePage/Admin/index";
			}
			
			System.out.println("用戶:' "+user+" '登入");
			System.out.println("登入身分: "+status);
			return nextPage;
		} else {
			System.out.println("經由該cookie找不到此帳號!將返回首頁...");
			return "redirect:/";
		}
			
		
	}
	
	@GetMapping("/admin")
	private String adminHome(Model model, @CookieValue("user") String user) {
		if (!mService.isAdmin(user)) {
			System.out.println("權限不足，無法登入管理員後台，將導回使用者首頁!");
			return "redirect:/Home";
		}
		List<Member> memberList = mService.selectAllMember();
		Map<String, String> imageMap = new HashMap<>();
		for (Member m : memberList) {
			imageMap.put(m.getAccount(), Base64.getEncoder().encodeToString(m.getImgBytes()));
		}			
		
		model.addAttribute("imageMap", imageMap);
		model.addAttribute("members", memberList);
		return "adminIndex";
	}
	
	@GetMapping("/aboutUs")
	private String aboutUs() {
		return "HomePage/User/aboutUs";
	}
	
	@ModelAttribute
	private void setUserAndAdmin(Model model) {
		model.addAttribute("user", "");
		model.addAttribute("isAdmin", false);
	}
}
