package Coupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDAO {

	private Connection conn;

//	public QuestionnaireDAO(Connection conn) {
//		this.conn = conn;
//	}

	// 建立連線
	public void createConn() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String urlString = "jdbc:sqlserver://group4.database.windows.net:1433;databaseName=TeamProject;user=everyone;password=Foodmap04!";
		this.conn = DriverManager.getConnection(urlString);

		boolean state = !conn.isClosed();
		if (state) {
			System.out.println("任務開始，開啟資料庫連線");
		}
	}

	// 關閉連線
	public void closeConn() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("連線已關閉");
		}

	}

	// table : Questionnaire
	// 新增一筆資料
	public void addNewData(QuestionnaireBean qBean) throws SQLException {
		String sqlString = "INSERT INTO Questionnaire(id, name, gender, birth, phone, foreigning, moving, family, vaccine, fever, label) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepareStatement = conn.prepareStatement(sqlString);
		prepareStatement.setString(1, qBean.getId());
		prepareStatement.setString(2, qBean.getName());
		prepareStatement.setString(3, qBean.getGender());
		prepareStatement.setString(4, qBean.getBirth());
		prepareStatement.setString(5, qBean.getPhone());
		prepareStatement.setString(6, qBean.getForeign());
		prepareStatement.setString(7, qBean.getMove());
		prepareStatement.setString(8, qBean.getFamily());
		prepareStatement.setString(9, qBean.getVaccine());
		prepareStatement.setString(10, qBean.getFever());
		prepareStatement.setString(11, qBean.getLabel());
		prepareStatement.execute();
		prepareStatement.close();
		System.out.println("已成功新增一筆資料");

	}

	// 查詢資料(是否有接踵過疫苗)
	public List<QuestionnaireBean> QueryDataByVaccine() throws SQLException {
		ArrayList<QuestionnaireBean> qbeanList = new ArrayList<>();
		String sqlString = "SELECT * FROM Questionnaire WHERE vaccine = 1";
		Statement createStatement = conn.createStatement();
		ResultSet rs = createStatement.executeQuery(sqlString);

		while (rs.next()) {
			QuestionnaireBean qBean = new QuestionnaireBean(rs.getString("id"), rs.getString("name"),
					rs.getString("gender"), rs.getString("birth"), rs.getString("phone"), rs.getString("foreigning"),
					rs.getString("moving"), rs.getString("family"), rs.getString("vaccine"), rs.getString("fever"),
					rs.getString("label"));
//			qBean.setId(rs.getString("id"));
//			qBean.setName(rs.getString("name"));
//			qBean.setGender(rs.getString("gender"));
//			qBean.setBirth(rs.getString("birth"));
//			qBean.setPhone(rs.getString("phone"));
//			qBean.setForeign(rs.getString("foreigning"));
//			qBean.setMove(rs.getString("moving"));
//			qBean.setFamily(rs.getString("family"));
//			qBean.setVaccine(rs.getString("vaccine"));
//			qBean.setFever(rs.getString("fever"));
//			qBean.setLabel(rs.getString("label"));
			System.out.println(qBean.getName());
			qbeanList.add(qBean);
		}

		rs.close();
		createStatement.close();
		return qbeanList;
	}

	// 修改問卷(當折價券發放時，更改折價券狀態)
	public List<QuestionnaireBean> SendCouponsUsers() throws SQLException {
		ArrayList<QuestionnaireBean> qbeanList = new ArrayList<>();
		String sqlString = "UPDATE Questionnaire SET label=1 WHERE label=0 AND vaccine=1";
		Statement createStatement = conn.createStatement();
		createStatement.execute(sqlString);
		String sqlString1 = "SELECT * FROM Questionnaire WHERE label=1 AND vaccine=1";
		ResultSet rs = createStatement.executeQuery(sqlString1);

		while (rs.next()) {
			QuestionnaireBean qBean = new QuestionnaireBean(rs.getString("id"), rs.getString("name"),
					rs.getString("gender"), rs.getString("birth"), rs.getString("phone"), rs.getString("foreigning"),
					rs.getString("moving"), rs.getString("family"), rs.getString("vaccine"), rs.getString("fever"),
					rs.getString("label"));
//				System.out.println(qBean.getName());
			qbeanList.add(qBean);
		}

		rs.close();
		createStatement.close();
		return qbeanList;
	}

	// 撤回修改問卷(當折價券發放後，更改折價券狀態)
	public List<QuestionnaireBean> revokeCouponsUsers() throws SQLException {
		ArrayList<QuestionnaireBean> qbeanList = new ArrayList<>();
		String sqlString = "UPDATE Questionnaire SET label=0 WHERE label=1 AND vaccine=1";
		Statement createStatement = conn.createStatement();
		createStatement.execute(sqlString);
		String sqlString1 = "SELECT * FROM Questionnaire WHERE label=0 AND vaccine=1";
		ResultSet rs = createStatement.executeQuery(sqlString1);

		while (rs.next()) {
			QuestionnaireBean qBean = new QuestionnaireBean(rs.getString("id"), rs.getString("name"),
					rs.getString("gender"), rs.getString("birth"), rs.getString("phone"), rs.getString("foreigning"),
					rs.getString("moving"), rs.getString("family"), rs.getString("vaccine"), rs.getString("fever"),
					rs.getString("label"));
//						System.out.println(qBean.getName());
			qbeanList.add(qBean);
		}

		rs.close();
		createStatement.close();
		return qbeanList;
	}

	// 修改一筆資料
	public void UpdateData(String before, String after) throws SQLException {
//			sqlStringBuilder.append("UPDATE GreenShop SET ");

		String sqlString = "UPDATE QuestionnaireBean SET label=? WHERE label=?";
		
//			sqlStringBuilder.append("name=? WHERE id=?");	
		PreparedStatement prepareStatement = conn.prepareStatement(sqlString);
		prepareStatement.setString(1, after);
		prepareStatement.setString(2, before);
		ResultSet rs = prepareStatement.executeQuery(sqlString);
		QuestionnaireBean qBean = new QuestionnaireBean(rs.getString("id"), rs.getString("name"),
				rs.getString("gender"), rs.getString("birth"), rs.getString("phone"), rs.getString("foreigning"),
				rs.getString("moving"), rs.getString("family"), rs.getString("vaccine"), rs.getString("fever"),
				rs.getString("label"));
		prepareStatement.close();

		System.out.println("修改成功!!!");
	}

	// 刪除一筆資料
	public void DeleteDataByID(String id) throws Exception {
		String sqlString = "DELETE FROM QuestionnaireBean WHERE id = ?";
		PreparedStatement prepareStatement = conn.prepareStatement(sqlString);
		prepareStatement.setString(1, id);
		prepareStatement.execute();
		prepareStatement.close();
		System.out.println("刪除成功!!!");
	}

}
