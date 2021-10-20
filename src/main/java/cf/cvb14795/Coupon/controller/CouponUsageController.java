package cf.cvb14795.Coupon.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Coupon")
public class CouponUsageController {
       
    public CouponUsageController() {
        super();
        // TODO Auto-generated constructor stub
    }

   @GetMapping(value = "/use",produces = "text/html; charset=UTF-8")
   @ResponseBody
	protected String couponUsage(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("code") String couponCode
			) throws IOException {
		return "<h1>測試中待完工...</h1><br/>您的優惠券代碼:"+couponCode;
	}
}
