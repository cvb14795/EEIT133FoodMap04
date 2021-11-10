package cf.cvb14795.recipe.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
	
	// ============== 新增食譜 -> 寫進資料庫 ==============
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
		
		AdminRecipeBean recipe = new AdminRecipeBean(
				name, category, 
				food1, food2, food3, food4, 
				sauce1, sauce2, sauce3,
				photo, base64String);
		
		aRecipeService.insert(recipe);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		
		return ResponseEntity.ok().headers(responseHeaders).body(new Gson().toJson(recipe));
	}
	
	// ============== 管理者查詢食譜 ==============
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
	return PREFIX + "AdminViewRecipe";
	}
	
	// ============== 根據ID讀取資料庫資料:更新 ==============
	@GetMapping(path = "AdminViewRecipe/id={id}")
	public String AdminViewRecipe(@PathVariable("id") int id, Model model) {

		AdminRecipeBean updateRecipe = aRecipeService.getUpdateId(id);

		String base64String = Base64.getEncoder().encodeToString(updateRecipe.getPhoto());

		model.addAttribute("updateRecipe", updateRecipe);
		model.addAttribute("base64String", base64String);
		
		return PREFIX + "AdminEditRecipe";
	}
	
	// ============== 資料更新 ==============
	@PostMapping(path = "AdminViewRecipe/id={id}")
	public ResponseEntity<String> adminEditRecipeAction(
			@PathVariable("id") int id,
			@ModelAttribute("recipe") AdminRecipeBean adminRecipe,
			@RequestParam("photo") String photo) throws IOException {
		
		adminRecipe.setBase64String(photo);
		adminRecipe.setPhoto(Base64.getDecoder().decode(photo));
		aRecipeService.saveRecipe(adminRecipe);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		
		return ResponseEntity.ok().headers(responseHeaders).body(new Gson().toJson(adminRecipe));
		
	}

	// ============== 根據ID讀取資料庫資料:刪除 ==============
	@GetMapping("AdminShowDeleteRecipe")
	public String adminShowDeleteRecipe(@RequestParam("id") int id, Model model) {

		AdminRecipeBean deleteRecipe = aRecipeService.getUpdateId(id);

		String base64String = Base64.getEncoder().encodeToString(deleteRecipe.getPhoto());

		model.addAttribute("recipe", deleteRecipe);
		model.addAttribute("base64String", base64String);
		return PREFIX+"AdminDeleteConfirm";
	}

	// ============== 資料刪除 ==============
	@PostMapping("AdminDeleteRecipeAction")
	public String adminDeleteRecipeAction(@RequestParam("id") Integer id) {
		aRecipeService.deleteById(id);
		return  PREFIX + "OK";
//		return "redirect:/Recipe/admin";
	}

}
