package member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import member.bean.Member;
import member.dao.MemberService;
import util.MemberStatus;
import util.hibernate.HibernateUtil;

/**
 * Servlet implementation class Revise
 */
@WebServlet(name = "Member/Revise", urlPatterns = { "/Member/Revise" })
public class Revise extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Cookie[] cookies;
	private String userAccount;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Revise() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cookies = request.getCookies();
		MemberStatus.setCookies(cookies);
		userAccount = MemberStatus.getCurrentUserAccount();
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		MemberService mService = new MemberService(session);
		Member m = mService.selectMemberByAccount(userAccount);
		
		request.getSession(false).setAttribute("member", m);
		request.getRequestDispatcher("./memberRevise.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		MemberService mService = new MemberService(session);
		
		HttpSession httpSession = request.getSession(false);
//		String account = request.getParameter("account");
		String hashpw = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(10));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		Part imgPart = request.getPart("image");
		
		// 獲取圖片檔名
		String imgPath = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
		String imgExt = FilenameUtils.getExtension(imgPath);
		httpSession.setAttribute("imgExt", imgExt);

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

		Member m = new Member(userAccount, hashpw, name, address, phone, imgBytes, email);
		mService.updateMember(userAccount, m);
		
		
		System.out.println("帳號:" + m.getAccount());
		System.out.println("密碼:" + m.getPassword());
		System.out.println("名稱:" + m.getName());
		System.out.println("地址:" + m.getAddress());
		System.out.println("電話:" + m.getPhone());
		System.out.println("電子郵件:" + m.getEmail());

		base64String = Base64.getEncoder().encodeToString(imgBytes);
		request.setAttribute("base64String", base64String);
	}

}
