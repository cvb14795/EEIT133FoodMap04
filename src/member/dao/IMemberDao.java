package member.dao;

import java.util.List;

import member.bean.Member;

public interface IMemberDao {
	
	// 新增(插入)單筆資料
	Member insertMember(Member m);

	// 查詢資料
	List<Member> selectAllMember();
	
	// 查詢單筆資料
	Member selectMemberByAccount(String userAccount);

	// 更新資料
	// 若沒有要更新的資料(屬性) m的屬性請留空
	Member updateMember(String userAccount, Member m);

	// 刪除資料
	boolean	deleteMemberByAccount(String userAccount);

}