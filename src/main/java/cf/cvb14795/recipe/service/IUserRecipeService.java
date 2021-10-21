package cf.cvb14795.recipe.service;

import java.util.List;

import cf.cvb14795.recipe.model.UserRecipeBean;


public interface IUserRecipeService {
	public void insert(UserRecipeBean uRecipe);
	
	public List<UserRecipeBean> findMembersRecipe();
	
	public List<UserRecipeBean> findByName(String userName);
}
