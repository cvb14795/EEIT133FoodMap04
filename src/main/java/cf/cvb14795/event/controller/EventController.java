package cf.cvb14795.Event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cf.cvb14795.Event.entity.EventBean;
import cf.cvb14795.Event.service.EventService;

/**
 * @author
 **/
@Controller
@RequestMapping("Event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public String registrationPage(Model model) {
        return "Event/event-registration";
    }

    @GetMapping("registration-list")
    public String registrationList(Model model) {
        model.addAttribute("list", eventService.getAll());
        return "Event/event-registration-list";
    }

    @ResponseBody
    @RequestMapping("registration")
    public void register(@RequestBody EventBean eventBean) {
        eventService.save(eventBean);
    }

    @ResponseBody
    @DeleteMapping("registration/{id}")
    public void delete(@PathVariable Integer id){
        eventService.delete(id);
    }
}
