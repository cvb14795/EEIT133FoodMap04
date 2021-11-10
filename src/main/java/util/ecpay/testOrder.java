package util.ecpay;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.QueryTradeInfoObj;

public class testOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderStatusUtil orderStatus = new OrderStatusUtil(new AllInOne(""));
		QueryTradeInfoObj infoObj = new QueryTradeInfoObj();
		infoObj.setMerchantID("2000132");
		infoObj.setMerchantTradeNo("EEIT1331636517946286");
//		infoObj.setMerchantTradeNo("item1636034635409");
		orderStatus.setQueryTradeInfoObj(infoObj);
		String[] params = orderStatus.queryTradeInfo().split("&");
		for (String param : params) {
			if (param.split("=").length == 1) {
				System.out.println(param.split("=")[0]+"=無資料");
				continue;
			}
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			System.out.println(name + ":" + value);
//			switch (name) {
//			case "PaymentDate":
//				System.out.println("付款日期:　"+value);
//				break;
//
//			case "TradeDate":
//				System.out.println("交易發生日期: "+value);
//				break;
//			}
			
			
		}
	}

}
