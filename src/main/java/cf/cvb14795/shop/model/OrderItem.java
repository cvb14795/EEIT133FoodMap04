package cf.cvb14795.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author cvb14795
 *
 */
//訂單明細
//單件商品(某一件貨)->*訂單明細(貨*數量-折扣)*->單筆訂單(使用者)
@Entity
@Table(name = "orderItem")
public class OrderItem {	
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//流水號
	private Integer rowNum;
	//數量
	@Column
	private Integer qty;
	//小計
	@Column
	private Double subTotal;	
	@Transient
	private Item item;
	@Column
	private Double discount = 1.0;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="orderId")
	private Order order;
	
	
	public OrderItem(Integer qty, Double subTotal, Item item, Double discount, Order order) {
		this.qty = qty;
		this.subTotal = subTotal;
		this.item = item;
		this.discount = discount;
		this.order = order;
	}

	public OrderItem() {
	}

	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
}
