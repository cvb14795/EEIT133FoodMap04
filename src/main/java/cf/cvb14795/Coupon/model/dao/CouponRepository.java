package cf.cvb14795.Coupon.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cf.cvb14795.Coupon.model.bean.CouponBean;


public interface CouponRepository extends CrudRepository<CouponBean, Integer> {

	
	
	
}
