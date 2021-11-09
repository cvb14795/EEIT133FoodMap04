package cf.cvb14795.Coupon.model.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cf.cvb14795.Coupon.model.bean.CouponBean;
import cf.cvb14795.Coupon.model.dao.CouponRepository;

@Repository
@Transactional
public class CouponService implements ICouponService {


	CouponRepository cDao;
	
	
	@Autowired
	public CouponService(CouponRepository cDao) {
		this.cDao = cDao;
	}

	@Override
	public CouponBean getCouponById(String id) {
		//找出符合Code的單一折價券
		return cDao.findById(id);
	}

	@Override
	public List<CouponBean> getCoupons() {
		// 找出所有折價券
		return cDao.findAll();
	}

	@Override
	public CouponBean addNewData(CouponBean bean) {
		// 新增一筆折價券資料
		return cDao.save(bean);
	}

	@Override
	public CouponBean updateCoupon(CouponBean bean) {
		// 修改單一筆折價券資料
		return cDao.save(bean);
	}

	@Override
	public void deleteCouponById(CouponBean bean) {
		// 刪除單一筆折價券資料
		cDao.delete(bean);
	}
	

}
