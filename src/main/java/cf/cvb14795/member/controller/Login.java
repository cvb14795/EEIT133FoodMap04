package cf.cvb14795.member.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.service.IMemberService;
import util.MemberStatus;

@Controller
@RequestMapping("/Member")
public class Login {
	private String prefix = "Member/";
	// 從cookie獲取已登入的使用者名稱
	private String userName = "";
	private boolean isAlreadyLogin = false;
	// 記得不要用class 用interface
	// 否則會出現but was actually of type 'com.sun.proxy.$Proxy48'

	IMemberService mService;
	
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public Login(IMemberService mService) {
		this.mService = mService;
	}

	@GetMapping("Login")
	private String memberLogin(HttpServletRequest request,
							   HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 確認登入紀錄
		Cookie[] cookies = request.getCookies();
		// 使用者是否已有登入紀錄(cookie)
		MemberStatus status = new MemberStatus(cookies);
		userName = status.getCurrentUserAccount();
		isAlreadyLogin = status.getLoginStatus();
		System.out.println("\nuser: " + userName);
		System.out.println("isAlreadyLogin: " + isAlreadyLogin);
		if (!isAlreadyLogin) {
			// 沒有登入紀錄 導向登入頁面
//			request.getRequestDispatcher(loginPage).forward(request, response);
			return prefix+"loginForm";
		}
		// 有登入紀錄 回到首頁
		return "redirect:/Home";
	}
	
	
	
	@PostMapping(value = "Login", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private ResponseEntity<String> doLogin(
			@RequestParam("account") String userAccount,
			@RequestParam("password") String userPassword,
			HttpServletRequest request,
			HttpServletResponse response){
		
		String message = "登入帳號:"+userAccount;
		HttpHeaders responseHeaders = new HttpHeaders();
		try {
			request.setCharacterEncoding("UTF-8");
//			response.setContentType("application/json; charset=UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		userName = request.getParameter("account");
		userName = userAccount;
//		Member m = mDAO.findAllByAccount(userName);
		Optional<Member> m = mService.selectMemberByAccount(userName);
//		String userPassword = request.getParameter("password");
		System.out.println("正在驗證使用者:" + userName + "的登入...");
		if (m.isPresent()) {
			// 撈資料庫傳回的加密後密碼
			String hashPassword = m.get().getPassword();
			boolean checkpw = BCrypt.checkpw(userPassword, hashPassword);

			if (checkpw) {
				Cookie cookie = new Cookie("user", userName);
				// 使全站(localhost下所有子路徑)都可以存取此路徑的cookie 而不只是原本限制的/Member底下
				cookie.setPath("/");
//					session.setAttribute("user", cookie.getValue());
				/* ==========不設置maxAge 則默認為-1（cookie直到關閉瀏覽器才會消失）=========== */
//					cookie.setMaxAge(30 * 60); //30分鐘內有效
				/* ====================== */
				response.addCookie(cookie);

				isAlreadyLogin = true;

				responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				responseHeaders.add("Content-Type", "application/json; charset=utf-8");
				return ResponseEntity.ok()
					      .headers(responseHeaders)
					      .body(new Gson().toJson(message));
				
			} else {
				// 密碼錯誤
				// 改成JSON回傳 前端login接收並alert
				System.out.println("密碼錯誤!");
//					request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
//				response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			// 找不到此帳號
			// 改成JSON回傳 前端login接收並alert
			System.out.println("無此帳號!");
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}	
	
	@GetMapping("Logout")
	private String doGet(
			HttpSession session,
			HttpServletResponse response,
			SessionStatus sessionStatus){
		// TODO Auto-generated method stub
		
		// 清除session旗下的attribute以及@SessionAttributes內的session
		session.removeAttribute("user");
        session.removeAttribute("isAdmin");
        sessionStatus.setComplete();
		// 登出 清空session
		session.invalidate();
		// 清空使用者名稱
		Cookie cookie = new Cookie("user", "");
		// cookie是屬於某個path及domain的 因此也要設定path才會使該名稱的cookie被刪除 
		// 而domain皆預設為該網域及其子網域 所以不用動
		cookie.setPath("/");
		// 使cookie立即失效
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return prefix+"goodbyeUser";
	}
	
}
