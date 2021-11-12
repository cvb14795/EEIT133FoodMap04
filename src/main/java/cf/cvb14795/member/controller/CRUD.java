package cf.cvb14795.member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.service.IMemberService;
import cf.cvb14795.photo.photoAttachment;

/**
 * Servlet implementation class MemberRegister
 */
//@WebServlet(urlPatterns = { "/Member/Register" }, initParams = {
//		@WebInitParam(name = "SUCCESS", value = "./registerSuccess.jsp"),
//		@WebInitParam(name = "ERROR", value = "./registerFail.jsp") })
//@MultipartConfig
@Controller
@RequestMapping("/Member")
public class CRUD {
	private String prefix = "Member/";
	IMemberService mService;

	public CRUD() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public CRUD(IMemberService mService) {
		this.mService = mService;
	}
	
	/* 修改會員資料 */
//	@PutMapping(path = "user/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(path = "user/{account}", consumes = { "multipart/form-data" })
	private ResponseEntity<String> doRevise(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			//固定不給改 故disabled沒有值
			@PathVariable("account") String account,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("idNum") String idNum,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
//			@RequestBody Member m,
//			@ModelAttribute("member") Member member,
			@RequestParam(value = "imgBase64String", required = false) String imgBase64String) throws IOException{
		
		String message;
		HttpHeaders responseHeaders = new HttpHeaders();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		
		Member memberBeforeRevise = mService.selectMemberByAccount(account).get();
		// 獲得使用者修改前的資料 以防使用者沒有上傳圖片或沒有變更密碼
		byte[] imgBytes = memberBeforeRevise.getImgBytes();
		String hashpw = memberBeforeRevise.getPassword();
		if (password != "") {
			// 則仍沿用原本密碼
			hashpw = BCrypt.hashpw(password, BCrypt.gensalt(10));			
		}
		if (imgBase64String != "") {
			// 使用者有上傳圖片 解密後存進資料庫
			imgBytes = Base64.getDecoder().decode(imgBase64String);					
		}
		Member m = new Member(account, hashpw, name, idNum, address, phone, imgBytes, email, false);
		mService.updateMember(m);

		System.out.println("*****修改會員資訊*****");
		System.out.println("帳號:" + m.getAccount());
		System.out.println("密碼:" + m.getPassword());
		System.out.println("身分證字號:" + m.getIdNum());
		System.out.println("名稱:" + m.getName());
		System.out.println("地址:" + m.getAddress());
		System.out.println("電話:" + m.getPhone());
		System.out.println("電子郵件:" + m.getEmail());
		System.out.println("*****修改會員資訊*****");
		
		/* 待完工 */
		// 回首頁 但在這之前先加一個jsp 用ajax給他swal
		message = "*****修改成功!!!*****";
		responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");
		return ResponseEntity.ok()
			      .headers(responseHeaders)
			      .body(new Gson().toJson(message));
	}
	
	/* 註冊會員 */
	@PostMapping(path = "user/{account}")
	private String doRegister(
			Model model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable("account") String account,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("idNum") String idNum,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam("email") String email,
			@RequestParam("imgPart") MultipartFile imgPart) throws IOException {

		System.out.println("收到註冊請求");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("註冊帳號: " + account);

		Optional<Member> alreadyRegisterdMember = mService.selectMemberByAccount(account);
		// 若此筆帳號沒註冊過(不存在資料庫) 才能進行註冊
		if (alreadyRegisterdMember.isEmpty()) {
			// 密碼+10位鹽值hash
			String hashpw = BCrypt.hashpw(password, BCrypt.gensalt(10));
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

			Member m = new Member(account, hashpw, name, idNum, address, phone, imgBytes, email, false);
//				mDAO.addMember(m);
			model.addAttribute("member", m);

			System.out.println("*****註冊會員資訊*****");
			System.out.println("帳號:" + m.getAccount());
			System.out.println("密碼:" + m.getPassword());
			System.out.println("身分證字號:" + m.getIdNum());
			System.out.println("名稱:" + m.getName());
			System.out.println("地址:" + m.getAddress());
			System.out.println("電話:" + m.getPhone());
			System.out.println("電子郵件:" + m.getEmail());
			System.out.println("*****註冊會員資訊*****");

			mService.insertMember(m);
			base64String = Base64.getEncoder().encodeToString(imgBytes);
			model.addAttribute("base64String", base64String);
//			request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
			return prefix + "registerSuccess";
		} else {
			System.out.println("會員: " + account + "已註冊！");
//			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
			model.addAttribute("user", account);
			return prefix + "registerFail";
		}
	}

	
	@GetMapping("/user/{account}/photo")
    @ResponseBody
    public ResponseEntity<?> getPicture(HttpServletResponse resp, @PathVariable String account) {
        Optional<Member> memberOpt = mService.selectMemberByAccount(account);
        if (memberOpt.isPresent()) {
	        Member member = memberOpt.get();
	        byte[] photo = member.getImgBytes();
	        String fileName = "avatar_"+account+".jpg";
	        return photoAttachment.getPhoto(resp, photo, fileName);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
