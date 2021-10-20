package cf.cvb14795.member.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.azure.core.annotation.QueryParam;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.dao.IMemberService;
import util.gmail.Mail;

@Controller
@RequestMapping("/Member")
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes("user")
public class ForgetPassword {
	private String prefix = "Member/";
	
	@Autowired
	IMemberService mService;
	
	public ForgetPassword() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("ForgetPassword")
	private String forgetPasswordPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");		
		
		return prefix + "forgetPassword";
	}

	@PostMapping("ForgetPassword")
	private ResponseEntity<HttpStatus> doForgetPassword(
			Model model,
			@RequestParam("account") String inputAccount,
			@RequestParam("email") String recipientEmail,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		/** Authorizes the installed application to access user's protected data. */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

//		String userAccount = request.getParameter("account");
		// 收件者(用戶輸入之email)
//		String recipientEmail = request.getParameter("email");
		
		System.out.println("正在確認註冊時信箱是否正確...");
		Member m = mService.selectMemberByAccount(inputAccount);
		String userAccount = m.getAccount();
		System.out.println("=====ForgetPassword=====");
		System.out.println("使用者輸入帳號:"+inputAccount);
		System.out.println("資料庫用戶帳號:"+userAccount);
		boolean isAccountCorrect = m.getAccount().equals(inputAccount);
		boolean isEmailCorrect = m.getEmail().equals(recipientEmail);
		System.out.println("isAccountCorrect: "+isAccountCorrect);
		System.out.println("isEmailCorrect: "+isEmailCorrect);
		// 判斷使用者帳號與使用者email是否與資料庫相符
		if (isAccountCorrect && isEmailCorrect) {
			model.addAttribute("user", userAccount);
			// 郵件主旨
			String subject = "FoodMap美食地圖——重設密碼認證信";
			// 將帳號作為參數寫進驗證信的URL 供重設密碼時核對用戶是誰 
			String hash = BCrypt.hashpw(userAccount, BCrypt.gensalt(10));
			// unix timestamp (GMT-0)
			String expireDateStr = String.valueOf(new Date().getTime()/1000);
			String token = Base64.getEncoder().encodeToString(
				StringUtils.join(new String[]{hash, expireDateStr}, "-")
				.getBytes()
			);
			/* =====拼出請求路徑===== */
			URL u = new URL(request.getRequestURL().toString());
			// 由u的完整路徑拼出前綴路徑
			StringBuilder sb = new StringBuilder();
			// u.getProtocol(): "http" or "https"
			sb.append(u.getProtocol()+"://");
			// u.getAuthority(): localhost:8080
			sb.append(u.getAuthority());
			// request.getContextPath(): /FoodMap04
			sb.append(request.getContextPath());
			String baseUrl = sb.toString();
			String url = baseUrl + "/Member/ResetPassword";
			
			// 郵件內容
			String text = String.format(
					"您好，%s！ <br/>"
					+ "<a href='%s?token=%s'>" 
					+"請點擊以下連結修改您的密碼</a>",
					userAccount, url, token);
			// 寄信
			Mail.SendGmail("me", recipientEmail, subject, text);
			System.out.println("送出成功");
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			// 找不到該用戶
//				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("ResetPassword")
	private String resetPasswordPage(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("user") String user,
			@QueryParam("token") String token) throws IOException {
			
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("=====ResetPassword=====");
		System.out.println("user(textplain): "+user);
		// 字串不能用== 要用equals
		if (token != "" && !user.equals("")){
			byte[] bytes = Base64.getDecoder().decode(token);
			// [0]: user
			// [1]: token過期時間
			String[] tokenArray = new String(bytes, StandardCharsets.UTF_8).split("-");
			System.out.println("user(encrypt):"+tokenArray[0]);
			System.out.println("expire date:"+tokenArray[1]);
			boolean isAccountVaild = BCrypt.checkpw(user, tokenArray[0]);
			// 過期時間大於現在時間即表示該token已過期
			boolean isExpired =  new Date().getTime() > Long.valueOf(tokenArray[1]);
			System.out.println("isAccountVaild:"+isAccountVaild);
			System.out.println("isExpired:"+isExpired);
			if (isAccountVaild && isExpired) {
				System.out.println("Ｏreset: 帳號驗證成功!");
				return prefix + "resetPassword";
			} else {
				System.out.println("＊reset: 帳號驗證失敗!");
				return "redirect:/Home";
			}
		} else {
			// token不合法或無user名
			response.getWriter().write("<p>驗證失敗！<br/>可能是您已進行過密碼變更，或該Email連結已過期！<p>");
			return null;
		}
	}
	
	@PostMapping("ResetPassword")
	@ResponseBody
	private ResponseEntity<String> doResetPassword(
			@ModelAttribute("user") String userAccount,  
			@RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

		/** Authorizes the installed application to access user's protected data. */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
		Member m = mService.selectMemberByAccount(userAccount);
		boolean isSuccess = mService.updateMemberPassword(hashedPassword, m);
		if (isSuccess) {
			return new ResponseEntity<String>(HttpStatus.OK);			
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

}
