package cf.cvb14795.Coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cf.cvb14795.Coupon.model.bean.CouponBean;
import cf.cvb14795.Coupon.model.service.CouponService;

@Controller
@RequestMapping("/Coupon")
public class CouponController {

	private final static String PREFIX = "Coupon/";
	
	CouponService cService;
	
	
	@Autowired
	public CouponController(CouponService cService) {
		super();
		this.cService = cService;
	}

	// 送空白表單
	@GetMapping("/insertCoupon")
	public String sendEmptyForm() {		
		return PREFIX+"insert_page";		
	}
	
	@GetMapping("/showCoupons")
	public String showCoupons(Model model) {		
		List<CouponBean> beans = cService.getCoupons();
		model.addAttribute("beans",beans);
		return PREFIX+"showCoupons";
	}
	
	// 新增一筆資料
	@PostMapping("/coupons")
	public String save(@ModelAttribute CouponBean bean) {
		System.out.println("新增一筆折價券: " + bean);
		cService.addNewData(bean);					
		return "redirect:/"+PREFIX + "coupons";		
	}
	
	
	// 同 model.addAttribute(bean)
	@ModelAttribute
	public CouponBean  n1(
			@RequestParam(required = false) String id
			) {	
		CouponBean bean = new CouponBean();			
		return bean;
	}
	
	
	//修改一筆資料前，先傳該筆欲修改的資料去該頁面
	@GetMapping("/coupons/{id}")
	public String getCouponForm(@PathVariable String id, 
			Model model) {
		CouponBean bean = cService.getCouponById(id);
		model.addAttribute("bean", bean);  
		return PREFIX + "editCoupon";
	}

	
	@PutMapping("/coupons/{id}")
	public String update(@ModelAttribute("bean") CouponBean bean, 
			@PathVariable String id, Model model) {
		cService.updateCoupon(bean);
		System.out.println("修改資料: " + bean);
		return "redirect:/"+PREFIX + "coupons";
	}
	
	@DeleteMapping("/coupons/{id}")
	public String delete(Model model, 
			@PathVariable(required=false) String id) {
		if (id==null) {
			throw new RuntimeException("請求未提供客戶Id");
		} else {	
			CouponBean bean = cService.getCouponById(id);
			cService.deleteCouponById(bean);
			System.out.println("刪除資料: " + bean);
		}
		
		return "redirect:/"+PREFIX + "coupons";
		
	}
	

			
	 
}
