package cf.cvb14795.Coupon.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Coupon")
public class CouponBean {
	
	@Id 
	@Type(type = "string")
	@Column(name = "id")
	private String id;
	private String name;
	private String start_time;
	private String end_time;
	private String value;
	private String description;
	private String status;
	private int price_floor;
	private int discount;
	
	public CouponBean() {
		
	}


	public CouponBean(String id, String name, String start_time, String end_time, String value, String description,
			String status, int price_floor, int discount) {
		super();
		this.id = id;
		this.name = name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.value = value;
		this.description = description;
		this.status = status;
		this.price_floor = price_floor;
		this.discount = discount;
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

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
	
	

	public int getPrice_floor() {
		return price_floor;
	}


	public void setPrice_floor(int price_floor) {
		this.price_floor = price_floor;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	@Override
	public String toString() {
		return "CouponBean [id=" + id + ", name=" + name + ", start_time=" + start_time + ", end_time=" + end_time
				+ ", value=" + value + ", description=" + description + ", status=" + status + ", price_floor="
				+ price_floor + ", discount=" + discount + "]";
	}


	
	
}