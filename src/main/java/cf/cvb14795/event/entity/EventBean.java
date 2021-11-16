package cf.cvb14795.event.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author
 **/
@Entity
@Table(name = "Event1")
public class EventBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer eventpeople;

    private String eventname;

    private Integer eventtime;

    private String eventcontent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventpeople() {
        return eventpeople;
    }

    public void setEventpeople(Integer eventpeople) {
        this.eventpeople = eventpeople;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public Integer getEventtime() {
        return eventtime;
    }

    public void setEventtime(Integer eventtime) {
        this.eventtime = eventtime;
    }

    public String getEventcontent() {
        return eventcontent;
    }

    public void setEventcontent(String eventcontent) {
        this.eventcontent = eventcontent;
    }
}
