<%@page import="cf.cvb14795.Coupon.model.bean.QuestionnaireBean"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理員專用</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/memberDetail.css">
<link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css"/>'>
<link rel="stylesheet" href='<c:url value="/css/memberDetail.css"/>'>
<script src='<c:url value="/js/jquery-3.6.0.js"/>'></script>
<script src='<c:url value="/js/bootstrap.js"/>'></script>
<script src='<c:url value="/js/memberAuth.js"/>'></script>

<style>
.box1 {
	border: 1px solid;
	height: 300px;
	padding: 20px;
}

td, th {
	border: 1px solid;
	width: 200px;
	text-align: center;
}

table {
	border: 1px;
	border-collapse: collapse;
}
</style>

<script>
	$(function() {
		adminAuth();
	})
	
</script>





</head>
<body>
	<input type="hidden" id="isAdmin" value="${isAdmin}">
	<input type="hidden" id="userName" value="${user}">
	<div class="memberDetail">
		<div class="text-right">
			會員： <span id="userNameContainer"></span>
		</div>
		<div class="text-right">
			身分： <span id="isAdminContainer"></span>
		</div>
	</div>

	<form action='admincontroller' method='post'>
		<div>
			<p>
				<label><input type='radio' name='action' value='R'>查詢疫苗接種者</label>
				<label><input type='radio' name='action' value='U'>發放折價券</label>
				<label><input type='radio' name='action' value='B'>撤回</label>
				<label><input type='radio' name='action' value='D'>刪除</label>
			</p>
		</div>
		<div>
			<h6>	註: 撤回與刪除:為方便測試時使用，並非正式功能。</h6>
			<h6>        刪除: 功能為刪除測試帳號 caterpillar123 </h6>
		</div>

		<div>
			<input type='submit' value='送出'> 
			<input type="button"
				value="回首頁" onclick="location.href='<c:url value="/Coupon/frontpage"/>'">
		</div>

	</form>

	<br>
	<br>

	<div class='box1'>
		<%
		List<QuestionnaireBean> beans = (List<QuestionnaireBean>) request.getAttribute("beans");

		if (null != beans && beans.isEmpty()) {
		%>
			<h4>無資料</h4>
		<%
		} else {
		%>
		
			<h1>刪除成功</h1>

		<%
		}
		%>

	</div>
	
</body>
</html>