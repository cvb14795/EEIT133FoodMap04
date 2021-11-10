package cf.cvb14795.Food.controller;

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

import cf.cvb14795.Food.model.AdminMapDataBean;
import cf.cvb14795.Food.service.AdminMapDataService;

@Controller
@RequestMapping("/Food/admin")
public class AdminFoodController {

	private final static String PREFIX = "Food/";
	AdminMapDataService aMapDataService;
	
	@Autowired
	public AdminFoodController(AdminMapDataService aMapDataService) {
		this.aMapDataService = aMapDataService;
	}
	
	//=======新增店家資料=======//
	@PostMapping("/AdminAddMap")
	@ResponseBody
	public ResponseEntity<String> AddMap(
			@RequestParam(name = "mapname") String mapname,
			@RequestParam(required = false, name = "mapku") String mapku,
			@RequestParam(required = false, name = "mapnb") String mapnb,
			@RequestParam(required = false, name = "mapxy") String mapxy,
			@RequestParam(required = false, name = "mapcheck") String mapcheck,
			@RequestParam(name = "category") String category,
			@RequestParam(required = false, name = "filename") MultipartFile filenamePart) throws IOException{
		
		
		byte[] filename = new byte[1024];
		String base64String="";
		if(filenamePart != null) {
			InputStream is = filenamePart.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int length;
			while ((length = is.read(filename)) != -1) {
				baos.write(filename, 0, length);
			}
			
			filename = baos.toByteArray();
			base64String = Base64.getEncoder().encodeToString(filename);
		}
		
		AdminMapDataBean Food = new AdminMapDataBean(
				mapname,mapku,mapnb,mapxy,mapcheck,category,filename,base64String);
		
		aMapDataService.insert(Food);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		
		return ResponseEntity.ok().headers(responseHeaders).body(new Gson().toJson(Food));
	}
	
	//=======查詢全部資料==========//
	
	@PostMapping("AdminViewFood")
	public String adminFoodList(Model model) throws IOException {
		List<AdminMapDataBean> lists = aMapDataService.selectAll();
		List<String> imgList = new ArrayList<String>();
		for (AdminMapDataBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getFilename());
			imgList.add(base64String);
		}
		model.addAttribute("lists",lists);
		model.addAttribute("imgList",imgList);
		return PREFIX + "AdminViewFood";
	}
	
	//========ByID updata=========//
	
	@GetMapping(path = "AdminViewFood/id={id}")
	public String AdminViewFood(@PathVariable("id") int id, Model model) {
		
		AdminMapDataBean updataFood = aMapDataService.updateId(id);
		
		String base64String = Base64.getEncoder().encodeToString(updataFood.getFilename());
		
		model.addAttribute("updataFood", updataFood);
		model.addAttribute("base64String", base64String);
		return PREFIX + "AdminEditFood";
	}
	
	//========updata==========//
	
	@PostMapping(path = "AdminViewFood/id={id}")
	public ResponseEntity<String> adminEditFoodAction(
			@PathVariable("id") int id,
			@ModelAttribute("Food") AdminMapDataBean adminMapData,
			@RequestParam("filename") String filename) throws IOException{
		
		adminMapData.setBase64String(filename);
		adminMapData.setFilename(Base64.getDecoder().decode(filename));
		aMapDataService.save(adminMapData);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		
		return ResponseEntity.ok().headers(responseHeaders).body(new Gson().toJson(adminMapData));
	}
			
	//=========ById delete==========//
	
	@GetMapping("AdminDeleteFood")
	public String AdminDeleteFood(@RequestParam("id") int id, Model model) {
		
		AdminMapDataBean deleteFood = aMapDataService.updateId(id);
		
		String base64String = Base64.getEncoder().encodeToString(deleteFood.getFilename());
		
		model.addAttribute("Food", deleteFood);
		model.addAttribute("base64String", base64String);
		return PREFIX + "AdminDeleteFood";
	}
	
	//=========delete===============//
	
	@PostMapping("AdminDeleteFoodAction")
	public String AdminDeleteFoodAction(@RequestParam("id") Integer id) {
		aMapDataService.deleteId(id);
		return PREFIX + "OK";
	}
	
}
			