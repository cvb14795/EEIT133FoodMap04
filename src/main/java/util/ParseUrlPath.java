package util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class ParseUrlPath {
	public static String getFullContextPath(HttpServletRequest request) {
		URL u = null;
		try {
			u = new URL(request.getRequestURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		StringBuilder sb = new StringBuilder();
        // u.getProtocol(): "http" or "https"
        sb.append(u.getProtocol()+"://");
        // u.getAuthority(): localhost:8080
        sb.append(u.getAuthority());
        // request.getContextPath(): /FoodMap04
        sb.append(request.getContextPath());
        
        return sb.toString();
	}
	
}
