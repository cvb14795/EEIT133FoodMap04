package util.gmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class Mail {
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.password}")
	private String pwd;
	/**
	 * @param to 收件者
	 * @param subject 主旨
	 * @param text 內文(可包含HTML元素)
	 */
	public void SendGmail(String to, String subject, String text, boolean isHtmlText) {
		
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper =  new MimeMessageHelper(message, isHtmlText);
			helper.setTo(to);  
			// Set Subject: header field
			helper.setSubject(subject);
			helper.setText(text);
			// 傳送含有html標籤的訊息
			mailSender.send(message);
			System.out.println("郵件寄送成功! 訊息:"+message);
			System.out.println(pwd);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("郵件寄送失敗! 訊息:"+message);
		}


	}

}
