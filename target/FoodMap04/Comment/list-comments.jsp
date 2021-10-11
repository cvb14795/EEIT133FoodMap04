<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
 
<head>
	<title>評論區</title>
	
	<link type="text/css" rel="stylesheet" href="../css/comment-style.css">
</head>



<body>

	<div id="wrapper">
		<div id="header">
			<h2>評論區</h2>
		</div>
	</div>
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Comment -->
			
			<input type="button" value="新增評論" 
				onclick="window.location.href='add-comment-form.jsp'; return false;"
				class="add-comment-button"
			/>
			
			<!--  add a search box -->
            <form action="CommentControllerServlet" method="GET">
        
                <input type="hidden" name="command" value="SEARCH" />
                
            	<div id="search">
                搜尋評論關鍵字： <input type="text" name="theSearchName" />                
                <input type="submit" value="搜尋" class="add-comment-button" />
            	</div>
            	
            </form>
            
            
            <!--  add a different type of list comments box -->
            <form action="CommentControllerServlet" method="GET">
        
                <input type="hidden" name="command" value="DIFFLIST" />
            	<div id="search">
                排序方式： <select name="diffType" onchange="this.form.submit()">
                <option value="">請選擇排序方式</option>
                <option value="high">評分高 → 低</option>
                <option value="lowhigh">評分低 → 高</option>
                <option value="new">時間新 → 舊</option>
                <option value="oldnew">時間舊 → 新</option>
            	</select>
            	
            	<!--<input type="submit" value="Submit">  -->
            	</div>
            </form>
			
			
			<div id = "comment">
				
				<c:forEach var="tempComment" items="${COMMENT_LIST}">
				
					<!-- set up a link for each comment -->
					<c:url var="tempLink" value="CommentControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="commentId" value="${tempComment.id}" />
					</c:url>
					
					<!-- set up a link to delete a comment -->
					<c:url var="deleteLink" value="CommentControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="commentId" value="${tempComment.id}" />
					</c:url>
					
					
					<div id = "commentArea">
						<h3>${tempComment.userName}</h3>
						<img src="<%= request.getContextPath() %>/images/star${tempComment.score}.png">
								|
						<label>${tempComment.userDate}</label><br /><br />
						<label>${tempComment.userComment}</label><br /><br />
					
						<div id = "ud">
						<a href="${tempLink}">編輯</a> 
								  |
								 <a href="${deleteLink}"
								 onclick="if(!(confirm('確定要刪除評論？')))return false">
								 刪除</a>
						</div>		 
          			</div>
          							
				</c:forEach>
		
			</div>
		</div>
	</div>
</body>


</html>