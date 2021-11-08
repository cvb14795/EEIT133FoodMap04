package util.ecpay;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.QueryTradeInfoObj;

public class testOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderStatusUtil orderStatus = new OrderStatusUtil(new AllInOne(""));
		QueryTradeInfoObj infoObj = new QueryTradeInfoObj();
		infoObj.setMerchantID("2000132");
//		infoObj.setMerchantTradeNo("t12341636030858216");
		infoObj.setMerchantTradeNo("item1636034635409");
		orderStatus.setQueryTradeInfoObj(infoObj);
		System.out.println(orderStatus.queryTradeInfo());
	}

}
