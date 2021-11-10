package cf.cvb14795.Food.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cf.cvb14795.Food.model.AdminMapDataBean;
import cf.cvb14795.Food.model.UserMapDataBean;
import cf.cvb14795.Food.service.AdminMapDataService;
import cf.cvb14795.Food.service.UserMapDataService;

@Controller
@RequestMapping("/Food/user")
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes({ "user", "isAdmin" })
public class UserFoodController {

	private final static String PREFIX = "Food/";

	@Autowired
	ServletContext context;

	UserMapDataService uFoodService;
	AdminMapDataService aFoodService;

	@Autowired
	public UserFoodController(UserMapDataService uFoodService, AdminMapDataService aFoodService) {
		this.uFoodService = uFoodService;
		this.aFoodService = aFoodService;
	}

	@GetMapping("UserFood")
	public String userRecipe(@ModelAttribute("user") String user, @ModelAttribute("isAdmin") boolean isAdmin) {
		return PREFIX + "UserFood";
	}
	
	@GetMapping("info/{id}")
	public String userRecipe(@PathVariable("id") String id , Model model) {
		AdminMapDataBean mapData = aFoodService.updateId(Integer.valueOf(id));
		model.addAttribute("mapData", mapData);
		return PREFIX + "RestaurantInfo";
	}
	

	// ===============使用者查詢===============
	@PostMapping("UserViewAdminFood")
	public String UserViewAdminFood(Model model) {

		List<AdminMapDataBean> lists = aFoodService.selectAll();
		List<String> imgList = new ArrayList<String>();
		for (AdminMapDataBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getFilename());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);

		return PREFIX + "UserViewAdminFood";
	}
	//==================無視區====================//
	@PostMapping("UserViewMembersFood")
	public String UserViewMembersFood(Model model) {

		List<UserMapDataBean> lists = uFoodService.findMembersFood();
		List<String> imgList = new ArrayList<String>();
		for (UserMapDataBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getFilename());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);

		return PREFIX + "UserViewMembersFood";
	}
}
