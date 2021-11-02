package util;

import org.mindrot.jbcrypt.BCrypt;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean checkpw = BCrypt.checkpw("test", "$2a$10$4myoUM6Wa1Nm34KDBP7FbuVKcsqffE3Vo9Q.hTnpqc02AAGAygdsi");
		System.out.println(checkpw);
	}

}
