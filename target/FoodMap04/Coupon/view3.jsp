<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="Coupon.model.QuestionnaireBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理員專用</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/memberDetail.css">
<script src="../js/jquery-3.6.0.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/memberAuth.js"></script>
<script>
	$(function() {
		adminAuth();
	})
</script>

<style>
	.box1{
		border: 1px solid;
		height:300px;
		padding: 20px;
	}
	
	td,th {
		border: 1px solid;
		width: 200px;
		text-align:center;

	}
	
	table {
		 border: 1px; 
		 border-collapse:collapse;
		
	}
	
	
	
	
	
</style>

</head>

<body>
<input type="hidden" id="isAdmin" value="${isAdmin}">
<input type="hidden" id="userName" value="${user}">
<div class="memberDetail">
	<div class="text-right">
		會員：
		<span id="userNameContainer"></span>
	</div>
	<div class="text-right">
		身分：
		<span id="isAdminContainer"></span>
	</div>
</div>

<form action='administratorcontroller' method='post'>
		<div>
			<p><label><input type='radio' name='action' value='R'>查詢疫苗接種者</label>
			<label><input type='radio' name='action' value='U'>發放折價券</label>
			<label><input type='radio' name='action' value='B'>撤回</label></p>
		</div>

		<div>
			<input type='submit' value='送出'>
			<input type="button" value="回首頁" onclick="self.location.href='frontpage.html'">
		</div>

</form>

<br>
<br>

<div class='box1'>
<%
	List<QuestionnaireBean> couponList = (List<QuestionnaireBean>)request.getAttribute("revokeUsersCoupons");

	if (couponList.isEmpty()){
%>
		<h4>無資料</h4>
<%
	} else {
%>
		<table>
			<thead>
				<th>身份證字號</th>
				<th>姓名</th>
				<th>性別</th>
				<th>生日</th>
				<th>電話</th>
				<th>出國</th>
				<th>跨縣市</th>
				<th>接觸</th>
				<th>接種</th>
				<th>發燒</th>
				<th>發送</th>
			</thead>
			<tbody>

<%-- <% --%>
<%-- 		for (QuestionnaireBean user:couponList){  --%>
<%-- %> --%>
		
<!-- 			<tr> -->
<%-- 			<td><%= user.getId() %></td> --%>
<%-- 			<td><%=	user.getName() %></td> --%>
<%-- 			<td><%=	user.getGender() %></td> --%>
<%-- 			<td><%=	user.getBirth() %></td> --%>
<%-- 			<td><%=	user.getPhone() %></td> --%>
<%-- 			<td><%=	user.getForeign() %></td> --%>
<%-- 			<td><%=	user.getMove() %></td> --%>
<%-- 			<td><%=	user.getFamily() %></td> --%>
<%-- 			<td><%=	user.getVaccine() %></td> --%>
<%-- 			<td><%=	user.getFever() %></td> --%>
<%-- 			<td><%=	user.getLabel() %></td> --%>
<!-- 			</tr> -->
<%-- <% --%>
<%-- } --%>
	
<%-- %> --%>

					<c:forEach var="user" items="${revokeUsersCoupons}" >
						<tr>
							<td>${user.id}</td>
							<td>${user.name}</td>
							<td>${user.gender}</td>
							<td>${user.birth}</td>
							<td>${user.phone}</td>
							<td>${user.abroad}</td>
							<td>${user.moving}</td>
							<td>${user.family}</td>
							<td>${user.vaccine}</td>
							<td>${user.fever}</td>
							<td>${user.label}</td>
						</tr>
					</c:forEach>
				</tbody>
		</table>

<%
	}
%>





</div>

</body>
</html>