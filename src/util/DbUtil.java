package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import com.zaxxer.hikari.HikariDataSource;

public class DbUtil {
	private Connection conn = null;
//	private HikariUtil connPool = new HikariUtil();
	
	public Connection createConn() throws SQLException {
		try {
			// Create a JNDI Initial context to be able to lookup the DataSource
			InitialContext ctx = new InitialContext();
			// Lookup the DataSource, which will be backed by a pool
			// that the application server provides.
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TeamProject");
			conn = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
//	// 開連線
//	public Connection createConn() throws SQLException {
//		HikariDataSource ds = connPool.openDataSource();
//		conn = ds.getConnection();
//
//		boolean status = !conn.isClosed();
//		if (status) {
//			System.out.println("已開啟連線");
//			return conn;
//		}
//		
//		return null;
//	}

	// 關連線
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("已關閉連線");
//			connPool.closeDataSource();
//			System.out.println("已關閉DataSource");
		}
	}
	
}
