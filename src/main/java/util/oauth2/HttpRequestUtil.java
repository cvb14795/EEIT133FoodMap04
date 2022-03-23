package util.oauth2;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HttpRequestUtil {

	public static Map<String, Object>  doReq(
			HttpMethod method, 
			String url, 
			@Nullable LinkedMultiValueMap<String, String> body) {
		// 使用Restemplate来发送HTTP请求
		RestTemplate restTemplate = new RestTemplate();
		// 设置请求header 为 APPLICATION_FORM_URLENCODED
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// 请求体，包括请求数据 body 和 请求头 headers
		HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, headers);
		
		// 使用 exchange 发送请求，以String的类型接收返回的数据
		// ps，我请求的数据，其返回是一个json
		ResponseEntity<String> strbody = restTemplate.exchange(url, method, httpEntity, String.class);
		// 解析返回的数据
		System.out.println(strbody.getBody());
		return  new Gson().fromJson(
			strbody.getBody(),
			new TypeToken<Map<String, Object>>() {}.getType()
		);
	}
	
	public static String getBaseUri(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		try {
			URL u = new URL(request.getRequestURL().toString());
			// u.getProtocol(): "http" or "https"
			sb.append(u.getProtocol() + "://");
			// u.getAuthority(): localhost:8080
			sb.append(u.getAuthority());
			// request.getContextPath(): /FoodMap04
			sb.append(request.getContextPath());
		} catch (MalformedURLException e) {
		}
		return sb.toString();
	}
}
