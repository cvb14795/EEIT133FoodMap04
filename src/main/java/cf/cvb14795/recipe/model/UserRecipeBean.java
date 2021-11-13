package cf.cvb14795.recipe.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "userRecipe")
public class UserRecipeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "USERNAME")
	private String userName;
	@Column(name = "FOODNAME")
	private String foodName;
	@Column(name = "CATEGORY")
	private String category;
	@Column(name = "FOOD1")
	private String food1;
	@Column(name = "FOOD2")
	private String food2;
	@Column(name = "FOOD3")
	private String food3;
	@Column(name = "FOOD4")
	private String food4;
	@Column(name = "SAUCE1")
	private String sauce1;
	@Column(name = "SAUCE2")
	private String sauce2;
	@Column(name = "SAUCE3")
	private String sauce3;
	@Column(name = "PHOTO")
	private byte[] photo;
	@Transient
	private String base64String;
	@Column
	private String step;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userRecipe", cascade = CascadeType.ALL)
	Set<ReportBean> userRecipe = new HashSet<ReportBean>();
	
	@Override
	public String toString() {
		return "UserRecipeBean [id=" + id + ", userName=" + userName + ", foodName=" + foodName + ", category="
				+ category + ", food1=" + food1 + ", food2=" + food2 + ", food3=" + food3 + ", food4=" + food4
				+ ", sauce1=" + sauce1 + ", sauce2=" + sauce2 + ", sauce3=" + sauce3 + ", photo="
				+ Arrays.toString(photo) + ", base64String=" + base64String + ", step=" + step + "]";
	}
	
	public UserRecipeBean() {
	}

	public UserRecipeBean(String userName, String foodName, String category, String food1, String food2, String food3,
			String food4, String sauce1, String sauce2, String sauce3, byte[] photo, String base64String) {
		this.userName = userName;
		this.foodName = foodName;
		this.category = category;
		this.food1 = food1;
		this.food2 = food2;
		this.food3 = food3;
		this.food4 = food4;
		this.sauce1 = sauce1;
		this.sauce2 = sauce2;
		this.sauce3 = sauce3;
		this.photo = photo;
		this.base64String = base64String;
	}
	
	public UserRecipeBean(String userName, String foodName, String category, String food1, String food2,
			String food3, String food4, String sauce1, String sauce2, String sauce3, byte[] photo, String base64String,
			String step) {
		this.userName = userName;
		this.foodName = foodName;
		this.category = category;
		this.food1 = food1;
		this.food2 = food2;
		this.food3 = food3;
		this.food4 = food4;
		this.sauce1 = sauce1;
		this.sauce2 = sauce2;
		this.sauce3 = sauce3;
		this.photo = photo;
		this.base64String = base64String;
		this.step = step;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFood1() {
		return food1;
	}

	public void setFood1(String food1) {
		this.food1 = food1;
	}

	public String getFood2() {
		return food2;
	}

	public void setFood2(String food2) {
		this.food2 = food2;
	}

	public String getFood3() {
		return food3;
	}

	public void setFood3(String food3) {
		this.food3 = food3;
	}

	public String getFood4() {
		return food4;
	}

	public void setFood4(String food4) {
		this.food4 = food4;
	}

	public String getSauce1() {
		return sauce1;
	}

	public void setSauce1(String sauce1) {
		this.sauce1 = sauce1;
	}

	public String getSauce2() {
		return sauce2;
	}

	public void setSauce2(String sauce2) {
		this.sauce2 = sauce2;
	}

	public String getSauce3() {
		return sauce3;
	}

	public void setSauce3(String sauce3) {
		this.sauce3 = sauce3;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getBase64String() {
		return base64String;
	}

	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}
	
}
