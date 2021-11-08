package cf.cvb14795.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cf.cvb14795.shop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	Order findByMemberAccount(String memberAccount);
	//照OrderDate
	//照Status
	
}