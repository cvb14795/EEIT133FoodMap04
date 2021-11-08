package cf.cvb14795.shop.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
public class ShoppingCart {   
	Date createTime ;
	private Map<Integer, OrderItem> cart = new LinkedHashMap< >();
	
	public ShoppingCart() {
		createTime = new Date();
	}
	
	public String getCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(createTime);
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Map<Integer, OrderItem> getCart() { 
		return cart;
	}
	public void addToCart(Integer itemId, OrderItem  orderItem) {
		if (orderItem.getQty() <= 0 ) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if ( cart.get(itemId) == null ) {
		    cart.put(itemId, orderItem);
		} else {
	        // 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
			OrderItem newOrderItem = cart.get(itemId);
			// 加購的數量：bean.getQuantity()
			// 原有的數量：oBean.getQuantity()			
			newOrderItem.setQty(orderItem.getQty() + newOrderItem.getQty());
		}
	}

	public boolean modifyQty(Integer itemId, int newQty) {
		if ( cart.get(itemId) != null ) {
		   OrderItem  orderItem = cart.get(itemId);
		   orderItem.setQty(newQty);
	       return true;
		} else {
		   return false;
		}
	}
	// 刪除某項商品
	public int deleteBook(Integer itemId) {
		if ( cart.get(itemId) != null ) {
	       cart.remove(itemId);  // Map介面的remove()方法
	       return 1;
		} else {
		   return 0;
		}
	}
	public int getItemNumber(){   // ShoppingCart.itemNumber
		return cart.size();
	}
	//計算購物車內所有商品的合計金額(每項商品的單價*數量的總和)
	public double getSubtotal(){
		double subTotal = 0 ;
		Set<Integer> set = cart.keySet();
		for(int n : set){
			OrderItem orderItem = cart.get(n);
			int price    = orderItem.getItem().getPrice();
			double discount = orderItem.getDiscount();
			int qty      = orderItem.getQty();
			subTotal +=  price * discount * qty;
		}
		return subTotal;
	}

	@Override
	public String toString() {
		String creteTimeString = getCreateTime();
		return "ShoppingCart [creteTime=" + creteTimeString + ", hashCode=" + hashCode() + "]";
	}
	
}
