package member.dao;

import java.util.List;

import org.hibernate.Session;

import member.bean.Member;

public class MemberService implements IMemberService {

	private MemberDAO mDAO;

	public MemberService(Session session) {
		mDAO = new MemberDAO(session);
	}

	@Override
	public Member insertMember(Member m) {
		// TODO Auto-generated method stub
		return mDAO.insertMember(m);
	}

	@Override
	public List<Member> selectAllMember() {
		// TODO Auto-generated method stub
		return mDAO.selectAllMember();
	}

	@Override
	public Member selectMemberByAccount(String userAccount) {
		// TODO Auto-generated method stub
		return mDAO.selectMemberByAccount(userAccount);
	}

	@Override
	public Member updateMember(String userAccount, Member m) {
		// TODO Auto-generated method stub
		return mDAO.updateMember(userAccount, m);
	}

	@Override
	public boolean deleteMemberByAccount(String userAccount) {
		// TODO Auto-generated method stub
		return mDAO.deleteMemberByAccount(userAccount);
	}

}
