package cf.cvb14795.Food.dao;

import org.springframework.data.repository.CrudRepository;

import cf.cvb14795.Food.model.MapDataBean;

public interface CustomerRepository 
					extends CrudRepository<MapDataBean, Integer> {
}
