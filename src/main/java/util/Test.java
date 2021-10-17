package util;

import org.mindrot.jbcrypt.BCrypt;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean checkpw = BCrypt.checkpw("wbh456", "$2a$10$Sho4VOfwqLuDbnS9jzwobOxdkGOXm.EJzYi5Beihp2o0ayizUhA/m");
		System.out.println(checkpw);
	}

}
