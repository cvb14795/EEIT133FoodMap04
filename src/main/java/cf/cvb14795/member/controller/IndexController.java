package cf.cvb14795.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@WebServlet(description = "首頁", urlPatterns = { "/Home" })
@Controller
public class IndexController  {
	
	public IndexController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@GetMapping({"/", "/Home"})
	private String index() {
		System.out.println("偵測到使用者連線/Home，將跳轉至首頁");
		return "index";
	}
}
