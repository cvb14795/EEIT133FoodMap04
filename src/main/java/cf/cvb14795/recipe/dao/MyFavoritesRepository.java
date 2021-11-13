package cf.cvb14795.recipe.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cf.cvb14795.recipe.model.MyFavoritesBean;

@Repository
public interface MyFavoritesRepository extends JpaRepository<MyFavoritesBean, Integer> {
	@Query("from MyFavoritesBean f where f.aRecipeId.id = ?1 and f.member.account = ?2")
	public Optional<MyFavoritesBean> findByRecipeIdAndAccount(Integer id, String account);
	
	@Modifying
	@Transactional
	@Query(value = "delete from myFavorites where myFavorites.id = ?1 and myFavorites.account = ?2",
			nativeQuery = true)
	public void deleteByRecipeIdAndAccount(Integer id, String member);
	
	@Query("from MyFavoritesBean f where f.member.account=?1")
	List<MyFavoritesBean> findByName(String member);
	
}
