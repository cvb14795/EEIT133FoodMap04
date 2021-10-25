<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <meta name="google-signin-client_id"
        content="196642336489-5j9n6rtmidbccrubh6vf406gve5cejrn.apps.googleusercontent.com"> -->
<title>首頁</title>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
<link rel="stylesheet" href="./css/bootstrap.min.css">
<script src="./js/jquery-3.6.0.js"></script>
<script src="./js/bootstrap.js"></script>
<!-- <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script> -->

<script>
	/* 待完工 */
	//接收登入狀態並alert
	/* 待完工 */

	// function signOut() {
	//     var auth2 = gapi.auth2.getAuthInstance();
	//     auth2.signOut().then(function () {
	//         console.log('User signed out.');
	//     });
	// }
	$(document).ready(function() {
		$('#tabs a').click(function(e) { //當按下 id=tabs 中的超連結時
			e.preventDefault(); //阻止事件向上提升 (處理一次)    
			var url = $(this).attr("data-url"); //取得 data-url 屬性值
			var href = this.hash; //取得 href 值
			var pane = $(this); //轉成 DOM 物件
			// console.log(url, href, pane);
			//若點擊首頁則改為頁籤顯示
			if (href == "#home") {
				$(href).load(url, function(result) { //載入頁嵌內容
					console.log("點擊頁面: " + href);
					// $(`a[href="${url}"]`).tab('show');                    //將此頁籤設為顯示 (active)
					$(this).tab('show');
				});
				// 否則直接跳轉至外部頁面
			} else {
				location.href = url;
			}
		});
	});
</script>
</head>

<body>
	<nav class="navbar navbar-default" id="headerNav">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="./Home">期中專題2</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<!-- nav-collapse: 頁寬太小時後改為直行顯示-->
			<div id="tabs">
				<div class="nav-collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#home"
							data-url="./homepage.html">首頁</a></li>
						<!-- 晉豪 -->
						<li><a data-toggle="tab" href="#food"
							data-url="./Food/FoodMap">商家資訊</a></li>
						<!-- 耿豪 -->
						<li><a data-toggle="tab" href="#coupon"
							data-url="./Coupon/frontpage">防疫專區</a></li>
						<!-- 玴辰 -->
						<!-- 注意 name 不要一樣 -->
						<li><a data-toggle="tab" href="#event"
							data-url="./Event/eventjava">活動報名</a></li>
						<!-- 峻豪 -->
						<li><a data-toggle="tab" href="#recipe"
							data-url="./Recipe/admin">營養資訊</a></li>
						<!-- 語君 -->
						<li><a data-toggle="tab" href="#comment"
							data-url="./Comment/CommentControllerServlet">評論區</a></li>
						<!-- 下拉式選單 -->
						<!-- <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                aria-expanded="false">測試選單1 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li> -->
					</ul>
					<ul class="nav navbar-nav navbar-right nav-tabs">
						<li><a data-toggle="tab" href="#" data-url=""> 您好，<span
								id="user"></span>！
						</a></li>
						<li id="editNavBtn"><a data-toggle="tab"
							href="./Member/Revise" data-url="./Member/Revise"> 修改會員資料<i
								class="fas fa-edit"></i>
						</a></li>
						<li id="registerNavBtn"><a data-toggle="tab"
							href="./Member/Register" data-url="./Member/Register"> 註冊會員 <i
								class="fas fa-user-plus"></i>
						</a></li>
						<li id="loginNavBtn"><a data-toggle="tab"
							href="./Member/Login" data-url="./Member/Login"> 登入 <i
								class="fas fa-sign-in-alt"></i>
						</a></li>
						<li id="logoutNavBtn"><a data-toggle="tab"
							href="./Member/Logout" data-url="./Member/Logout"> 登出 <i
								class="fas fa-sign-out-alt"></i>
						</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>

	<div class="tab-content" style="padding: 10px;" id="headerTabs">
		<div class="tab-pane active" id="home"></div>
		<!-- 待新增 其他頁面 -->

		<!-- 晉豪 -->
		<div class="tab-pane" id="food"></div>

		<!-- 耿豪 -->
		<div class="tab-pane" id="coupon"></div>

		<!-- 玴辰 -->
		<div class="tab-pane" id="event"></div>

		<!-- 峻豪 -->
		<div class="tab-pane" id="recipe"></div>

		<!-- 語君 -->
		<div class="tab-pane" id="comment"></div>
	</div>
	<script>
		$(function() {
			const cookies = document.cookie;
			console.log("cookies:"+cookies);
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
</body>

</html>