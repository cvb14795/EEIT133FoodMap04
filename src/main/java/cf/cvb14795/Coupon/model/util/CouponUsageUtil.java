package cf.cvb14795.Coupon.model.util;

import java.util.Optional;

import cf.cvb14795.member.bean.Member;
import util.gmail.Mail;

public class CouponUsageUtil {
    // u.getAuthority(): localhost:8080
    // request.getContextPath(): /FoodMap04
    private String baseUrl;
    
    
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public boolean sendMail(Optional<Member> member, String couponCode) {
        if (member.isPresent()) {
            Member m = member.get();
        	// 寄件者(自己)
            String from = "me";
            // 收件者(使用者Email)
            String to = m.getEmail();
            String subject = "FoodMap美食地圖——發送優惠通知信";
            String url = baseUrl+"/Coupon/use?code="+couponCode;
            // 郵件內文的優惠券超連結
            String href = String.format("<a href=%s>點擊這裡</a>", url);
            // 郵件內文
            String text = String.format("您好，%s<br/>這是您的優惠券代碼: %s<br/>請%s或以下連結以使用該優惠券:<br/>%s",
            		m.getAccount(), couponCode, href, url);
            Mail.SendGmail(from, to, subject, text);
            System.out.println("Ｏ發送優惠券：成功! 收件者:" + to);
            return true;
        } else {
            System.out.println("＊發送優惠券：失敗! 找不到該會員!");
            return false;
        }
    }

    public String generateCouponCode(int degit) {
        // 以指定位數亂數生成優惠券代碼
        // 將亂數產生之double轉int以捨去小數點後數字 
        return String.valueOf((int)(Math.random() * Math.pow(10, degit)));
    }

    public CouponUsageUtil(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public CouponUsageUtil() {
    }
}