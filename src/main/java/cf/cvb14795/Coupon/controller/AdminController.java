package cf.cvb14795.Coupon.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import cf.cvb14795.Coupon.model.bean.CouponBean;
import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;
import cf.cvb14795.Coupon.model.service.CouponService;
import cf.cvb14795.Coupon.model.service.QuestionnaireService;
import cf.cvb14795.Coupon.model.util.CouponUsageUtil;
import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.service.MemberService;

@Controller
@RequestMapping("/Coupon")
public class AdminController {
	private final static String PREFIX = "Coupon/";

	QuestionnaireService qService;
	MemberService mService;
	CouponService cService;

	@Autowired
	public AdminController(QuestionnaireService qService, MemberService mService, CouponService cService) {
		this.qService = qService;
		this.mService = mService;
		this.cService = cService;
	}

	@PostMapping("/admincontroller")
	public String adminAction(HttpServletRequest request, @RequestParam("action") String action, Model model) {
		String label = null;

		if ("R".equals(action)) {
			List<QuestionnaireBean> beans = qService.QueryDataByVaccine("1");
			model.addAttribute("beans", beans);
			label = "show_page";

		} else if ("U".equals(action)) {
			List<QuestionnaireBean> beans = new ArrayList<>();
			List<QuestionnaireBean> queriedData = qService.QueryDataByVaccine("1");
			for (QuestionnaireBean bean : queriedData) {
				bean.setLabel("1");
				qService.SendUsersCoupons(bean);
				beans.add(bean);
			}

			model.addAttribute("beans", beans);

			/* =====由url的完整路徑拼出前綴路徑===== */
			URL u;
			try {
				u = new URL(request.getRequestURL().toString());
			} catch (MalformedURLException e) {

				System.out.println("請求URL時發生錯誤: " + e.getMessage());
				System.out.println("URL: " + request.getRequestURL().toString());
				System.out.println("*****將跳轉回首頁*****");
				return "redirect:/Home";
			}
			StringBuilder sb = new StringBuilder();
			// u.getProtocol(): "http" or "https"
			sb.append(u.getProtocol() + "://");
			// u.getAuthority(): localhost:8080
			sb.append(u.getAuthority());
			// request.getContextPath(): /FoodMap04
			sb.append(request.getContextPath());
			String baseUrl = sb.toString();

			/* =====寄信===== */
			CouponUsageUtil couponUsage = new CouponUsageUtil(cService, baseUrl);

			System.out.println("================正在寄送Email...==================");
			for (QuestionnaireBean b : beans) {
				System.out.println(b.getId());
				/* ================================== */
				/* 待修改成問卷表格與會員表格join的形式 */

				// 由問卷表格查詢該身分證對應的會員
				Optional<Member> member = mService.selectMemberByAccount(b.getAccount());   //填問券的帳號 -> email 寄
				// 產生優惠券代碼(預設為6位數)
//				String couponCode = couponUsage.generateCouponCode(6);
				String couponCode = "VCCP15";
				// 發送優惠券Email
				couponUsage.sendMail(member, couponCode);

				/* ================================== */
			}
			System.out.println("================寄送Email完成！==================");
			label = "show_page";

		} else if ("B".equals(action)) {
			List<QuestionnaireBean> beans = new ArrayList<>();
			List<QuestionnaireBean> queriedData = qService.QueryDataByVaccine("1");

			for (QuestionnaireBean bean : queriedData) {
				bean.setLabel("0");
				qService.revokeUsersCoupons(bean);
				beans.add(bean);
			}
			model.addAttribute("beans", beans);
			label = "show_page";

		} else if ("D".equals(action)) {
			qService.deleteDataById("H134029606");
			label = "deleted_page";
		}
		return PREFIX + label;

	}

}
