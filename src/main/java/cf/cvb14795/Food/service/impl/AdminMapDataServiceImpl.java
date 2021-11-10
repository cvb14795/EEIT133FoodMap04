package cf.cvb14795.Food.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cf.cvb14795.Food.dao.AdminMapDataRepository;
import cf.cvb14795.Food.model.AdminMapDataBean;
import cf.cvb14795.Food.service.AdminMapDataService;

@Repository
@Transactional
public class AdminMapDataServiceImpl implements AdminMapDataService{
	
	AdminMapDataRepository mapDAO;
	
	@Autowired
	public AdminMapDataServiceImpl(AdminMapDataRepository mapDAO) {
		this.mapDAO = mapDAO;
	}

	@Override
	public void insert(AdminMapDataBean Food) {
		mapDAO.save(Food);
	}

	@Override
	public List<AdminMapDataBean> selectAll() {
		List<AdminMapDataBean> list = mapDAO.findAll();
		return list;
	}

	@Override
	public AdminMapDataBean updateId(int id) {
		return mapDAO.getById(id);
	}

	@Override
	public void save(AdminMapDataBean adminMapData) {
		mapDAO.save(adminMapData);
	}

	@Override
	public void deleteId(int id) {
		mapDAO.deleteById(id);
	}

	@Override
	public Iterable<AdminMapDataBean> getAllData() {
		return null;
	}
}
