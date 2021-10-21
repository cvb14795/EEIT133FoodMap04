package cf.cvb14795.recipe.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import cf.cvb14795.recipe.model.AdminRecipeBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;

@Controller
@RequestMapping("/Recipe/admin")
public class AdminRecipeControllerAjax {
	
	private final static String PREFIX = "Recipe/"; 

	IAdminRecipeService aRecipeService;

	@Autowired
	public AdminRecipeControllerAjax(IAdminRecipeService aRecipeService) {
		this.aRecipeService = aRecipeService;
	}
	
	// ============== 管理者查詢食譜 ==============
//	@SuppressWarnings("unchecked")
	@GetMapping("AdminViewRecipe")
	@ResponseBody
	public ResponseEntity<String> adminRecipeList(Model model) throws IOException {
		List<AdminRecipeBean> beanLists = aRecipeService.selectAll();
//		@SuppressWarnings("rawtypes")
//		List<List> resultList = new ArrayList<>();
		
//		for (int i = 0; i < beanLists.size(); i++) {
//			
//			String base64String = Base64.getEncoder().encodeToString(beanLists.get(i).getPhoto());
//			resultList.get(i).add(beanLists.get(i));
//			resultList.get(i).add(base64String);			
//		}
		
//		model.addAttribute("lists", lists);
//		model.addAttribute("imgList", imgList);
//		return PREFIX+"AdminViewRecipe";
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        
		return ResponseEntity.ok().headers(responseHeaders).body(new Gson().toJson(beanLists));
	}
	
	
	@GetMapping("AdminShowEditRecipe")
	public String adminShowEditRecipe(@RequestParam("id") String id) {
		//
		return"";
	}
	// ============== 從資料庫讀取ID資料 -> 更新 ==============
	@GetMapping(path = "AdminViewRecipe/{id}")
	public String AdminViewRecipe(@PathVariable("id") int id, Model model) {

		AdminRecipeBean updateRecipe = aRecipeService.getUpdateId(id);

		String base64String = Base64.getEncoder().encodeToString(updateRecipe.getPhoto());

		model.addAttribute("recipe", updateRecipe);
		model.addAttribute("base64String", base64String);

		return PREFIX+"AdminEditRecipe";
	}

	@PostMapping(path = "AdminViewRecipe/{id}")
	public String adminEditRecipeAction(
			@ModelAttribute("recipe") AdminRecipeBean adminRecipe,
			@RequestParam("photo") MultipartFile photo) throws IOException {
		
		adminRecipe.setPhoto(photo.getBytes());
		aRecipeService.saveRecipe(adminRecipe);

		return PREFIX+"AdminStartingPage";
	}

	// ============== 從資料庫讀取ID資料 -> 刪除 ==============
	@GetMapping("AdminShowDeleteRecipe")
	public String adminShowDeleteRecipe(@RequestParam("id") int id, Model model) {

		AdminRecipeBean updateRecipe = aRecipeService.getUpdateId(id);

		String base64String = Base64.getEncoder().encodeToString(updateRecipe.getPhoto());

		model.addAttribute("recipe", updateRecipe);
		model.addAttribute("base64String", base64String);
		return PREFIX+"AdminDeleteConfirm";
	}

	@PostMapping("AdminDeleteRecipeAction")
	public String adminDeleteRecipeAction(@RequestParam("id") Integer id) {
		aRecipeService.deleteById(id);
		return PREFIX+"AdminStartingPage";
	}

	// ============== 新增食譜 -> 寫進資料庫 ==============
//	@PostMapping("AdminInsertRecipe")
//	@ResponseBody
//	public String adminInsertRecipe(
//			Model model,
//			@RequestParam(required = false, name = "name") String name,
//			@RequestParam(required = false, name = "category") String category,
//			@RequestParam(required = false, name = "food1") String food1,
//			@RequestParam(required = false, name = "food2") String food2,
//			@RequestParam(required = false, name = "food3") String food3,
//			@RequestParam(required = false, name = "food4") String food4,
//			@RequestParam(required = false, name = "sauce1") String sauce1,
//			@RequestParam(required = false, name = "sauce2") String sauce2,
//			@RequestParam(required = false, name = "sauce3") String sauce3) throws IOException {
//
//		AdminRecipeBean recipe = new AdminRecipeBean(
//				name, category, 
//				food1, food2, food3, food4, 
//				sauce1, sauce2, sauce3);
//		model.addAttribute("recipe", recipe);
//		return PREFIX+"AdminInsertConfirm";
//	}

	@PostMapping("adminInsertToDB")
	@ResponseBody
	public ResponseEntity<String> adminInsertToDB(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "category") String category,
			@RequestParam(required = false, name = "food1") String food1,
			@RequestParam(required = false, name = "food2") String food2,
			@RequestParam(required = false, name = "food3") String food3,
			@RequestParam(required = false, name = "food4") String food4,
			@RequestParam(required = false, name = "sauce1") String sauce1,
			@RequestParam(required = false, name = "sauce2") String sauce2,
			@RequestParam(required = false, name = "sauce3") String sauce3,
			@RequestParam(required = false, name = "photo") MultipartFile photoPart
			) throws IOException {
//		System.out.println("==========recipe============");
//		System.out.println("photo:" +recipe.getPhoto());
//		System.out.println("name:" +recipe.getName());
//		System.out.println("category:" +recipe.getCategory());
		
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
		
		AdminRecipeBean recipe = new AdminRecipeBean(
				name, category, 
				food1, food2, food3, food4, 
				sauce1, sauce2, sauce3,
				photo, base64String);
		aRecipeService.insert(recipe);

//		return "Insert OK!!";
		
		HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        
        return ResponseEntity.ok().headers(responseHeaders).body(new Gson().toJson(recipe));
	}

}
