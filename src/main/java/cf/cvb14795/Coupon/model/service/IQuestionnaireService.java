package cf.cvb14795.Coupon.model.service;

import java.util.List;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;



public interface IQuestionnaireService {

	public void addNewData(QuestionnaireBean qBean); // 新增問卷資料

	public List<QuestionnaireBean> QueryDataByVaccine(String vaccine); // 查詢有無接種疫苗

	public QuestionnaireBean SendUsersCoupons(QuestionnaireBean qBean); // 發放單一使用者折價券(當折價券發放時，更改折價券狀態)	

	public QuestionnaireBean revokeUsersCoupons(QuestionnaireBean qBean); // 撤回單一使用者折價券(當折價券發放後，更改折價券狀態 => 測試用)

	public void UpdateData(String before, String after);	// 修改一筆資料

	public void deleteDataById(String id); // 刪除資料
	
	public QuestionnaireBean queryIdData(String id); //查詢特定身分證資料 
	
	public boolean checkAccount(String account);
	


}
