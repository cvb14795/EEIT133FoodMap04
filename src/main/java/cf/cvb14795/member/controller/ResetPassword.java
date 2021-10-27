package cf.cvb14795.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.azure.core.annotation.QueryParam;
import com.google.gson.Gson;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.service.IMemberService;

@Controller
@RequestMapping("/Member")
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes("user")
public class ResetPassword {
	private String prefix = "Member/";
	
	@Autowired
	IMemberService mService;
	
	public ResetPassword() {
		// TODO Auto-generated constructor stub
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
		System.out.println("user(textplain): "+user);
		// 字串不能用== 要用equals
		if (token != "" && token != null && !user.equals("")){
			byte[] bytes = Base64.getDecoder().decode(token);
			// [0]: user
			// [1]: token過期時間 (單位:秒)
			String[] tokenArray = new String(bytes, StandardCharsets.UTF_8).split("-");
			System.out.println("user(encrypt):"+tokenArray[0]);
			System.out.println("expire date:"+tokenArray[1]);
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
		Optional<Member> m = mService.selectMemberByAccount(user);
		if (m.isPresent()) {
			//修改密碼
			m.get().setPassword(hashedPassword);
			//更新資料庫
			mService.updateMember(m.get());
			message = "修改成功!!!";
			System.out.println(message);
			responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			responseHeaders.add("Content-Type", "application/json; charset=utf-8");
			return ResponseEntity.ok()
				      .headers(responseHeaders)
				      .body(new Gson().toJson(message));		
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);			
		}
	}

	@ExceptionHandler(HttpSessionRequiredException.class)
	public String ExceptionHandler(
			HttpSessionRequiredException ex,
			HttpServletRequest request){
        String msg = "Error: "+ex.getMessage()+"\n偵測到使用者尚未登入，將初始化user為空值!";
		System.out.println(msg);
        // 初始化user
        request.getSession().setAttribute("user", "");
        return "redirect:/Member/ResetPassword";
    }
}

