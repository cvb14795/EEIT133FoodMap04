package util.gmail;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.Resource;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.jackson2.JacksonFactory;

public class googleUserAuthorization {
	public static GoogleClientSecrets loadClientSecretsResource(JacksonFactory jsonFactory, Resource resource)
			throws IOException {
		System.out.println(resource.getFile().getPath());
//		StackTraceElement[] ste = new Throwable().getStackTrace();
//		List.of(ste).forEach(System.out::println);
//		Class<?> recentClassName = null;
//		try {
//			recentClassName = Class.forName(ste[1].getClassName());
//			
//			System.out.println("\n================");
//			System.out.println("Class FullName:");
//			System.out.println(ste[1].getClassName());
//			System.out.println("\nClass SimpleName:");
//			System.out.println(Class.forName(ste[2].getClassName()).getSimpleName());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return GoogleClientSecrets.load(jsonFactory,
				new InputStreamReader(resource.getInputStream(), "UTF-8"));
	}
}
