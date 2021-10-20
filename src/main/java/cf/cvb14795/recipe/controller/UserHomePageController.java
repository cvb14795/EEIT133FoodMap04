package cf.cvb14795.recipe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/Recipe")
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes({"user", "isAdmin"})
public class UserHomePageController {

	public UserHomePageController() {
	}

//	@GetMapping("")
//	public String userPage(HttpServletRequest request, Model model) {
//		MemberStatus status = new MemberStatus(request.getCookies());
//		String userAccount = status.getCurrentUserAccount();
////			httpSession.setAttribute("user", userAccount);
//		model.addAttribute("user", userAccount);
//
//		// 若沒有登入(沒有帳號)時則跳轉到登入畫面
//		model.addAttribute("isAdmin", mService.isAdmin(userAccount));
//		return "Recipe/UserStartingPage";
//	}
	
	// ==============連結首頁==============
	@GetMapping("/user")
	public String userRecipeListPage(
			@ModelAttribute("user") String userAccount,
			@ModelAttribute("isAdmin") boolean isAdmin,
			HttpServletRequest request, 
			Model model) {

		// 若沒有登入(沒有帳號)時則跳轉到登入畫面
		return "Recipe/UserStartingPage";
	}

}
