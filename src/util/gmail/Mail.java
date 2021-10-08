package util.gmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private static String userAccount = "foodmap04@gmail.com";
	private static String userPassword = "food04map!";
	
	/**
	 * @param from 寄件者(預設為userAccount)
	 * @param to 收件者
	 * @param subject 主旨
	 * @param text 內文(可包含HTML元素)
	 */
	public static void SendGmail(String from, String to, String subject, String text) {
		String host = "smtp.gmail.com";

		Properties props = new Properties();

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
//		props.put("mail.debug", "true");
		

		try {
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userAccount, userPassword);
				}
			});
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
//			message.setText(text);
			
			// 傳送含有html標籤的訊息
			message.setContent(text, "text/html;charset=utf-8");

			// Send message
			Transport.send(message);

			System.out.println("郵件寄送成功!");

		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
