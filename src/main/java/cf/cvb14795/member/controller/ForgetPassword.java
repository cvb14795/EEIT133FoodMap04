package cf.cvb14795.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.azure.core.annotation.QueryParam;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.dao.IMemberService;
import util.gmail.Mail;

//@WebServlet("/Member/ForgetPassword")
@Controller
@RequestMapping("/Member")
public class ForgetPassword {
	private String prefix = "Member/";
	private String currentLoginUserAccount;

	@Autowired
	IMemberService mService;

	public ForgetPassword() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("ForgetPassword")
	private String forgetPasswordPage(
			Model model,
			@RequestParam(required = false, value = "method") String method,
			@QueryParam("token") String token,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(method);
		// 不能用== 要用equals
		if (method.equals("reset")) {
			boolean isAccountVaild = BCrypt.checkpw(currentLoginUserAccount, token);
			if (isAccountVaild) {
				System.out.println("Ｏreset: 帳號驗證成功!");
				model.addAttribute("user", currentLoginUserAccount);
				return prefix + "resetPassword";
			} else {
				System.out.println("＊reset: 帳號驗證失敗!");
				return "redirect:/Home";
			}
		} else {
//			request.getRequestDispatcher("./forgetPassword.html").forward(request, response);			
			return prefix + "forgetPassword";
		}
	}

	@PostMapping("ForgetPassword")
	private ResponseEntity<HttpStatus> doForgetPassword(
			@RequestParam("account") String account,
			@RequestParam("email") String recipientEmail,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO Auto-generated method stub

		/** Authorizes the installed application to access user's protected data. */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

//		String userAccount = request.getParameter("account");
		// 收件者(用戶輸入之email)
//		String recipientEmail = request.getParameter("email");
		
		System.out.println("正在確認註冊時信箱是否正確...");
		Member m = mService.selectMemberByAccount(account);
		
		boolean isAccountCorrect = m.getAccount().equals(account);
		boolean isEmailCorrect = m.getEmail().equals(recipientEmail);
		System.out.println("isAccountCorrect: "+isAccountCorrect);
		System.out.println("isEmailCorrect: "+isEmailCorrect);
		if (isAccountCorrect && isEmailCorrect) {
			currentLoginUserAccount = m.getAccount();
			// 郵件主旨
			String subject = "FoodMap美食地圖——重設密碼認證信";
			// 將帳號作為參數寫進驗證信的URL 供重設密碼時核對用戶是誰 
			String token = BCrypt.hashpw(currentLoginUserAccount, BCrypt.gensalt(10));
			// 郵件內容
			/* 參考耿豪那邊的用參數拚網址*/
			String text = "您好，" + currentLoginUserAccount + "！ <br/>"
					+ "<a href='http://localhost:8080/FoodMap04/Member/ForgetPassword?"
					+ "method=reset&token='" + token 
					+">請點擊以下連結修改您的密碼</a>";
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
	
	@PostMapping("resetPassword")
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
