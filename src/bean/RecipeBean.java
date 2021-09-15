package bean;

import java.io.Serializable;
import java.util.Arrays;

public class RecipeBean implements Serializable {
	private int id;
	private String name;
	private String category;
	private String food1;
	private String food2;
	private String food3;
	private String food4;
	private String sauce1;
	private String sauce2;
	private String sauce3;
	private byte[] photo;
	
	

	@Override
	public String toString() {
		return "RecipeBean [id=" + id + ", name=" + name + ", category=" + category + ", food1=" + food1 + ", food2="
				+ food2 + ", food3=" + food3 + ", food4=" + food4 + ", sauce1=" + sauce1 + ", sauce2=" + sauce2
				+ ", sauce3=" + sauce3 + ", photo=" + Arrays.toString(photo) + "]";
	}

	public RecipeBean() {
	}

	public RecipeBean(int id, String name, String category, String food1, String food2, String food3, String food4,
			String sauce1, String sauce2, String sauce3, byte[] photo) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.food1 = food1;
		this.food2 = food2;
		this.food3 = food3;
		this.food4 = food4;
		this.sauce1 = sauce1;
		this.sauce2 = sauce2;
		this.sauce3 = sauce3;
		this.photo = photo;
	}

	public RecipeBean(String name, String category, String food1, String food2, String food3, String food4,
			String sauce1, String sauce2, String sauce3, byte[] photo) {
		this.name = name;
		this.category = category;
		this.food1 = food1;
		this.food2 = food2;
		this.food3 = food3;
		this.food4 = food4;
		this.sauce1 = sauce1;
		this.sauce2 = sauce2;
		this.sauce3 = sauce3;
		this.photo = photo;
	}

	public RecipeBean(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setFile1(byte[] photo) {
		this.photo = photo;
	}

}
