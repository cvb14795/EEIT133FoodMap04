package cf.cvb14795.member.oauth2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.service.IMemberService;
import util.gmail.googleUserAuthorization;

@Controller
@RequestMapping("/api/oauth2callback")
public class OAuth2Login {
	
	@Value("classpath:static/client_secrets.json")
    private Resource resource;
	
//	private static final List<String> SCOPES = Arrays.asList(
//		 "https://www.googleapis.com/auth/userinfo.email",
//		 "https://www.googleapis.com/auth/userinfo.profile"
//	);
	
	final NetHttpTransport HTTP_TRANSPORT;
	
	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	

	IMemberService mService;
	
	@Autowired
	public OAuth2Login(IMemberService mService) throws GeneralSecurityException, IOException, NoSuchFieldException {
		this.mService = mService;
		HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	}

	
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
	public String googleOAuth2(
			HttpServletRequest request,
			@RequestParam(name = "g_csrf_token", required = false) String csrfTokenBody,
			@RequestParam(name = "credential", required = false) String idToken,
			HttpServletResponse response
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

//			Cookie[] cookies = request.getCookies();
//			boolean isCsrfTokenCookieExist = false;
//			String csrfTokenCookie = "";
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals("g_csrf_token")) {
//					isCsrfTokenCookieExist = true;
//					csrfTokenCookie = cookie.getValue();
//				}
//			}
			
			if (csrfTokenBody == null || StringUtils.isBlank(csrfTokenBody)) {
				response.sendError(HttpStatus.BAD_REQUEST.value());
				return "redirect:/login";
//				'No CSRF token in post body.'
			}
//			if (!csrfTokenCookie.equals(csrfTokenBody)){
////				'Failed to verify double submit cookie.'
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);					
//			}
			
			// accessType設為offline才能獲取離線令牌(Refresh Token)
			
//			List<String> scopes = Arrays.asList("https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile"); 
			// accessType設為offline才能獲取離線令牌(Refresh Token)
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
			// 发起授权请求，获得Token和Refresh Token
//			GoogleTokenResponse tokenResponse = tokenRequest.execute();
//			String token = tokenResponse.getAccessToken();
//			String refreshToken = tokenResponse.getRefreshToken();

			// 获得email
			String email = "";
			String userName = "";
			String avatarUrl = "";
//			System.out.println("\ntoken: "+ token);
//			System.out.println("refreshToken: "+ refreshToken);
			if (StringUtils.isNotBlank(idToken)) {
				GoogleIdTokenVerifier idTokenVerifier = new GoogleIdTokenVerifier
						.Builder(HTTP_TRANSPORT, JSON_FACTORY)
						.build();
				//該GoogleIdTokenVerifier.verify()方法驗證 JWT 簽名、aud聲明、iss聲明和 exp聲明。
				GoogleIdToken googleIdToken = idTokenVerifier.verify(idToken);
				if (googleIdToken != null && googleIdToken.getPayload() != null) {
					email = googleIdToken.getPayload().getEmail();
					userName = (String) googleIdToken.getPayload().get("name");
					avatarUrl = (String) googleIdToken.getPayload().get("picture");
					
					System.out.println("googleIdToken: "+ googleIdToken);
					System.out.println("email: "+ email);
					System.out.println("userName: "+ userName);
					System.out.println("avatarUrl: "+ avatarUrl);
					
					
					Optional<Member> m = mService.selectMemberByEmail(email);
					System.out.println("\n***Google OAuth2***\n正在驗證使用者"+userName+"之電子郵件:" + email + "是否已註冊...");
					if (m.isPresent()) {
						System.out.println("該用戶已用此email註冊過，將直接登入!");
						Cookie cookie = new Cookie("user", m.get().getAccount());
						cookie.setPath("/");
						response.addCookie(cookie);
						return "redirect:/Home";
					} else {
						// todo: 若未註冊 自動帶入註冊資料為該用戶email及頭貼網址(avatarUrl)
						String msg = "您好，您的Email:"+email+"尚未在本網站註冊! 將回到登入畫面!";
						String encodedMsg= URLEncoder.encode(msg, "utf-8");
						return "redirect:/loginAlert?msg="+encodedMsg;
					}
				}
			}
			String msg = "驗證Google登入時發生錯誤，請稍後再試!";
			String encodedMsg= URLEncoder.encode(msg, "utf-8");
			return "redirect:/loginAlert?msg="+encodedMsg;
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
