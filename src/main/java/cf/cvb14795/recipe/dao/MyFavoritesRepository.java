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
	@Query("from MyFavoritesBean f where f.aRecipeId.id = ?1")
	public Optional<MyFavoritesBean> findByRecipeId(Integer id);
	
	@Modifying
	@Transactional
	@Query(value = "delete from myFavorites where myFavorites.id = ?1",
			nativeQuery = true)
	public void deleteByRecipeId(Integer id);
	
	@Query("from MyFavoritesBean f where f.member.account=?1")
	List<MyFavoritesBean> findByName(String member);
	
}
