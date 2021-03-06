package cf.cvb14795.recipe.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.member.service.IMemberService;
import cf.cvb14795.recipe.model.AdminRecipeBean;
import cf.cvb14795.recipe.model.MyFavoritesBean;
import cf.cvb14795.recipe.model.ReportBean;
import cf.cvb14795.recipe.model.UserRecipeBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;
import cf.cvb14795.recipe.service.IMyFavoriteService;
import cf.cvb14795.recipe.service.IReportService;
import cf.cvb14795.recipe.service.IUserRecipeService;
import util.gmail.Mail;

@Controller
@RequestMapping("/Recipe/user")
//???Model???????????????user?????????
//??????Session?????????????????????????????????????????????????????????
@SessionAttributes({ "user", "isAdmin" })
public class UserRecipeControllerAjax {

	private final static String PREFIX = "Recipe/";

	IUserRecipeService uRecipeService;
	IAdminRecipeService aRecipeService;
	IMemberService memberService;
	IMyFavoriteService favoritesService;
	IReportService reportService;

	@Autowired
	public UserRecipeControllerAjax(IUserRecipeService uRecipeService, IAdminRecipeService aRecipeService,
			IMemberService memberService, IMyFavoriteService favoritesService, 
			IReportService reportService) {
		this.uRecipeService = uRecipeService;
		this.aRecipeService = aRecipeService;
		this.memberService = memberService;
		this.favoritesService = favoritesService;
		this.reportService = reportService;
	}

//	@GetMapping("UserRecipe")
//	public String userRecipe(@ModelAttribute("user") String user, @ModelAttribute("isAdmin") boolean isAdmin) {
//		return PREFIX + "UserViewAdminRecipe2";
//	}

	@GetMapping("UserInsertRecipe2")
	public String recipe(Model model) {
		UserRecipeBean userRecipe = new UserRecipeBean();
		model.addAttribute("userRecipe", userRecipe);
		return PREFIX + "UserInsertRecipe2";
	}

	// ============== ???????????? -> ??????????????? ==============
	@PostMapping("UserInsertToDB")
	@ResponseBody
	public ResponseEntity<String> userInsertForm(@CookieValue("user") String userName,
			@RequestParam(required = false, name = "foodName") String foodName,
			@RequestParam(required = false, name = "category") String category,
			@RequestParam(required = false, name = "food1") String food1,
			@RequestParam(required = false, name = "food2") String food2,
			@RequestParam(required = false, name = "food3") String food3,
			@RequestParam(required = false, name = "food4") String food4,
			@RequestParam(required = false, name = "sauce1") String sauce1,
			@RequestParam(required = false, name = "sauce2") String sauce2,
			@RequestParam(required = false, name = "sauce3") String sauce3,
			@RequestParam(required = false, name = "photo") MultipartFile photoPart,
			@RequestParam(required = false, name = "step") String step) throws IOException {

		byte[] photo = new byte[1024];
		String base64String = "";
		if (photoPart != null) {
			InputStream is = photoPart.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			int length;
			while ((length = is.read(photo)) != -1) {
				baos.write(photo, 0, length);
			}

			photo = baos.toByteArray();
			base64String = Base64.getEncoder().encodeToString(photo);
		}
		UserRecipeBean userRecipe = new UserRecipeBean(userName, foodName, category, food1, food2, food3, food4, sauce1,
				sauce2, sauce3, photo, base64String, step);

		uRecipeService.insert(userRecipe);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");

		return ResponseEntity.ok().headers(responseHeaders).body(new Gson().toJson(userRecipe));
	}

	// ============== ??????????????????????????? ==============
//	@PostMapping("UserViewAdminRecipe2")
//	public String UserViewAdminRecipe(Model model) {
//
//		List<AdminRecipeBean> lists = aRecipeService.selectAll();
//		List<String> imgList = new ArrayList<String>();
//		for (AdminRecipeBean bean : lists) {
//			String base64String = Base64.getEncoder().encodeToString(bean.getPhoto());
//			imgList.add(base64String);
//		}
//		model.addAttribute("lists", lists);
//		model.addAttribute("imgList", imgList);
//
//		return PREFIX + "UserViewAdminRecipe2";
//	}

	// ============== ????????????????????????????????? ==============
	@GetMapping("UserViewMembersRecipe2")
	public String UserViewMembersRecipe(Model model) {

		List<UserRecipeBean> lists = uRecipeService.findMembersRecipe();
		List<String> imgList = new ArrayList<String>();
		HashMap<Integer, ReportBean> reportMap = new HashMap<>();

		for (UserRecipeBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getPhoto());
			imgList.add(base64String);
			// ??????????????????: ??????????????????
			// ?????? xxx ???????????????: ????????????????????? ????????????????????????????????????
			Optional<ReportBean> opt = reportService.findByRecipeId(bean.getId());
			if (opt.isPresent()) {
				reportMap.put(bean.getId(), opt.get());
			}
		}

