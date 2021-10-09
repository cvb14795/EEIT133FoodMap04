package recipe.model;

import java.util.List;

public interface IUserRecipeBeanDao {
	public UserRecipeBean insert(UserRecipeBean uRecipe);
	
	public List<UserRecipeBean> findAll();
}