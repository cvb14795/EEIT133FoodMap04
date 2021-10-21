package cf.cvb14795.recipe.dao;

import java.util.List;

import cf.cvb14795.recipe.model.UserRecipeBean;


public interface IUserRecipeDao {
	public void insert(UserRecipeBean uRecipe);
	
	public List<UserRecipeBean> findMembersRecipe();
	
	public List<UserRecipeBean> findByName(String userName);
}