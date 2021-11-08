package cf.cvb14795.recipe.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.recipe.model.MyFavoritesBean;

public interface IMyFavoriteService {
	public void insert(MyFavoritesBean myFavoritesBean);
	public Optional<MyFavoritesBean> findByRecipeId(Integer id);
	public void deleteByRecipeId(Integer id);
	public List<MyFavoritesBean> findAll();
}
