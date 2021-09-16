package member.memberDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import member.memberBean.Member;
import util.DbUtil;

public class MemberDAO {

	private Connection conn;
	// scanner不需要close
	// sc也不需要try catch 否則會無窮迴圈
	private Scanner sc = new Scanner(System.in);

	
	/* ========== 使用連線池 ========== */
	// 創連線
	public void createConn() throws SQLException {
		DbUtil util = new DbUtil();
		conn = util.createConn();		
		System.out.println("已開啟連線!!");
	}

	// 關連線
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("已關閉連線!!");
		}
	}
	/* ========== 使用連線池 ========== */
	
	
	public Member createMember(String account, String password, String name, String address, String phone) {
		Member m = new Member();
		m.setAccount(account);
		m.setPassword(password);
		m.setName(name);
		m.setAddress(address);
		m.setPhone(phone);

		return m;
	}

	// 新增單筆資料
	public void addMember(Member m) throws SQLException {
//		userAccount VARCHAR(20) NOT NULL,
//		userPassword  VARCHAR(20) NOT NULL,
//		userName VARCHAR(20),
//		userAddress VARCHAR(50),
//		userPhone VARCHAR(10),
//		PRIMARY KEY(userAccount)
		String sqlStr = "INSERT INTO users(userAccount, userPassword, userName, userAddress, userPhone) " 
					+ "values(?,?,?,?,?)";
		PreparedStatement preState;
		preState = conn.prepareStatement(sqlStr);
		// id為auto increament 不用手動給
		preState.setString(1, m.getAccount());
		preState.setString(2, m.getPassword());
		preState.setString(3, m.getName());
		preState.setString(4, m.getAddress());
		preState.setString(5, m.getPhone());

		preState.execute();
		preState.close();
	}

	// 查詢資料
	public Member findAllByAccount(String userAccount) throws SQLException {
		String sqlStr = "SELECT * FROM users WHERE userAccount = ?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, userAccount);

		// 設一個空Member 以接收其查詢結果(rs)
		Member m = null;
		// 注意不是execute
		ResultSet rs = preState.executeQuery();
		System.out.println("正在查詢帳號:" + userAccount + " 的資料...");
		
		
		// 是if 不是while 因為在ID不重複的情況下其資料只有一筆
		if (rs.next()) {
			// rs.get使用欄位名稱做查詢 不是index
			m = new Member();
			m.setAccount(rs.getString("userAccount"));
			m.setPassword(rs.getString("userPassword"));
			m.setName(rs.getString("userName"));
			m.setAddress(rs.getString("userAddress"));
			m.setPhone(rs.getString("userPhone"));

		} else {
			System.out.println("查無資料 資料庫無結果!");
		}
		rs.close();
		preState.close();

		return m;

	}

	// 刪除所有資料
	public void deleteByAccount(String userAccount) throws SQLException {
		String sqlStr = "DELETE FROM users WHERE userAccount = ?";

		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, userAccount);
		preState.execute();
		System.out.println("即將刪除users : " + userAccount + "的資料...");
		preState.close();

	}

}
