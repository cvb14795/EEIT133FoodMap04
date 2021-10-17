package cf.cvb14795.member.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cf.cvb14795.member.bean.Member;

@Service
// 不要用javax.transaction 
// 要用springframework
@Transactional
public class MemberService implements IMemberService {

	SessionFactory factory;
	MemberDAO mDAO;

	@Autowired
	public MemberService(SessionFactory factory, MemberDAO mDAO) {
		this.factory = factory;
		this.mDAO = mDAO;
	}

	public MemberService() {
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
	public Member selectMemberById(String id) {
		// TODO Auto-generated method stub
		return mDAO.selectMemberById(id);
	}

	@Override
	public Member updateMember(String userAccount, Member m) {
		// TODO Auto-generated method stub
		return mDAO.updateMember(userAccount, m);
	}
	
	@Override
	public boolean updateMemberPassword(String hashedPassword, Member m) {
		// TODO Auto-generated method stub
		return mDAO.updateMemberPassword(hashedPassword, m);
	}

	@Override
	public boolean deleteMemberByAccount(String userAccount) {
		// TODO Auto-generated method stub
		return mDAO.deleteMemberByAccount(userAccount);
	}

	@Override
	public boolean isAdmin(String userAccount) {
		// TODO Auto-generated method stub
		return mDAO.isAdmin(userAccount);
	}

	

}
