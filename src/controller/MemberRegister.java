package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import member.Member;
import member.MemberDAO;
import member.MemberDAOFactory;

/**
 * Servlet implementation class MemberRegister
 */
@WebServlet("/Register")
public class MemberRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO mDAO = MemberDAOFactory.getMemberDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberRegister() {
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

//				String base64String = Base64.encodeBase64String(bytes);
//				Part img = request.getPart("image");
//				// 設定檔案最大上傳大小
//				int MAX_IMG_SIZE = 3*1024;
//				byte[] imgBytes = new byte[MAX_IMG_SIZE];
//				InputStream is = img.getInputStream();
//				ByteArrayOutputStream bos = new ByteArrayOutputStream();
//				int length = 0;
//				while ((length = is.read()) != 0) {
//					bos.write(imgBytes);
//				}

				request.getRequestDispatcher("/registerSuccess.jsp").include(request, response);
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
		request.getRequestDispatcher("memberRegister.html").forward(request, response);
	}
}
