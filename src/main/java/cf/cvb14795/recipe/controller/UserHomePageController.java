package cf.cvb14795.recipe.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cf.cvb14795.member.service.IMemberService;
import cf.cvb14795.recipe.model.AdminRecipeBean;
import cf.cvb14795.recipe.model.MyFavoritesBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;
import cf.cvb14795.recipe.service.IMyFavoriteService;
import cf.cvb14795.recipe.service.IUserRecipeService;

@Controller
@RequestMapping("/Recipe")
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes({"user", "isAdmin"})
public class UserHomePageController {
	
	IUserRecipeService uRecipeService;
	IAdminRecipeService aRecipeService;
	IMyFavoriteService favoritesService;
	IMemberService memberService;
	
	@Autowired
	public UserHomePageController(IUserRecipeService uRecipeService, IAdminRecipeService aRecipeService,
			IMyFavoriteService favoritesService, IMemberService memberService) {
		this.uRecipeService = uRecipeService;
		this.aRecipeService = aRecipeService;
		this.favoritesService = favoritesService;
		this.memberService = memberService;
	}

	// ==============使用者查詢官方食譜==============
	@GetMapping("/user")
	public String userRecipeListPage(
			@ModelAttribute("user") String userAccount,
			@ModelAttribute("isAdmin") boolean isAdmin,
			Model model) {
		
		
		List<AdminRecipeBean> lists = aRecipeService.selectAll();
		List<String> imgList = new ArrayList<String>();
		List<MyFavoritesBean> favList = favoritesService.findAll();
		HashMap<Integer, MyFavoritesBean> favMap = new HashMap<>();
		for (int i = 0; i < lists.size(); i++) {
			String base64String = Base64.getEncoder().encodeToString(lists.get(i).getPhoto());
			imgList.add(base64String);
			if (i < favList.size() && favList.get(i).getMember().getAccount().equals(userAccount)) {
				favMap.put(favList.get(i).getaRecipeId().getId(), favList.get(i));				
			}
		}
//		Integer max = favMap.keySet().stream().max(Integer::compareTo).orElse(0);
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);
		model.addAttribute("favMap", favMap);

		return "Recipe/UserViewAdminRecipe2";
	}
	
	//預防沒進首頁直接連進來會找不到user的問題
	@ModelAttribute
	public void initAttr(@CookieValue("user") String user, Model model) {
		model.addAttribute("user", user);
	}

	//若找不到cookie(使用者沒登入)則提示登入
	@ExceptionHandler(MissingRequestCookieException.class)
	private String handleMissingCookieError(Model model) {
		return "redirect:/loginAlert";
	}
}
