package cf.cvb14795.recipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.cvb14795.recipe.dao.AdminRecipeRepository;
import cf.cvb14795.recipe.model.AdminRecipeBean;

@Service
public class AdminRecipeServiceImpl implements IAdminRecipeService{
	
	AdminRecipeRepository recipeDao;
	
	@Autowired
	public AdminRecipeServiceImpl(AdminRecipeRepository recipeDao) {
		this.recipeDao = recipeDao;
	}

	@Override
	public void insert(AdminRecipeBean recipe) {
		recipeDao.save(recipe);
	}

	@Override
	public List<AdminRecipeBean> selectAll() {
		List<AdminRecipeBean> list = recipeDao.findAll();
		return list;
	}

	@Override
	public AdminRecipeBean getId(int id) {
		return recipeDao.getById(id);
	}

	@Override
	public void saveRecipe(AdminRecipeBean adminRecipe) {
		recipeDao.save(adminRecipe);
		
	}
	
	@Override
	public void deleteById(int id) {
		recipeDao.deleteById(id);
	}

	@Override
	public Optional<AdminRecipeBean> findById(int id) {
		return recipeDao.findById(id);
	}
}
