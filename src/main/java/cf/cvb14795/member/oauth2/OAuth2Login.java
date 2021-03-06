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
			String msg = "?????????????????????!???????????????!";
			return "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}
	}

	/* line?????? */
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
			// ??????????????????state??????????????? ????????????????????????????????????
			String msg = "Line??????????????????state?????????????????????????????????!!";
			return "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}
		// true: ???????????? LINE ???????????????????????? / ?????????????????????
		// false: ??????????????? LINE ????????????????????? / ???????????? LINE ???????????????????????? / ???????????????????????? LINE ????????????
		System.out.println("friendship_status_changed: " + friendship_status_changed);

		String nextPage = "";
		try {
			// LinkedMultiValueMap ?????????JSON???????????????post???????????????????????????????????????
			// MultiValueMap<>?????????post??????
			// ????????????????????????????????????????????????????????????????????????????????????????????????????????????
			
			String redirectUri = HttpRequestUtil.getBaseUri(request) + "/api/oauth2callback/line";
			System.out.println("redirectUri: "+redirectUri);
			LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
			
			//???token
			body.add("grant_type", "authorization_code");
			body.add("redirect_uri", redirectUri);
			body.add("code", code);
			//TODO: ?????????config??????
			body.add("client_id", "1656572191");
			body.add("client_secret", "71f0fbca6b39be439a6ba55bb8adf1da");
			Map<String, Object> resopnseMap = HttpRequestUtil.doReq(HttpMethod.POST, lineTokenUrl, body); 

			//???accessToken???profile(?????? email)
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
			String msg = "??????Line???????????????????????????????????????!";
			nextPage = "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}

		return nextPage;
	}

	/* google?????? */
	@PostMapping("/api/oauth2callback/google")
	public String googleOAuth2(
			@RequestParam(name = "g_csrf_token", required = false) String csrfTokenBody,
			@RequestParam(name = "credential", required = false) String idToken,
			HttpServletResponse response,
			Model model
			) {
		String nextPage = "";
		try {
			// ??????google????????????(client_secret.json)
			InputStream in = new ClassPathResource("/static/client_secrets.json").getInputStream();
			GoogleClientSecrets googleAuthorization = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
			String clientId = googleAuthorization.getDetails().getClientId();
			System.out.println("clientId: " + clientId);
			System.out.println("redirectUri??????:");
			googleAuthorization.getDetails().getRedirectUris().forEach(uri -> {
				System.out.println(uri);
				String baseUrl = HttpRequestUtil.getBaseUri(request);
				
				if (uri.startsWith(baseUrl)) {
					System.out.println("**???????????????redirectUri: " + uri + "**");
				}
			});
	
			System.out.println("\n???????????????OAuth2.0???callback");
			for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
				String queryParam = e.nextElement();
				System.out.println("???????????????" + queryParam);
				System.out.println("????????????" + request.getParameter(queryParam));
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
			// ???GoogleIdTokenVerifier.verify()???????????? JWT ?????????aud?????????iss????????? exp?????????
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
			String msg = "??????Google???????????????????????????????????????!";
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
			String msg = "?????????????????????!???????????????!";
			return "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}
		
		System.out.println("email: " + email);

		List<Member> queryMberList = mService.selectMemberByEmail(email);
		System.out.println("\n***OAuth2***\n????????????????????????????????????: '" + email + "' ???????????????...");
		if (queryMberList.size() > 0) {
			System.out.println("??????????????????email???????????????????????????!");
			queryMberList.forEach(m -> {
				System.out.println(m);
				mList.add(m);
			});
//			sessionOpt.get().setAttribute("mList", mList);
			model.addAttribute("mList", mList);
			model.addAttribute("email", email);

			nextPage = "redirect:/Member/selectUsers";
		} else {
			// TODO: ???????????? ????????????????????????????????????email???????????????(avatarUrl)
			String msg = "???????????????Email: '" + email + "' ????????????????????????! ?????????????????????!";
			nextPage = "redirect:/loginAlert?msg=" + LoginAlertMessage.encode(msg);
		}
		return nextPage;
	}


// accessType??????offline????????????????????????(Refresh Token)

//List<String> scopes = Arrays.asList("https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile"); 
// accessType??????offline????????????????????????(Refresh Token)
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
// ???????????????????????????Token???Refresh Token
//GoogleTokenResponse tokenResponse = tokenRequest.execute();
//String token = tokenResponse.getAccessToken();
//String refreshToken = tokenResponse.getRefreshToken();
}
