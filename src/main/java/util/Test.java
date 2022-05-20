package util;

import org.mindrot.jbcrypt.BCrypt;

import com.microsoft.azure.storage.core.Base64;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		boolean checkpw = BCrypt.checkpw("test", "$2a$10$4myoUM6Wa1Nm34KDBP7FbuVKcsqffE3Vo9Q.;hTnpqc02AAGAygdsi");
//		boolean checkpw = BCrypt.checkpw("", "$2a$10$zHKYCbQu4NCHQ2xSmGXKguNakPeCRLnj3X6WzUyq6tbnueFf5lfu6");
//		System.out.println(checkpw);
		String hashpw = BCrypt.hashpw("eeit133group4", BCrypt.gensalt(10));
		String result = Base64.encode(String.join("-", hashpw,String.valueOf(System.currentTimeMillis())).getBytes());
		System.out.println(result);
	}

}
