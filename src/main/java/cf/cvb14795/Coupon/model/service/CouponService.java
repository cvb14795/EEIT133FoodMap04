package cf.cvb14795.Coupon.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.cvb14795.Coupon.model.bean.CouponBean;
import cf.cvb14795.Coupon.model.dao.CouponRepository;

@Service
public class CouponService {

	@Autowired
	CouponRepository cDao;
	

}
