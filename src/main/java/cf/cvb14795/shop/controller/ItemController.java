package cf.cvb14795.shop.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import cf.cvb14795.photo.photoAttachment;
import cf.cvb14795.shop.model.Item;
import cf.cvb14795.shop.service.IOrderService;
import cf.cvb14795.shop.service.IShopService;

@Controller
@RequestMapping("/Shop/Item")
@SessionAttributes("user")
public class ItemController {
//	private static Logger log = LoggerFactory.getLogger(AdminItemController.class);
	private String prefix = "Shop/Item/";
//	private OrderStatusUtil orderStatus;
	
	IOrderService orderService;
	IShopService shopService;
	public ItemController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public ItemController(IOrderService orderService, IShopService shopService) {
		this.orderService = orderService;
		this.shopService = shopService;
	}
	
	/* 新增商品 */
	@GetMapping("/Add")
	public String addItem(Model model) {
		return prefix+"add";
	}
	

	/* 新增商品 */
	@PostMapping("/Add")
	public ResponseEntity<String> doAddItem(
			Model model,
			@RequestParam String name,
			@RequestParam String description,
			@RequestParam("price") String priceStr,
			@RequestParam MultipartFile photo) throws IOException {
		
		System.out.println("商品名："+name);
		System.out.println("價錢："+priceStr);
		Integer price;
		try {
			price = Integer.valueOf(priceStr);
		} catch (NumberFormatException e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		Item item = new Item(name,description,price,photo.getBytes());
		System.out.println(item.toString());
		
		shopService.addItem(item);
		return ResponseEntity.ok(new Gson().toJson(item));
	}
	
	
	@GetMapping("/photo/{id}")
    @ResponseBody
    public ResponseEntity<?> getPicture(HttpServletResponse resp, @PathVariable String id) {
        Optional<Item> itemOpt = shopService.findById(Integer.valueOf(id));
        if (itemOpt.isPresent()) {
        	Item item = itemOpt.get();
	        byte[] photo = item.getPhoto();
	        String fileName = "avatar_"+id+".jpg";
	        return photoAttachment.getPhoto(resp, photo, fileName);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	

}
