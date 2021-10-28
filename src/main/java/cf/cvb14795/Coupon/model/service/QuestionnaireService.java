package cf.cvb14795.Coupon.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;
import cf.cvb14795.Coupon.model.dao.QuestionnaireRepository;

@Service
public class QuestionnaireService implements IQuestionnaireService {

//	SessionFactory factory;

	@Autowired
	QuestionnaireRepository qDao;

	/* 在Repository下extends CrudRepository，在Service實作方法 */

	@Override
	public void addNewData(QuestionnaireBean qBean) {
		qDao.save(qBean);
	}

	@Override
	public List<QuestionnaireBean> QueryDataByVaccine(String vaccine) {
		// 查詢有打疫苗者
		return qDao.findByVaccine(vaccine);
	}

	@Override
	public QuestionnaireBean SendUsersCoupons(QuestionnaireBean qBean) {
		// 更改單一折價券狀態
		return qDao.save(qBean);
	}

	@Override
	public QuestionnaireBean revokeUsersCoupons(QuestionnaireBean qBean) {
		// 撤回單一折價券狀態
		return qDao.save(qBean);
	}

	@Override
	public void UpdateData(String before, String after) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean deleteDataById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public QuestionnaireBean queryIdData(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	// 檢查帳號
	@Override
	public boolean checkAccount(String account) {
		QuestionnaireBean queriedBean = qDao.findByAccount(account); // account
		// 若該帳號不存在DB中，回傳true
		// if(queriedBean!=null || !queriedBean.getAccount().equals(account))
		if (queriedBean == null) {
			return true;
		}

		return false;
	}

}
