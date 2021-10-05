package commentsJDBC;

public class Comment {

	private int id;
	private String userName;
	private String score;
	private String userComment;	
	private String userDate;
		


	public Comment(String userName, String score, String userComment, String userDate) {
		this.userName = userName;
		this.score = score;
		this.userComment = userComment;
		this.userDate = userDate;
	}


	public Comment(int id, String userName, String score, String userComment, String userDate) {
		this.id = id;
		this.userName = userName;
		this.score = score;
		this.userComment = userComment;
		this.userDate = userDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getScore() {
		return score;
	}


	public void setScore(String score) {
		this.score = score;
	}


	public String getUserComment() {
		return userComment;
	}


	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	
	public String getUserDate() {
		return userDate;
	}

	
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userName=" + userName + ", score=" + score + ", userComment=" + userComment + ", userDate=" + userDate + "]";
	}
	
	
}
