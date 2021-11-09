package cf.cvb14795.Coupon.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cf.cvb14795.Coupon.model.bean.CouponBean;


public interface CouponRepository extends 
			CrudRepository<CouponBean, Integer>, JpaRepository<CouponBean, Integer> {

	CouponBean findById(String id); //找符合Code的折價券
	
	
}
