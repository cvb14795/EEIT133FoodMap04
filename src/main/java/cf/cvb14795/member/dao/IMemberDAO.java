package cf.cvb14795.member.dao;

import java.util.List;

import cf.cvb14795.member.bean.Member;

public interface IMemberDAO {
	
	// 新增(插入)單筆資料
	Member insertMember(Member m);

	// 查詢資料
	List<Member> selectAllMember();
	
	// 由帳號查詢單筆資料
	Member selectMemberByAccount(String userAccount);
	
	// 由身分證字號(ID)查詢單筆資料
	public Member selectMemberById(String id);
	
	// 查詢會員是否具有管理者權限
	boolean isAdmin(String userAccount);

	// 更新資料(除密碼之外)
	// 若沒有要更新的資料(屬性) m的屬性請留空
	Member updateMember(String userAccount, Member m);
	
	// 更新密碼
	boolean updateMemberPassword(String hashedPassword, Member m);
	
	// 刪除資料
	boolean	deleteMemberByAccount(String userAccount);

}