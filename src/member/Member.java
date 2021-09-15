package member;

import java.io.Serializable;

public class Member implements Serializable {

	// 序列化版本
	private static final long serialVersionUID = 1L;

	// primary key (auto increment)
	private String id;
	private String account;
	private String password;
	private String name;
	private String address;
	private String phone;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	
	public Member() {
		
	}
	public Member(String account, String password, String name, String address, String phone) {
		this.account = account;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	public Member(String id, String account, String password, String name, String address, String phone) {
		this.id = id;
		this.account = account;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	
	
	

}
