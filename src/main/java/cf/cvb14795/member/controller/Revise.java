package cf.cvb14795.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.dao.IMemberService;
import util.MemberStatus;

/**
 * Servlet implementation class Revise
 */
//@MultipartConfig
//@WebServlet(name = "Member/Revise", urlPatterns = { "/Member/Revise" })
@Controller
@RequestMapping("/Member")
public class Revise {
	private String prefix = "Member/";
	private Cookie[] cookies;
	private String userAccount="";
	
	@Autowired
	IMemberService mService;

	public Revise() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GetMapping("Revise")
	private String revisePage(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response){
		response.setContentType("text/html; charset=UTF-8");
//		HttpSession httpSession = request.getSession();
		cookies = request.getCookies();
		System.out.println(cookies[1].getName()+" "+cookies[1].getValue());

		MemberStatus status = new MemberStatus(cookies);
		userAccount = status.getCurrentUserAccount();
		if (status.getLoginStatus(userAccount)) {
//			SessionFactory factory = HibernateUtil.getSessionFactory();
//			Session hibernateSession = factory.getCurrentSession();
//			MemberService mService = new MemberService(hibernateSession);
			
//			StringBuilder sb = new StringBuilder();
//			for (Cookie cookie : cookies) {
//				System.out.println("CookieNameALL: " + cookie.getName());
//				System.out.println("CookieValueALL: " + cookie.getValue());
//				// cookies: null for會拋NullPointerException
//				System.out.println(cookie.getPath().equals("/"));
//				if (cookie.getName() == "user") {
//					sb.append(cookie.getName());
//					System.out.println("CookieName: " + cookie.getName());
//					System.out.println("CookieValue: " + cookie.getValue());
//					break;
//				}
//			}
//			userAccount = sb.toString();
//			System.out.println(sb.toString());
			Member m = mService.selectMemberByAccount(userAccount);
			model.addAttribute("member", m);
			
			String base64String = Base64.getEncoder().encodeToString(m.getImgBytes());
//			httpSession.setAttribute("base64String", base64String);
			System.out.println("base64 size: "+ base64String.length());
			model.addAttribute("base64String", base64String);
			model.addAttribute("a", "測試");
//			request.getRequestDispatcher("./memberRevise.jsp").forward(request, response);
			return prefix+"memberRevise";
		} else {
//			PrintWriter out = response.getWriter();
			System.out.println("尚未登入!");

			// 設定幾秒後跳轉頁面
//			Integer refreshCountDown = 5;
			// 要跳轉的網址位置
//			String redirectUrl = "../Home";
			// 設定在指定時間後自動跳轉至某頁面
//			response.addHeader("Refresh", "5;URL=" + redirectUrl);
//			out.write(String.format("您尚未登入，%d秒後將跳轉到首頁！", refreshCountDown));
			
			// response.sendRedirect("../Home");
			return "redirect://Home";
		}
	}

	@PostMapping("Revise")
	private String doRevise(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			//固定不給改 故disabled沒有值
//			@RequestParam("account") String account,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("id") String id,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("imgBytes") MultipartFile imgPart) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(10));

		// 獲取圖片檔名
//		String imgPath = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
//		String imgExt = FilenameUtils.getExtension(imgPath);
//		httpSession.setAttribute("imgExt", imgExt);
		String imgPath = Paths.get(imgPart.getOriginalFilename()).getFileName().toString();
		String imgExt = FilenameUtils.getExtension(imgPath);
		model.addAttribute("imgExt", imgExt);

		// test
		System.out.println("圖片名稱: " + imgPath);
		System.out.println("圖片類型: " + imgExt);

		// 設定讀取圖片之緩衝區大小
		final int MAX_BUFFER_SIZE = 1024;
		// 圖片寫入時之緩衝區
		byte[] imgBuffer = new byte[MAX_BUFFER_SIZE];
		// 圖片之完整byte資料
		byte[] imgBytes;
		// 加密圖片byte資料為base64編碼後之字串
//		String base64String;
		//有必要在修改後再顯示用戶修改後的圖片嗎？ 要的話還要再加一個jsp去顯示

		InputStream is = imgPart.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int length = 0;
		while ((length = is.read(imgBuffer)) != -1) {
			baos.write(imgBuffer, 0, length);
		}

		imgBytes = baos.toByteArray();
		Member m = new Member(userAccount, hashpw, name, id, address, phone, imgBytes, email, false);
		mService.updateMember(userAccount, m);

		System.out.println("帳號:" + m.getAccount());
		System.out.println("密碼:" + m.getPassword());
		System.out.println("名稱:" + m.getName());
		System.out.println("地址:" + m.getAddress());
		System.out.println("電話:" + m.getPhone());
		System.out.println("電子郵件:" + m.getEmail());
		
		/* 待完工 */
		// 回首頁 但在這之前先加一個jsp 用ajax給他swal
//		response.sendRedirect("../Home");
		return "redirect://Home";
		
	}

}
