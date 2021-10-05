package commentsJDBC;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
 
/**
 * Servlet implementation class CommentControllerServlet
 */
@WebServlet("/Comment/CommentControllerServlet")
public class CommentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CommentDbUtil commentDbUtil;
	
	@Resource(name="jdbc/TeamProject")
	private DataSource dataSource;
	
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our comment db util ... and pass in the conn pool / datasource
		try {
			commentDbUtil = new CommentDbUtil(dataSource);
		}
		catch(Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing comments
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listComments(request, response);
				break;
				
			case "ADD":
				addComment(request, response);
				break;
				
			case "LOAD":
				loadComment(request, response);
				break;
				
			case "UPDATE":
				updateComment(request, response);
				break;
				
			case "DELETE":
				deleteComment(request, response);
				break;
				
			case "SEARCH":
                searchComments(request, response);
                break;
                
			case "DIFFLIST":
                diffListComments(request, response);
                break;
				
			default:
				listComments(request, response);
			}
		
		} 
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	private void diffListComments(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{

		// read search name from form data
        String diffType = request.getParameter("diffType");
        
        // Set UTF-8
     	request.setCharacterEncoding("UTF-8");
     	response.setCharacterEncoding("UTF-8");
        
        // search students from db util
        List<Comment> comments = commentDbUtil.diffListComment(diffType);
        
        // add students to the request
        request.setAttribute("COMMENT_LIST", comments);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("./list-comments.jsp");
        dispatcher.forward(request, response);
		
	}

	private void searchComments(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{

		// read search name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // Set UTF-8
     	request.setCharacterEncoding("UTF-8");
     	response.setCharacterEncoding("UTF-8");
        
        // search comments from db util
        List<Comment> comments = commentDbUtil.searchComments(theSearchName);
        
        // add comments to the request
        request.setAttribute("COMMENT_LIST", comments);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("./list-comments.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteComment(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		
		// read comment id from form data
		String theCommentId = request.getParameter("commentId");
		
		// delete comment from database
		commentDbUtil.deleteComment(theCommentId);
		
		// send them back to "list comments" page
		listComments(request, response);
	}

	private void updateComment(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		
		// read comment info from form data
		int id = Integer.parseInt(request.getParameter("commentId"));
		String userName = request.getParameter("userName");
		String score = request.getParameter("score");
		String userComment = request.getParameter("userComment");
		String userDate = request.getParameter("userDate");
		
		// set UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// create a new comment object
		Comment theComment = new Comment(id, userName, score, userComment, userDate);
				
		// perform update on database
		commentDbUtil.updateComment(theComment);
		
		// send them back to the "list comments" page
		listComments(request,response);
		
	}

	private void loadComment(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		
		// read comment id from form data
		String theCommentId = request.getParameter("commentId");
		
		// set UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// get comment from database (db util)
		Comment theComment = commentDbUtil.getComment(theCommentId);
		
		// place comment in the request attribute
		request.setAttribute("THE_COMMENT", theComment);
		
		// send to jsp page: update-comment-form.jsp
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("./update-comment-form.jsp");
		dispatcher.forward(request,response);
	}

	private void addComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read comment info from the form data
		String userName = request.getParameter("userName");
		String score = request.getParameter("score");
		String userComment = request.getParameter("userComment");
		String userDate = request.getParameter("userDate");
		
		// Set UTF-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// create a new comment object
		Comment theComment = new Comment(userName, score, userComment, userDate);
		
		// add the comment to the database
		commentDbUtil.addComment(theComment);
		
		// send back to the main page (the comment list)
		listComments(request, response);
	}

	private void listComments(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		// get comments from db util
		List<Comment> comments = commentDbUtil.getComments();
		
		// add comments to the request
		request.setAttribute("COMMENT_LIST", comments);
		
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("./list-comments.jsp");
		dispatcher.forward(request, response);
	}

}
