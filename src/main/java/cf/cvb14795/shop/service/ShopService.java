package cf.cvb14795.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cf.cvb14795.shop.dao.ShopRepository;
import cf.cvb14795.shop.model.Item;


@Service
@Transactional
public class ShopService implements IShopService{
	
	ShopRepository shopRepository;

	@Autowired
	public ShopService(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	@Override
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		shopRepository.save(item);
		
	}

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return shopRepository.findAll();
	}

	@Override
	public Optional<Item> findById(Integer id) {
		// TODO Auto-generated method stub
		return shopRepository.findById(id);
	}

	@Override
	public List<Item> findByName(String itemName) {
		// TODO Auto-generated method stub
		return shopRepository.findByName(itemName);
	}

	@Override
	public void reviseItem(Item item) {
		shopRepository.save(item);
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		shopRepository.deleteById(id);
	}

	@Override
	public Long findItemCount() {
		// TODO Auto-generated method stub
		return shopRepository.count();
	}
}
