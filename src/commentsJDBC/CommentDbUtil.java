package commentsJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class CommentDbUtil {
	
		private DataSource dataSource;
		
		public CommentDbUtil(DataSource theDataSource) {
			dataSource = theDataSource;
		}
	
		public List<Comment> getComments() throws Exception {
			
			List<Comment> comments = new ArrayList<>();
			
			Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRs = null;
			
			try {
				// get a connection
				myConn = dataSource.getConnection();
				
				// create sql statement
				String sql = "select * from comment order by id DESC";
				
				myStmt = myConn.createStatement();
				
				// execute query
				myRs = myStmt.executeQuery(sql);
				
				// process result set
				while (myRs.next()) {
					
					// retrieve data from result set row
					int id = myRs.getInt("id");
					String userName = myRs.getString("user_name");
					String score = myRs.getString("user_score");
					String userComment = myRs.getString("user_comment");
					String userDate = myRs.getString("user_date");
					
					// create new comment object
					Comment tempComment = new Comment(id, userName, score, userComment, userDate);
					
					// add it to the list of comments
					comments.add(tempComment);
					
				}
				
				return comments;
			}
			finally {

				// close JDBC objects
				close(myConn, myStmt, myRs);
				
			}
		}

		private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
			
			try {
				if(myRs != null) {
					myRs.close();
				}
				
				if(myStmt != null) {
					myStmt.close();
				}
				
				if(myConn != null) {
					myConn.close();  
				}
			}
			catch (Exception exc){
				exc.printStackTrace();
			}
		}

		public void addComment(Comment theComment) throws Exception {
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// get db connection
				myConn = dataSource.getConnection();
				
				// create sql for insert
				String sql = "insert into comment"
						+ "(user_name, user_score, user_comment, user_date) "
						+ "values (?, ?, ?, ?)";
				
				myStmt = myConn.prepareStatement(sql);
				
				// set the param values for the comment
				myStmt.setString(1, theComment.getUserName());
				myStmt.setString(2, theComment.getScore());
				myStmt.setString(3, theComment.getUserComment());
				myStmt.setString(4, theComment.getUserDate());
				
				// execute sql insert
				myStmt.execute();
			}
			finally {
				// clean up JDBC objects
				close(myConn, myStmt, null);
			}
			
		}

		public Comment getComment(String theCommentId) throws Exception {
			
			Comment theComment = null;
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			ResultSet myRs = null;
			int commentId;
			
			try {
				// convert comment id to int
				commentId = Integer.parseInt(theCommentId);
				
				// get connection to database
				myConn = dataSource.getConnection();
				
				// create sql to get selected comment
				String sql = "select * from comment where id=?";
				
				// create prepared statement 
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setInt(1, commentId);
				
				// execute statement
				myRs = myStmt.executeQuery();
				
				// retrieve data from result set row
				if(myRs.next()) {
					String userName = myRs.getString("user_name");
					String score = myRs.getString("user_score");
					String userComment = myRs.getString("user_comment");
					String userDate = myRs.getString("user_date");
					
					// use the commentId during construction
					theComment = new Comment(commentId, userName, score, userComment, userDate);
				}
				
				else {
					throw new Exception("Could not find comment id: " +commentId);
				}
				
				return theComment;			
			}
			finally {
				// clean up JDBC object
				close(myConn, myStmt, myRs);
			}
			
		}

		public void updateComment(Comment theComment) throws Exception {
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// get db connection
				myConn = dataSource.getConnection();
				
				// create SQL update statement
				String sql = "update comment "
							+ "set user_name=?, user_score=?, user_comment=?, user_date=? "
							+ "where id=?";
				// prepare statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setString(1, theComment.getUserName());
				myStmt.setString(2, theComment.getScore());
				myStmt.setString(3, theComment.getUserComment());
				myStmt.setString(4, theComment.getUserDate());
				myStmt.setInt(5, theComment.getId());
				
				// execute SQL statement
				myStmt.execute();
			}
			finally {
				// clean up JDBC objects
				close(myConn, myStmt, null);
			}
		}

		public void deleteComment(String theCommentId) throws Exception {
			
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// convert comment id to int
				int commentId = Integer.parseInt(theCommentId);
						
				// get connection to database
				myConn = dataSource.getConnection();
				
				// create sql to delete comment
				String sql = "delete from comment where id =?";
				
				// prepare statement
				myStmt = myConn.prepareStatement(sql);
				
				// set params
				myStmt.setInt(1, commentId);
				
				// execute sql statement
				myStmt.execute();
				
			}
			finally {
				// clean up JDBC code
				close(myConn, myStmt, null);
			}
		}

		public List<Comment> searchComments(String theSearchName) throws Exception {
			List<Comment> comments = new ArrayList<>();
	        
	        Connection myConn = null;
	        PreparedStatement myStmt = null;
	        ResultSet myRs = null;
	        int commentId;
	        
	        try {
	            
	            // get connection to database
	            myConn = dataSource.getConnection();
	            
	            //
	            // only search by name if theSearchName is not empty
	            //
	            if (theSearchName != null && theSearchName.trim().length() > 0) {
	            	
	                // create sql to search for comments by name
	                String sql = "select * from comment where (user_comment) like ?";
	                
	                // create prepared statement
	                myStmt = myConn.prepareStatement(sql);
	                
	                // set params
	                String theSearchNameLike = "%" + theSearchName + "%";
	                myStmt.setString(1, theSearchNameLike);
	                
	                
	            } else {
	            	
	                // create sql to get all comments
	                String sql = "select * from comment order by id DESC";
	                
	                // create prepared statement
	                myStmt = myConn.prepareStatement(sql);
	            }
	            
		            // execute statement
		            myRs = myStmt.executeQuery();
		            
		            // retrieve data from result set row
		            while (myRs.next()) {
	                
	                // retrieve data from result set row
	                int id = myRs.getInt("id");
	                String userName = myRs.getString("user_name");
					String score = myRs.getString("user_score");
					String userComment = myRs.getString("user_comment");
					String userDate = myRs.getString("user_date");
	                
	                // create new comment object
	                Comment tempComment = new Comment(id, userName, score, userComment, userDate);
	                
	                // add it to the list of comments
	                comments.add(tempComment);            
	            }
	            
	            return comments;
	        }
		        finally {
		            // clean up JDBC objects
		            close(myConn, myStmt, myRs);
	        }
		}

		public List<Comment> diffListComment(String diffType) throws Exception {
			
		List<Comment> comments = new ArrayList<>();
			        
			        Connection myConn = null;
			        PreparedStatement myStmt = null;
			        ResultSet myRs = null;
			        int commentId;
			        
			        try {
			            
			            // get connection to database
			            myConn = dataSource.getConnection();
			            
			            //
			            // only search by type if diffType is not empty
			            //
			            if (diffType != null && diffType.trim().length() == 4) {
			            	
			                // create sql to search for comments by name
			                String sql = "select * from comment order by user_score DESC";
			                
			                // create prepared statement
			                myStmt = myConn.prepareStatement(sql);		                
			                
			                
			            } else if (diffType != null && diffType.trim().length() == 7) {
			            	
			            	// create sql to search for comments by name
			                String sql = "select * from comment order by user_score";
			                
			                // create prepared statement
			                myStmt = myConn.prepareStatement(sql);
			                
			                
			            } else if (diffType != null && diffType.trim().length() == 3) {
			            	
			            	// create sql to search for comments by name
			                String sql = "select * from comment order by user_date DESC";
			                
			                // create prepared statement
			                myStmt = myConn.prepareStatement(sql);
			                
			                
			            } else if (diffType != null && diffType.trim().length() == 6) {
			            	
			            	// create sql to search for comments by name
			                String sql = "select * from comment order by user_date";
			                
			                // create prepared statement
			                myStmt = myConn.prepareStatement(sql);
			                
			                
			            }else {
			                // create sql to get all comments
			                String sql = "select * from comment order by id DESC";
			                
			                // create prepared statement
			                myStmt = myConn.prepareStatement(sql);
			                
			                
			            }
			            
			            // execute statement
			            myRs = myStmt.executeQuery();
			            
			            // retrieve data from result set row
			            while (myRs.next()) {
			                
			                // retrieve data from result set row
			                int id = myRs.getInt("id");
			                String userName = myRs.getString("user_name");
							String score = myRs.getString("user_score");
							String userComment = myRs.getString("user_comment");
							String userDate = myRs.getString("user_date");
			                
			                // create new comment object
			                Comment tempComment = new Comment(id, userName, score, userComment, userDate);
			                
			                // add it to the list of comments
			                comments.add(tempComment);            
			            }
			            
			            return comments;
			        }
			        finally {
			            // clean up JDBC objects
			            close(myConn, myStmt, myRs);
			        }
		}
		
}









