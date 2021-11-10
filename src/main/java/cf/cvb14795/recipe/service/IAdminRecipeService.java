package cf.cvb14795.recipe.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.recipe.model.AdminRecipeBean;


public interface IAdminRecipeService {
	public void insert(AdminRecipeBean recipe);

	public List<AdminRecipeBean> selectAll();

	public AdminRecipeBean getId(int id);
	
	public void saveRecipe(AdminRecipeBean adminRecipe);
	
	public void deleteById(int id);
	
	Optional<AdminRecipeBean> findById(int id);
}
