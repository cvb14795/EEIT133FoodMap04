package cf.cvb14795.recipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cf.cvb14795.recipe.dao.UserRecipeRepository;
import cf.cvb14795.recipe.model.UserRecipeBean;


@Service
@Transactional
public class UserRcipeServiceImpl implements IUserRecipeService{
	
	UserRecipeRepository uRecipeDao;
	
	@Autowired
	public UserRcipeServiceImpl(UserRecipeRepository uRecipe) {
		this.uRecipeDao = uRecipe;
	}

	@Override
	public void insert(UserRecipeBean uRecipe) {
		uRecipeDao.save(uRecipe);
		
	}

	@Override
	public List<UserRecipeBean> findMembersRecipe() {
		List<UserRecipeBean> list = uRecipeDao.findAll();
		return list;
	}

	@Override
	public List<UserRecipeBean> findByName(String userName) {
		List<UserRecipeBean> list = uRecipeDao.findByName(userName);
		return list;
	}

	@Override
	public Optional<UserRecipeBean> getUpdateId(int id) {
		return uRecipeDao.findById(id);
	}

	@Override
	public void saveRecipe(UserRecipeBean userRecipe) {
		uRecipeDao.save(userRecipe);		
	}

	@Override
	public void deleteById(int id) {
		uRecipeDao.deleteById(id);		
	}

	@Override
	public Optional<UserRecipeBean> findById(int id) {
		return uRecipeDao.findById(id);
	}

}
