package member.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import member.bean.Member;

public class MemberDAO implements IMemberDao {
	private Session session;
	
	public MemberDAO(Session session) {
		this.session = session;
	}

	@Override
	public Member insertMember(Member m) {
		// TODO Auto-generated method stub
		Member resultMember = session.get(Member.class, m.getAccount());
		if (resultMember == null) {
			session.save(m);
		}
		return resultMember;
	}
	
	@Override
	public List<Member> selectAllMember() {
		// TODO Auto-generated method stub
		Query<Member> query = session.createQuery("from Member", Member.class);
		return query.list();
	}

	@Override
	public Member selectMemberByAccount(String userAccount) {
		// TODO Auto-generated method stub
		Member resultMember = session.get(Member.class, userAccount);
		return resultMember;
	}

	@Override
	public Member updateMember(String userAccount, Member m) {
		// TODO Auto-generated method stub
		Member resultMember = session.get(Member.class, userAccount);
		if (resultMember != null) {
			resultMember.setAddress(m.getAddress());
			resultMember.setEmail(m.getEmail());
			resultMember.setImgBytes(m.getImgBytes());
			resultMember.setName(m.getName());
			// 不給改密碼 要改密碼要在其他頁面執行
			resultMember.setPhone(m.getPhone());
		}
		return resultMember;
	}

	@Override
	public boolean deleteMemberByAccount(String userAccount) {
		// TODO Auto-generated method stub
		Member resultMember = session.get(Member.class, userAccount);
		if (resultMember != null) {
			session.delete(resultMember);
			return true;			
		}
		return false;
	}

	

	

}
