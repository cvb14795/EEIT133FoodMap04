package cf.cvb14795.Coupon.model.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.Coupon.model.bean.CouponBean;

public interface ICouponService {

	Optional<CouponBean> getCopounById(int id);    //查

	List<CouponBean> getCoupons();

	CouponBean save(CouponBean bean);       //新
	
	CouponBean updateCustomer(CouponBean bean);  //改

	void deleteCustomerByPrimaryKey(int key);  //刪
}
