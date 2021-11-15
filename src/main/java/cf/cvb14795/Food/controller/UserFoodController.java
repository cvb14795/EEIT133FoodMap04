package cf.cvb14795.Food.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cf.cvb14795.Comment.entity.Comment;
import cf.cvb14795.Comment.service.CommentService;
import cf.cvb14795.Food.model.AdminMapDataBean;
import cf.cvb14795.Food.model.UserMapDataBean;
import cf.cvb14795.Food.service.AdminMapDataService;
import cf.cvb14795.Food.service.UserMapDataService;

@Controller
@RequestMapping("/Food/user")
//將Model中屬性名為user的屬性
//放到Session屬性列表中，以便這個屬性可以跨請求訪問
@SessionAttributes({ "user", "isAdmin" })
public class UserFoodController {

	private final static String PREFIX = "Food/";

	@Autowired
	ServletContext context;

	UserMapDataService uFoodService;
	AdminMapDataService aFoodService;
	CommentService commentService;

	@Autowired
	public UserFoodController(
			UserMapDataService uFoodService,
			AdminMapDataService aFoodService,
			CommentService commentService) {
		this.uFoodService = uFoodService;
		this.aFoodService = aFoodService;
		this.commentService = commentService;
	}

	@GetMapping("UserFood")
	public String userRecipe(@ModelAttribute("user") String user, @ModelAttribute("isAdmin") boolean isAdmin) {
		return PREFIX + "UserFood";
	}

	@GetMapping("info/{id}")
	public String userRecipe(@PathVariable("id") String id, Model model) {
		AdminMapDataBean mapData = aFoodService.updateId(Integer.valueOf(id));
		model.addAttribute("mapData", mapData);
		return PREFIX + "RestaurantInfo";
	}

	@GetMapping("/FoodBoardInfo/{id}")
	public String foodBoardInfo(@PathVariable("id") String id, Model model) {
		AdminMapDataBean mapData = aFoodService.updateId(Integer.valueOf(id));
		List<Comment> commentList = commentService.searchByMapName(mapData.getMapname());
		// 該商家共幾則評論
		Long commentCount = commentService.findCommentCountByMapName(mapData.getMapname());
		// 評分星數對應幾則評論
		Map<String, Integer> ratingToCommentsQtyMap = new HashMap<>();
		ratingToCommentsQtyMap.put("1", 0);
		ratingToCommentsQtyMap.put("2", 0);
		ratingToCommentsQtyMap.put("3", 0);
		ratingToCommentsQtyMap.put("4", 0);
		ratingToCommentsQtyMap.put("5", 0);
		//每則評論星數
		String rating = "";
		//計算平均星數
		double ratingAvg = 0.0;
		double ratingSum = 0.0;
		/* 列出所有星數的評論統計 */
		System.out.println("****** 以下為"+ mapData.getMapname() +" 的統計資料 ****");
		for (Comment comment : commentList) {
			// 取該評論星數
			rating = comment.getScore();
			// 加總
			ratingSum += Integer.valueOf(rating);
			// 更新評論星數統計
			int commentQty = ratingToCommentsQtyMap.get(rating);
			commentQty++;
			ratingToCommentsQtyMap.put(rating, commentQty);
		}
		for (Map.Entry<String, Integer> entry : ratingToCommentsQtyMap.entrySet()) {
			System.out.println(entry.getKey()+"星的有 "+ entry.getValue() + "則評論");			
		}
		ratingAvg = ratingSum / commentCount;
		System.out.println("****** 以上為"+ mapData.getMapname() +" 的統計資料 ****");
		model.addAttribute("mapData", mapData);
		model.addAttribute("commentCount", commentCount);
		model.addAttribute("commentCountsByRating", ratingToCommentsQtyMap);
		model.addAttribute("ratingAvg", ratingAvg);
		return PREFIX + "FoodBoardInfo";
	}

	// ===============使用者查詢===============
	@PostMapping("UserViewAdminFood")
	public String UserViewAdminFood(Model model) {

		List<AdminMapDataBean> lists = aFoodService.selectAll();
		List<String> imgList = new ArrayList<String>();
		for (AdminMapDataBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getFilename());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);

		return PREFIX + "UserViewAdminFood";
	}

	// ==================無視區====================//
	@PostMapping("UserViewMembersFood")
	public String UserViewMembersFood(Model model) {

		List<UserMapDataBean> lists = uFoodService.findMembersFood();
		List<String> imgList = new ArrayList<String>();
		for (UserMapDataBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getFilename());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);

		return PREFIX + "UserViewMembersFood";
	}
}
