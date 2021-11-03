package cf.cvb14795.Coupon.model.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity @Table(name = "coupon")
public class CouponBean {
	
	@Id 
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "CODE")
	private String code;
	@Column(name = "START_TIME")
	private Date start_time;
	@Column(name = "END_TIME")
	private Date end_time;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name="STATUS")
	private String status;
	
	public CouponBean() {
		
	}
	
	public CouponBean(String id, String name, String code, Date start_time, Date end_time, String description,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.start_time = start_time;
		this.end_time = end_time;
		this.description = description;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
