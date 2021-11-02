package cf.cvb14795.Coupon.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;

public interface QuestionnaireRepository
		extends CrudRepository<QuestionnaireBean, String>, JpaRepository<QuestionnaireBean, String> {

	
	
	List<QuestionnaireBean> findByVaccine(String vaccine); // 找出所有接踵疫苗者

	QuestionnaireBean findByAccount(String account);
	
	

	
	
	
	
	

}
