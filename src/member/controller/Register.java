package member.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.mindrot.jbcrypt.BCrypt;

import member.bean.Member;
import member.dao.MemberDAO;
import member.dao.MemberDAOFactory;

/**
 * Servlet implementation class MemberRegister
 */
@WebServlet("/Member/Register")
@MultipartConfig
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO mDAO = MemberDAOFactory.getMemberDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("收到註冊請求");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			String account = request.getParameter("account");
			// 密碼+10位鹽值hash
			String hashpw = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(10));
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			Part imgPart = request.getPart("image");
			// 獲取圖片檔名
			String imgPath = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
			String imgExt = FilenameUtils.getExtension(imgPath);
			request.setAttribute("imgExt", imgExt);
			
			//test
			System.out.println("圖片名稱: " + imgPath);
			System.out.println("圖片類型: " + imgExt);

			// 設定讀取圖片之緩衝區大小
			final int  MAX_BUFFER_SIZE = 1024;
			// 圖片寫入時之緩衝區
			byte[] imgBuffer = new byte[MAX_BUFFER_SIZE];
			// 圖片之完整byte資料
			byte[] imgBytes;
			// 加密圖片byte資料為base64編碼後之字串
			String base64String;
			
			InputStream is = imgPart.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			Member m = null;
			/* ========== 開啟連線 ========== */
			mDAO.createConn();

			System.out.println("註冊帳號: " + account);
			if (mDAO.findAllByAccount(account) == null) {
				m = mDAO.createMember(account, hashpw, name, address, phone);

				mDAO.addMember(m);
				request.setAttribute("member", m);

				System.out.println("帳號:" + m.getAccount());
				System.out.println("密碼:" + m.getPassword());
				System.out.println("名稱:" + m.getName());
				System.out.println("地址:" + m.getAddress());
				System.out.println("電話:" + m.getPhone());
				
				int length = 0;
				while ((length = is.read(imgBuffer)) != -1) {
					baos.write(imgBuffer, 0, length);
				}
				
				imgBytes = baos.toByteArray();
				
				base64String = Base64.getEncoder().encodeToString(imgBytes);
				request.setAttribute("base64String", base64String);

				request.getRequestDispatcher("./registerSuccess.jsp").include(request, response);
			} else {
				System.out.println("會員: " + account + "已註冊！");
			}

		} catch (SQLException e) {
			// TODO: handle exception
			throw new ServletException(e);
		} finally {
			/* ========== 關閉連線(將連線還回連線池) ========== */
			try {
				mDAO.closeConn();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("無法關閉連線!");
			}
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("跳轉到註冊頁面");
		request.getRequestDispatcher("./memberRegister.html").forward(request, response);
	}
}
