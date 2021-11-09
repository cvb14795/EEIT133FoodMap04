package cf.cvb14795.Comment.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cf.cvb14795.Comment.entity.Comment;
import cf.cvb14795.Comment.service.CommentService;
import javassist.expr.NewArray;

@Controller
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	public CommentController(CommentService theCommentService) {
		commentService = theCommentService;
	}

	@GetMapping("/list")
	public String listComments(Model theModel) {
	
		List<Comment> theComments = commentService.findAll();
		
		theModel.addAttribute("comments", theComments);
		
		return "comments/list-comments-user";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Comment theComment = new Comment();
		
		theModel.addAttribute("comment", theComment);
		theModel.addAttribute("now", new Date());
		
		return "comments/comment-form";
	}
	
	@GetMapping("/showFormForUpdate/{commentId}")
	public String showFormForUpdate(@PathVariable("commentId") int theId,
									Model theModel) {
		
		Comment theComment = commentService.findById(theId);
		
		theModel.addAttribute("comment", theComment);
		theModel.addAttribute("now", new Date());
		
		// send over to our form
		return "comments/comment-update-form";
	}
	
	@PostMapping("/save")
	public String saveComment(@ModelAttribute("comment") Comment theComment) {
		
		commentService.save(theComment);
		
		return "redirect:/comments/list";
		
	}
	
	@GetMapping("/delete/{commentId}")
	public String delete(@PathVariable("commentId") int theId) {
		
		commentService.deleteById(theId);
		
		return "redirect:/comments/list";
		
	}
	
	@GetMapping("/search")
	public String searchComments(@RequestParam("commentName") String theComment,
									Model theModel) {

		List<Comment> theComments = commentService.searchBy(theComment);
		
		theModel.addAttribute("comments", theComments);

		return "/comments/list-comments-user";		
	}
		
	@GetMapping("/admin-list")
	public String listCommentsAdmin(Model theModel) {
	
		List<Comment> theComments = commentService.findAll();
		
		theModel.addAttribute("comments", theComments);
		
		return "comments/list-comments-admin";
	}
	
	@GetMapping("/admin-delete/{commentId}")
	public String deleteByAdmin(@PathVariable("commentId") int theId) {
		
		commentService.deleteById(theId);
		
		return "redirect:/comments/admin-list";
		
	}
	
	@GetMapping("/admin-search")
	public String searchCommentsAdmin(@RequestParam("commentName") String theComment,
			Model theModel) {

		List<Comment> theComments = commentService.searchBy(theComment);

		theModel.addAttribute("comments", theComments);

		return "/comments/list-comments-admin";		
	}
	
	@GetMapping("/sortByMapName")
	public String searchMapName(@RequestParam("mapName") String theMapName,
									Model theModel) {

		List<Comment> theMapNames = commentService.searchByMapName(theMapName);
		
		theModel.addAttribute("comments", theMapNames);

		return "/comments/list-comments-user";		
	}
	
	@GetMapping("/sortByUserAccount")
	public String searchUserAccount(@RequestParam("userAccount") String theUserAccount,
									Model theModel) {

		List<Comment> theUserAccounts = commentService.searchByUserAccount(theUserAccount);
		
		theModel.addAttribute("comments", theUserAccounts);

		return "/comments/list-comments-show-userAccount";		
	}
	
	@GetMapping("/sortByUserDate")
	public String listCommentsByUserDate(Model theModel) {
	
		List<Comment> theComments = commentService.findAllByUserDate();
		
		theModel.addAttribute("comments", theComments);
		
		return "comments/list-comments-user";
	}
	
	@GetMapping("/sortByUserScore")
	public String listCommentsByUserScore(Model theModel) {
	
		List<Comment> theComments = commentService.findAllByUserScore();
		
		theModel.addAttribute("comments", theComments);
		
		return "comments/list-comments-user";
	}
	
	@GetMapping("/sortByUserScoreAsc")
	public String listCommentsByUserScoreAsc(Model theModel) {
	
		List<Comment> theComments = commentService.findAllByUserScoreAsc();
		
		theModel.addAttribute("comments", theComments);
		
		return "comments/list-comments-user";
	}
	
	@GetMapping("/sortByUserLikes")
	public String listCommentsByUserLikes(Model theModel) {
	
		List<Comment> theComments = commentService.findAllByUserLikes();
		
		theModel.addAttribute("comments", theComments);
		
		return "comments/list-comments-user";
	}
	
}
