package cf.cvb14795.shop.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cvb14795
 *
 */
// 商家特約商店商品
//*單件商品(某一件貨)*->訂單明細(貨*數量-折扣)->單筆訂單(使用者)
@Entity
@Table(name = "item")
public class Item {	
	
	@Id
	@Column(name = "itemId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "itemName")
	private String name;
	
	@Column(name = "itemDescription")
	private String description;
	
	@Column
	private Integer price;
	
	@Column
	private byte[] photo;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	
	public Item() {
	}

	public Item(String name, String description, Integer price, byte[] photo) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", photo="
				+ Arrays.toString(photo) + "]";
	}
	
	
	
}
