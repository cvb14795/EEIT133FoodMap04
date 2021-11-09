package cf.cvb14795.shop.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author cvb14795
 *
 */
//單件商品(某一件貨)->訂單明細(貨*數量-折扣)->*單筆訂單(使用者)*
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column
	private String orderId;
	//購買人
	@Column
	private String memberAccount;
	//總價
	@Column
	private Integer totalPrice;
	//收件地址
	@Column
	private String shippingAddress;
	//下單日期
	@Column
	private String orderDate;
	//狀態(已下單未付款、已發貨、已付款、已取消)
	@Column
	private String status;
	//發貨日期
	@Column
	private String shippingDate;
	//訂單是否被使用者取消
	@Column
	private boolean cancelFlag; 
	//優惠券代碼
	@Column
	private String coupon; 
	@Column
	private Integer shippingFee;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order",cascade = CascadeType.ALL)
	Set<OrderItem> orderItemList = new LinkedHashSet<>();

	public Order() {
	}

	public Order(String memberAccount, Integer totalPrice, String shippingAddress, String orderDate, String status,
			String shippingDate, boolean cancelFlag, String coupon, Set<OrderItem> orderItemList, Integer shippingFee) {
		this.memberAccount = memberAccount;
		this.totalPrice = totalPrice;
		this.shippingAddress = shippingAddress;
		this.orderDate = orderDate;
		this.status = status;
		this.shippingDate = shippingDate;
		this.cancelFlag = cancelFlag;
		this.coupon = coupon;
		this.orderItemList = orderItemList;
		this.shippingFee = shippingFee;
	}

	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getMemberAccount() {
		return memberAccount;
	}


	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getShippingDate() {
		return shippingDate;
	}


	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}


	public boolean isCancelFlag() {
		return cancelFlag;
	}


	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}


	public String getCoupon() {
		return coupon;
	}


	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}


	public Set<OrderItem> getOrderItemList() {
		return orderItemList;
	}


	public void setOrderItemList(Set<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public Integer getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(Integer shippingFee) {
		this.shippingFee = shippingFee;
	}
	
	
	
}
