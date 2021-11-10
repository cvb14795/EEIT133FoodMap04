package cf.cvb14795.Food.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cf.cvb14795.Food.model.AdminMapDataBean;
import cf.cvb14795.Food.service.AdminMapDataService;
import cf.cvb14795.Food.service.UserMapDataService;
import cf.cvb14795.photo.photoAttachment;

@Controller
@RequestMapping("/Food/user")
@SessionAttributes({"user", "isAdmin"})
public class UserHomeController {
	
	UserMapDataService uMapDataService;
	AdminMapDataService aMapDataService;
	
	@Autowired
	public UserHomeController(UserMapDataService uMapDataService, AdminMapDataService aMapDataService) {
		this.uMapDataService = uMapDataService;
		this.aMapDataService = aMapDataService;
	}
	
	public UserHomeController() {
	}

	@GetMapping({"", "/"})
	public String userhome(
			@ModelAttribute("user") String userAccount,
			@ModelAttribute("isAdmin") boolean isAdmin,
			HttpServletRequest request,
			Model model) {
		
		List<AdminMapDataBean> lists = aMapDataService.selectAll();
//		List<String> imgList = new ArrayList<String>();
//		for (AdminMapDataBean bean : lists) {
//			String base64String = Base64.getEncoder().encodeToString(bean.getFilename());
//			imgList.add(base64String);
//		}
		model.addAttribute("lists", lists);
//		model.addAttribute("imgList", imgList);
		
		
		return "Food/UserStartingPage";
	}
	
	@GetMapping("photo/{id}")
	public ResponseEntity<Resource> getFoodPhoto (
			@PathVariable("id") String id,
			HttpServletResponse resp) {
		AdminMapDataBean mapData = aMapDataService.updateId(Integer.valueOf(id));
		return photoAttachment.getPhoto(resp, mapData.getFilename(), "food_"+id);
	}
}
