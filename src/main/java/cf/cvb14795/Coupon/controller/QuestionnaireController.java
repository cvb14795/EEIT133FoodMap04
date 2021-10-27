package cf.cvb14795.Coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;
import cf.cvb14795.Coupon.model.service.IQuestionnaireService;

@Controller
@RequestMapping("/Coupon")
public class QuestionnaireController {
	IQuestionnaireService qService;
	
	
	@Autowired
	public QuestionnaireController(IQuestionnaireService qService) {
		this.qService = qService;
	}



	@PostMapping("/controller")
	public String addNewData(@RequestParam("name") String name, @RequestParam("gender") String gender,
			@RequestParam("id") String id, @RequestParam("birth") String birth, @RequestParam("phone") String phone,
			@RequestParam("abroad") String abroad, @RequestParam("moving") String moving,
			@RequestParam("family") String family, @RequestParam("fever") String fever,
			@RequestParam("vaccine") String vaccine, @RequestParam(name="label", required=false) String label,
			@CookieValue("user") String account, Model model) {
		String send_page;
		label = "0";
		System.out.println("cookie user: " + account);
		System.out.println("labe****/*/*/*/*/*/*/*/*/*/l:" + label);
		QuestionnaireBean qbean = new QuestionnaireBean(id, name, gender, birth, phone, abroad, 
				moving, family, vaccine, fever, label, account);
		if (qService.checkAccount(qbean.getAccount())) {
			qService.addNewData(qbean);
			send_page = "/Coupon/send_success";
		} else {
			send_page = "/Coupon/send_error";
		}

		return send_page;
	}

}
