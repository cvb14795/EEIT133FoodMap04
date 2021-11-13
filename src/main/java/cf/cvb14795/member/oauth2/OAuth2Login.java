package cf.cvb14795.member.oauth2;

import java.io.IOException;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Controller
@RequestMapping("/api")
public class OAuth2Login {
	
	@GetMapping("/oauth2callback")
	@ResponseBody
	public static ResponseEntity<String> lineOAuth2(
			@RequestParam String code,
			@RequestParam String state,
			@RequestParam(required = false) String friendship_status_changed) {
		System.out.println("oauth2");
		System.out.println("code: "+code);
		System.out.println("state: "+state);
		// true: 用戶已將 LINE 官方帳號加入好友 / 用戶已解除封鎖
		// false: 用戶一直是 LINE 官方帳號的好友 / 用戶未將 LINE 官方帳號加入好友 / 用戶沒有解除封鎖 LINE 官方帳號
		System.out.println("friendship_status_changed: "+friendship_status_changed);
		
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.add("Content-Type", "application/x-www-form-urlencoded");
//		Map<String, String> body = new HashMap<>();
//		body.put("grant_type", "authorization_code");
//		body.put("code", code);
//		String nextPage = URLEncoder.encode("https://640d-180-217-233-211.ngrok.io/api/token", Charset.defaultCharset());
//		body.put("redirect_uri", nextPage);
//		body.put("client_id", "1656572191");
//		body.put("client_secret", "71f0fbca6b39be439a6ba55bb8adf1da");
		
		//使用Restemplate来发送HTTP请求
        RestTemplate restTemplate = new RestTemplate();
        // json对象
        JSONObject jsonObject = new JSONObject();
        
        try {
        	// LinkedMultiValueMap 有点像JSON，用于传递post数据，网络上其他教程都使用 
            // MultiValueMpat<>来传递post数据
            // 但传递的数据类型有限，不能像这个这么灵活，可以传递多种不同数据类型的参数
            LinkedMultiValueMap<String, String> body=new LinkedMultiValueMap<>();
            body.add("grant_type", "authorization_code");
    		body.add("code", code);
    		String nextPage = URLEncoder.encode("https://eeit133-foodmap04.herokuapp.com/FoodMap04/api/token", "utf-8");
    		System.out.println("next: "+nextPage);
    		body.add("redirect_uri", nextPage);
    		body.add("client_id", "1656572191");
    		body.add("client_secret", "71f0fbca6b39be439a6ba55bb8adf1da");
            
            //设置请求header 为 APPLICATION_FORM_URLENCODED
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            // 请求体，包括请求数据 body 和 请求头 headers
            HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(body,headers);
            
        	String url = "https://api.line.me/oauth2/v2.1/token";
            //使用 exchange 发送请求，以String的类型接收返回的数据
            //ps，我请求的数据，其返回是一个json
            ResponseEntity<String> strbody = restTemplate.exchange(url,HttpMethod.POST,httpEntity,String.class);
			//解析返回的数据
            Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
            String json = gson.toJson(strbody.getBody());
            System.out.println(json);
            return new ResponseEntity<String>(HttpStatus.OK);

        }catch (IOException e){
            System.out.println(e);
        }
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/token")
	@ResponseBody
	public static String getAccessToken(@RequestBody String json) {
		System.out.println(json);
		return json;
		
	}
}
