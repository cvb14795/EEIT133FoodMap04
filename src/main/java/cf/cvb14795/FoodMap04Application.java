package cf.cvb14795;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class FoodMap04Application extends SpringBootServletInitializer{

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FoodMap04Application.class);
	}
	
	@Value("${line.bot.channel-token}")
	private static String channelToken;
	
 	private static final String liffId = "1656572191-Pz4yxOWO";

	public static void main(String[] args) {
		SpringApplication.run(FoodMap04Application.class, args);
	}

	public static String getChannelToken() {
		return channelToken;
	}

	public static String getLiffId() {
		return liffId;
	}
	
	/*
	 * 使用以下line-bot SDK則無法使用dialogflow對line的webhook支援 
	 * 因為在messaging-api的webhook-setting只能server端(SDK方法擷取與回應訊息)與dialogflow端二選一
	 * 若想使用dialogflow回應訊息則需將以下程式碼註解
	 * */
//	@EventMapping
//	public TextMessage handleTextMessageEvent(
//			@LineBotDestination String destination,
//			MessageEvent<TextMessageContent> event) {
//		// 這邊做的就是簡單的 echo
//		System.out.println("event: " + event);
//		System.out.println(destination);
//	    return new TextMessage(event.getMessage().getText());
//	}
//
//	@EventMapping
//	public void handleDefaultMessageEvent(Event event) {
//		// 就是加入聊天室, 離開聊天室, 還有一些有的沒的事件
//	    System.out.println("event: " + event);
//	}
//
//	public void post(HttpServletRequest request) throws LineBotCallbackException, IOException {
//        LineSignatureValidator validator = new LineSignatureValidator(CHANNEL_ACCESS_TOKEN.getBytes());
//        LineBotCallbackRequestParser parser = new LineBotCallbackRequestParser(validator);
//        CallbackRequest callbackRequest = parser.handle(request);
//        //獲取該對話之用戶ID
//        System.out.println(callbackRequest.getDestination());
//	}
	
}
