package cf.cvb14795.Comment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_score")
	private String score;
	
	@Column(name="user_comment")
	private String userComment;
	
	@Column(name="user_date")
	private String userDate;	
	
	@Column(name="user_likes")
	private String userLikes;
	
	// 測試頁面用
	
	@Column(name="userAccount")
	private String userAccount;
	
	@Column(name="mapname")
	private String mapName;
	
	@Column(name="userPhotoTest")
	private String userPhotoTest;
	
	
	public Comment() {
		
	}

	public Comment(int id, String userName, String score, String userComment, String userDate, String userLikes) {
		
		this.id = id;
		this.userName = userName;
		this.score = score;
		this.userComment = userComment;
		this.userDate = userDate;
		this.userLikes = userLikes;
	}

	

	public Comment(String userName, String score, String userComment, String userDate, String userLikes) {
		
		this.userName = userName;
		this.score = score;
		this.userComment = userComment;
		this.userDate = userDate;
		this.userLikes = userLikes;
	}

	public Comment(String userName, String score, String userComment, String userDate) {
		this.userName = userName;
		this.score = score;
		this.userComment = userComment;
		this.userDate = userDate;
	}
	
	// 測試用
	
	public Comment(int id, String userName, String score, String userComment, String userDate, String userLikes,
			String userAccount, String mapName) {
		this.id = id;
		this.userName = userName;
		this.score = score;
		this.userComment = userComment;
		this.userDate = userDate;
		this.userLikes = userLikes;
		this.userAccount = userAccount;
		this.mapName = mapName;
	}
	
		

	

	public Comment(int id, String userName, String score, String userComment, String userDate, String userLikes,
			String userAccount, String mapName, String userPhotoTest) {
		super();
		this.id = id;
		this.userName = userName;
		this.score = score;
		this.userComment = userComment;
		this.userDate = userDate;
		this.userLikes = userLikes;
		this.userAccount = userAccount;
		this.mapName = mapName;
		this.userPhotoTest = userPhotoTest;
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
	
	public String getUserLikes() {
		return userLikes;
	}

	public void setUserLikes(String userLikes) {
		this.userLikes = userLikes;
	}
	
	
	
	// 測試頁面用	

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	

	public String getUserPhotoTest() {
		return userPhotoTest;
	}

	public void setUserPhotoTest(String userPhotoTest) {
		this.userPhotoTest = userPhotoTest;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userName=" + userName + ", score=" + score + ", userComment=" + userComment
				+ ", userDate=" + userDate + "]";
	}

	
	
}
