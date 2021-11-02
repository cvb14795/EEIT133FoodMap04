<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理員專用</title>
<link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css"/>'>
<link rel="stylesheet" href='<c:url value="/css/memberDetail.css"/>'>
<script src='<c:url value="/js/jquery-3.6.0.js"/>'></script>
<script src='<c:url value="/js/bootstrap.js"/>'></script>
<script src='<c:url value="/js/memberAuth.js"/>'></script>
<script>
	$(function() {
		adminAuth();
	})
	
</script>

<!-- <script> -->
<!-- // 	$(function () { -->
<%-- // 		const cookies = `${document.cookie}`; --%>
<%-- // 		console.log(`cookies: ${cookies}`); --%>
<!-- // 		let userName = cookies.split("user=")[1]; -->
<%-- // 		$("#user").text(`${userName}`); --%>
<!-- // 	}) -->
<!-- </script> -->

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
		</div>
		<div>
			<h6>	註: 撤回與刪除:為方便測試時使用，並非正式功能。</h6>
			<h6>        刪除: 功能為刪除測試帳號 caterpillar123 </h6>
		</div>

		<div>
			<input type='submit' value='送出'> 
			<input type="button"
				value="首頁" onclick="location.href='<c:url value="/Coupon/frontpage"/>'">
		</div>

	</form>
	



</body>
</html>