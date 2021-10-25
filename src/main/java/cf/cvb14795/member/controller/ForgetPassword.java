package cf.cvb14795.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.azure.core.annotation.QueryParam;
import com.google.gson.Gson;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.dao.IMemberService;
import util.gmail.Mail;

@Controller
@RequestMapping("/Member")
public class ForgetPassword {
	private String prefix = "Member/";
	private String userAccount;
	
	@Autowired
	IMemberService mService;
	
	public ForgetPassword() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("ForgetPassword")
	private String forgetPasswordPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");		
		
		return prefix + "forgetPassword";
	}

	@PostMapping("ForgetPassword")
	private ResponseEntity<String> doForgetPassword(
			Model model,
			@RequestParam("account") String inputAccount,
			@RequestParam("email") String recipientEmail,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		/** Authorizes the installed application to access user's protected data. */
		String message;
		HttpHeaders responseHeaders = new HttpHeaders();
		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");

		System.out.println("正在確認註冊時信箱是否正確...");
		Member m = mService.selectMemberByAccount(inputAccount);
		String dbAccount = m.getAccount();
		System.out.println("=====ForgetPassword=====");
		System.out.println("使用者輸入帳號:"+inputAccount);
		System.out.println("資料庫用戶帳號:"+dbAccount);
		userAccount = inputAccount;
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
			// 現在時間(單位:秒)
			long now = new Date().getTime()/1000; 
			// unix timestamp (GMT-0) (單位:秒)
			String expireDurationStr = String.valueOf(
					now + TimeUnit.SECONDS.convert(1L, TimeUnit.HOURS));
			String token = Base64.getEncoder().encodeToString(
				StringUtils.join(new String[]{hash, expireDurationStr}, "-")
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
					+"請點擊以下連結修改您的密碼</a>"
					+ "<br/>有效期限為1小時，若過期請再重新接收新的驗證信!",
					userAccount, url, token);
			// 寄信
			Mail.SendGmail("me", recipientEmail, subject, text);
			System.out.println("送出成功");
			message = "送出成功";
			responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			responseHeaders.add("Content-Type", "application/json; charset=utf-8");
			return ResponseEntity.ok()
				      .headers(responseHeaders)
				      .body(new Gson().toJson(message));
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
		PrintWriter out = response.getWriter();
		System.out.println("=====ResetPassword=====");
		
		
		// 字串不能用== 要用equals
		if (token != "" && token != null){
			byte[] bytes = Base64.getDecoder().decode(token);
			// [0]: user
			// [1]: token過期時間 (單位:秒)
			String[] tokenArray = new String(bytes, StandardCharsets.UTF_8).split("-");
			System.out.println("user(encrypt):"+tokenArray[0]);
			System.out.println("expire date:"+tokenArray[1]);
			if (user.equals("")) {
				user = userAccount;
			}
			System.out.println("user(textplain): "+user);
			boolean isAccountVaild = BCrypt.checkpw(user, tokenArray[0]);
			// 獲取當前時間 (單位:毫秒)
			Date now = new Date();
			Date expiredDate = new Date(Long.valueOf(tokenArray[1])*1000);
			// 現在時間較過期時間晚 即表示該token已過期
			boolean isExpired =  now.after(expiredDate);
			System.out.println("isAccountVaild:"+isAccountVaild);
			System.out.println("isExpired:"+isExpired);
			System.out.println("驗證時間:"+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(now));
			System.out.println("過期時間:"+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(expiredDate));
			if (isAccountVaild && !isExpired) {
				System.out.println("Ｏreset: 帳號驗證成功!");
				return prefix + "resetPassword";
			} else {
				System.out.println("＊reset: 帳號驗證失敗!");
				// token不合法或無user名
				out.write("<p>驗證失敗！<br/>可能是您已進行過密碼變更，或該Email連結已過期！<p>");
				return null;
			}
		} else {
			// token不合法或無user名
			out.write("<p>驗證失敗！<br/>可能是您已進行過密碼變更，或該Email連結已過期！<p>");
			return null;
		}
	}
	
	@PostMapping("ResetPassword")
	@ResponseBody
	private ResponseEntity<String> doResetPassword(
			@ModelAttribute("user") String user,  
			@RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		/** Authorizes the installed application to access user's protected data. */
		String message;
		HttpHeaders responseHeaders = new HttpHeaders();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
		System.out.println("==="+user+"====");
		Member m = mService.selectMemberByAccount(user);
		boolean isSuccess = mService.updateMemberPassword(hashedPassword, m);
		if (isSuccess) {
			message = "修改成功!!!";
			System.out.println(message);
			responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			responseHeaders.add("Content-Type", "application/json; charset=utf-8");
			return ResponseEntity.ok()
				      .headers(responseHeaders)
				      .body(new Gson().toJson(message));		
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpSessionRequiredException.class)
	public String ExceptionHandler(
			HttpSessionRequiredException ex,
			HttpServletRequest request){
        String msg = "Error: "+ex.getMessage()+"\n偵測到使用者尚未登入，將初始化user!";
		System.out.println(msg);
        // 初始化user
        request.getSession().setAttribute("user", userAccount);
        return "redirect:/Member/ResetPassword";
    }

}
