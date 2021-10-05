package util;

import javax.servlet.http.Cookie;


public class MemberStatus {
	private static Cookie[] cookies;

	public static void setCookies(Cookie[] cookies) {
		MemberStatus.cookies = cookies;
	}

	/**
	 * @param userName 要查詢登入狀態的使用者名稱
	 * @return 該帳號使用者現在是否有登入
	 */
	public static boolean getLoginStatus(String userName) {
		boolean isAlreadyLogin=false;
		
		if (cookies != null) { // 有登入紀錄
			for (Cookie cookie : cookies) {
				if (cookie.getName() == "user") {
					isAlreadyLogin = true;
					userName = cookie.getName();
					
					System.out.println("CookieName: " + cookie.getName());
					System.out.println("CookieValue: " + cookie.getValue());
					break;
				} else {
					isAlreadyLogin = false;
				}
			}
		}
		return isAlreadyLogin;
	}
	
	/**
	 * @return 現正登入的使用者名稱
	 */
	public static String getCurrentUserAccount() {
		StringBuilder sb = new StringBuilder();
		if (cookies != null) { // 有登入紀錄
			for (Cookie cookie : cookies) {
				String CookieName = cookie.getName();
				String CookieValue = cookie.getValue();
				System.out.println("value:'" + CookieName+"'");
				if (CookieName == "user") {
					sb.append(CookieValue);
					System.out.println("CookieName: " + CookieName);
					System.out.println("CookieValue: " + CookieValue);
					break;
				} 
			}
			System.out.println("userName:" + sb.toString());
		}
		return sb.toString();
	}
}
