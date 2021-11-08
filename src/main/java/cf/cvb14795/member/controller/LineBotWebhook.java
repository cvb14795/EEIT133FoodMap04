package cf.cvb14795.member.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linecorp.bot.liff.LiffApp;

import cf.cvb14795.recipe.model.UserRecipeBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;
import cf.cvb14795.recipe.service.IUserRecipeService;

@Controller
public class LineBotWebhook {
	

	IUserRecipeService uRecipeService;
	IAdminRecipeService aRecipeService;
	
	@Autowired
	public LineBotWebhook(IUserRecipeService uRecipeService, IAdminRecipeService aRecipeService) {
		this.uRecipeService = uRecipeService;
		this.aRecipeService = aRecipeService;
	}


	@PostMapping("/webhook")
	@ResponseBody
	private ResponseEntity<String> webhook(HttpServletRequest request)
			throws NoSuchAlgorithmException, InvalidKeyException, IOException {
		System.out.println("webhook");

//		BufferedReader reader = request.getReader();
//	    JSONTokener tokener = new JSONTokener(reader);
	    System.out.println("*****Webhook Begin*****");
//	    JSONObject obj = new JSONObject(tokener);
//	    System.out.println(obj.toString(2));
	    
	    // 查詢某帳號之個人食譜名稱
	    // {"text": {"text": ["查詢食譜類型：個人"]}}
	    JSONObject fulfillmentMessages = new JSONObject();
	    
	    UserRecipeBean bean = uRecipeService.findByName("cvb14795").get(0);
	    String foodName = bean.getFoodName();
	    String responseStr = String.format("[{'text': {'text': ['查詢到了：%s']}}]", foodName);
//	    JSONObject origObj = obj.getJSONObject("queryResult")
//	    		.getJSONArray("fulfillmentMessages")
//	    		.getJSONObject(0);	 
	    JSONArray thisFulfillmentMessages = new JSONArray(responseStr);
	    fulfillmentMessages.put("fulfillmentMessages", thisFulfillmentMessages);
	    
	    // 附加傳回內容進linebot
	    // {fulfillmentMessages:thisFulfillmentMessages}
	    System.out.println(fulfillmentMessages.toString());
		return ResponseEntity.ok(fulfillmentMessages.toString());
	}
	
	@PostMapping("/liff")
	private ResponseEntity<String> liff(HttpServletRequest request){
//		new LiffApp();
		return ResponseEntity.ok("");
	}
	
	/*
	 * 在main已有介接line-bot SDK方法可用 故棄用以下方法
	 * */
	
//	@PostMapping("webhook")
//	@ResponseBody
//	private ResponseEntity<String> webhook(HttpServletRequest request)
//			throws NoSuchAlgorithmException, InvalidKeyException, IOException {
//		System.out.println("webhook");
//
////		String channelSecret = "63d92a902c9bf5c743b5767379b25060"; // Channel secret string
////		String httpRequestBody = "123test"; // Request body string
////		SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
////		Mac mac = Mac.getInstance("HmacSHA256");
////		mac.init(key);
////		byte[] source = httpRequestBody.getBytes("UTF-8");
////		String signature = Base64.encodeBase64String(mac.doFinal(source));
////		System.out.println("=====signature=====");
////		System.out.println(signature);
////		//Compare x-line-signature request header string and the signature
//		
////		System.out.println("=====header=====");
////		Enumeration<String> headerNames = request.getHeaderNames();
////		while (headerNames.hasMoreElements()) {
////			String i = headerNames.nextElement();
////			System.out.println("name: " + i);
////			System.out.println("value: " + request.getHeader(i));
////		}
//
//		BufferedReader reader = request.getReader();
//	    JSONTokener tokener = new JSONTokener(reader);
//	    JSONObject obj = new JSONObject(tokener);
//	    JSONArray events = obj.getJSONArray("events");
//	    System.out.println("*****Webhook Begin*****");
//	    //該events的HTTP請求本體是包括一個或多個Webhook事件物件的JSON格式物件 因此需使用迴圈處理
//	    for (int i = 0; i < events.length(); i++){
//	    	JSONObject eventObj = events.getJSONObject(i);
//	    	// 該通知發生日期
//	    	Date notificationDate = new Date(eventObj.getBigInteger("timestamp").longValue()); //毫秒轉秒
//	    	System.out.println("timestamp: "+eventObj.getBigInteger("timestamp"));
//	    	System.out.println("date: "+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(notificationDate));
//	    	// 由userId獲取該帳號資訊
//	    	//可使用 ^U[0-9a-f]{32}$ 驗證
//	    	String userId = eventObj.getJSONObject("source").getString("userId");
//	    	System.out.println("userId: "+userId);
//	    	Map<String, String> profile = MessageUtil.getProfile(userId);
//	    	// 獲取replyToken
//	    	String replyToken = eventObj.getString("replyToken");
//	    	System.out.println("replyToken: "+replyToken);
//	    	// 判斷"訊息/追蹤/退追"
//	        switch (eventObj.getString("type")) {
//			case "message":
//				System.out.println("*****訊息通知*****");
//				JSONObject messageObj = eventObj.getJSONObject("message");
//				String message = messageObj.getString("text");
//				System.out.println("message_text: "+message);
//				// 回傳訊息
//				MessageUtil.reply("我聽到你說了...\n"+message, replyToken);
//				break;
//
//			case "follow":
//				System.out.println("*****加入好友通知*****");
//				break;
//				
//			case "unfollow":
//				System.out.println("*****解除好友通知*****");
//				break;
//			}
//	    }
//		System.out.println("*****END*****\n");
//		return new ResponseEntity<String>(HttpStatus.OK);
//	}
}
