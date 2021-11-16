package cf.cvb14795.Coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cf.cvb14795.Coupon.model.bean.CouponBean;
import cf.cvb14795.Coupon.model.service.CouponService;

@Controller
@RequestMapping("/Coupon")
public class CouponQueryController {

	private final static String PREFIX = "Coupon/";
	@Autowired
	CouponService cService;

		
	// 查詢所有會員資料
	@GetMapping("/coupons")
	public String getCoupons(Model model) {
		List<CouponBean> beans = cService.getCoupons();		//將所有Coupon資訊取出
		model.addAttribute("beans",beans);
		// 若屬性物件為Bean型別的物件，則預設的識別字串 ==> Bean
		// 若屬性物件為List<Bean>型別的物件，則預設的識別字串 ==> BeanList
		return PREFIX + "showCoupons-final";
	}
	
	

}
