package cf.cvb14795.recipe.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.recipe.model.UserRecipeBean;

public interface IUserRecipeService {
	void insert(UserRecipeBean uRecipe);
	
//	List<UserRecipeBean> findAll();
	
	Optional<UserRecipeBean> findById(int id);

	List<UserRecipeBean> findMembersRecipe();

	List<UserRecipeBean> findByName(String userName);

	Optional<UserRecipeBean> getUpdateId(int id);

	void saveRecipe(UserRecipeBean userRecipe);

	void deleteById(int id);
}
