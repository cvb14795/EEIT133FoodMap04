package cf.cvb14795.member.controller;

import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.dao.IMemberService;
import util.MemberStatus;

@Controller
@RequestMapping("/Member")
public class Revise {
	private String prefix = "Member/";
	private Cookie[] cookies;
	private String userAccount="";
	
	IMemberService mService;

	public Revise() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public Revise(IMemberService mService) {
		this.mService = mService;
	}

	@GetMapping("Revise")
	private String revisePage(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response){
		response.setContentType("text/html; charset=UTF-8");
//		HttpSession httpSession = request.getSession();
		cookies = request.getCookies();

		MemberStatus status = new MemberStatus(cookies);
		userAccount = status.getCurrentUserAccount();
		if (status.getLoginStatus(userAccount)) {
			Member m = mService.selectMemberByAccount(userAccount);
			model.addAttribute("member", m);
			
			String base64String = Base64.getEncoder().encodeToString(m.getImgBytes());
			model.addAttribute("base64String", base64String);
			return prefix+"memberRevise";
		} else {
//			PrintWriter out = response.getWriter();
			System.out.println("尚未登入!");

			// 設定幾秒後跳轉頁面
//			Integer refreshCountDown = 5;
			// 要跳轉的網址位置
//			String redirectUrl = "../Home";
			// 設定在指定時間後自動跳轉至某頁面
//			response.addHeader("Refresh", "5;URL=" + redirectUrl);
//			out.write(String.format("您尚未登入，%d秒後將跳轉到首頁！", refreshCountDown));
			
			// response.sendRedirect("../Home");
			return "redirect:/Home";
		}
	}

}
