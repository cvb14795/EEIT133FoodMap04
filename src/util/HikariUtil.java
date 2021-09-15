package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariUtil {
	//連線池(CP)的物件 通常叫做 datasource
	
	private HikariDataSource ds;
	public HikariDataSource openDataSource() {
		HikariConfig config = new HikariConfig();
		//使用者跟密碼拿掉 在此需要分開處理
		String urlStr = "jdbc:sqlserver://group4.database.windows.net:1433;databaseName=TeamProject";
		String userName = "everyone";
		String userPwd = "Foodmap04!";
		//資料庫URL(不含帳密)
		config.setJdbcUrl(urlStr);
		//指定登入帳號密碼
		config.setUsername(userName);
		config.setPassword(userPwd);
		//指定SQL資料庫驅動名稱
		config.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//最大連線數量
		config.setMaximumPoolSize(10);
		
		HikariDataSource ds = new HikariDataSource(config);
		
		return ds;
		
	}
	
	public void closeDataSource() {
		if (ds != null) {
			ds.close();
			System.out.println("dataSource已關閉!");
			
		}
		
	}
	
	
}
