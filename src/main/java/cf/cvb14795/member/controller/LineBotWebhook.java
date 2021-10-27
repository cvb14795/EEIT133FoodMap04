package cf.cvb14795.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import util.lintbot.MessageUtil;

@Controller
public class LineBotWebhook {
	@PostMapping("webhook")
	@ResponseBody
	private ResponseEntity<String> webhook(HttpServletRequest request)
			throws NoSuchAlgorithmException, InvalidKeyException, IOException {
		System.out.println("webhook");

//		String channelSecret = "63d92a902c9bf5c743b5767379b25060"; // Channel secret string
//		String httpRequestBody = "123test"; // Request body string
//		SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
//		Mac mac = Mac.getInstance("HmacSHA256");
//		mac.init(key);
//		byte[] source = httpRequestBody.getBytes("UTF-8");
//		String signature = Base64.encodeBase64String(mac.doFinal(source));
//		System.out.println("=====signature=====");
//		System.out.println(signature);
//		//Compare x-line-signature request header string and the signature
		
//		System.out.println("=====header=====");
//		Enumeration<String> headerNames = request.getHeaderNames();
//		while (headerNames.hasMoreElements()) {
//			String i = headerNames.nextElement();
//			System.out.println("name: " + i);
//			System.out.println("value: " + request.getHeader(i));
//		}

		BufferedReader reader = request.getReader();
	    JSONTokener tokener = new JSONTokener(reader);
	    JSONObject obj = new JSONObject(tokener);
	    JSONArray events = obj.getJSONArray("events");
	    System.out.println("*****Webhook Begin*****");
	    //該events的HTTP請求本體是包括一個或多個Webhook事件物件的JSON格式物件 因此需使用迴圈處理
	    for (int i = 0; i < events.length(); i++){
	    	JSONObject eventObj = events.getJSONObject(i);
	    	// 該通知發生日期
	    	Date notificationDate = new Date(eventObj.getBigInteger("timestamp").longValue()); //毫秒轉秒
	    	System.out.println("timestamp: "+eventObj.getBigInteger("timestamp"));
	    	System.out.println("date: "+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(notificationDate));
	    	// 由userId獲取該帳號資訊
	    	//可使用 ^U[0-9a-f]{32}$ 驗證
	    	String userId = eventObj.getJSONObject("source").getString("userId");
	    	System.out.println("userId: "+userId);
	    	Map<String, String> profile = MessageUtil.getProfile(userId);
	    	// 獲取replyToken
	    	String replyToken = eventObj.getString("replyToken");
	    	System.out.println("replyToken: "+replyToken);
	    	// 判斷"訊息/追蹤/退追"
	        switch (eventObj.getString("type")) {
			case "message":
				System.out.println("*****訊息通知*****");
				JSONObject messageObj = eventObj.getJSONObject("message");
				String message = messageObj.getString("text");
				System.out.println("message_text: "+message);
				// 回傳訊息
				MessageUtil.reply("我聽到你說了...\n"+message, replyToken);
				break;

			case "follow":
				System.out.println("*****加入好友通知*****");
				break;
				
			case "unfollow":
				System.out.println("*****解除好友通知*****");
				break;
			}
	    }
		System.out.println("*****END*****\n");
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
