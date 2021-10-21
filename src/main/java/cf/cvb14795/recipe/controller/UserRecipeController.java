package cf.cvb14795.recipe.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import cf.cvb14795.recipe.model.AdminRecipeBean;
import cf.cvb14795.recipe.model.UserRecipeBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;
import cf.cvb14795.recipe.service.IUserRecipeService;

@Controller
@RequestMapping("/Recipe/user")
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes({"user", "isAdmin"})
public class UserRecipeController {

	private final static String PREFIX = "Recipe/";

	IUserRecipeService uRecipeService;
	IAdminRecipeService aRecipeService;
	
	@Autowired
	public UserRecipeController(IUserRecipeService uRecipeService, IAdminRecipeService aRecipeService) {
		this.uRecipeService = uRecipeService;
		this.aRecipeService = aRecipeService;
	}

	@GetMapping("UserRecipe")
	public String userRecipe(
			@ModelAttribute("user") String user,
			@ModelAttribute("isAdmin") boolean isAdmin) {
		return PREFIX + "UserRecipe";
	}

	@GetMapping("UserInsertRecipe")
	public String recipe(Model model) {
		UserRecipeBean userRecipe = new UserRecipeBean();
		model.addAttribute("userRecipe", userRecipe);
		return PREFIX + "UserInsertRecipe";
	}

	// ==============新增食譜 -> 寫進資料庫==============
	@PostMapping("UserInsertRecipe")
	public String userInsertForm(Model model, @CookieValue("user") String userName,
			@RequestParam(required = false, name = "foodName") String foodName,
			@RequestParam(required = false, name = "category") String category,
			@RequestParam(required = false, name = "food1") String food1,
			@RequestParam(required = false, name = "food2") String food2,
			@RequestParam(required = false, name = "food3") String food3,
			@RequestParam(required = false, name = "food4") String food4,
			@RequestParam(required = false, name = "sauce1") String sauce1,
			@RequestParam(required = false, name = "sauce2") String sauce2,
			@RequestParam(required = false, name = "sauce3") String sauce3,
			@RequestParam(required = false, name = "photo") MultipartFile photoPart) throws IOException {

		byte[] photo = new byte[1024];
		InputStream is = photoPart.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int length;
		while ((length = is.read(photo)) != -1) {
			baos.write(photo, 0, length);
		}

		photo = baos.toByteArray();
		UserRecipeBean userRecipe = new UserRecipeBean(userName, foodName, category, food1, food2, food3, food4, sauce1,
				sauce2, sauce3, photo);
		String base64String = Base64.getEncoder().encodeToString(photo);
		model.addAttribute("userRecipe", userRecipe);
		model.addAttribute("base64String", base64String);
		return PREFIX + "UserInsertConfirm";
	}

	@PostMapping("UserInsertToDB")
	public String userInsertToDB(@ModelAttribute("userRecipe") UserRecipeBean recipe) {
		uRecipeService.insert(recipe);

		return PREFIX + "UserStartingPage";
	}

	// ==============使用者查詢官方食譜==============
	@PostMapping("UserViewAdminRecipe")
	public String UserViewAdminRecipe(Model model) {

		List<AdminRecipeBean> lists = aRecipeService.selectAll();
		List<String> imgList = new ArrayList<String>();
		for (AdminRecipeBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getPhoto());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);

		return PREFIX + "UserViewAdminRecipe";
	}

	@PostMapping("UserViewMembersRecipe")
	public String UserViewMembersRecipe(Model model) {

		List<UserRecipeBean> lists = uRecipeService.findMembersRecipe();
		List<String> imgList = new ArrayList<String>();
		for (UserRecipeBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getPhoto());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);

		return PREFIX + "UserViewMembersRecipe";
	}
	
	@PostMapping("ViewYourRecipe")
	public String ViewYourRecipe(Model model) {
		
		
		
		
		
		return PREFIX + "ViewYourRecipe";
	}

}
