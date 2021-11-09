package cf.cvb14795.Coupon.model.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.Coupon.model.bean.CouponBean;

public interface ICouponService {

	CouponBean getCouponById(String id);    //依code查詢單一折價券

	List<CouponBean> getCoupons();        //查全部折價券

	CouponBean addNewData(CouponBean bean);       //新
	
	CouponBean updateCoupon(CouponBean bean);  //改

	void deleteCouponById(CouponBean bean);  //刪
}
