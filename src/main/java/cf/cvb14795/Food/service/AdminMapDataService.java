package cf.cvb14795.Food.service;

import java.util.List;

import cf.cvb14795.Food.model.AdminMapDataBean;

public interface AdminMapDataService {
//	Optional<MapDataBean> getMapDataById(int id);
//	
//	List<MapDataBean> getMapData();
//
//	MapDataBean save(MapDataBean bean);
//	
//	MapDataBean updateMapData(MapDataBean bean); 
//
//	void deleteMapDataById(int id);
//	
//	Iterable<MapDataBean> getAllMapData();

//--------------------------------------------------------------//	
	
	public void insert(AdminMapDataBean Food);
	
	public List<AdminMapDataBean> selectAll();
	
	public AdminMapDataBean updateId(int id);
	
	public void save(AdminMapDataBean adminMapData);
	
	public void deleteId(int id);
	
	Iterable<AdminMapDataBean> getAllData();
	
}
