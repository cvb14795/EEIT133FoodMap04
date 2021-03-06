package cf.cvb14795;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cf.cvb14795.member.service.IMemberService;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.AioCheckOutATM;
import ecpay.payment.integration.domain.AioCheckOutWebATM;
import ecpay.payment.integration.exception.EcpayException;

@Controller
@RequestMapping("/Cart")
public class Cart {
	private String prefix = "Cart/";
	IMemberService mService;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public Cart(IMemberService mService) {
		this.mService = mService;
	}
	
	@PostMapping("callback")
	private String callback(@RequestBody String body) {
		System.out.println("callback");
		System.out.println(body);
		return "";
	}
	@GetMapping("item/{id}")
	@ResponseBody
	private String checkOut(
			Model model,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) throws MalformedURLException {
		
		AllInOne all = new AllInOne("");
		System.out.println("購物車");
		
		// ATM: 測試用 網路ATM (使用台新銀行測試)
		// all:支援所有付款方式 (信用卡/網路ATM/ATM櫃員機/超商條碼/超商代碼)
		AioCheckOutWebATM obj = new AioCheckOutWebATM();
		// 訂單編號
		String orderId = "t1234";
		// 交易編號(不可重複)
		obj.setMerchantTradeNo(orderId + "10004");
		// 交易發生日期
		obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		// 交易金額（不是數量）
		obj.setTotalAmount("49");
		obj.setTradeDesc("test Description");
		// 商品明細
		// 格式: {商品名1}{OO元}x{數量n1}#{商品名2}{OO元}x{數量n2}#...
		// 常因瀏覽器編碼問題造成格式跑版  因此建議設定'XX商城商品一批X1'
		obj.setItemName("想食What商城商品一批X1");
		
		String baseUrl = util.ParseUrlPath.getFullContextPath(request);
		
		// 發送付款資訊的網址(POST)
		System.out.println(baseUrl+prefix+"/callback");
		obj.setReturnURL(baseUrl+"/Cart/callback");

		// 付款成功後將用戶導向的網址（返回商店）
		obj.setClientBackURL(baseUrl); // 檢查ContextPath是否為/結尾
//		obj.setNeedExtraPaidInfo("N");
		String form;
		try {
			form = all.aioCheckOut(obj, null);
		} catch (EcpayException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			form = "交易錯誤，請重新嘗試!";
		}
		System.out.println(form);
//		return prefix+"cart";
		return form;
	}

}
