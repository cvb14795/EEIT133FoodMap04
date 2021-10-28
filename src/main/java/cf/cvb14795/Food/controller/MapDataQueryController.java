package cf.cvb14795.Food.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cf.cvb14795.Food.model.MapDataBean;
import cf.cvb14795.Food.service.CustomerService;

@Controller
@RequestMapping("/Food/_mapdata")
public class MapDataQueryController {
	
	static String url = "Food/_mapdata/";
	
	@Autowired
	ServletContext context;

	@Autowired
	CustomerService service;
	
	// 查詢所有會員資料
		@GetMapping("/customers")
		public String getCustomers(Model model) {
			List<MapDataBean> beans = service.getMapData();
			model.addAttribute(beans);      
			// 若屬性物件為CustomerBean型別的物件，則預設的識別字串 ==> customerBean
			// 若屬性物件為List<CustomerBean>型別的物件，則預設的識別字串 ==> customerBeanList
			return url+"ShowCustomers";
		}
		

	// 本方法送出新增Customer資料的空白表單
		@GetMapping("/insertCustomer")
		public String showCustomerForm(Model model) {
			MapDataBean bean = new MapDataBean();
			model.addAttribute(bean);
			return url+"InsertCustomerForm";
		}
		
		@DeleteMapping("/customers/{id}")
		public String deleteCustomerData(@PathVariable Integer id) {
			service.deleteMapDataById(id);	
			return "redirect:/"+ url +"customers";
		}
		
		@RequestMapping("/index")
		public String home() {
			return url+"index";
		}

}
