package cf.cvb14795.Food.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cf.cvb14795.Food.model.AdminMapDataBean;

@Repository
public interface AdminMapDataRepository extends JpaRepository<AdminMapDataBean, Integer> {

}
