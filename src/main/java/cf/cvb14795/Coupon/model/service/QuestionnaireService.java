package cf.cvb14795.Coupon.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;
import cf.cvb14795.Coupon.model.dao.QuestionnaireDAO;


@Service
public class QuestionnaireService implements IQuestionnaireService {
	
	SessionFactory factory;
	QuestionnaireDAO qDao;
	
	@Autowired
	public QuestionnaireService(SessionFactory factory, QuestionnaireDAO qDao) {
		this.factory = factory;
		this.qDao = qDao;
	}
	

	@Transactional
	@Override
	public void addNewData(QuestionnaireBean qBean) {
		qDao.addNewData(qBean);
	}

	@Transactional
	@Override
	public List<QuestionnaireBean> QueryDataByVaccine() {
		return qDao.QueryDataByVaccine();
	}

	@Transactional
	@Override
	public List<QuestionnaireBean> SendUsersCoupons() {
		return qDao.SendUsersCoupons();
	}

	@Transactional
	@Override
	public List<QuestionnaireBean> revokeUsersCoupons() {
		return qDao.revokeUsersCoupons();
	}

	@Transactional
	@Override
	public void UpdateData(String before, String after) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public boolean deleteDataById(String id) {
		return qDao.deleteDataById(id);
	}
	
	@Transactional
	@Override
	public QuestionnaireBean queryIdData(String id) {
		return qDao.queryIdData(id);
	}


	@Transactional
	@Override
	public Boolean checkAccount(String account) {
		return qDao.checkAccount(account);
	}
}
