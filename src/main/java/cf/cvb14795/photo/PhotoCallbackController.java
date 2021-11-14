package cf.cvb14795.photo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/photo")
public class PhotoCallbackController {
	
	
	@Autowired
	public PhotoCallbackController() {
	}
	
	@PostMapping("/callback")
    @ResponseBody
    public String getPicture(
    		@RequestBody String body) {
		JSONObject obj = new JSONObject(body);
		String uploadUrl = "";
		System.out.println(obj.toString(4));
		switch (obj.getString("notification_type")) {
		case "upload":
			uploadUrl = obj.getString("url");
			System.out.println(uploadUrl);
			break;
		}
        return body;
	}
}
