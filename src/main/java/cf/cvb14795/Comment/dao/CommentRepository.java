package cf.cvb14795.Comment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cf.cvb14795.Comment.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	public List<Comment> findAllByOrderByIdDesc();

	public List<Comment> findByUserCommentContainsAllIgnoreCase(String comment);
	
	public List<Comment> findByMapName(String theMapName);
	
	public List<Comment> findByUserAccount(String theUserAccount);

	public List<Comment> findAllByOrderByUserDateAsc();
	
	public List<Comment> findAllByOrderByUserDateDesc();
	
	public List<Comment> findAllByOrderByScoreAsc();

	public List<Comment> findAllByOrderByScoreDesc();
	
	public List<Comment> findAllByOrderByUserLikesDesc();
	
	public Long countByMapName(String theMapName);
}
