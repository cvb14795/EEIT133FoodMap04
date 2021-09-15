package servelet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import bean.RecipeBean;

public class RecipeDAO {

	private Connection conn;

	public RecipeDAO(Connection conn) {
		this.conn = conn;
	}
	
	public static final String INSERT_RECIPE_SQL = "insert into recipe(Name, Category, Food1, Food2, Food3, Food4, "
			+ "Sauce1, Sauce2, Sauce3, photo) values(?,?,?,?,?,?,?,?,?,?)";
	public static final String UPDATE_RECIPE_SQL = "update recipe set Name=?, Category=?, Food1=? , Food2=?, Food3=?,"
			+ "Food4=?, Sauce1=?, Sauce2=?, Sauce3=? where id=? ";
	public static final String DELETE_RECIPE_SQL = "delete from recipe where id=?";
	

	// 新增資料
	public boolean insertData(RecipeBean recipe) {
		try {
//			String sqlStr = "insert into recipe(Name, Category, Food1, Food2, Food3, Food4, "
//					+ "Sauce1, Sauce2, Sauce3) values(?,?,?,?,?,?,?,?,?)";
//			PreparedStatement preState = conn.prepareStatement(sqlStr);
			PreparedStatement preState = conn.prepareStatement(INSERT_RECIPE_SQL);
			preState.setString(1, recipe.getName());
			preState.setString(2, recipe.getCategory());
			preState.setString(3, recipe.getFood1());
			preState.setString(4, recipe.getFood2());
			preState.setString(5, recipe.getFood3());
			preState.setString(6, recipe.getFood4());
			preState.setString(7, recipe.getSauce1());
			preState.setString(8, recipe.getSauce2());
			preState.setString(9, recipe.getSauce3());
			preState.setBytes(10, recipe.getPhoto());
//			for(String f : recipe.getFile1()) {
//			preState.setString(10, "");
//				System.out.println(f);
//			}

			preState.execute();

			preState.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	// 修改資料
	public void updateData(RecipeBean recipe) {	
		try {
			PreparedStatement preState = conn.prepareStatement(UPDATE_RECIPE_SQL);
			preState.setString(1,recipe.getName() );
			preState.setString(2,recipe.getCategory() );
			preState.setString(3,recipe.getFood1() );
			preState.setString(4,recipe.getFood2() );
			preState.setString(5,recipe.getFood3() );
			preState.setString(6,recipe.getFood4() );
			preState.setString(7,recipe.getSauce1() );
			preState.setString(8,recipe.getSauce2() );
			preState.setString(9,recipe.getSauce3() );
			preState.setInt(10, recipe.getId());
			
			preState.executeUpdate();
			preState.close();
			
			System.out.println("更新ID: "+recipe.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 刪除資料
	public void deleteData(int id) {
		try {
			PreparedStatement preState = conn.prepareStatement(DELETE_RECIPE_SQL);
			preState.setInt(1, id);
			preState.executeUpdate();
			preState.close();
			System.out.println("刪除ID: "+id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
