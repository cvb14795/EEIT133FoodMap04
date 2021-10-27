package cf.cvb14795.member.oauth2;
//package cf.cvb14795.member.oauth2;
//
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
//import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//
//import util.gmail.googleUserAuthorization;
//
///**
// * Servlet implementation class MemberOAuth2callback
// */
//@WebServlet("/api/oauth2callback")
//public class Authorize extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	/** Authorizes the installed application to access user's protected data. */
//	// 改為由client_secret.json讀取 故棄用
////	private static String clientId = "196642336489-5j9n6rtmidbccrubh6vf406gve5cejrn.apps.googleusercontent.com";
////	private static String clientSecret = "yFsWzDlb73AGyU0_yXNaeMhe";
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public Authorize() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//			System.out.println("context path:\n"+request.getServletContext().getContextPath());
//			// 設置google認證參數(client_secret.json)
//			GoogleClientSecrets googleAuthorization = googleUserAuthorization.loadClientSecretsResource(new JacksonFactory(), request.getServletContext());
//			String clientId = googleAuthorization.getDetails().getClientId(); 
//			String redirectUrl = googleAuthorization.getDetails().getRedirectUris().get(1);
//			System.out.println("\nredirectUrl列表:");
//			List.of(googleAuthorization.getDetails().getRedirectUris()).forEach(System.out::println);
//			
//			response.setContentType("text/html; charset=UTF-8");
//			System.out.println(request.getMethod());
//
//			System.out.println("接收到來自OAuth2.0之callback");
//			for (Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
//				String queryParam = e.nextElement();
//				System.out.println("參數名稱：" + queryParam);
//				System.out.println("參數值：" + request.getParameter(queryParam));
//			}
//
//			String authorizationCode = request.getParameter("code");
//			List<String> scopes = new ArrayList<String>();
//			for (String scope : request.getParameterValues("scope")) {
//				scopes.add(scope);
//			}
//			
//			
////			// accessType設為offline才能獲取離線令牌(Refresh Token)
////			String url = new GoogleAuthorizationCodeRequestUrl(clientId, redirectUrl,
////					Collections.singleton(GmailScopes.GMAIL_SEND)).setAccessType("offline").build();
//			
//			// GoogleTokenResponse token =
//			// new GoogleAuthorizationCodeTokenRequest(
//			// new NetHttpTransport(),
//			// new JacksonFactory(),
//			// clientId,
//			// clientSecret,
//			// authorizationCode,
//			// redirectUrl)
//			// .execute();
//			
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
//			// todo 保留账号token、refreshToken、email信息
//			// 待更新
//		} catch (GeneralSecurityException e) {
//			// TODO Auto-generated catch block
//			throw new ServletException(e);
//		}
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		System.out.println(request.getMethod());
//		doGet(request, response);
//	}
//
//	
//
//}
