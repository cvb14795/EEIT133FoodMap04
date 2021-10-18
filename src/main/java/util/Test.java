package util;

import org.mindrot.jbcrypt.BCrypt;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean checkpw = BCrypt.checkpw("cvb14795", "$2a$10$b/RxGt3bGgIOk6MMg5Y.UuD9uY5LOYpYmQ4ueW5KTdQDS49h4JTDC");
		System.out.println(checkpw);
	}

}
