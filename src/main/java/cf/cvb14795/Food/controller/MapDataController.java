package cf.cvb14795.Food.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cf.cvb14795.Food.model.MapDataBean;
import cf.cvb14795.Food.service.CustomerService;
import cf.cvb14795.Food.validate.CustomerValidator;

@Controller
@RequestMapping("/Food/_mapdata")
public class MapDataController {
	
	static String url = "Food/_mapdata/";
	
	@Autowired
	ServletContext context;
	
	@Autowired
	CustomerService service;

	// 查詢單筆紀錄(送出含會員資料的表單以供修改)
		@GetMapping("/")
		public String index() {
			return url+"index";
		}
	
	// 查詢單筆紀錄(送出含會員資料的表單以供修改)
		@GetMapping("/customers/{id}")
		public String getCustomerForm(Model model, @PathVariable Integer id) {
			Optional<MapDataBean> op = service.getMapDataById(id);
			MapDataBean bean = op.get();
			model.addAttribute("mapDataBean", bean);
			return url+"EditCustomerForm";
		}
		
//	// 查詢紀錄(送出含會員資料的表單以供修改)
//		@GetMapping("/customers")
//		public String getAllCustomer(Model model) {
//			Iterator<MapDataBean> it = service.getAllMapData().iterator();
////			while (it.hasNext()) {
////				MapDataBean mapDataBean = (MapDataBean) it.next();
////				
////			}
//			model.addAttribute("AllMapDataBean", it);
//			return url+"EditCustomerAll";
//		}
		
		// 修改客戶資料
		@PutMapping("/customers/{id}")
		public String modifyCustomerData(@ModelAttribute("mapDataBean") MapDataBean bean, BindingResult bindingResult) {
			new CustomerValidator().validate(bean, bindingResult);
			System.out.println("修改會員(PUT, 11-05): " + bean);

			if (bindingResult.hasErrors()) {
				return url+"EditCustomerForm";
			}
			service.updateMapData(bean);
			return "redirect:/"+ url +"customers";
		}
	
		// 新增客戶資料
		@PostMapping("/customers")
		public String insertCustomerData(@ModelAttribute MapDataBean bean, BindingResult bindingResult) {
			new CustomerValidator().validate(bean, bindingResult);
			System.out.println("新增客戶: " + bean);

			if (bindingResult.hasErrors()) {
				System.out.println("======================");
				List<ObjectError> list = bindingResult.getAllErrors();
				for (ObjectError error : list) {
					System.out.println("有錯誤：" + error);
				}
				System.out.println("======================");
				return url+"InsertCustomerForm";
			}
			if (bean.getId() != null) {
				service.updateMapData(bean);
			}
			service.save(bean);
			return "redirect:/"+ url +"customers";
		}
		
		@ModelAttribute
		public MapDataBean editCustomerBean(@RequestParam(value = "customerId", required = false) Integer id) {
			MapDataBean cbean = new MapDataBean();
			if (id != null) {
				cbean = service.getMapDataById(id).get();
				System.out.println("在@ModelAttribute修飾的方法 getCustomerBean()中，讀到物件:" + cbean);
			} else {
				System.out.println("在@ModelAttribute修飾的方法 getCustomerBean()中，無法讀取物件:" + cbean);
			}
			return cbean;
		}
}
