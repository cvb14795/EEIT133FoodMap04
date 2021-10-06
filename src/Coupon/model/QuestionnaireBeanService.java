package Coupon.model;

import java.util.List;

import org.hibernate.Session;



public class QuestionnaireBeanService implements IQuestionnaireBeanService {
	
	private QuestionnaireDAO qDao;
	
	
	public QuestionnaireBeanService(Session session) {
		qDao = new QuestionnaireDAO(session);
	}
	

	@Override
	public void addNewData(QuestionnaireBean qBean) {
		
	}

	@Override
	public List<QuestionnaireBean> QueryDataByVaccine() {
		return qDao.QueryDataByVaccine();
	}

	@Override
	public List<QuestionnaireBean> SendUsersCoupons() {
		return qDao.SendUsersCoupons();
	}

	@Override
	public List<QuestionnaireBean> revokeUsersCoupons() {
		return qDao.revokeUsersCoupons();
	}

	@Override
	public void UpdateData(String before, String after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteDataById(String id) {
		return qDao.deleteDataById(id);
	}

}
