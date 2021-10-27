package cf.cvb14795.recipe.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import cf.cvb14795.recipe.model.AdminRecipeBean;
import cf.cvb14795.recipe.model.UserRecipeBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;
import cf.cvb14795.recipe.service.IUserRecipeService;

@Controller
@RequestMapping("/Recipe/user")
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes({"user", "isAdmin"})
public class UserRecipeControllerAjax {

	private final static String PREFIX = "Recipe/";
	
	@Autowired
	ServletContext context;

	IUserRecipeService uRecipeService;
	IAdminRecipeService aRecipeService;
	
	@Autowired
	public UserRecipeControllerAjax(IUserRecipeService uRecipeService, IAdminRecipeService aRecipeService) {
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

	// ============== 新增食譜 -> 寫進資料庫 ==============
	@PostMapping("UserInsertToDB")
	@ResponseBody
	public ResponseEntity<String> userInsertForm(
			@CookieValue("user") String userName,
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
		String base64String="";
		if (photoPart != null) {
			InputStream is = photoPart.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int length;
			while ((length = is.read(photo)) != -1) {
				baos.write(photo, 0, length);
			}
			
			photo = baos.toByteArray();
			base64String = Base64.getEncoder().encodeToString(photo);
		}
		UserRecipeBean userRecipe = new UserRecipeBean(
				userName, foodName, category, 
				food1, food2, food3, food4, 
				sauce1, sauce2, sauce3, photo, base64String);
		
		uRecipeService.insert(userRecipe);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		
		return ResponseEntity.ok().headers(responseHeaders).body(new Gson().toJson(userRecipe));
	}

	// ============== 使用者查詢官方食譜 ==============
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
	public String ViewYourRecipe(
			@CookieValue("user") String userName,
			Model model) {
		
		List<UserRecipeBean> lists = uRecipeService.findByName(userName);
		List<String> imgList = new ArrayList<String>();
		for (UserRecipeBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getPhoto());
			imgList.add(base64String);
		}
		
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);
		
		return PREFIX + "ViewYourRecipe";
	}
	
	// ============== 根據ID讀取資料庫資料:更新 ==============
	@GetMapping(path = "ViewYourRecipe/id={id}")
	public String ViewYourRecipe(
			@PathVariable("id") int id, 
			Model model
			) {
		UserRecipeBean updateRecipe = uRecipeService.getUpdateId(id);
		String base64String = Base64.getEncoder().encodeToString(updateRecipe.getPhoto());

		model.addAttribute("updateRecipe", updateRecipe);
		model.addAttribute("base64String", base64String);
		
		return PREFIX + "UserShowEditRecipe";
	}
	
	// ============== 資料更新 ==============
		@PostMapping(path = "ViewYourRecipe/id={id}")
		public ResponseEntity<String> UserEditRecipeAction(
				@PathVariable("id") int id,
				@ModelAttribute("recipe") UserRecipeBean userRecipe,
				@RequestParam("photo") String photo
				) throws IOException {
			
			userRecipe.setBase64String(photo);
			userRecipe.setPhoto(Base64.getDecoder().decode(photo));
			uRecipeService.saveRecipe(userRecipe);
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			responseHeaders.add("Content-Type", "application/json; charset=utf-8");
			
			return ResponseEntity.ok().headers(responseHeaders).body(new Gson().toJson(userRecipe));
			
		}
		
		// ============== 根據ID讀取資料庫資料:刪除 ==============
		@GetMapping("UserShowDeleteRecipe")
		public String UserShowDeleteRecipe(
				@RequestParam("id") int id, 
				Model model
				) {
			UserRecipeBean deleteRecipe = uRecipeService.getUpdateId(id);

			String base64String = Base64.getEncoder().encodeToString(deleteRecipe.getPhoto());

			model.addAttribute("recipe", deleteRecipe);
			model.addAttribute("base64String", base64String);
			
			return PREFIX+"UserDeleteConfirm";
		}
		
		// ============== 資料刪除 ==============
		@PostMapping("UserDeleteRecipeAction")
		public String UserDeleteRecipeAction(@RequestParam("id") Integer id) {
			uRecipeService.deleteById(id);
			return  PREFIX + "UserSuccess";
		}
}
