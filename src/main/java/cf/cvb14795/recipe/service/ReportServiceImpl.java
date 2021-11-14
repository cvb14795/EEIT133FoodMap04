package cf.cvb14795.recipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cf.cvb14795.recipe.dao.ReportRepository;
import cf.cvb14795.recipe.model.ReportBean;

@Service
@Transactional
public class ReportServiceImpl implements IReportService {
	
	ReportRepository reportDao;
	
	@Autowired
	public ReportServiceImpl(ReportRepository reportDao) {
		this.reportDao = reportDao;
	}

	@Override
	public void save(ReportBean report) {
		reportDao.save(report);
	}

	@Override
	public Optional<ReportBean> findByRecipeId(Integer id) {
		return reportDao.findByRecipeId(id);
	}

	@Override
	public void deleteByRecipeId(Integer id) {
		reportDao.deleteById(id);
	}

	@Override
	public List<ReportBean> findAll() {
		return reportDao.findAll();
	}

	@Override
	public List<ReportBean> findByName(String member) {
		return reportDao.findByName(member);
	}

}
