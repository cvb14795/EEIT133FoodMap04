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
<script src="../js/jquery-3.6.0.js"></script>
<script src="../js/bootstrap.js"></script>
<script>
	$(function () {
		const cookies = `${document.cookie}`;
		console.log(`cookies: ${cookies}`);
		let userName = cookies.split("user=")[1];
		$("#user").text(`${userName}`);
	})
</script>
</head>
<body>
	<div class="text-right memberDetail">
		您好，<span id="user"></span>！
	</div>

	<form action='admincontroller' method='post'>
		<div>
			<p>
				<label><input type='radio' name='action' value='R'>查詢疫苗接種者</label>
				<label><input type='radio' name='action' value='U'>發放折價券</label>
				<label><input type='radio' name='action' value='B'>撤回</label>
		</div>

		<div>
			<input type='submit' value='送出'> <input type="button"
				value="首頁" onclick="location.href='<c:url value='/frontpage' />'">
		</div>

	</form>


</body>
</html>