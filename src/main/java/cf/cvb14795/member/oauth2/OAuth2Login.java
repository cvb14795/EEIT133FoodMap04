package cf.cvb14795.member.oauth2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.service.IMemberService;
import util.LoginAlertMessage;
import util.oauth2.HttpRequestUtil;

@Controller
@SessionAttributes({"mList", "email"})
public class OAuth2Login {
//	private static final List<String> SCOPES = Arrays.asList(
//		 "https://www.googleapis.com/auth/userinfo.email",
//		 "https://www.googleapis.com/auth/userinfo.profile"
//	);
	final NetHttpTransport HTTP_TRANSPORT;
	HttpServletRequest request;
	final String lineTokenUrl = "https://api.line.me/oauth2/v2.1/token";
	final String lineVerifyUrl = "https://api.line.me/oauth2/v2.1/verify";

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	IMemberService mService;

	@Autowired
	public OAuth2Login(IMemberService mService, HttpServletRequest request)
			throws GeneralSecurityException, IOException, NoSuchFieldException {
		this.mService = mService;
		this.request = request;
		HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/Member/selectUsers")
	public String selectUsers(Model model) {
		request.getSession(false).getAttributeNames().asIterator().forEachRemaining(n -> {
			System.out.printf("%s: %s%n", n, request.getSession(false).getAttribute(n));
		});
		Optional<List<Member>> mListOpt = Optional.ofNullable((List<Member>) model.getAttribute("mList"));
		if (mListOpt.isPresent()) {
			return "Member/thirdPartyLoginSelectUsers";
		} else {
			String msg = "登入校期已過期!請稍後再試!";
			return "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}
	}

	/* line登入 */
	@GetMapping("/api/oauth2callback/line")
	public String lineOAuth2(@RequestParam String code,
			@RequestParam(required = false, defaultValue = "") String state,
			@RequestParam(required = false) String friendship_status_changed,
			Model model
			) throws UnsupportedEncodingException {
		System.out.println("oauth2");
		System.out.println("code: " + code);
		System.out.println("state: " + state);

		if (!state.equals("12345abcde")) {
			// 若前端發來的state驗證碼不對 將使用者導回重新登入頁面
			String msg = "Line第三方登入之state檢查碼錯誤，請重新登入!!";
			return "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}
		// true: 用戶已將 LINE 官方帳號加入好友 / 用戶已解除封鎖
		// false: 用戶一直是 LINE 官方帳號的好友 / 用戶未將 LINE 官方帳號加入好友 / 用戶沒有解除封鎖 LINE 官方帳號
		System.out.println("friendship_status_changed: " + friendship_status_changed);

		String nextPage = "";
		try {
			// LinkedMultiValueMap 有点像JSON，用于传递post数据，网络上其他教程都使用
			// MultiValueMap<>来传递post数据
			// 但传递的数据类型有限，不能像这个这么灵活，可以传递多种不同数据类型的参数
			
			String redirectUri = HttpRequestUtil.getBaseUri(request) + "/api/oauth2callback/line";
			System.out.println("redirectUri: "+redirectUri);
			LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			
			//拿token
			body.add("grant_type", "authorization_code");
			body.add("redirect_uri", redirectUri);
			body.add("code", code);
			//TODO: 改成從config讀取
			body.add("client_id", "1656572191");
			body.add("client_secret", "71f0fbca6b39be439a6ba55bb8adf1da");
			Map<String, Object> resopnseMap = HttpRequestUtil.doReq(HttpMethod.POST, lineTokenUrl, body); 

			//從accessToken拿profile(名稱 email)
			body.clear();
			body.add("id_token", (String) resopnseMap.get("id_token"));
			body.add("client_id", "1656572191");
			resopnseMap = HttpRequestUtil.doReq(HttpMethod.POST, lineVerifyUrl, body);
			
			String email = (String) resopnseMap.get("email");
			Long expireTime = (Long) Double.doubleToLongBits((double) resopnseMap.get("exp"));
			if(StringUtils.isNotBlank(email) && System.currentTimeMillis() < expireTime){
				nextPage = doThirdPartyLogin(model, email);				
			}
		} catch (RestClientException e) {
			String msg = "驗證Line登入時發生錯誤，請稍後再試!";
			nextPage = "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}

		return nextPage;
	}

	/* google登入 */
	@PostMapping("/api/oauth2callback/google")
	public String googleOAuth2(
			@RequestParam(name = "g_csrf_token", required = false) String csrfTokenBody,
			@RequestParam(name = "credential", required = false) String idToken,
			HttpServletResponse response,
			Model model
			) {
		String nextPage = "";
		try {
			// 設置google認證參數(client_secret.json)
			InputStream in = new ClassPathResource("/static/client_secrets.json").getInputStream();
			GoogleClientSecrets googleAuthorization = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
			String clientId = googleAuthorization.getDetails().getClientId();
			System.out.println("clientId: " + clientId);
			System.out.println("redirectUri列表:");
			googleAuthorization.getDetails().getRedirectUris().forEach(uri -> {
				System.out.println(uri);
				String baseUrl = HttpRequestUtil.getBaseUri(request);
				
				if (uri.startsWith(baseUrl)) {
					System.out.println("**找到符合的redirectUri: " + uri + "**");
				}
			});
	
			System.out.println("\n接收到來自OAuth2.0之callback");
			for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
				String queryParam = e.nextElement();
				System.out.println("參數名稱：" + queryParam);
				System.out.println("參數值：" + request.getParameter(queryParam));
			}
	
	
			if (csrfTokenBody == null || StringUtils.isBlank(csrfTokenBody)) {
				response.sendError(HttpStatus.BAD_REQUEST.value());
				return "redirect:/Member/Login";
				// 'No CSRF token in post body.'
			}
	
			String email = "";
			String userName = "";
			String avatarUrl = "";
			GoogleIdTokenVerifier idTokenVerifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
					.build();
			// 該GoogleIdTokenVerifier.verify()方法驗證 JWT 簽名、aud聲明、iss聲明和 exp聲明。
			GoogleIdToken googleIdToken = idTokenVerifier.verify(idToken);
			if (googleIdToken != null && googleIdToken.getPayload() != null) {
				email = googleIdToken.getPayload().getEmail();
				userName = (String) googleIdToken.getPayload().get("name");
				avatarUrl = (String) googleIdToken.getPayload().get("picture");
				System.out.println("userName: " + userName);
				System.out.println("avatarUrl: " + avatarUrl);
				System.out.println("googleIdToken: " + googleIdToken);
				nextPage = doThirdPartyLogin(model, email);
			}
		} catch (IOException | GeneralSecurityException e){
			String msg = "驗證Google登入時發生錯誤，請稍後再試!";
			nextPage =  "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}
		return nextPage;
	}



	@SuppressWarnings("unchecked")
	@GetMapping("/listParams")
	@ResponseBody
	public static String getAccessToken(@ModelAttribute("tokenMap") Object tokenMapObj) {
		Map<String, Object> tokenMap =  (Map<String, Object>) tokenMapObj;
		StringJoiner sj = new StringJoiner("<br/>");
		tokenMap.forEach((k,v)-> {
			sj.add("key: "+k+", value: "+v);
			System.out.println("key: "+k+", value: "+v);
		});
		return sj.toString();

	}

	private String doThirdPartyLogin(Model model, String email) {
		List<Member> mList = new ArrayList<>();
		String nextPage = "";
		
		Optional<HttpSession> sessionOpt = Optional.ofNullable(request.getSession(false));
		if (sessionOpt.isEmpty()) {
			String msg = "登入校期已過期!請稍後再試!";
			return "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}
		
		System.out.println("email: " + email);

		List<Member> queryMberList = mService.selectMemberByEmail(email);
		System.out.println("\n***OAuth2***\n正在驗證使用者之電子郵件: '" + email + "' 是否已註冊...");
		if (queryMberList.size() > 0) {
			System.out.println("該用戶已用此email註冊過，將直接登入!");
			queryMberList.forEach(m -> {
				System.out.println(m);
				mList.add(m);
			});
//			sessionOpt.get().setAttribute("mList", mList);
			model.addAttribute("mList", mList);
			model.addAttribute("email", email);

			nextPage = "redirect:/Member/selectUsers";
		} else {
			// TODO: 若未註冊 自動帶入註冊資料為該用戶email及頭貼網址(avatarUrl)
			String msg = "您好，您的Email: '" + email + "' 尚未在本網站註冊! 將回到登入畫面!";
			nextPage = "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}
		return nextPage;
	}


// accessType設為offline才能獲取離線令牌(Refresh Token)

//List<String> scopes = Arrays.asList("https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile"); 
// accessType設為offline才能獲取離線令牌(Refresh Token)
//GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow = 
//	new GoogleAuthorizationCodeFlow.Builder(
//		new NetHttpTransport(),
//		new JacksonFactory(),
//		googleAuthorization,
//		scopes)
//	.setAccessType("offline").build();
//GoogleAuthorizationCodeTokenRequest tokenRequest = googleAuthorizationCodeFlow
//	.newTokenRequest(authorizationCode);
//tokenRequest.setRedirectUri(GoogleOAuthConstants.OOB_REDIRECT_URI);
// 发起授权请求，获得Token和Refresh Token
//GoogleTokenResponse tokenResponse = tokenRequest.execute();
//String token = tokenResponse.getAccessToken();
//String refreshToken = tokenResponse.getRefreshToken();
}
