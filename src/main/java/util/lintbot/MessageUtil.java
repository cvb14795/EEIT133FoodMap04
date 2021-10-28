package util.lintbot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.model.response.BotApiResponse;

/**
 * @author cvb14795
 *
 */
public class MessageUtil {
	final static String CHANNEL_ACCESS_TOKEN = "URD9rRscqtskuIOeyH3gE34kr2M9BlYNsIl89uQzXnKbwxOC8VgGuxfTOINwJ+/K8W24tD1kDIsfel9+c57WZSUvyV4mcV3V32xWjeCE4te4peIp3YbTfnenLp2+CDieTt8WM8bQ/oeV+0IRxc7HbQdB04t89/1O/w1cDnyilFU=";
	final static LineMessagingClient client = LineMessagingClient
			.builder(CHANNEL_ACCESS_TOKEN)
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
