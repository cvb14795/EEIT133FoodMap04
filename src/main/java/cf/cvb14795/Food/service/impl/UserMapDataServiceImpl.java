package cf.cvb14795.Food.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cf.cvb14795.Food.dao.UserMapDataRepository;
import cf.cvb14795.Food.model.UserMapDataBean;
import cf.cvb14795.Food.service.UserMapDataService;

@Service
@Transactional
public class UserMapDataServiceImpl implements UserMapDataService{
	
	UserMapDataRepository uMapDAO;
	
	@Autowired
	public UserMapDataServiceImpl(UserMapDataRepository uMapData) {
		this.uMapDAO = uMapData;
	}

//	@Override
//	public void insert(userMapDataBean uMapData) {
//		uMapDAO.save(uMapData);
//	}

	@Override
	public List<UserMapDataBean> findMembersFood() {
		List<UserMapDataBean> list = uMapDAO.findAll();
		return list;
	}

	@Override
	public List<UserMapDataBean> findByName(String mapname) {
		List<UserMapDataBean> list = uMapDAO.findByName(mapname);
		return list;
	}

//	@Override
//	public userMapDataBean UpdataId(int id) {
//		return uMapDAO.getById(id);
//	}
//
//	@Override
//	public void sava(userMapDataBean uMapData) {
//		uMapDAO.save(uMapData);
//	}
//
//	@Override
//	public void deleteById(int id) {
//		uMapDAO.deleteById(id);
//	}
}
