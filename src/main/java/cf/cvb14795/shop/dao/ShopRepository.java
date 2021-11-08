package cf.cvb14795.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cf.cvb14795.recipe.model.UserRecipeBean;
import cf.cvb14795.shop.model.Item;

@Repository
public interface ShopRepository extends JpaRepository<Item, Integer> {
	List<Item> findByName(String itemName);
	
}