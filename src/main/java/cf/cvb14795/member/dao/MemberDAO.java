package cf.cvb14795.member.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cf.cvb14795.member.bean.Member;

@Repository
public class MemberDAO implements IMemberDAO {
	SessionFactory factory;
	
	@Autowired
	public MemberDAO(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public Member insertMember(Member m) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Member resultMember = session.get(Member.class, m.getAccount());
		if (resultMember == null) {
			session.save(m);
		}
		return resultMember;
	}
	
	@Override
	public List<Member> selectAllMember() {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Query<Member> query = session.createQuery("from Member", Member.class);
		return query.list();
	}

	@Override
	public Member selectMemberByAccount(String userAccount) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Member resultMember = session.get(Member.class, userAccount);
		return resultMember;
	}
	
	@Override
	public Member selectMemberById(String id) {
		// TODO Auto-generated method stub
		String hql = "FROM Member as m WHERE m.id = :id";
		Member resultMember = null;
		Session session = factory.getCurrentSession();
		try {
			resultMember = session.createQuery(hql, Member.class).setParameter("id", id).getSingleResult();			
		} catch (NonUniqueResultException e) {
			// TODO: handle exception
//			System.out.println("在selectMemberById方法出現錯誤：\n"+e.getMessage());
//			System.out.println("於ID（身分證字號）為："+id+"之會員出現錯誤：\n該身份證字號對應一筆以上之帳號 因此無法特定某一位會員");
		} catch (NoResultException e) {
			// TODO: handle exception
//			System.out.println("在selectMemberById方法出現錯誤：\n"+e.getMessage());
//			System.out.println("於ID（身分證字號）為："+id+"之會員出現錯誤：\n找不到該身分證對應之會員");
		}
		return resultMember;
	}

	@Override
	public Member updateMember(String userAccount, Member m) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Member resultMember = session.get(Member.class, userAccount);
		if (resultMember != null) {
			resultMember.setAddress(m.getAddress());
			resultMember.setEmail(m.getEmail());
			resultMember.setImgBytes(m.getImgBytes());
			resultMember.setName(m.getName());
			// 不給改密碼 要改密碼要在其他頁面執行
			resultMember.setPhone(m.getPhone());
		}
		session.update(resultMember);
		return resultMember;
	}
	
	@Override
	public boolean updateMemberPassword(String hashedPassword, Member m) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Member resultMember = session.get(Member.class, m.getAccount());
		if (resultMember != null) {
			resultMember.setPassword(hashedPassword);
			session.update(resultMember);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean deleteMemberByAccount(String userAccount) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Member resultMember = session.get(Member.class, userAccount);
		if (resultMember != null) {
			session.delete(resultMember);
			return true;			
		}
		return false;
	}

	@Override
	public boolean isAdmin(String userAccount) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		Member resultMember = session.get(Member.class, userAccount);
		return resultMember.isAdmin();
	}


	

	

}
