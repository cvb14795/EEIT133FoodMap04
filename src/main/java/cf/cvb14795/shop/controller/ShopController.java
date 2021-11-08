package cf.cvb14795.shop.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cf.cvb14795.shop.model.Item;
import cf.cvb14795.shop.model.Order;
import cf.cvb14795.shop.model.OrderItem;
import cf.cvb14795.shop.service.IOrderService;
import cf.cvb14795.shop.service.IShopService;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import util.ecpay.OrderStatusUtil;

@Controller
@RequestMapping("/Shop")
public class ShopController {
//	private static Logger log = LoggerFactory.getLogger(ShopController.class);
	private String prefix = "Shop/";
	private OrderStatusUtil orderStatus;
	Map<Integer, OrderItem> cart = new LinkedHashMap<>();
	
	IShopService shopService;
	IOrderService orderService;
	
	public ShopController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public ShopController(IShopService shopService, IOrderService orderService) {
		this.shopService = shopService;
		this.orderService = orderService;
	}
	
	/* 商城首頁 */
	@GetMapping("/")
	public String index(Model model) {
		List<Item> items = shopService.findAll();
		model.addAttribute("items", items);
		model.addAttribute("cart", cart);
		return prefix+"shop";
	}
	
	/* 購物車總覽 */
	@GetMapping("/Cart")
	public String cart(Model model) {
		model.addAttribute("entrySet", cart.entrySet().iterator());
		model.addAttribute("subTotal", getSubtotal());
		return prefix+"cart";
	}
	
	/* 加進購物車 */
	@PostMapping("/AddtoCart/{idStr}")
	public ResponseEntity<HttpStatus> addItem(
			Model model,
			@PathVariable String idStr,
			@RequestParam("qty") Integer qty){
		try {
			Integer id = Integer.valueOf(idStr);
			Optional<Item> opt = shopService.findById(id);
			if (opt.isPresent()) {
				OrderItem orderItem = new OrderItem();
				if ( cart.get(id) == null ) {
					orderItem.setItem(opt.get());
					//預設無折扣(尚未使用優惠券)
					orderItem.setDiscount(1.0);
					orderItem.setQty(qty);
					cart.put(id, orderItem);
				} else {
			        // 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
					OrderItem updatedOrderItem = cart.get(id);
					// 加購的數量：orderItem.getQty()
					// 原有的數量：updatedOrderItem.getQty()			
					updatedOrderItem.setQty(qty + updatedOrderItem.getQty());
					cart.put(id, updatedOrderItem);
				}
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/RemoveFromCart/{idStr}")
	public ResponseEntity<HttpStatus> removeCartItem(
			Model model,
			@PathVariable String idStr) {
		try {
			Integer id = Integer.valueOf(idStr);
			Optional<Item> opt = shopService.findById(id);
			if (opt.isPresent()) {
				cart.remove(id);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/* 結帳&回調 */
	@PostMapping("/listenPayResult")
	@ResponseBody
	private String listenPayResult(@RequestBody String body, HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println("listenPayResult");
		System.out.println(body);
		Iterator<Entry<String, String[]>> it = request.getParameterMap().entrySet().iterator();
		
		System.out.println("===== 以下為綠界付款成功後回調 =====");
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue()[0];
			
			switch (key) {
			case "PaymentDate":
				
				break;

			case "RtnMsg":
				Hashtable<String, String> dict = new Hashtable<String, String>();
				dict.put("CheckMacValue", value);
				System.out.println(orderStatus.checkMacValue(dict)); 
				break;
			
			case "RtnCode":
				// 狀態碼為1表示交易成功 其餘表示失敗
				break;
			}
			
			System.out.println("key:"+key);
			System.out.println("value:"+value+"\n");
		}
		System.out.println("===== 以上為綠界付款成功後回調 =====");
		return "1|OK";
	}
	
	@GetMapping("checkout")
	@ResponseBody
	private String checkOut(
			Model model,
			HttpServletRequest request, HttpServletResponse response) throws MalformedURLException {
		
		AllInOne all = new AllInOne("");
		Order order = new Order();
		
		orderStatus = new OrderStatusUtil(all);
		AioCheckOutALL obj = orderStatus.getCheckOutObj();
		
		// ATM: 測試用 網路ATM (使用台新銀行測試)
		// all:支援所有付款方式 (信用卡/網路ATM/ATM櫃員機/超商條碼/超商代碼)
		
		// 訂單編號
		Date nowDate = new Date();
		String nowMsec =Long.toString(nowDate.getTime());
		String merchantTradeNo = "EEIT133" + nowMsec;
		// 交易編號(不可重複)
		obj.setMerchantTradeNo(merchantTradeNo);
		order.setOrderId(merchantTradeNo);
		// 交易發生日期
		String now = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
		obj.setMerchantTradeDate(now);
		order.setShippingDate(now);
		// 交易金額（不是數量）
		// 必須大於10(不含10)
		obj.setTotalAmount(String.valueOf(getSubtotal()));
		order.setTotalPrice(getSubtotal());
		//交易描述
		obj.setTradeDesc("test Description");
			
		Iterator<OrderItem> it = cart.values().iterator();
		while (it.hasNext()) {
			OrderItem orderItem = (OrderItem) it.next();
			orderItem.setOrder(order);
			orderService.addOrderItem(orderItem);
		}
		orderService.addOrder(order);
		
		// 商品明細
		// 格式: {商品名1}{OO元}x{數量n1}#{商品名2}{OO元}x{數量n2}#...
		// 常因瀏覽器編碼問題造成格式跑版  因此建議設定'XX商城商品一批X1'
		obj.setItemName("想食What商城商品一批X1");
		
		// http://localhost:8080/FoodMap04/
		String baseUrl = util.ParseUrlPath.getFullContextPath(request)+request.getContextPath()+"/";
		
		// 發送付款資訊的網址(POST)
		System.out.println("base網址:"+baseUrl+prefix);
//		obj.setReturnURL("https://b283-1-200-32-88.ngrok.io/FoodMap04/Cart/listenPayResult");
		obj.setReturnURL(baseUrl+prefix+"/callback");
		obj.setNeedExtraPaidInfo("N");
		// 付款成功後將用戶導向的網址（返回商店）
		obj.setClientBackURL(baseUrl); // 檢查ContextPath是否為/結尾
//		obj.setNeedExtraPaidInfo("N");
		String form = orderStatus.getECPayForm();
		
		System.out.println(form);
//		return prefix+"cart";
		return form;
	}
	
	//計算購物車內所有商品的合計金額(每項商品的單價*數量的總和)
	public Integer getSubtotal(){
		Double subTotal = 0.0 ;
		Set<Integer> set = cart.keySet();
		for(int n : set){
			OrderItem orderItem = cart.get(n);
			int price    = orderItem.getItem().getPrice();
			double discount = orderItem.getDiscount();
			int qty      = orderItem.getQty();
			subTotal +=  price * discount * qty;
			System.out.println("小計:"+price * discount * qty);
		}
		return subTotal.intValue();
	}

}
