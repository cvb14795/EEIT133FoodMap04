package cf.cvb14795.shop.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	// 運費先固定60 有折價再扣
	private Integer shippingFee = 60;
	
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
	
	@ModelAttribute
	public void initAttr(Model model) {
		List<Item> items = shopService.findAll();
		model.addAttribute("items", items);
		model.addAttribute("cart", cart);
		model.addAttribute("shippingFee", shippingFee);
	}
	
	/* 商城首頁 */
	@GetMapping({"/", ""})
	public String index() {
		return prefix+"shop";
	}
	
	/* 購物車總覽 */
	@GetMapping("/Cart")
	public String cart(Model model) {
		model.addAttribute("entrySet", cart.entrySet().iterator());
		model.addAttribute("total", getTotal());
		return prefix+"cart";
	}
	
	/* 加進購物車 */
	@PostMapping("/Cart/Add/{idStr}")
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
					Item item = opt.get();
					orderItem.setItem(item);
					// 預設無折扣(尚未使用優惠券)
					// 85折=0.85, 9折=0.9, etc...
					Double discount = 1.0;
					orderItem.setDiscount(discount);
					orderItem.setQty(qty);
					orderItem.setSubTotal(qty * item.getPrice());
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
	
	/* 移除購物車內某商品*/
	@GetMapping("/Cart/Remove/{idStr}")
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
	
	/* 更新購物車小計&總價 */
	@PostMapping("/Cart/update")
	@ResponseBody
	public ResponseEntity<String> updateCart(
			@RequestBody String json
		) throws IOException {
		
		System.out.println(json);
		JSONObject obj = new JSONObject(json);
		
		OrderItem orderItem = cart.get(obj.getInt("orderItemId"));
		orderItem.setQty(obj.getInt("qty"));
		
		//計算總計 其中會重新計算到各商品的小計
		//因此不用再setSubTotal
		obj = new JSONObject();
		obj.append("subTotal", orderItem.getSubTotal());
		obj.append("total", getTotal());
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");

		return ResponseEntity.ok().headers(responseHeaders).body(obj.toString());
	}
	
	/* 結帳&回調 */
	@PostMapping("/listenPayResult")
	@ResponseBody
	private String listenPayResult(
			@RequestBody String body,
			@RequestParam("RtnMsg") String rtnMsg,
			@RequestParam("CheckMacValue") String checkMacValue,
			@RequestParam("RtnCode") String rtnCode,
			@RequestParam("MerchantTradeNo") String merchantTradeNo,
			@RequestParam("PaymentDate") String paymentDate,
			@RequestParam("TradeAmt") String tradeAmt,
			@RequestParam("TradeDate") String tradeDate,
			HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println("listenPayResult");
		
		System.out.println("===== 以下為綠界付款成功後回調 =====");
		System.out.println("訂單編號:"+merchantTradeNo);
		System.out.println("訂單狀態:"+rtnMsg);
		System.out.println("付款日期:"+paymentDate);
		System.out.println("交易金額:"+tradeAmt);
		System.out.println("交易發生日期:"+tradeDate);
		
		System.out.println(body);
		Order order = new Order();
		Optional<Order> opt = orderService.findByOrderId(merchantTradeNo);
		if (opt.isPresent()) {
			order = opt.get();
			order.setStatus("已付款");
			System.out.println("訂單狀態更新為: 已付款");
			//付款日期
			order.setShippingDate(paymentDate);	
			//更新訂單
			orderService.addOrder(order);
		}
	
		Hashtable<String, String> dict = new Hashtable<String, String>();
		dict.put("CheckMacValue", checkMacValue);
		System.out.println(orderStatus.checkMacValue(dict)); 

		System.out.println("===== 以上為綠界付款成功後回調 =====");
		
		return "1|OK";
	}
	
	@PostMapping("applyCoupon")
	private ResponseEntity<HttpStatus> applyCoupon(@RequestParam("coupon") String coupon){
		if (true) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("checkout")
	@ResponseBody
	private String doCheckOut(
			Model model,
			@CookieValue("user") String user,
			HttpServletRequest request, HttpServletResponse response) throws MalformedURLException {
		
		Order order = new Order();
		order.setMemberAccount(user);
		order.setShippingFee(shippingFee);
		order.setCoupon("");
		order.setStatus("未付款");
		
		AllInOne all = new AllInOne("");
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
		//下單日期(付款前)
		order.setOrderDate(now);
		// 交易金額（不是數量）
		// 必須大於10(不含10)
		obj.setTotalAmount(String.valueOf( getTotal()+shippingFee ));
		order.setTotalPrice(getTotal());
		//交易描述
		obj.setTradeDesc("test Description");
			
		orderService.addOrder(order);
		Iterator<OrderItem> it = cart.values().iterator();
		while (it.hasNext()) {
			OrderItem orderItem = (OrderItem) it.next();
			orderItem.setOrder(order);
			System.out.println("***id: "+orderItem.getOrder().getOrderId());
			orderService.addOrderItem(orderItem);
		}
		
		// 商品明細
		// 格式: {商品名1}{OO元}x{數量n1}#{商品名2}{OO元}x{數量n2}#...
		// 常因瀏覽器編碼問題造成格式跑版  因此建議設定'XX商城商品一批X1'
		obj.setItemName("想食What商城商品一批X1");
		
		// http://localhost:8080/FoodMap04/
		String baseUrl = util.ParseUrlPath.getFullContextPath(request)+"/";
		
		// 發送付款資訊的網址(POST)
		System.out.println("base網址:"+baseUrl+prefix);
		obj.setReturnURL("https://fcdb-140-115-236-39.ngrok.io/FoodMap04/Shop/listenPayResult");
//		obj.setReturnURL(baseUrl+prefix+"listenPayResult");
		obj.setNeedExtraPaidInfo("N");
		// 付款成功後將用戶導向的網址（返回商店）
		obj.setClientBackURL(baseUrl+prefix+""); // 檢查ContextPath是否為/結尾
//		obj.setNeedExtraPaidInfo("N");
		String form = orderStatus.getECPayForm();
		
		System.out.println(form);
//		return prefix+"cart";
		return form;
	}
	
	//計算購物車內所有商品的合計金額(每項商品的單價*數量的總和)
	public Integer getTotal(){
		Integer total = 0 ;
		Set<Integer> set = cart.keySet();
		for(int n : set){
			OrderItem orderItem = cart.get(n);
			int price    = orderItem.getItem().getPrice();
			double discount = orderItem.getDiscount();
			int qty      = orderItem.getQty();
			int subTotal = Long.valueOf(Math.round(price * discount * qty)).intValue();
			orderItem.setSubTotal(subTotal);
			total += subTotal;
			System.out.println("小計 "+ orderItem.getItem().getName() +":"+subTotal);
		}
		System.out.println("總計:"+total);
		//四捨五入折扣後的金額
		return total;
	}
	
	@ExceptionHandler(MissingRequestCookieException.class)
	private String handleMissingCookieError(Model model) {
		return "redirect:/loginAlert";
	}

}
