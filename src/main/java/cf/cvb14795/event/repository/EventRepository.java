package cf.cvb14795.Event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cf.cvb14795.Event.entity.EventBean;

/**
 * @author
 **/
@Repository
public interface EventRepository extends JpaRepository<EventBean, Integer> {
}
