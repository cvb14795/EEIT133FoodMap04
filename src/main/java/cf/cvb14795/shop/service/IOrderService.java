package cf.cvb14795.shop.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.shop.model.Order;
import cf.cvb14795.shop.model.OrderItem;


public interface IOrderService {
	 void addOrder(Order order);
	 
	 void addOrderItem(OrderItem orderItem);
	
	 List<Order> findAll();
	
	 Optional<Order> findByOrderId(Integer id);
	
	 Order findByMemberAccount(String memberAccount);
	
	//修改訂單
	 void reviseOrder(Order order);
	
	 void deleteById(Integer id);

	
}
