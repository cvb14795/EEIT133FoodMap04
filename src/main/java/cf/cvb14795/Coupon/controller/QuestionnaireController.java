package cf.cvb14795.Coupon.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;
import cf.cvb14795.Coupon.model.service.QuestionnaireService;
import cf.cvb14795.Coupon.model.util.CouponUsageUtil;
import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.service.MemberService;

@Controller
@RequestMapping("/Coupon")
public class QuestionnaireController {
	QuestionnaireService qService;
	MemberService mService;
	URL u;

	@Autowired
	public QuestionnaireController(QuestionnaireService qService, MemberService mService) {
		this.qService = qService;
		this.mService = mService;
	}

	// 將問卷內容存入資料庫
	@PostMapping("/qcontroller")
	public String addNewData(@ModelAttribute QuestionnaireBean bean, @CookieValue("user") String account, Model model,
			HttpServletRequest request) {
		String send_page;
		bean.setLabel("0");
		bean.setAccount(account);
		System.out.println("cookie user: " + account);

		if (qService.checkAccount(bean.getAccount())) {
			qService.addNewData(bean);
			send_page = "Coupon/send_success";
			
			
			// 寄信
			/* 由url的完整路徑拼出前綴路徑 */

			try {
				u = new URL(request.getRequestURL().toString());
			} catch (MalformedURLException e) {

				System.out.println("請求URL時發生錯誤: " + e.getMessage());
				System.out.println("URL: " + request.getRequestURL().toString());
				System.out.println("*****將跳轉回首頁*****");
				return "redirect:/Home";
			}
			StringBuilder sb = new StringBuilder();
			sb.append(u.getProtocol() + "://"); // u.getProtocol(): "http" or "https"
			sb.append(u.getAuthority()); // u.getAuthority(): localhost:8080
			sb.append(request.getContextPath()); // request.getContextPath(): /FoodMap04
			String baseUrl = sb.toString();

			CouponUsageUtil couponUsage = new CouponUsageUtil(baseUrl);
			System.out.println("================正在寄送Email...==================");
			Optional<Member> m = mService.selectMemberByAccount(account);
			// 產生優惠券代碼(預設為6位數)
			String couponCode = couponUsage.generateCouponCode(6);
			// 發送優惠券Email
			couponUsage.sendMail(m, couponCode);

		} else {
			send_page = "Coupon/send_error";
		}

		

		
		

		System.out.println("================寄送Email完成！==================");

		return send_page;
	}

}
