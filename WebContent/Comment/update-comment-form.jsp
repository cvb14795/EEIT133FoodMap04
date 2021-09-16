<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>更改評論</title>
	
	<link type="text/css" rel="stylesheet" href="../css/comment-style.css">	
	<link rel="stylesheet" href="../css/jquery.rating.css" />
		
	<script type="text/javascript" src="../js/comment-validation.js"></script>	
	<script src="../js/jquery.js"></script>
    <script src="../js/jquery.rating.js"></script>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>評論區</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="editarea">
		<h3>編輯評論</h3>
		<form action="CommentControllerServlet" method="GET" name="commentForm" onSubmit="return validateForm()">
		
			<input type="hidden" name="command" value="UPDATE"/>
			
			<input type="hidden" name="commentId" value="${THE_COMMENT.id}"/>
			
			
			<label>姓名：</label>
			<p class="note">(1.不可空白 2.10字以內)</p>
			<input type="text" name="userName" id="name" onblur="return checkname()" value="${THE_COMMENT.userName}"/>
			<span id="idsp"></span><br /><br />
			
			<label>評分：</label><div>
			<input type="radio" name="score" value="1" class="star">
            <input type="radio" name="score" value="2" class="star">
            <input type="radio" name="score" value="3" class="star">
            <input type="radio" name="score" value="4" class="star">
            <input type="radio" name="score" value="5" class="star">
            </div><br /><br />
					
			
			<label>評論：</label>
			<p class="note">(1.不可空白 2.100字以內)</p>
			<textarea id="commentarea" name="userComment" cols="40" rows="5" onblur="return checkcommentarea()"/>${THE_COMMENT.userComment}</textarea>
			<span id="idsp3"></span><br /><br /><br />		
			
			
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<input type="hidden" name="userDate" value="<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />"/>
			
			
			<label></label>
			<input type="submit" value="儲存" class="save"/>
			
		</form>
		
		
		<p>
			<a href="CommentControllerServlet">回到評論區</a>
		</p>
		</div>
	</div>
</body>

</html>