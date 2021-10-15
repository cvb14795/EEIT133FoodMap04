<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>

<head>
	<title>評論區</title>
	
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />
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
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button"
			/>
			<!--  add a search box -->
			<form:form action="search" method="GET">
				搜尋評論關鍵字： <input type="text" name="theSearchComment" />
				
				<input type="submit" value="搜尋" class="add-button" />
			</form:form>
			
			<!--  add our html table here -->
		
			<table>
				<tr>
					<th>姓名</th>
					<th>評分</th>
					<th>評論</th>
					<th>修改/刪除</th>
				</tr>
				
				<!-- loop over and print our comments -->
				<c:forEach var="tempComment" items="${comments}">
				
					<!-- construct an "update" link with comment id -->
					<c:url var="updateLink" value="/comment/showFormForUpdate">
						<c:param name="commentId" value="${tempComment.id}" />
					</c:url>					

					<!-- construct an "delete" link with comment id -->
					<c:url var="deleteLink" value="/comment/delete">
						<c:param name="commentId" value="${tempComment.id}" />
					</c:url>					
					
					<tr>
						<td> ${tempComment.userName} </td>
						<td> ${tempComment.score} </td>
						<td> ${tempComment.userComment} </td>
						
						<td>
							<!-- display the update link -->
							<a href="${updateLink}">修改</a>
							|
							<a href="${deleteLink}"
							   onclick="if (!(confirm('確定要刪除評論？'))) return false">刪除</a>
						</td>
						
					</tr>
				
				</c:forEach>
						
			</table>
				
		</div>
	
	</div>
	

</body>

</html>








