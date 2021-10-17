<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    
<!DOCTYPE html>
<html>

<head>
	<title>儲存評論</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-comment-style.css">
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>評論區</h2>
		</div>
	</div>

	<div id="container">
		<h3>儲存評論</h3>
	
		<form:form action="saveComment" modelAttribute="comment" method="POST">

			
			<form:hidden path="id" />
					
			<table>
				<tbody>
					<tr>
						<td><label>姓名：</label></td>
						<td><form:input path="userName" /></td>
					</tr>
				
					<tr>
						<td><label>評分：</label></td>
						<td><form:input path="score" /></td>
						<td>(1~5)</td>
					</tr>

					<tr>
						<td><label>評論：</label></td>
						<td><form:input path="userComment" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="送出" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/comment/list">回到評論區</a>
		</p>
	
	</div>

</body>

</html>










