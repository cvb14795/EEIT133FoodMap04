package cf.cvb14795.member.bean;

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
@Entity
@Table(name = "users")
public class Member {

	@Id
	@Column(name = "userAccount")
	private String account;

	@Column(name = "userPassword")
	private String password;

	@Column(name = "userName")
	private String name;

	@Column(name = "id")
	private String id;
	
	@Column(name = "userAddress")
	private String address;

	@Column(name = "userPhone")
	private String phone;

	@Column(name = "img")
	private byte[] imgBytes;

	@Column(name = "userEmail")
	private String email;

	@Column(name = "isAdmin")
	private boolean isAdmin;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Member() {
	}

	public Member(String account, String password, String name, String id, String address, String phone,
			byte[] imgBytes, String email, boolean isAdmin) {
		this.account = account;
		this.password = password;
		this.name = name;
		this.id = id;
		this.address = address;
		this.phone = phone;
		this.imgBytes = imgBytes;
		this.email = email;
		this.isAdmin = isAdmin;
	}
}
