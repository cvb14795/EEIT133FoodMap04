package cf.cvb14795;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

import cf.cvb14795.Comment.service.CommentService;
import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.service.IMemberService;
import cf.cvb14795.shop.service.IOrderService;
import cf.cvb14795.shop.service.IShopService;
import util.MemberStatus;

@Controller
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes({ "user", "isAdmin" })
public class IndexController {

	IMemberService mService;
	IShopService shopService;
	IOrderService orderService;
	CommentService commentService;

	@Autowired
	public IndexController(
			IMemberService mService,
			IShopService shopService,
			IOrderService orderService,
			CommentService commentService) {
		this.mService = mService;
		this.shopService = shopService;
		this.orderService = orderService;
		this.commentService = commentService;
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
				nextPage = "redirect:/admin"; 
			}
			
			System.out.println("用戶: '"+user+"' 登入");
			System.out.println("登入身分: "+status);
			return nextPage;
		} else {
			System.out.println("經由該cookie找不到此帳號!將返回首頁...");
			return "redirect:/";
		}
	}
	
	@GetMapping("/admin")
	private String adminHome(Model model, 
			@CookieValue("user") String user,
			@ModelAttribute("imageMap") Map<String, String> imageMap) {
		if (!mService.isAdmin(user)) {
			String msg = "權限不足，無法登入管理員後台，將導回使用者首頁!";
			System.out.println(msg);
			return "redirect:/loginAlert?msg="+msg;
		}
		List<Member> memberList = mService.selectAllMember();
		
		for (Member m : memberList) {
			imageMap.put(m.getAccount(), Base64.getEncoder().encodeToString(m.getImgBytes()));
		}			
		
		model.addAttribute("members", memberList);
		return "HomePage/Admin/index";
	}
	
	@GetMapping(path = "/statCount", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private String statCount(@ModelAttribute("statMap") Map<String, Long> statMap) {
		statMap.put("商城商品銷售額", orderService.calcOrderTotalPrice());
		statMap.put("商城商品總數",  shopService.findItemCount());
		statMap.put("評論區留言總數",  commentService.findCommentCount());
		statMap.put("會員註冊人數",  mService.findMemberCount());
		return new Gson().toJson(statMap);
	}
	
	
	@GetMapping("/aboutUs")
	private String aboutUs() {
		return "HomePage/User/aboutUs";
	}
	
	@GetMapping("/loginAlert")
	private String alertToLoginPage(@RequestParam(required = false, name = "msg") String msg) {
		return "HomePage/loginAlert";
	}
	
	@ModelAttribute
	private void setUserAndAdmin(Model model, HttpServletRequest request) {
		Map<String, String> imageMap = new HashMap<>();
		Map<String, Long> statMap = new HashMap<>();
		
		statMap.put("商城商品銷售額", orderService.calcOrderTotalPrice());
		statMap.put("商城商品總數",  shopService.findItemCount());
		statMap.put("評論區留言總數",  commentService.findCommentCount());
		statMap.put("會員註冊人數",  mService.findMemberCount());
		
		model.addAttribute("user", new MemberStatus(request.getCookies()).getCurrentUserAccount());
		model.addAttribute("isAdmin", false);
		model.addAttribute("imageMap", imageMap);
		model.addAttribute("statMap", statMap);
	}
	
	@ExceptionHandler(MissingRequestCookieException.class)
	private String handleMissingCookieError(Model model) {
		return "redirect:/loginAlert";
	}
}
