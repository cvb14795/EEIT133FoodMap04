package cf.cvb14795.Coupon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity @Table(name = "questionnaire")
public class QuestionnaireBean {

	@Id 
	@Column(name="ID")
	@Type(type = "string")
	private String id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "BIRTH")
	private String birth;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "ABROAD")
	private String abroad;
	
	@Column(name = "MOVING")
	private String moving;
	
	@Column(name = "FAMILY")
	private String family;
	
	@Column(name = "FEVER")
	private String fever;
	
	@Column(name = "VACCINE")
	private String vaccine;
	
	@Column(name = "LABEL")
	private String label;
	
	
	public QuestionnaireBean() {
		
	}
	
	

	public QuestionnaireBean(String id, String name, String gender, String birth, String phone, String abroad,
			String moving, String family, String vaccine, String fever, String label) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.phone = phone;
		this.abroad = abroad;
		this.moving = moving;
		this.family = family;
		this.vaccine = vaccine;
		this.fever = fever;
		this.label = label;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAbroad() {
		return abroad;
	}

	public void setAbroad(String abroad) {
		this.abroad = abroad;
	}

	public String getMoving() {
		return moving;
	}

	public void setMoving(String moving) {
		this.moving = moving;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getFever() {
		return fever;
	}

	public void setFever(String fever) {
		this.fever = fever;
	}

	public String getVaccine() {
		return vaccine;
	}

	public void setVaccine(String vaccine) {
		this.vaccine = vaccine;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
