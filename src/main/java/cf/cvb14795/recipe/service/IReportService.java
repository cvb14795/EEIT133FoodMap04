package cf.cvb14795.recipe.service;

import java.util.List;
import java.util.Optional;

import cf.cvb14795.recipe.model.ReportBean;

public interface IReportService {
	public void save(ReportBean report);
	
	public Optional<ReportBean> findByRecipeId(Integer id);
	
	public void deleteByRecipeId(Integer id);
	
	public List<ReportBean> findAll();
	
	List<ReportBean> findByName(String member);
	
}
