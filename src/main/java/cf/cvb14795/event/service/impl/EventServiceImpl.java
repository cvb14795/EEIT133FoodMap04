package cf.cvb14795.event.service.impl;
import cf.cvb14795.event.entity.EventBean;
import cf.cvb14795.event.repository.EventRepository;
import cf.cvb14795.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author
 **/
@Service
@Transactional
public class EventServiceImpl implements EventService {


    @Autowired
    private EventRepository eventRepository;

    @Override
    public List<EventBean> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public void save(EventBean eventBean) {
        eventRepository.save(eventBean);
    }

    @Override
    public void update(EventBean eventBean) {
        eventRepository.save(eventBean);
    }

    @Override
    public void delete(int id) {
        eventRepository.deleteById(id);
    }
}
