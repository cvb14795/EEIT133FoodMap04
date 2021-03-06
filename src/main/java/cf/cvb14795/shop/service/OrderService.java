package cf.cvb14795.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.cvb14795.shop.dao.OrderItemRepository;
import cf.cvb14795.shop.dao.OrderRepository;
import cf.cvb14795.shop.model.Order;
import cf.cvb14795.shop.model.OrderItem;

@Service
public class OrderService implements IOrderService{
	
//	@Qualifier("AdminRecipeRepository")
	OrderRepository orderRepository;
	OrderItemRepository orderItemRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		orderRepository.save(order);
	}

	@Override
	public void addOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}
	
	@Override
	public Optional<Order> findByOrderId(String trackNo) {
		// TODO Auto-generated method stub
		return orderRepository.findById(trackNo);
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}


	@Override
	public Order findByMemberAccount(String memberAccount) {
		// TODO Auto-generated method stub
		return orderRepository.findByMemberAccount(memberAccount);
	}

	@Override
	public void reviseOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void deleteById(String trackNo) {
		// TODO Auto-generated method stub
		orderRepository.deleteById(trackNo);
	}

	@Override
	public Long calcOrderTotalPrice() {
		// TODO Auto-generated method stub
		Long sum = 0L;
		List<Order> all = orderRepository.findAll();
		for (Order order : all) {
			if (order.getStatus().equals("已付款")) {
				sum += order.getTotalPrice();				
			}
		}
		return sum;
	}


	
	


}
