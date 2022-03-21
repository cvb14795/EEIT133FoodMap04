<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>選擇登入使用者</title>
<link rel="stylesheet" href="<c:url value='/css/admin/css/cs-skin-elastic.css'/>">
<link rel="stylesheet" href="<c:url value='/css/admin/css/admin.css'/>">
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
<script src="<c:url value='/js/jquery-3.6.0.js"'/>"></script>
<script src="<c:url value='/js/bootstrap.js'/>"></script>
<style>
body {
	margin: 5em;
}
img.user-avatar {
	width: 150px;
}
td {
	padding: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<div style="width:700px;height:500px;margin:0px auto;">
<%-- 			<form id="form" action="<c:url value='/'/>"> --%>
				<table  class="table table-hover table-striped">
					<tr style="text-align:center;">
						<th scope="col">頭像</th>
						<th scope="col">帳號</th>
						<th scope="col">名稱</th>
						<th scope="col"></th>
					</tr>
					<c:forEach var="m" items="${mList}">
						<tr>
							<td class="text-center"><img class="user-avatar" src="<c:url value='/Member/user/${m.account}/photo'/>"></td>
							<td class="text-center account">${m.account}</td>
							<td class="text-center">${m.name}</td>
							<td class="text-center">
								<button class="sltBtn btn btn-secondary" type="button">選擇</button>
							</td>
						</tr>
					</c:forEach>	
				</table>
<!-- 			</form> -->
		</div>
	
	</div>
	<script>
		$(".sltBtn").on("click", function(e){
			let isSuccess = false;
			let account = $(e.target).parent().siblings(".account").text();
			let data = {
				"account": account,
				"password": "*",
				"isThirdPartyLogin": true
			};
			$.ajax({
				url:"<c:url value='/Member/Login'/>",
				method:"POST",
				async: false,
				data:data,
				success:function(msg){
					isSuccess = confirm(msg);
				},
				error:function(res, m){
					console.log(res);
					switch(res.status){
						case 400:
							alert("帳號: '"+account+"' 驗證失敗!\n請檢查此帳號是否有在此網站註冊!");
							break;
						case 403:
							alert("帳號: '"+account+"' 驗證失敗!\n此帳號不在您email下列出的帳號清單內!");
							break;
					}
				}
			})
			// 因為是開新視窗 所以是父頁面跳轉而不是本頁跳轉
			if (isSuccess) {
				window.opener.location.href = "<c:url value='/'/>";
				window.close();
			}
			
		})
	</script>
</body>
</html>