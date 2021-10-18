package cf.cvb14795.recipe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.cvb14795.recipe.dao.IUserRecipeDao;
import cf.cvb14795.recipe.model.UserRecipeBean;


@Service
@Transactional
public class UserRcipeServiceImpl implements IUserRecipeService{

	SessionFactory factory;
	IUserRecipeDao uRecipeDao;
	
	@Autowired
	public UserRcipeServiceImpl(SessionFactory factory, IUserRecipeDao uRecipeDao) {
		this.factory = factory;
		this.uRecipeDao = uRecipeDao;
	}

	@Override
	public void insert(UserRecipeBean uRecipe) {
		uRecipeDao.insert(uRecipe);
	}

	@Override
	public List<UserRecipeBean> findMembersRecipe() {
		List<UserRecipeBean> list = uRecipeDao.findMembersRecipe();
		return list;
	}
	
}
