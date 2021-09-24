package member.dao;

public class MemberDAOFactory {

	private static final MemberDAO MEMBER_DAO = createMemberDAO();
	
	private static MemberDAO createMemberDAO() {
		MemberDAO mDAO = new MemberDAO();
		return mDAO;
		
	}
	
	//設為public 以供Action使用
	//若為static則只有該Factory類別以及其子package能用 會呼叫不到
	public static MemberDAO getMemberDAO() {
		return MEMBER_DAO;
	}

}
