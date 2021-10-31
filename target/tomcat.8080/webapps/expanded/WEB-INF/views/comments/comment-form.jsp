<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<title>新增評論</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value='/css/comment-style.css'/>" />
<script type="text/javascript" src="<c:url value='/js/comment-validation.js'/>"></script>	
<script src='<c:url value="/js/jquery-3.6.0.js"/>'></script>		
</head>

<body>

	
	<div class="container">
	
		<h3>評論區</h3>
		<hr>
		
		<p class="h4 mb-4">新增評論</p>
		
		<form:form action="" id="commentForm"
					modelAttribute="comment" method="POST" name="commentForm"
					onSubmit="return validateForm()">
						 
			<!-- Add hidden form field to handle update -->
			<form:input type="hidden" path="id" />
<%-- 			<input type="hidden" value="${#dates.format(now, 'yyyy-MM-dd')}" name="userDate" > --%>
			<form:input type="hidden" path="userDate" id="userDate"/>
			<input type="hidden" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${now}"/>' id="now"/>
					
			 
			<form:input type="text" path="userName" id="name"
					class="form-control mb-4 col-4" placeholder="姓名(1.不可空白 2.100字以內)" onblur="'checkname()'"/>
			<span id="idsp"></span><br />
					
			<!-- Ratting system -->
			<label>評分(不可空白)：</label>	<br>
			<div class="rate">
<!-- 			    <input type="radio" id="star5" name="score" value="5" /> -->
				<form:radiobutton id="star5" path="score" value="5"/>  
			    <label for="star5" title="text"></label>
			    <form:radiobutton id="star4" path="score" value="4" />
			    <label for="star4" title="text"></label>
			    <form:radiobutton id="star3" path="score" value="3" />
			    <label for="star3" title="text"></label>
			    <form:radiobutton id="star2" path="score" value="2" />
			    <label for="star2" title="text"></label>
			    <form:radiobutton id="star1" path="score" value="1" />
			    <label for="star1" title="text"></label>
		 	 </div>
		 	 <br><br><br>
			
			<input type="hidden" id="userComment" value="${comment.userComment}"/>
			<form:textarea  id="commentarea" path="userComment" class="form-control input-sm" rows="5" placeholder="評論區(1.不可空白 2.100字以內)" onblur="'checkcommentarea'" />
			<span id="idsp3"></span><br /><br /><br />			
			
			<button type="submit" class="btn btn-info col-2">送出</button>
		
		</form:form >
		
		<hr>
		<a href="<c:url value='/comments/list'/>">回到評論區</a>
	
	</div>
	
	<script type="text/javascript">
		var userDate = $("#userDate").val()
		var userComment = $("#userComment").val()
		var date = $("#now").val()
		$("#userDate").val(date)
		$("#commentarea").text(userComment)
		$("#commentForm").attr("action", "<c:url value='/comments/save'/>")
		
	</script>
	
	
</body>

</html>










