package util;

import javax.servlet.http.Cookie;


public class MemberStatus {
	private Cookie[] cookies;
	
	public MemberStatus(Cookie[] cookies) {
		this.cookies = cookies;
	}
	
	/**
	 * @param userName 要查詢登入狀態的使用者名稱
	 * @return 該帳號使用者現在是否有登入
	 */
	public boolean getLoginStatus(String userName) {
		if (cookies != null) { // 有登入紀錄
			for (Cookie cookie : cookies) {
				// 兩個沒有放在字串池的字串不能用== 要用equals
//				if (cookie.getName() == "user") {
				if(cookie.getName().equals("user")) {
//					System.out.println("getLoginStatus, 獲取用戶登入名稱: " + cookie.getValue());
					return cookie.getValue() == userName;
				}
			}
		} else {
			System.out.println("沒有Cookie!");
		}
		return false;
	}
	
	/**
	 * @return 現正登入的使用者名稱
	 */
	public String getCurrentUserAccount() {
		String userName = "";
		if (cookies != null) { // 有登入紀錄
			for (Cookie cookie : cookies) {
				// cookies: null for會拋NullPointerException
				if (cookie.getName().equals("user")) {
					userName = cookie.getValue();
//					System.out.println("getCurrentUserAccount, 獲取用戶登入名稱: " + cookie.getValue());
					break;
				} 
			}
		} else {
			System.out.println("沒有Cookie!");
		}
		
		return userName;
	}

	
}
