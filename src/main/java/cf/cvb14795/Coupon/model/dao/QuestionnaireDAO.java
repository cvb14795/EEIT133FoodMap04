package cf.cvb14795.Coupon.model.dao;

import java.util.List;

import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;

@Repository
public class QuestionnaireDAO implements IQuestionnaireDAO {
	
	SessionFactory factory;
	
	@Autowired
	public QuestionnaireDAO(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void addNewData(QuestionnaireBean qBean) {
		Session session = factory.getCurrentSession();
		QuestionnaireBean resultBean = session.get(QuestionnaireBean.class, qBean.getId());
		
		if (resultBean==null) {
			session.save(qBean);
		} else {
			throw new NonUniqueResultException();
		}
		
	}

	@Override
	public List<QuestionnaireBean> QueryDataByVaccine() {
		Session session = factory.getCurrentSession();
		Query<QuestionnaireBean> query = session.createQuery("from QuestionnaireBean where vaccine = 1", QuestionnaireBean.class);	

		return query.list();
	}

	@Override
	public List<QuestionnaireBean> SendUsersCoupons() {
		Session session = factory.getCurrentSession();
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
		Session session = factory.getCurrentSession();
		Query<QuestionnaireBean> query = session.createQuery("from QuestionnaireBean where vaccine= 1 AND label = 1 ", QuestionnaireBean.class);	
		List<QuestionnaireBean> resultBeans = query.list();
		
		for (QuestionnaireBean bean:resultBeans) {
			bean.setLabel("0");
			session.update(bean);	
		}	
		return resultBeans;
	}
	
	
	
	@Override
	public QuestionnaireBean queryIdData(String id) {
		Session session = factory.getCurrentSession();
		String hql = "from QuestionnaireBean where id=:id";
		Query<QuestionnaireBean> query = session.createQuery(hql, QuestionnaireBean.class);	
		query.setParameter("id", id);
		return query.getSingleResult();
	}


	@Override
	public void UpdateData(String before, String after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteDataById(String id) {
		Session session = factory.getCurrentSession();
		QuestionnaireBean deletedbean = session.get(QuestionnaireBean.class, id);
		if (deletedbean!=null) {
			session.delete(deletedbean);
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean checkAccount(String account) {
		Session session = factory.getCurrentSession();
		String hql = "from QuestionnaireBean where account=:account";
		Query<QuestionnaireBean> query = session.createQuery(hql, QuestionnaireBean.class);	
		query.setParameter("account", account);
//		QuestionnaireBean resultBean = null;
		
		QuestionnaireBean queriedBean = query.getSingleResult();
		
		//若該帳號不存在DB中，回傳true
		if(queriedBean!=null || !queriedBean.getAccount().equals(account)) {
			return true;
		}
		
		return false;
		
//		try {
//			resultBean = query.getSingleResult();
//		}catch (Exception e) {
//			System.err.println("account not found.");
//			return false;
//		}
//		
//		if (resultBean!=null) {
//			System.out.println("此帳號已填過");
//			return false;
//		} else {
//			return true;
//		}
		
	}

	
	
	
	

}
