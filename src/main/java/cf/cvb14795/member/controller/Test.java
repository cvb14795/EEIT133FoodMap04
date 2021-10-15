package cf.cvb14795.member.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//
//@WebServlet(urlPatterns = { "/Member/Login" },
//			initParams = {
//					@WebInitParam(name = "loginPage", value = "./loginForm.html"),
//			}
//)
@Controller
@RequestMapping("/test")
public class Test {
	
	@PostMapping(path = "/upload")
	@ResponseBody
	public String processFileUploadAction(
			@RequestParam("myFile") MultipartFile multipartFile)
			throws IllegalStateException, IOException {
		String fileName = multipartFile.getOriginalFilename();
		System.out.println("fileName:" + fileName);
		String saveDirPath = "D:/git/static/";
//		String saveDirPath = "10.120.40.40/Shared";
		File savefileDir = new File(saveDirPath);
		savefileDir.mkdirs();
		File saveFilePath = new File(savefileDir + "/" +fileName);
		multipartFile.transferTo(saveFilePath);
		System.out.println("saveFilePath:" + saveFilePath);
		return "Success";
	}
	
//	@GetMapping(path = "/upload")
//	@ResponseBody
//	public String uploadPage() {
//		return "test";
//	}
	
}
