package cf.cvb14795.Food.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.Food.model.MapDataBean;

public interface CustomerService {
	Optional<MapDataBean> getMapDataById(int id);
	
	List<MapDataBean> getMapData();

	MapDataBean save(MapDataBean bean);
	
	MapDataBean updateMapData(MapDataBean bean); 

	void deleteMapDataById(int id);
	
	Iterable<MapDataBean> getAllMapData();
	
}
