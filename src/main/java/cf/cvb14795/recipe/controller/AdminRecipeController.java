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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cf.cvb14795.recipe.model.AdminRecipeBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;

@Controller
@RequestMapping("/Recipe/admin")
public class AdminRecipeController {
	
	private final static String PREFIX = "Recipe/"; 

	IAdminRecipeService aRecipeService;

	@Autowired
	public AdminRecipeController(IAdminRecipeService aRecipeService) {
		this.aRecipeService = aRecipeService;
	}

	// ==============管理者查詢食譜==============
	@PostMapping("AdminViewRecipe")
	public String adminRecipeList(Model model) throws IOException {
		List<AdminRecipeBean> lists = aRecipeService.selectAll();
		List<String> imgList = new ArrayList<String>();
		for (AdminRecipeBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getPhoto());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);
		return PREFIX+"AdminViewRecipe";
	}

	// ==============從資料庫讀取ID資料 -> 更新==============
	@GetMapping("AdminShowEditRecipe")
	public String adminShowEditRecipe(@RequestParam("id") int id, Model model) {

		AdminRecipeBean updateRecipe = aRecipeService.getUpdateId(id);

		String base64String = Base64.getEncoder().encodeToString(updateRecipe.getPhoto());

		model.addAttribute("recipe", updateRecipe);
		model.addAttribute("base64String", base64String);

		return PREFIX+"AdminEditRecipe";
	}

	@PostMapping("adminEditRecipeAction")
	public String adminEditRecipeAction(@ModelAttribute("recipe") AdminRecipeBean adminRecipe,
			@RequestParam("photo") String photo) {
		adminRecipe.setPhoto(Base64.getDecoder().decode(photo));

		aRecipeService.saveRecipe(adminRecipe);

		return "AdminStartingPage";
	}

	// ==============從資料庫讀取ID資料 -> 刪除==============
	@GetMapping("AdminShowDeleteRecipe")
	public String adminShowDeleteRecipe(@RequestParam("id") int id, Model model) {

		AdminRecipeBean updateRecipe = aRecipeService.getUpdateId(id);

		String base64String = Base64.getEncoder().encodeToString(updateRecipe.getPhoto());

		model.addAttribute("recipe", updateRecipe);
		model.addAttribute("base64String", base64String);
		return "AdminDeleteConfirm";
	}

	@PostMapping("AdminDeleteRecipeAction")
	public String adminDeleteRecipeAction(@RequestParam("id") Integer id, Model model) {
		aRecipeService.deleteById(id);
		return "AdminStartingPage";
	}

	// ==============新增食譜 -> 寫進資料庫==============
	@PostMapping("AdminInsertRecipe")
	public String adminInsertRecipe(
			Model model,
			@RequestParam(required = false, name = "name") String name,
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
		AdminRecipeBean recipe = new AdminRecipeBean(
				name, category, 
				food1, food2, food3, food4, 
				sauce1, sauce2, sauce3, photo);
		String base64String = Base64.getEncoder().encodeToString(photo);
		model.addAttribute("recipe", recipe);
		model.addAttribute("base64String", base64String);
		return "AdminInsertConfirm";
	}

	@PostMapping("adminInsertToDB")
	public String adminInsertToDB(
			@ModelAttribute("recipe") AdminRecipeBean recipe) {
//		System.out.println("==========recipe============");
//		System.out.println("photo:" +recipe.getPhoto());
//		System.out.println("name:" +recipe.getName());
//		System.out.println("category:" +recipe.getCategory());
		aRecipeService.insert(recipe);

		return "AdminStartingPage";
	}

}
