package cf.cvb14795.shop.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.shop.model.Order;
import cf.cvb14795.shop.model.OrderItem;


public interface IOrderService {
	 void addOrder(Order order);
	 
	 void addOrderItem(OrderItem orderItem);
	
	 List<Order> findAll();
	
	 Optional<Order> findByOrderId(String trackNo);
	 
	 Order findByMemberAccount(String memberAccount);
	 
	 //修改訂單
	 void reviseOrder(Order order);
	
	 void deleteById(String trackNo);
	 
	 //計算銷售總額(僅計算狀態為"已付款"的訂單)
	 Long calcOrderTotalPrice();

}
