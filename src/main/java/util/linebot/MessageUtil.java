package util.linebot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.response.BotApiResponse;

import cf.cvb14795.FoodMap04Application;

/**
 * @author cvb14795
 *
 */
public class MessageUtil {
	final static LineMessagingClient client = LineMessagingClient
			.builder(FoodMap04Application.getChannelToken())
			.build();
	
	public static void reply(String message, String replyToken) {

		final TextMessage textMessage = new TextMessage(message);
		final ReplyMessage replyMessage = new ReplyMessage(replyToken, textMessage);

		final BotApiResponse botApiResponse;
		try {
		    botApiResponse = client.replyMessage(replyMessage).get();
		} catch (InterruptedException | ExecutionException e) {
		    e.printStackTrace();
		    return;
		}

		System.out.println(botApiResponse);
	}
	
	public static Map<String, String> getProfile(String userId) {
		final UserProfileResponse userProfileResponse;
		Map<String, String> result = new HashMap<>();
		try {
		    userProfileResponse = client.getProfile(userId).get();
		    result.put("UserId", userProfileResponse.getUserId());
		    result.put("DisplayName", userProfileResponse.getDisplayName());
		    result.put("PictureUrl", userProfileResponse.getPictureUrl().toString());
		} catch (InterruptedException | ExecutionException e) {
		    e.printStackTrace();
		    return null;
		}

		System.out.println("UserId: "+userProfileResponse.getUserId());
		System.out.println("DisplayName: "+userProfileResponse.getDisplayName());
		System.out.println("PictureUrl: "+userProfileResponse.getPictureUrl());
		return result;
	}
}
