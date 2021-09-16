package Coupon.model;

public class QuestionnaireBean {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String gender;
	private String birth;
	private String phone;
	private String foreign;
	private String move;
	private String family;
	private String fever;
	private String vaccine;
	private String label;
	
	
	
	public QuestionnaireBean() {
	}
	
	public QuestionnaireBean(String id, String name, String gender, String birth, String phone, String foreign,
			String move, String family, String vaccine, String fever, String label) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.phone = phone;
		this.foreign = foreign;
		this.move = move;
		this.family = family;
		this.vaccine = vaccine;
		this.fever = fever;
		this.label = label;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getForeign() {
		return foreign;
	}
	public void setForeign(String foreign) {
		this.foreign = foreign;
	}
	public String getMove() {
		return move;
	}
	public void setMove(String move) {
		this.move = move;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
