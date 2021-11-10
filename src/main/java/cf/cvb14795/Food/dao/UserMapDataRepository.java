package cf.cvb14795.Food.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cf.cvb14795.Food.model.UserMapDataBean;

@Repository
public interface UserMapDataRepository extends JpaRepository<UserMapDataBean, Integer>{

	@Query("from UserMapDataBean ur where ur.mapname=:mapname")
	List<UserMapDataBean> findByName(String mapname);
}
