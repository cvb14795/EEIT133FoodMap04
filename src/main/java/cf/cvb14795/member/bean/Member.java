package cf.cvb14795.member.bean;

import java.util.HashSet;
import java.util.Objects;
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
import cf.cvb14795.recipe.model.ReportBean;

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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    Set<MyFavoritesBean> fBeans = new HashSet<MyFavoritesBean>();
	
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "member", cascade = CascadeType.ALL)
	private Set<ReportBean>  reports;

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

	public Set<ReportBean>  getReports() {
		return reports;
	}

	public void setReports(Set<ReportBean>  reports) {
		this.reports = reports;
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

	@Override
	public String toString() {
		return "Member [account=" + account + ", name=" + name + ", idNum=" + idNum + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", admin=" + admin + "]";
	}

	// 除了頭像, 檢舉, 我的最愛以外 都符合才相等
	@Override
	public int hashCode() {
		return Objects.hash(account, address, admin, email, idNum, name, password, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Member)) {
			return false;
		}
		Member other = (Member) obj;
		return Objects.equals(account, other.account) && Objects.equals(address, other.address) && admin == other.admin
				&& Objects.equals(email, other.email) && Objects.equals(idNum, other.idNum)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(phone, other.phone);
	}
	
	
	
	
	
}
