package util.ecpay;

import java.util.Hashtable;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.QueryTradeInfoObj;
import ecpay.payment.integration.exception.EcpayException;

public class OrderStatusUtil {
	private AllInOne all;

	private QueryTradeInfoObj queryTradeInfoObj = new QueryTradeInfoObj();
	private AioCheckOutALL checkOutObj = new AioCheckOutALL();
	

	public boolean checkMacValue(Hashtable<String, String> dict) {
		return all.compareCheckMacValue(dict);
	}
	
	public String getECPayForm() {
		String form;
		try {
			form = all.aioCheckOut(checkOutObj, null);
		} catch (EcpayException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			form = "交易錯誤，請重新嘗試!";
		}
		return form;
	}
	
	public AllInOne getAll() {
		return all;
	}
	
	public void setAll(AllInOne all) {
		this.all = all;
	}

	public String queryTradeInfo() {
		return all.queryTradeInfo(queryTradeInfoObj);
	}

	public AioCheckOutALL getCheckOutObj() {
		return checkOutObj;
	}
	
	public void setCheckOutObj(AioCheckOutALL checkOutObj) {
		this.checkOutObj = checkOutObj;
	}
	
	public QueryTradeInfoObj getQueryTradeInfoObj() {
		return queryTradeInfoObj;
	}

	public void setQueryTradeInfoObj(QueryTradeInfoObj queryTradeInfoObj) {
		this.queryTradeInfoObj = queryTradeInfoObj;
	}

	public OrderStatusUtil(AllInOne all) {
		this.all = all;
		queryTradeInfoObj.setMerchantTradeNo(checkOutObj.getMerchantTradeNo());
	}
	
	public OrderStatusUtil() {
		
	}
	
}
