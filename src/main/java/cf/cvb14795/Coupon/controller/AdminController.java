package cf.cvb14795.Coupon.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;
import cf.cvb14795.Coupon.model.service.IQuestionnaireService;
import cf.cvb14795.Coupon.model.util.CouponUsageUtil;
import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.dao.IMemberService;

@Controller
@RequestMapping("/Coupon")
public class AdminController {

	private final static String PREFIX = "Coupon/";
	IQuestionnaireService qService;
	IMemberService mService;

	@Autowired
	public AdminController(IQuestionnaireService qService, IMemberService mService) {
		this.qService = qService;
		this.mService = mService;
	}

	@PostMapping("/admincontroller")
	public String adminAction(
			HttpServletRequest request,
			@RequestParam("action") String action,
			Model model) {
		String label = null;
		if ("R".equals(action)){
			List<QuestionnaireBean> queriedData = qService.QueryDataByVaccine();
			model.addAttribute("vaccinelist", queriedData);		
			label = "findVaccine";
		} else if("U".equals(action)) {
			List<QuestionnaireBean> sendUsersCoupons = qService.SendUsersCoupons();
			model.addAttribute("sendUsersCoupons", sendUsersCoupons);
			
			/* =====由url的完整路徑拼出前綴路徑===== */
			URL u;
			try {
				u = new URL(request.getRequestURL().toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				System.out.println("請求URL時發生錯誤: "+e.getMessage());
				System.out.println("URL: "+request.getRequestURL().toString());
				System.out.println("*****將跳轉回首頁*****");
				return "redirect:/Home";
			}
			StringBuilder sb = new StringBuilder();
			// u.getProtocol(): "http" or "https"
			sb.append(u.getProtocol()+"://");
			// u.getAuthority(): localhost:8080
			sb.append(u.getAuthority());
			// request.getContextPath(): /FoodMap04
			sb.append(request.getContextPath());
			String baseUrl = sb.toString();
			
			/* =====寄信===== */
			CouponUsageUtil couponUsage = new CouponUsageUtil(baseUrl);
			System.out.println("================正在寄送Email...==================");
			for (QuestionnaireBean b : sendUsersCoupons) {
				System.out.println(b.getId());
				/* ================================== */
				/* 待修改成問卷表格與會員表格join的形式 */
				
				// 由問卷表格查詢該身分證對應的會員
				Member member = mService.selectMemberById(b.getId());
				// 產生優惠券代碼(預設為6位數)
				String couponCode = couponUsage.generateCouponCode(6);
				// 發送優惠券Email
				couponUsage.sendMail(member, couponCode);
				
				/* ================================== */
			}
			System.out.println("================寄送Email完成！==================");
			label = "sendCoupons";
		} else if("B".equals(action)){
			List<QuestionnaireBean> revokeUsersCoupons = qService.revokeUsersCoupons();
			model.addAttribute("revokeUsersCoupons", revokeUsersCoupons);
			label = "revoke";
		}	
		return PREFIX+label;
		
	}

}