		for (int i = 0; i < lists.size(); i++) {
			String base64String = Base64.getEncoder().encodeToString(lists.get(i).getPhoto());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);
		model.addAttribute("reportMap", reportMap);

		return PREFIX + "UserViewMembersRecipe2";
	}

	// ============== ?????????????????????????????? ==============
	@GetMapping("ViewYourRecipe2")
	public String ViewYourRecipe(@CookieValue("user") String userName, Model model) {

		List<UserRecipeBean> lists = uRecipeService.findByName(userName);
		List<String> imgList = new ArrayList<String>();
		for (UserRecipeBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getPhoto());
			imgList.add(base64String);
		}

		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);

		return PREFIX + "ViewYourRecipe2";
	}

	// ============== ??????ID?????????????????????:?????? ==============
	@GetMapping(path = "ViewYourRecipe2/{id}")
	public String ViewYourRecipe(@PathVariable("id") int id, Model model) {
		Optional<UserRecipeBean> opt = uRecipeService.getUpdateId(id);
		UserRecipeBean updateRecipe = new UserRecipeBean();
		if (opt.isPresent()) {
			updateRecipe = opt.get();
		}
		String base64String = Base64.getEncoder().encodeToString(updateRecipe.getPhoto());

		model.addAttribute("updateRecipe", updateRecipe);
		model.addAttribute("base64String", base64String);

		return PREFIX + "UserShowEditRecipe2";
	}

	// ============== ???????????? ==============
	@PostMapping(path = "ViewYourRecipe2/{id}")
	public ResponseEntity<String> UserEditRecipeAction(@PathVariable("id") int id,
			@ModelAttribute("recipe") UserRecipeBean userRecipe, @RequestParam("photo") String photo)
			throws IOException {

		userRecipe.setBase64String(photo);
		userRecipe.setPhoto(Base64.getDecoder().decode(photo));
		uRecipeService.saveRecipe(userRecipe);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		responseHeaders.add("Content-Type", "application/json; charset=utf-8");

		return ResponseEntity.ok().headers(responseHeaders).body(new Gson().toJson(userRecipe));

	}

	// ============== ??????ID?????????????????????:?????? ==============
