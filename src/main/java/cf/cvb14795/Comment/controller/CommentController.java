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
		
		return "comments/list-comments";
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

		return "/comments/list-comments";		
	}
	
//	@GetMapping("/sorting")
//	public String sortingComments(String sortingType, Model theModel) {
//	
//		List<Comment> theComments = commentService.sortBy(sortingType);
//		
//		theModel.addAttribute("comments", theComments);
//		
//		return "comments/list-comments";
//	}
//	
	
}
