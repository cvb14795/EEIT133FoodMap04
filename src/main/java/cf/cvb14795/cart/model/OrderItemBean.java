package cf.cvb14795.cart.model;

//本類別封裝單筆訂單資料
public class OrderItemBean {
	Integer seqno;
	BookBean bean;
	Integer quantity;
	Double discount = 1.0;

	public OrderItemBean(Integer seqno, BookBean bean, Integer quantity, Double discount) {
		this.seqno = seqno;
		this.bean = bean;
		this.quantity = quantity;
		this.discount = discount;
	}
	
	public OrderItemBean() {
	}

	public Integer getSeqno() {
		return seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	public BookBean getBean() {
		return bean;
	}

	public void setBean(BookBean bean) {
		this.bean = bean;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}