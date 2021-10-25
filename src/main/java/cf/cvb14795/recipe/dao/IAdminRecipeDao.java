package cf.cvb14795.recipe.dao;

import java.util.List;

import cf.cvb14795.recipe.model.AdminRecipeBean;


public interface IAdminRecipeDao {
	public void insert(AdminRecipeBean recipe);

	public List<AdminRecipeBean> selectAll();
	
	public AdminRecipeBean getUpdateId(int id);
	
	public void saveRecipe(AdminRecipeBean adminRecipe);
	
	public void deleteById(int id);
}
