package cf.cvb14795.recipe.model;

import java.util.List;

public interface IUserRecipeBeanDao {

	UserRecipeBean insert(UserRecipeBean uRecipe);

	List<UserRecipeBean> findAll();

}