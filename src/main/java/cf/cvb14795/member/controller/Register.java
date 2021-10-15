package cf.cvb14795.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Base64;

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

/**
 * Servlet implementation class MemberRegister
 */
//@WebServlet(urlPatterns = { "/Member/Register" }, initParams = {
//		@WebInitParam(name = "SUCCESS", value = "./registerSuccess.jsp"),
//		@WebInitParam(name = "ERROR", value = "./registerFail.jsp") })
//@MultipartConfig
@Controller
@RequestMapping("/Member")
public class Register {
	private String SUCCESS_VIEW = "registerSuccess";
	private String ERROR_VIEW = "registerFail";
	private String prefix = "Member/";

	@Autowired
	IMemberService mService;

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PostMapping("Register")
	private String doRegister(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("account") String account,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("id") String id,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("imgPart") MultipartFile imgPart) throws IOException{
		
//		SessionFactory factory = HibernateUtil.getSessionFactory();
//		Session session = factory.getCurrentSession();
//		MemberService mService = new MemberService(session);

		System.out.println("收到註冊請求");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		String account = request.getParameter("account");
		System.out.println("註冊帳號: " + account);

		// 若此筆帳號沒註冊過(不存在資料庫) 才能進行註冊
		if (mService.selectMemberByAccount(account) == null) {
			// 密碼+10位鹽值hash
			String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(10));
//			String name = request.getParameter("name");
//			String id = request.getParameter("id");
//			String address = request.getParameter("address");
//			String phone = request.getParameter("phone");
//			String email = request.getParameter("email");
//			Part imgPart = request.getPart("image");
			// 獲取圖片檔名
//			String imgPath = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
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
			String base64String;

			InputStream is = imgPart.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			int length = 0;
			while ((length = is.read(imgBuffer)) != -1) {
				baos.write(imgBuffer, 0, length);
			}

			imgBytes = baos.toByteArray();

			Member m = new Member(account, hashpw, name, id, address, phone, imgBytes, email, false);
//				mDAO.addMember(m);
			mService.insertMember(m);
			model.addAttribute("member", m);
			

			System.out.println("帳號:" + m.getAccount());
			System.out.println("密碼:" + m.getPassword());
			System.out.println("名稱:" + m.getName());
			System.out.println("地址:" + m.getAddress());
			System.out.println("電話:" + m.getPhone());
			System.out.println("電子郵件:" + m.getEmail());

			base64String = Base64.getEncoder().encodeToString(imgBytes);
			model.addAttribute("base64String", base64String);
//			request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
			return prefix+SUCCESS_VIEW;
		} else {
			System.out.println("會員: " + account + "已註冊！");
//			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
			model.addAttribute("user", account);
			return prefix+ERROR_VIEW;
		}
	}

	@GetMapping("Register")
	private String registerPage(
			HttpServletRequest request,
			HttpServletResponse response){
		System.out.println("跳轉到註冊頁面");
//		request.getRequestDispatcher("./memberRegister.html").forward(request, response);
		return prefix+"memberRegister";
		
	}
}
