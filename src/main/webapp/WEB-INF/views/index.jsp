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
<link rel="stylesheet" href="./css/lineLogin.css">
<!-- 開關改成IOS風格(左右滑動按鈕) -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.css">
<script src="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.min.js"></script>
<!-- 暗黑模式 -->
<script
	src="https://cdn.jsdelivr.net/npm/darkmode-js@1.5.7/lib/darkmode-js.min.js"></script>
<script src="./js/jquery-3.6.0.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/lineLogin.js"></script>
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
	$('document').ready(function() {
		$('#tabs a').click(function(e) { //當按下 id=tabs 中的超連結時
			e.preventDefault(); //阻止事件向上提升 (處理一次)    
			var url = $(this).attr("data-url"); //取得 data-url 屬性值
			var href = this.hash; //取得 href 值
			var pane = $(this); //轉成 DOM 物件
			// console.log(url, href, pane);
			//若點擊首頁則改為頁籤顯示
			if (href == "#home" || href == "#darkmode") {
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
	<nav class="navbar navbar-default" id="nav">
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
							data-url="./resources/homepage.html">首頁</a></li>
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
						<li>
							<a data-toggle="tab" href="#darkmode" data-url="">
								<label for="checkbox-switch">暗黑模式</label>
								<input type="checkbox" id="checkbox-switch" class="checkbox-switch"/>							
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	
	<!-- line加好友按鈕 -->
	<div>
		<span>快加入我們的Line好友：FoodMap美食地圖</span>
		<div class="line-it-button" data-lang="zh_Hant" data-type="friend" data-lineid="@lineteamjp" style="display: none;"></div></br>
		<a href="" id="lineLoginHref">
			<input type="button" class="lineLogin" style="width: 151px;height: 44px;border:none"/>
		</a>
		
	</div>
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
			setLineOAuthUrl();
			var darkmode = new Darkmode({
				saveInCookies: true, // default: true,
			});
			// 將所有checkbox-switch改成IOS風格
			var el = document.querySelector('.checkbox-switch');
			var nav = document.getElementById("nav");
			var mySwitch = new Switch(el, {
				size: 'small',
				checked:false,
				onChange: function(){
					darkmode.toggle();
					nav.classList.toggle("navbar-default");
					nav.classList.toggle("navbar-dark");
					nav.classList.toggle("bg-dark");
				}
			});
			// 先關閉new完後預設自動轉暗黑模式的動作
// 			darkmode.toggle();
			
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
	<script src="https://www.line-website.com/social-plugins/js/thirdparty/loader.min.js" async defer></script>
</body>

</html>