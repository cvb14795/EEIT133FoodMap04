package cf.cvb14795.recipe.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cf.cvb14795.recipe.model.ReportBean;

@Repository
public interface ReportRepository extends JpaRepository<ReportBean, Integer> {
	@Query("from ReportBean rb where rb.userRecipe.id = ?1")
//	public Optional<ReportBean> findByRecipeId(Integer id);
	public Optional<ReportBean> findByRecipeId(Integer id);
	
	@Modifying
	@Transactional
	@Query(value = "delete from report where report.id = ?1",
			nativeQuery = true)
	public void deleteByRecipeId(Integer id);
	
	@Query("from ReportBean f where f.member.account=?1")
	List<ReportBean> findByName(String member);
	
}
