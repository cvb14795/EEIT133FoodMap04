package cf.cvb14795.member.oauth2;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class OAuth2Login {
	
	@GetMapping("/oauth2callback")
	public static String lineOAuth2(
			@RequestParam String code,
			@RequestParam(required = false, defaultValue = "") String state,
			@RequestParam(required = false) String friendship_status_changed) throws UnsupportedEncodingException {
		System.out.println("oauth2");
		System.out.println("code: "+code);
		System.out.println("state: "+state);
		
		if (!state.equals("12345abcde")) {
			//若前端發來的state驗證碼不對 將使用者導回重新登入頁面
			String msg = "Line第三方登入之state檢查碼錯誤，請重新登入!!";
			String encodedMsg= URLEncoder.encode(msg, "utf-8");
			return "redirect:/loginAlert?msg="+encodedMsg;
		}
		// true: 用戶已將 LINE 官方帳號加入好友 / 用戶已解除封鎖
		// false: 用戶一直是 LINE 官方帳號的好友 / 用戶未將 LINE 官方帳號加入好友 / 用戶沒有解除封鎖 LINE 官方帳號
		System.out.println("friendship_status_changed: "+friendship_status_changed);
		
		return "redirect:/";
	}
	
	@GetMapping("/token")
	@ResponseBody
	public static String getAccessToken(@RequestBody String json) {
		System.out.println(json);
		return json;
		
	}
	
	
//	//使用Restemplate来发送HTTP请求
//  RestTemplate restTemplate = new RestTemplate();
//  // json对象
//  JSONObject jsonObject = new JSONObject();
//  
//  try {
//  	// LinkedMultiValueMap 有点像JSON，用于传递post数据，网络上其他教程都使用 
//      // MultiValueMpat<>来传递post数据
//      // 但传递的数据类型有限，不能像这个这么灵活，可以传递多种不同数据类型的参数
//      LinkedMultiValueMap<String, String> body=new LinkedMultiValueMap<>();
//      body.add("grant_type", "authorization_code");
//		body.add("code", code);
////		String nextPage = URLEncoder.encode("https://eeit133-foodmap04.herokuapp.com/FoodMap04/api/oauth2callback", "utf-8");
//		String nextPage = URLEncoder.encode("https://518c-49-216-222-99.ngrok.io/FoodMap04/api/oauth2callback", "utf-8");
//		System.out.println("next: "+nextPage);
//		body.add("redirect_uri", nextPage);
//		body.add("client_id", "1656572191");
//		body.add("client_secret", "71f0fbca6b39be439a6ba55bb8adf1da");
//      
//      //设置请求header 为 APPLICATION_FORM_URLENCODED
//      HttpHeaders headers = new HttpHeaders();
//      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//      
//      // 请求体，包括请求数据 body 和 请求头 headers
//      HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(body,headers);
//      
//  	String url = "https://api.line.me/oauth2/v2.1/token";
//      //使用 exchange 发送请求，以String的类型接收返回的数据
//      //ps，我请求的数据，其返回是一个json
//      ResponseEntity<String> strbody = restTemplate.exchange(url,HttpMethod.POST,httpEntity,String.class);
//		//解析返回的数据
//      Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
//      String json = gson.toJson(strbody.getBody());
//      System.out.println(json);
//      return new ResponseEntity<String>(HttpStatus.OK);
//
//  }catch (IOException e){
//      System.out.println(e);
//  }
//	return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	
}