//	@DeleteMapping("UserShowDeleteRecipe")
//	public String UserShowDeleteRecipe(@RequestParam("id") int id, Model model) {
//		UserRecipeBean deleteRecipe = uRecipeService.getUpdateId(id);
//
//		String base64String = Base64.getEncoder().encodeToString(deleteRecipe.getPhoto());
//
//		model.addAttribute("recipe", deleteRecipe);
//		model.addAttribute("base64String", base64String);
//
//		return PREFIX + "UserDeleteConfirm";
//	}

	// ============== ???????????? ==============
	@DeleteMapping("ViewYourRecipe2/{id}")
	public ResponseEntity<String> UserDeleteRecipeAction(@PathVariable("id") Integer id) {
		Optional<UserRecipeBean> opt = uRecipeService.findById(id);
		if (opt.isPresent()) {
			uRecipeService.deleteById(id);
			return new ResponseEntity<String>(HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	// ============== ???????????????????????? ==============
	@GetMapping("UserViewAdminRecipe2/{id}")
	public String AdminRecipeDetails(@PathVariable("id") Integer id, Model model) {

		List<AdminRecipeBean> lists = aRecipeService.selectAll();
		List<String> imgList = new ArrayList<String>();
		for (AdminRecipeBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getPhoto());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);

		AdminRecipeBean recipe = aRecipeService.getId(id);
		String base64String = Base64.getEncoder().encodeToString(recipe.getPhoto());

		model.addAttribute("recipe", recipe);
		model.addAttribute("base64String", base64String);
		return PREFIX + "SingleAdminRecipe";
	}

	// ============== ???????????????????????? ==============
	@GetMapping("UserViewMembersRecipe2/{id}")
	public String MembersRecipeDetails(@PathVariable("id") Integer id, Model model) {

		List<UserRecipeBean> lists = uRecipeService.findMembersRecipe();
		List<String> imgList = new ArrayList<String>();
		for (UserRecipeBean bean : lists) {
			String base64String = Base64.getEncoder().encodeToString(bean.getPhoto());
			imgList.add(base64String);
		}
		model.addAttribute("lists", lists);
		model.addAttribute("imgList", imgList);

		Optional<UserRecipeBean> opt = uRecipeService.getUpdateId(id);
		UserRecipeBean recipe = new UserRecipeBean();
		if (opt.isPresent()) {
			recipe = opt.get();
		}
		String base64String = Base64.getEncoder().encodeToString(recipe.getPhoto());

		model.addAttribute("recipe", recipe);
		model.addAttribute("base64String", base64String);
		return PREFIX + "SingleMembersRecipe";
	}

	// ============== ??????????????????????????? ==============
	@GetMapping("myfavorites/{id}")
	public ResponseEntity<?> goToMyfavorites(@PathVariable("id") Integer id, @ModelAttribute("member") Member member,
			Model model) {
		MyFavoritesBean myFavBean = new MyFavoritesBean();

		AdminRecipeBean aRecipe = aRecipeService.getId(id);
		myFavBean.setaRecipeId(aRecipe);
		myFavBean.setMember(member);

		Optional<MyFavoritesBean> opt = favoritesService.findByRecipeIdAndAccount(id, member.getAccount());
		if (!opt.isPresent()) {
			// ?????????ID??????????????????????????? ???????????? ????????????
			favoritesService.insert(myFavBean);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} else {
			favoritesService.deleteByRecipeIdAndAccount(id, member.getAccount());
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}

	}

	// ============== ???????????????????????? ==============
	@GetMapping("showMyfavorites")
	public String showMyfavorites(@CookieValue("user") String userName, Model model) {
		List<MyFavoritesBean> lists = favoritesService.findByName(userName);
		List<AdminRecipeBean> recipeBeans = new ArrayList<AdminRecipeBean>();
		List<String> imgList = new ArrayList<String>();
		for (MyFavoritesBean favoritesBean : lists) {
			AdminRecipeBean idBean = aRecipeService.getId(favoritesBean.getaRecipeId().getId());
			recipeBeans.add(idBean);
			String base64String = Base64.getEncoder().encodeToString(favoritesBean.getaRecipeId().getPhoto());
			imgList.add(base64String);
		}
		model.addAttribute("recipeBeans", recipeBeans);
		model.addAttribute("imgList", imgList);
		return PREFIX + "MyFavoriteAdmin";
	}

	// ============== ?????????????????? ==============
	@GetMapping("report/{id}")
	public ResponseEntity<HttpStatus> reportRecipe(@PathVariable("id") Integer id,
			@ModelAttribute("member") Member member, Model model) {

		ReportBean reportBean = new ReportBean();
		Optional<UserRecipeBean> userRecipe = uRecipeService.getUpdateId(id);
		reportBean.setUserRecipe(userRecipe.get());
		reportBean.setMember(member);
		
		Optional<ReportBean> opt = reportService.findByRecipeId(id);
		if (!opt.isPresent()) {
			reportService.save(reportBean);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} else {
//			reportService.deleteByRecipeId(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("sendReportMail/{id}")
	public ResponseEntity<HttpStatus> sendReportMail(@PathVariable("id") Integer id, 
			@CookieValue("user") String userName){
		
		Optional<UserRecipeBean> userRecipe = uRecipeService.getUpdateId(id);
		
		Mail mail = new Mail();
		String text = "?????? : "+userName+"\r\n"
				+"??????????????????????????????????????????????????????"+"\r\n"
				+ "??????????????????:"+"\r\n"
				+ "????????????:"+userRecipe.get().getUserName()+"\r\n"
				+ "????????????:"+userRecipe.get().getFoodName()+"\r\n"+"\r\n";
		mail.SendGmail("foodmap04@gmail.com", "??????What! ??????????????????", text, true);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	

	// ?????????????????????????????????????????????user?????????
	@ModelAttribute
	public void initMember(Model model, @CookieValue("user") String user) {
		Optional<Member> opt = memberService.selectMemberByAccount(user);
		if (opt.isPresent()) {
			model.addAttribute("member", opt.get());
		}
	}

	// ????????????cookie(??????????????????)???????????????
	@ExceptionHandler(MissingRequestCookieException.class)
	private String handleMissingCookieError(Model model) {
		return "redirect:/loginAlert";
	}

	// ???????????????????????????CSV
	@GetMapping("csv")
	public ResponseEntity<String> doExport() throws IOException, SQLException {
			FileOutputStream fos = new FileOutputStream(new File("C:/_Favorites/myFav.csv"));
			OutputStreamWriter osw = new OutputStreamWriter(fos,"MS950");
			BufferedWriter bw = new BufferedWriter(osw);
			List<MyFavoritesBean> favList = favoritesService.findAll();
			String sTitle = "??????, ??????1, ??????2, ??????3, ??????4, ?????????1, ?????????2, ?????????3, ??????";
			bw.write(sTitle);
			for (MyFavoritesBean myFavBean : favList) {
				String dataList = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
						myFavBean.getaRecipeId().getName(),myFavBean.getaRecipeId().getFood1(),
						myFavBean.getaRecipeId().getFood2(),myFavBean.getaRecipeId().getFood3(),
						myFavBean.getaRecipeId().getFood4(),myFavBean.getaRecipeId().getSauce1(),
						myFavBean.getaRecipeId().getSauce2(),myFavBean.getaRecipeId().getSauce3(),myFavBean.getaRecipeId().getStep());
				bw.newLine();
				bw.write(dataList);
			}
			bw.close();
			osw.close();
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
