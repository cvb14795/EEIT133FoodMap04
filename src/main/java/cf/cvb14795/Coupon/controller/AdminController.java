package cf.cvb14795.Coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;
import cf.cvb14795.Coupon.model.service.IQuestionnaireService;

@Controller
public class AdminController {
	
	IQuestionnaireService qService;
	
	
	@Autowired
	public AdminController(IQuestionnaireService qService) {
		this.qService = qService;
	}



	@PostMapping("/admincontroller")
	public String adminAction(
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
			label = "sendCoupons";
		} else if("B".equals(action)){
			List<QuestionnaireBean> revokeUsersCoupons = qService.revokeUsersCoupons();
			model.addAttribute("revokeUsersCoupons", revokeUsersCoupons);
			label = "revoke";
		}	
		return label;
		
	}
	
	
	
}
