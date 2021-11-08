package cf.cvb14795.recipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.cvb14795.recipe.dao.MyFavoritesRepository;
import cf.cvb14795.recipe.model.MyFavoritesBean;

@Service
public class MyFavoritesServiceImpl implements IMyFavoriteService {
	
	MyFavoritesRepository favoritesDao;
	
	@Autowired
	public MyFavoritesServiceImpl(MyFavoritesRepository favoritesDao) {
		this.favoritesDao = favoritesDao;
	}

	@Override
	public void insert(MyFavoritesBean myFavoritesBean) {
		favoritesDao.save(myFavoritesBean);
	}

	@Override
	public Optional<MyFavoritesBean> findByRecipeId(Integer id) {
		return favoritesDao.findByRecipeId(id);
		
	}

	@Override
	public List<MyFavoritesBean> findAll() {
		// TODO Auto-generated method stub
		return favoritesDao.findAll();
	}

	@Override
	public void deleteByRecipeId(Integer id) {
		// TODO Auto-generated method stub
		favoritesDao.deleteByRecipeId(id);
	}

}
