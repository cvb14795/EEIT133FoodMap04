package cf.cvb14795.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/Member")
public class Register {

	private String prefix = "Member/";

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GetMapping("Register")
	private String registerPage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("跳轉到註冊頁面");
//		request.getRequestDispatcher("./memberRegister.html").forward(request, response);
		return prefix + "memberRegister";

	}

}
