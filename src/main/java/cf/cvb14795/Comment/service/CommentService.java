package cf.cvb14795.Comment.service;

import java.util.List;

import cf.cvb14795.Comment.entity.Comment;

public interface CommentService {

	public List<Comment> findAll();
	
	public Comment findById(int theId);
	
	public void save(Comment theComment);
	
	public void deleteById(int theId);

	public List<Comment> searchBy(String theComment);

//	public List<Comment> sortBy(String sortingType);
	
}