package cf.cvb14795.Coupon.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cf.cvb14795.Coupon.model.QuestionnaireBean;
import cf.cvb14795.Coupon.model.QuestionnaireDAO;
import cf.cvb14795.Coupon.util.CouponUsageUtil;
import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.dao.MemberService;
import util.MemberStatus;
import util.hibernate.HibernateUtil;

/**
 * Servlet implementation class AdministratorController
 */
@WebServlet("/Coupon/administratorcontroller")
public class AdministratorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PATH;
	@Autowired
	private MemberService mService;
	private QuestionnaireDAO qDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministratorController() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
				
		String action = request.getParameter("action");
		
		// 由Cookie獲取使用者當前登入狀態
		MemberStatus status = new MemberStatus(request.getCookies());
		// 獲取登入資料
		Member m = mService.selectMemberByAccount(status.getCurrentUserAccount());
		request.setAttribute("user", m.getAccount());
		request.setAttribute("isAdmin", m.isAdmin());
		System.out.println("當前登入帳號:"+m.getAccount());
		System.out.println("是否為管理員:"+m.isAdmin());
		
		/* 待完工 */
			// 加入是否為管理員判斷 若為一般使用者則強制跳回frontpage首頁
			// 參考
		/* 待完工 */
		if (action.equals("R")) {
			try {

//				QuestionnaireDAO qDAO = new QuestionnaireDAO(session);

				PATH = "view.jsp";
				List<QuestionnaireBean> vaccine = qDAO.QueryDataByVaccine();
				request.setAttribute("vaccine", vaccine);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				request.getRequestDispatcher(PATH).forward(request, response);
			}

		} else if (action.equals("U")) {
			try {

//				QuestionnaireDAO qDAO = new QuestionnaireDAO(session);
				PATH = "view2.jsp";
				List<QuestionnaireBean> sendUsersCoupons = qDAO.SendUsersCoupons();
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
				CouponUsageUtil couponUsage = new CouponUsageUtil(baseUrl);
				
				System.out.println("================正在寄送優惠券Email...==================");
				for (QuestionnaireBean b : sendUsersCoupons) {
					System.out.println(b.getId());
					/* ================================== */
					/* 待修改成問卷表格與會員表格join的形式 */
					
					// 由問卷表格查詢該身分證對應的會員
					Member member = mService.selectMemberById(b.getId());
					// 產生優惠券代碼(預設為6位數)
					String couponCode = couponUsage.generateCouponCode(6);
					// 發送優惠券Email
					couponUsage.sendCouponMail(member, couponCode);
					
					/* ================================== */
				}
				System.out.println("================寄送優惠券Email完成！==================");
				request.setAttribute("sendUsersCoupons", sendUsersCoupons);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				request.getRequestDispatcher(PATH).forward(request, response);
			}

		} else if (action.equals("B")) {
			try {

				QuestionnaireDAO qDAO = new QuestionnaireDAO(session);

				PATH = "view3.jsp";
				List<QuestionnaireBean> revokeUsersCoupons = qDAO.revokeUsersCoupons();

				request.setAttribute("revokeUsersCoupons", revokeUsersCoupons);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				request.getRequestDispatcher(PATH).forward(request, response);
			}
		}
	}
}