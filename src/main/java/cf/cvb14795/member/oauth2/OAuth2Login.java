package cf.cvb14795.member.oauth2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.OAuth2Utils;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.GmailScopes;

import util.gmail.googleUserAuthorization;

@Controller
@RequestMapping("/api/oauth2callback")
public class OAuth2Login {
	
	@Value("classpath:static/client_secrets.json")
    private Resource resource;
	
	/* line登入*/
	@GetMapping("line")
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
	
	/* google登入*/
	@PostMapping("google")
	public Object googleOAuth2(
			HttpServletRequest request,
			@RequestParam(name = "g_csrf_token", required = false) String csrfTokenBody,
			@RequestParam(name = "credential", required = false) String credential 
			) throws IOException, GeneralSecurityException{

			// 設置google認證參數(client_secret.json)
			GoogleClientSecrets googleAuthorization = googleUserAuthorization.loadClientSecretsResource(new JacksonFactory(), resource);
			String clientId = googleAuthorization.getDetails().getClientId(); 
			String redirectUrl = googleAuthorization.getDetails().getRedirectUris().get(1);
			System.out.println("clientId: "+clientId);
			System.out.println("redirectUrl: "+redirectUrl);
			System.out.println("\nredirectUrl列表:");
			List.of(googleAuthorization.getDetails().getRedirectUris()).forEach(System.out::println);

			System.out.println("接收到來自OAuth2.0之callback");
			for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
				String queryParam = e.nextElement();
				System.out.println("參數名稱：" + queryParam);
				System.out.println("參數值：" + request.getParameter(queryParam));
			}

			Cookie[] cookies = request.getCookies();
			boolean isCsrfTokenCookieExist = false;
			String csrfTokenCookie = "";
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("g_csrf_token")) {
					isCsrfTokenCookieExist = true;
					csrfTokenCookie = cookie.getValue();
				}
			}
			
			if (!isCsrfTokenCookieExist || csrfTokenBody == null || csrfTokenBody.equals("")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);					
//				'No CSRF token in post body.'
			}
			if (!csrfTokenCookie.equals(csrfTokenBody)){
//				'Failed to verify double submit cookie.'
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);					
			}
			
			return "redirect:/";	
//			// accessType設為offline才能獲取離線令牌(Refresh Token)
//			
//			List<String> scopes = Arrays.asList("https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile"); 
//			// accessType設為offline才能獲取離線令牌(Refresh Token)
//			GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = 
//					new GoogleAuthorizationCodeFlow.Builder(
//						new NetHttpTransport(),
//						new JacksonFactory(),
//						googleAuthorization,
//						scopes)
//					.setAccessType("offline").build();
//			GoogleAuthorizationCodeTokenRequest tokenRequest = googleAuthorizationCodeFlow
//					.newTokenRequest(authorizationCode);
//			tokenRequest.setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI);
//			// 发起授权请求，获得Token和Refresh Token
//			GoogleTokenResponse tokenResponse = tokenRequest.execute();
//			String token = tokenResponse.getAccessToken();
//			String refreshToken = tokenResponse.getRefreshToken();
//			// 获得email
//			String email = "";
//			System.out.println("\ntoken: "+ token);
//			System.out.println("refreshToken: "+ refreshToken);
//			if (StringUtils.isNotBlank(tokenResponse.getIdToken())) {
//				GoogleIdTokenVerifier idTokenVerifier = new GoogleIdTokenVerifier.Builder(
//						googleAuthorizationCodeFlow.getTransport(), googleAuthorizationCodeFlow.getJsonFactory())
//								.build();
//				GoogleIdToken googleIdToken = idTokenVerifier.verify(tokenResponse.getIdToken());
//				if (googleIdToken != null && googleIdToken.getPayload() != null) {
//					email = googleIdToken.getPayload().getEmail();
//					System.out.println("googleIdToken: "+ googleIdToken);
//					System.out.println("email: "+ email);
//				}
//			}
		// todo 保留账号token、refreshToken、email信息
		// 待更新
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
