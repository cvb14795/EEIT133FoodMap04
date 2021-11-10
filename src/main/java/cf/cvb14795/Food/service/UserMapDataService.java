package cf.cvb14795.Food.service;

import java.util.List;

import cf.cvb14795.Food.model.UserMapDataBean;

public interface UserMapDataService {
	
//	void insert(userMapDataBean uMapData);
	
	List<UserMapDataBean> findMembersFood();
	
	List<UserMapDataBean> findByName(String mapname);
	
//	userMapDataBean UpdataId(int id);
	
//	void sava(userMapDataBean uMapData);
	
//	void deleteById(int id);

}
