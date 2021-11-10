package cf.cvb14795.recipe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cf.cvb14795.recipe.model.AdminRecipeBean;

@Repository
public interface AdminRecipeRepository extends JpaRepository<AdminRecipeBean, Integer>  {
	Long countByCategory(String category);
}
