package cf.cvb14795.Coupon.model.dao;

import java.util.List;

import cf.cvb14795.Coupon.model.bean.QuestionnaireBean;

public interface IQuestionnaireDAO {

	public void addNewData(QuestionnaireBean qBean); // 新增問卷資料

	public List<QuestionnaireBean> QueryDataByVaccine(); // 查詢有無接種疫苗

	public List<QuestionnaireBean> SendUsersCoupons(); // 修改問卷(當折價券發放時，更改折價券狀態)

	public List<QuestionnaireBean> revokeUsersCoupons(); // 撤回修改問卷(當折價券發放後，更改折價券狀態)

	public void UpdateData(String before, String after);	// 修改一筆資料

	public boolean deleteDataById(String id); // 刪除資料

	public QuestionnaireBean queryIdData(String id); //查詢特定身分證資料
	
	public Boolean checkAccount(String account);  //檢查同一帳號是否重複填寫

}
