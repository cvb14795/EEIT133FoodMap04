package cf.cvb14795.member.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import cf.cvb14795.recipe.model.MyFavoritesBean;

/**
 * @author cvb14795
 *
 */
@Entity
@Table(name = "users",
	uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"id"}) 
	})
public class Member {

	@Id
	@Column(name = "userAccount")
	private String account;

	@Column(name = "userPassword")
	private String password;

	@Column(name = "userName")
	private String name;

	@Column(name = "id", unique = true, length = 10)
	private String idNum;
	
	@Column(name = "userAddress")
	private String address;

	@Column(name = "userPhone")
	private String phone;

	@Column(name = "img")
	private byte[] imgBytes;

	@Column(name = "userEmail")
	private String email;

	@Column(name = "isAdmin")
	private boolean admin;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "member", cascade = CascadeType.ALL)
    Set<MyFavoritesBean> fBeans = new HashSet<MyFavoritesBean>();

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getImgBytes() {
		return imgBytes;
	}

	public void setImgBytes(byte[] imgBytes) {
		this.imgBytes = imgBytes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Member() {
	}

	public Member(String account, String password, String name, String idNum, String address, String phone,
			byte[] imgBytes, String email, boolean admin) {
		this.account = account;
		this.password = password;
		this.name = name;
		this.idNum = idNum;
		this.address = address;
		this.phone = phone;
		this.imgBytes = imgBytes;
		this.email = email;
		this.admin = admin;
	}
	
}
