package cf.cvb14795.Food.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cf.cvb14795.Food.dao.CustomerRepository;
import cf.cvb14795.Food.model.MapDataBean;
import cf.cvb14795.Food.service.CustomerService;

@Repository
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository custDao;

	@Override
	public Optional<MapDataBean> getMapDataById(int id) {
		return custDao.findById(id);
	}

	@Override
	public List<MapDataBean> getMapData() {
		return (List<MapDataBean>) custDao.findAll();
	}

	@Override
	public MapDataBean save(MapDataBean bean) {
		return custDao.save(bean);
	}

	@Override
	public MapDataBean updateMapData(MapDataBean bean) {
		return custDao.save(bean);
	}

	@Override
	public void deleteMapDataById(int id) {
		custDao.deleteById(id);
	}

	@Override
	public Iterable<MapDataBean> getAllMapData() {
		return custDao.findAll();
	}
	
	
}
