package util.gmail;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class Mail {
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * @param from 寄件者(預設為userAccount)
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
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("郵件寄送失敗! 訊息:"+message);
		}


	}

}
