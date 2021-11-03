//package cf.cvb14795.cart.model;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Set;
//public class ShoppingCart {   
//	Date creteTime ;
//	private Map<Long, OrderItemBean> cart = new LinkedHashMap< >();
//	
//	public ShoppingCart() {
//		creteTime = new Date();
//	}
//	
//	public String getCreteTime() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//		return sdf.format(creteTime);
//	}
//
//	public void setCreteTime(Date creteTime) {
//		this.creteTime = creteTime;
//	}
//
//	public Map<Long, OrderItemBean>  getContent() { // ${ShoppingCart.content}
//		return cart;
//	}
//	public void addToCart(long bookId, OrderItemBean  oib) {
//		if (oib.getQuantity() <= 0 ) {
//			return;
//		}
//		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
//		if ( cart.get(bookId) == null ) {
//		    cart.put(bookId, oib);
//		} else {
//	        // 如果客戶在伺服器端已有此項商品的資料，則客戶『加購』此項商品
//			OrderItemBean oiBean = cart.get(bookId);
//			// 加購的數量：bean.getQuantity()
//			// 原有的數量：oBean.getQuantity()			
//			oiBean.setQuantity(oib.getQuantity() + oiBean.getQuantity());
//		}
//	}
//
//	public boolean modifyQty(long bookId, int newQty) {
//		if ( cart.get(bookId) != null ) {
//		   OrderItemBean  bean = cart.get(bookId);
//		   bean.setQuantity(newQty);
//	       return true;
//		} else {
//		   return false;
//		}
//	}
//	// 刪除某項商品
//	public int deleteBook(long bookId) {
//		if ( cart.get(bookId) != null ) {
//	       cart.remove(bookId);  // Map介面的remove()方法
//	       return 1;
//		} else {
//		   return 0;
//		}
//	}
//	public int getItemNumber(){   // ShoppingCart.itemNumber
//		return cart.size();
//	}
//	//計算購物車內所有商品的合計金額(每項商品的單價*數量的總和)
//	public double getSubtotal(){
//		double subTotal = 0 ;
//		Set<Long> set = cart.keySet();
//		for(long n : set){
//			OrderItemBean oib = cart.get(n);
//			double price    = oib.getBean().getPrice();
//			double discount = oib.getDiscount();
//			int qty      = oib.getQuantity();
//			subTotal +=  price * discount * qty;
//		}
//		return subTotal;
//	}
//
//	@Override
//	public String toString() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//		String creteTimeString = sdf.format(creteTime);
//		return "ShoppingCart [creteTime=" + creteTimeString + ", hashCode=" + hashCode() + "]";
//	}
//	
//}
