package Coupon.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.build.AllowSysOut;

import Coupon.model.QuestionnaireBean;
import Coupon.model.QuestionnaireDAO;
import member.bean.Member;
import member.dao.MemberService;
import util.MemberStatus;
import util.gmail.Mail;
import util.hibernate.HibernateUtil;

/**
 * Servlet implementation class AdministratorController
 */
@WebServlet("/Coupon/administratorcontroller")
public class AdministratorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String PATH;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String action = request.getParameter("action");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();

		if (action.equals("R")) {
			try {

				QuestionnaireDAO qDAO = new QuestionnaireDAO(session);

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

				QuestionnaireDAO qDAO = new QuestionnaireDAO(session);

				PATH = "view2.jsp";
				List<QuestionnaireBean> sendUsersCoupons = qDAO.SendUsersCoupons();
				
//				MemberStatus.setCookies(request.getCookies());
				String currentUserAccount = MemberStatus.getCurrentUserAccount();
//				MemberService mService = new MemberService(session);
				
				System.out.println("\ncurrentUserAccount:" + currentUserAccount + "\n");
//				Member m = mService.selectMemberByAccount(currentUserAccount);
				String subject = "FoodMap美食地圖 優惠券寄送通知";
				// 優惠券長度
				final double COUPON_CODE_LENGTH = 6;
				// 優惠券代碼
				String couponCode = String.valueOf((Math.random() * Math.pow(10, COUPON_CODE_LENGTH)) + 1);
				String text = "您好 " + currentUserAccount + "<br/>" + "這是您的優惠券代碼:"+couponCode;
//				Mail.SendGmail("me", m.getEmail(), subject, text);

				for (QuestionnaireBean b : sendUsersCoupons) {
					System.out.println(b.getId());
				}

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
