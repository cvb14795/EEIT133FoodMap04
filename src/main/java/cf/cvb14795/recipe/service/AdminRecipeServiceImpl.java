package cf.cvb14795.recipe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.cvb14795.recipe.dao.IAdminRecipeDao;
import cf.cvb14795.recipe.model.AdminRecipeBean;

@Service
@Transactional
public class AdminRecipeServiceImpl implements IAdminRecipeService{
	
	SessionFactory factory;
	IAdminRecipeDao recipeDao;
	
	@Autowired
	public AdminRecipeServiceImpl(SessionFactory factory, IAdminRecipeDao recipeDao) {
		this.factory = factory;
		this.recipeDao = recipeDao;
	}

	@Override
	public void insert(AdminRecipeBean recipe) {
		recipeDao.insert(recipe);
	}

	@Override
	public List<AdminRecipeBean> selectAll() {
		List<AdminRecipeBean> list = recipeDao.selectAll();
		return list;
	}

	@Override
	public AdminRecipeBean getUpdateId(int id) {
		return recipeDao.getUpdateId(id);
	}

	@Override
	public void saveRecipe(AdminRecipeBean adminRecipe) {
		recipeDao.saveRecipe(adminRecipe);
		
	}
	
	@Override
	public void deleteById(int id) {
		recipeDao.deleteById(id);
	}


}
