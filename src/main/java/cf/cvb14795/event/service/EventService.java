package cf.cvb14795.event.service;

import cf.cvb14795.event.entity.EventBean;

import java.util.List;

/**
 * @author
 **/
public interface EventService {

    List<EventBean> getAll();

    void save(EventBean eventBean);

    void update(EventBean eventBean);

    void delete(int id);
}
