package cf.cvb14795.linebot.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cf.cvb14795.recipe.model.UserRecipeBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;
import cf.cvb14795.recipe.service.IUserRecipeService;

@Controller
public class LineBotWebhook {
	

	private static final UserRecipeBean recipe = null;
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
		String baseUrl = util.ParseUrlPath.getFullContextPath(request);
		String imageUri = "";
		BufferedReader reader = request.getReader();
	    JSONTokener tokener = new JSONTokener(reader);
	    System.out.println("*****Webhook Begin*****");
	    JSONObject obj = new JSONObject(tokener);
	    System.out.println(obj.toString(2));
	    
	    // 查詢某帳號之個人食譜名稱
	    // {"text": {"text": ["查詢食譜類型：個人"]}}
	    JSONObject fulfillmentMessages = new JSONObject();
	    
	    String userAccount = obj
	    		.getJSONObject("queryResult")
	    		.getJSONArray("outputContexts")
	    		.getJSONObject(0)
	    		.getJSONObject("parameters")
	    		.getString("UserAccount");
	    JSONArray thisFulfillmentMessages = new JSONArray("[{'text': {'text': ['查詢到了：']}}]");
//	    JSONArray thisFulfillmentMessages = new JSONArray();
	    
	    String foodName = "";
	    StringBuilder sb = new StringBuilder();
	    JSONObject textObj = thisFulfillmentMessages.getJSONObject(0).getJSONObject("text");
	    // 只截取["查詢到了..."]的回應部分
	    JSONArray textArr = textObj.getJSONArray("text");
	    
	    // 查詢符合使用者輸入的帳號名的食譜
	    List<UserRecipeBean> recipeList = uRecipeService.findByName(userAccount);
		if (recipeList.size() > 0) {
			System.out.println("*以下為食譜列表*");
			for (UserRecipeBean recipe : recipeList) {
				JSONObject card = new JSONObject();
				JSONObject cardContent = new JSONObject();
				foodName = recipe.getFoodName();
				sb.append("\n"+foodName);
				System.out.println("查詢到了："+foodName);
				
				cardContent.put("title", recipe.getFoodName());
				cardContent.put("subtitle", recipe.getCategory());
				if (baseUrl.startsWith("http://localhost") && baseUrl.contains("ngrok") ) {
					//將本機請求轉到ngrok(https)
					imageUri = "https://67bc-101-10-94-107.ngrok.io/FoodMap04/Recipe/user/photo/"+recipe.getId();					
				} else if (!baseUrl.startsWith("https")){
					// line不支援非https請求 故導向heroku做顯示
					imageUri = "https://eeit133-foodmap04.herokuapp.com/FoodMap04/Recipe/user/photo/"+recipe.getId();					
				} else {
					// 否則使用原始路徑
					imageUri = baseUrl+"/Recipe/user/photo/"+recipe.getId();					
				}
				cardContent.put("imageUri", imageUri);
				JSONObject buttons = new JSONObject();
				buttons.put("text", "看大圖");
				buttons.put("postback", imageUri);
				cardContent.put("buttons", new JSONArray().put(0, buttons));
				
				card.put("card", cardContent);
				thisFulfillmentMessages.put(card);
				
			}
			textArr.put(0, textArr.getString(0)+sb.toString()); 
			System.out.println("*以上為食譜列表*");			
		} else {
			textArr.put(0, "該帳號:"+userAccount+"沒有查詢到任何食譜！\n可能此會員尚未新增個人食譜或輸入了錯誤的帳號！"); 
		}
	    
	    // 附加傳回內容進linebot
	    fulfillmentMessages.put("fulfillmentMessages", thisFulfillmentMessages);
	    System.out.println(fulfillmentMessages.toString(4));
		return ResponseEntity.ok(fulfillmentMessages.toString());
	}
	
//    JSONObject origObj = obj.getJSONObject("queryResult")
//	.getJSONArray("fulfillmentMessages")
//	.getJSONObject(0);	 
	
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
