package cf.cvb14795.Comment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cf.cvb14795.Comment.dao.CommentRepository;
import cf.cvb14795.Comment.entity.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentRepository commentRepository;
	
	@Autowired
	public CommentServiceImpl(CommentRepository theCommentRepository) {
		commentRepository = theCommentRepository;
	}

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAllByOrderByIdDesc();
	}

	@Override
	public Comment findById(int theId) {
		Optional<Comment> result = commentRepository.findById(theId);
		
		Comment theComment = null;
		
		if (result.isPresent()) {
			theComment = result.get();
		}
		else {
			throw new RuntimeException("Did not find comment id - " + theId);
		}
		
		return theComment;
	}

	@Override
	public void save(Comment theComment) {
		commentRepository.save(theComment);
	}

	@Override
	public void deleteById(int theId) {
		commentRepository.deleteById(theId);
	}

	@Override
	public List<Comment> searchBy(String theComment) {
		
		List<Comment> results = null;
		
		if (theComment != null && (theComment.trim().length() > 0)) {
			results = commentRepository.findByUserCommentContainsAllIgnoreCase(theComment);
		}
		else {
			results = findAll();
		}
		
		return results;
	}
	
//	@Override
//	public List<Comment> sortBy(int theSortField) {
//		List<Comment> results = null;
//		
//		switch (sortingType) {
//		case "high":
//			results = commentRepository.findAllByOrderByScoreDesc();
//			break;
//			
//		case "lowToHigh":
//			results = commentRepository.findAllByOrderByScoreAsc();
//			break;
//			
//		case "new":
//			results = commentRepository.findAllByOrderByUserDateDesc();
//			break;
//			
//		case "oldToNew":
//			results = commentRepository.findAllByOrderByUserDateAsc();
//			break;
//			
//		default:
//			results = commentRepository.findAllByOrderByIdDesc();
//		}
//		
//		return results;
//	}

}
