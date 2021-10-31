package cf.cvb14795.recipe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cf.cvb14795.recipe.model.UserRecipeBean;

@Repository
public interface UserRecipeRepository extends JpaRepository<UserRecipeBean, Integer> {
	@Query("from UserRecipeBean ur where ur.userName=:userName")
	List<UserRecipeBean> findByName(String userName);

//	void insert(UserRecipeBean uRecipe);
//	
//	List<UserRecipeBean> findMembersRecipe();
//	
//	
//	UserRecipeBean getUpdateId(int id);
//	
//	void saveRecipe(UserRecipeBean userRecipe);
//	
//	void deleteById(int id);
	
}