package cf.cvb14795.recipe.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.recipe.model.MyFavoritesBean;

public interface IMyFavoriteService {
	public void insert(MyFavoritesBean myFavoritesBean);
	
	public Optional<MyFavoritesBean> findByRecipeIdAndAccount(Integer id, String account);
	
	public void deleteByRecipeIdAndAccount(Integer id, String account);
	
	public List<MyFavoritesBean> findAll();
	
	List<MyFavoritesBean> findByName(String member);
}
