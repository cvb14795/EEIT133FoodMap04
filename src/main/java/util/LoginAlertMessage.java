package util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginAlertMessage {
	public static String encode(String msg) {
		String encodedMsg;
		try {
			encodedMsg = URLEncoder.encode(msg, "utf-8");
		} catch (UnsupportedEncodingException e) {
			encodedMsg = "發生錯誤，請稍後再試!";
		}
		return encodedMsg;
	}
}
