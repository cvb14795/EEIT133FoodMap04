package cf.cvb14795.event.repository;

import cf.cvb14795.event.entity.EventBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author
 **/
@Repository
public interface EventRepository extends JpaRepository<EventBean, Integer> {
}
