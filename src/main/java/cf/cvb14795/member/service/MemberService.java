package cf.cvb14795.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.dao.MemberRepository;

@Service
// 不要用javax.transaction 
// 要用springframework
@Transactional
public class MemberService implements IMemberService {

	MemberRepository mDao;

	@Autowired
	public MemberService(MemberRepository mDao) {
		this.mDao = mDao;
	}

	public MemberService() {
	}

	@Override
	public Member insertMember(Member m) {
		// TODO Auto-generated method stub
		return mDao.save(m);
	}

	@Override
	public List<Member> selectAllMember() {
		// TODO Auto-generated method stub
		return mDao.findAll();
	}

	@Override
	public Optional<Member> selectMemberByAccount(String userAccount) {
		// TODO Auto-generated method stub
		return mDao.findById(userAccount);
	}

	@Override
	public Optional<Member> selectMemberByIdNum(String idNum) {
		// TODO Auto-generated method stub
		return mDao.findByIdNum(idNum);
	}

	@Override
	public Member updateMember(Member m) {
		// TODO Auto-generated method stub
		return mDao.save(m);
	}
	

	@Override
	public void deleteMemberByAccount(String userAccount) {
		// TODO Auto-generated method stub
		mDao.deleteById(userAccount);
		return;
	}

	@Override
	public boolean isAdmin(String userAccount) {
		// TODO Auto-generated method stub
		boolean result = mDao.isAdmin(userAccount);
		System.out.println(String.format("查詢帳號:%s的身分:%s", userAccount, result));
		return result;
	}

	@Override
	public Long findMemberCount() {
		// TODO Auto-generated method stub
		return mDao.count();
	}

	
	
	
	
}
