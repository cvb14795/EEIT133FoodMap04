package cf.cvb14795.shop.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.shop.model.Item;

public interface IShopService {
	void addItem(Item item);
	
	List<Item> findByName(String itemName);
	
	List<Item> findAll();
	
	Optional<Item> findById(Integer id);
	
	//修改訂單
	void reviseItem(Item item);

	void deleteById(Integer id);
	
	//上架商品總數(不同組商品視為同一筆 即不計庫存數量)
	Long findItemCount();
}
