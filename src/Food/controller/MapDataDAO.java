package Food.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Food.bean.MapData;

public class MapDataDAO {

	private Connection conn;
	private DataSource ds;
	
	
	
	public MapDataDAO() {
	}

	// 創連線
	public void createConn() throws SQLException {
		try {
			System.out.println("Open Connection!");
			// Create a JNDI Initial context to be able to lookup the DataSource
			InitialContext ctx = new InitialContext();
			// Lookup the DataSource, which will be backed by a pool
			// that the application server provides.
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TeamProject");
			conn = ds.getConnection();
			if (ds == null)
				throw new NamingException("Unknown DataSource 'jdbc/TeamProject'");
		} catch (NamingException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ========================================//
	// close connection
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("Close Connection!");
		}
	}

	// 新增資料

	public void addMapData(MapData m) throws SQLException {
		String sqlStr = "insert into MapData(mapname,mapku,mapnb,mapxy,mapcheck,img)" + "values(?,?,?,?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, m.getMapname());
		preState.setString(2, m.getMapku());
		preState.setString(3, m.getMapnb());
		preState.setString(4, m.getMapxy()); 
		preState.setString(5, m.getMapcheck()); 
		preState.setString(6, m.getImgName()); 
		int AddData = preState.executeUpdate();
		System.out.println("新增資料筆數: "+ AddData);
		preState.close();
	}

	// 修改資料 (依靠名稱)
	public void updateName(MapData m) throws SQLException {
		String sqlStr = "Update MapData Set mapku = ?, mapnb = ?, mapxy = ?, mapcheck = ?, img = ? " 
				+ " where mapname LIKE ?";

		StringBuilder sb = new StringBuilder();
		sb.append("DAO:");
		sb.append("\nparam1: " + m.getMapku());
		sb.append("\nparam2: " + m.getMapnb());
		sb.append("\nparam3: " + m.getMapxy());
		sb.append("\nparam4: " + m.getMapcheck());
		sb.append("\nparam5: " + m.getMapname());
		sb.append("\nimgName: " + m.getImgName());

		System.out.println(sb.toString());

		PreparedStatement preState = conn.prepareStatement(sqlStr);

		preState.setString(1, m.getMapku());
		preState.setString(2, m.getMapnb());
		preState.setString(3, m.getMapxy());
		preState.setString(4, m.getMapcheck());
		preState.setString(5, m.getImgName());
		preState.setString(6, "%"+m.getMapname()+"%");
		int updateCount = preState.executeUpdate();
		System.out.println("受影響資料筆數: "+ updateCount);
		preState.close();
	}

	// 查詢資料
	public MapData findByName(String mapname) throws SQLException {
		String sqlStr = "select * from MapData where mapname LIKE ?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);

		preState.setString(1, "%" + mapname + "%");
		ResultSet rs = preState.executeQuery();

		MapData m = null;

		if (rs.next()) {
			m = new MapData();
			m.setMapname(rs.getString("mapname"));
			m.setMapku(rs.getString("mapku"));
			m.setMapnb(rs.getString("mapnb"));
			m.setMapxy(rs.getString("mapxy"));
			m.setMapcheck(rs.getString("mapcheck"));
			m.setImgName(rs.getString("img"));
		}
		
		if (m != null) {
			System.out.println(String.format("mapname: '%s'" , m.getMapname()));
			System.out.println(String.format("mapku: '%s'" , m.getMapku()));
			System.out.println(String.format("mapnb: '%s'" , m.getMapnb()));
			System.out.println(String.format("mapxy: '%s'" , m.getMapxy()));
			System.out.println(String.format("mapcheck: '%s'" , m.getMapcheck()));
			System.out.println(String.format("img: '%s'" , m.getImgName()));
		} else {
			System.out.println("查詢名稱: "+ mapname +"無資料!");
		}
		
		rs.close();
		preState.close();
		return m;
	}

	// 透過 mapname 刪除該筆資料
	public void deleteMapDataByname(String mapname) throws SQLException {
		String sqlStr = "Delete from MapData where mapname LIKE ?";

		PreparedStatement preState = conn.prepareStatement(sqlStr);
		preState.setString(1, "%" +mapname + "%" );
		int deldata = preState.executeUpdate();
		System.out.println("刪除資料筆數: "+ deldata);
		preState.close();
	}
}
