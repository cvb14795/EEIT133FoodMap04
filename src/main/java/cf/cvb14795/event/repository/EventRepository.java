package cf.cvb14795.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cf.cvb14795.event.entity.EventBean;

/**
 * @author
 **/
@Repository
public interface EventRepository extends JpaRepository<EventBean, Integer> {
}
