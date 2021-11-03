<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
response.setContentType("text/html;charset=UTF-8");
%>
<html>
<head>
<meta charset="UTF-8">
<title>使用者起始畫面</title>
<link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css"/>'>
<link rel="stylesheet" href='<c:url value="/css/memberDetail.css"/>'>
<link rel="stylesheet" href="<c:url value='/css/index.css'/>">
<!-- 開關改成IOS風格(左右滑動按鈕) -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.css">
<script src="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.min.js"></script>
<script src='<c:url value="/js/jquery-3.6.0.js"/>'></script>
<script src='<c:url value="/js/bootstrap.js"/>'></script>
<script src='<c:url value="/js/memberAuth.js"/>'></script>
<script>
	$(function() {
		adminAuth();
		// 將所有checkbox-switch改成IOS風格
		var el = document.querySelector('.checkbox-switch');
		var nav = document.querySelector("nav");
		var mySwitch = new Switch(el, {
			size: 'small',
			checked:false,
			onChange: function(){
// 					darkmode.toggle();
				nav.classList.toggle("navbar-default");
				nav.classList.toggle("navbar-dark");
				nav.classList.toggle("bg-dark");

				if (mySwitch.getChecked()) {
					console.log("nav:深色模式");
					$(".navbar").css("background-color", "#ffc078");
					$("ul.nav li a").css("color", "#333")
					$("ul.nav li a:hover").css("color", "#EA7500")
					$("ul.nav li a:hover").css("background-color", "#333")
					$("label[for='checkbox-switch']").text("深色模式")
				} else {
					console.log("nav:淺色模式");
					$(".navbar").css("background-color", "#333");
					$("ul.nav li a").css("color", "#ffc078");
					$("ul.nav li a:hover").css("color", "#333")
					$("ul.nav li a:hover").css("background-color", "#EA7500")
					$("label[for='checkbox-switch']").text("淺色模式")
				}
			}														
		});
		// 獲取使用者資訊
		const cookies = document.cookie;
		console.log("cookies:" + cookies);
		let userName = cookies.split("user=")[1];
		if (userName === undefined) {
			console.log("使用者名稱:未定義 ");
			// 未登入狀態下(登出後)顯示登入按鈕及註冊按鈕
			$("#loginNavBtn").show();
			$("#registerNavBtn").show();
			// 未登入狀態下(登出後)隱藏登出按鈕及編輯按鈕
			$("#logoutNavBtn").hide();
			$("#editNavBtn").hide();
			// 未登入顯示為Guest
			$("#user").text("Guest");
			console.log(document.getElementById("user").outerHTML);
		} else {
			console.log(`使用者名稱:${userName}`);
			// 已登入狀態下(登入後)隱藏登入按鈕及註冊按鈕
			$("#loginNavBtn").hide();
			$("#registerNavBtn").hide();
			// 已登入狀態下(登入後)顯示登出按鈕及編輯按鈕
			$("#logoutNavBtn").show();
			$("#editNavBtn").show();
			// 登入後顯示用戶名稱
			$("#user").text(userName);
		}
	})
</script>
<style>
    header {
	background: #9393FF;
	color: white;
	padding: 20px;
	text-align: center;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<input type="hidden" id="isAdmin" value="${isAdmin}">
	<input type="hidden" id="userName" value="${user}">
    <nav class="navbar navbar-default">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value='/Home'/>">期中專題2</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<!-- nav-collapse: 頁寬太小時後改為直行顯示-->
			<div class="nav-collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav mr-auto">
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/Home'/>">首頁</a>
					</li>
					<!-- 晉豪 -->
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/Food/Fooddex'/>">商家資訊</a>
					</li>
					<!-- 耿豪 -->
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/Coupon/frontpage'/>">防疫專區</a>
					</li>
					<!-- 玴辰 -->
					<!-- 注意 name 不要一樣 -->
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/Event/eventjava'/>">活動報名</a>
					</li>
					<!-- 語君 -->
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/comments/list'/>">評論專區</a>
					</li>
					<!-- 峻豪 -->
					<li class="nav-item">
						<a class="nav-link" href="<c:url value='/Recipe/user'/>">營養資訊</a>
					</li>
					<!-- 下拉式選單 -->
					<!-- <li class="dropdown">
						<a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">測試選單1 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="">Action</a></li>
							<li><a href="">Another action</a></li>
							<li><a href="">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="">Separated link</a></li>
						</ul>
					</li> -->
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="nav-item">
						<a class="nav-link" href=""> 您好，<span id="user"></span>！</a>
					</li>
					<li id="editNavBtn" class="nav-item">
						<a class="nav-link" href="./Member/Revise"> 修改會員資料
							<i class="fas fa-edit"></i>
						</a>
					</li>
					<li id="registerNavBtn" class="nav-item">
						<a class="nav-link" href="./Member/Register"> 註冊會員
							<i class="fas fa-user-plus"></i>
						</a>
					</li>
					<li id="loginNavBtn" class="nav-item">
						<a class="nav-link" href="./Member/Login"> 登入 
							<i class="fas fa-sign-in-alt"></i>
						</a>
					</li>
					<li id="logoutNavBtn" class="nav-item">
						<a class="nav-link" href="./Member/Logout"> 登出
							<i class="fas fa-sign-out-alt"></i>
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">
							<label for="checkbox-switch">深色模式</label>
							<input type="checkbox" id="checkbox-switch" class="checkbox-switch"/>							
						</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
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
	
    <form action='<c:url value="/Recipe/user/UserRecipe"/>'>
        <button type="submit">食譜</button>
    </form>
<!--     <form action=""> -->
<!--         <button type="submit">營養</button> -->
<!--     </form> -->
</body>
</html>