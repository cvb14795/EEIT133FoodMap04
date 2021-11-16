package cf.cvb14795.Event.service;

import java.util.List;

import cf.cvb14795.Event.entity.EventBean;

/**
 * @author
 **/
public interface EventService {

    List<EventBean> getAll();

    void save(EventBean eventBean);

    void update(EventBean eventBean);

    void delete(int id);
}
