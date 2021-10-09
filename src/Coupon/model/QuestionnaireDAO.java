package Coupon.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class QuestionnaireDAO implements IQuestionnaireDAO {
	
	private Session session;
	
	public QuestionnaireDAO(Session session) {
		this.session = session;
	}

	@Override
	public void addNewData(QuestionnaireBean qBean) {
		QuestionnaireBean resultBean = session.get(QuestionnaireBean.class, qBean.getId());
		
		if (resultBean==null) {
			session.save(qBean);
		} else {
			throw new NonUniqueResultException();
		}
		
	}

	@Override
	public List<QuestionnaireBean> QueryDataByVaccine() {
//		session.beginTransaction();
		Query<QuestionnaireBean> query = session.createQuery("from QuestionnaireBean where vaccine = 1", QuestionnaireBean.class);	

		return query.list();
	}

	@Override
	public List<QuestionnaireBean> SendUsersCoupons() {
		Query<QuestionnaireBean> query = session.createQuery("from QuestionnaireBean where vaccine= 1 AND label = 0 ", QuestionnaireBean.class);	
		List<QuestionnaireBean> resultBeans = query.list();
		
		for (QuestionnaireBean bean:resultBeans) {
			bean.setLabel("1");
			session.update(bean);
			
		}
		
		return resultBeans;
	}

	@Override
	public List<QuestionnaireBean> revokeUsersCoupons() {
		Query<QuestionnaireBean> query = session.createQuery("from QuestionnaireBean where vaccine= 1 AND label = 1 ", QuestionnaireBean.class);	
		List<QuestionnaireBean> resultBeans = query.list();
		
		for (QuestionnaireBean bean:resultBeans) {
			bean.setLabel("0");
			session.update(bean);
			
		}
		
		return resultBeans;
	}

	@Override
	public void UpdateData(String before, String after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteDataById(String id) {
		QuestionnaireBean deletedbean = session.get(QuestionnaireBean.class, id);
		if (deletedbean!=null) {
			session.delete(deletedbean);
			return true;
		}
		
		return false;
	}
	
	

}
