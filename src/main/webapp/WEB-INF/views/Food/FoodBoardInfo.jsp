<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%
response.setContentType("text/html;charset=UTF-8");
%>
<html>
<head>
<meta charset="UTF-8">
<title>店家詳細資訊</title>
<!-- favicon -->
<link rel="shortcut icon" type="image/png"
	href="<c:url value='/image/user/favicon.png'/>">
<!-- google font -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap"
	rel="stylesheet">
<!-- fontawesome -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" -->
<!-- 	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" -->
<!-- 	crossorigin="anonymous" /> -->
<link rel="stylesheet" href="<c:url value='/css/user/css/all.min.css'/>">
<!-- owl carousel -->
<link rel="stylesheet"
	href="<c:url value='/css/user/css/owl.carousel.css'/>">
<!-- magnific popup -->
<link rel="stylesheet"
	href="<c:url value='/css/user/css/magnific-popup.css'/>">
<!-- animate css -->
<link rel="stylesheet" href="<c:url value='/css/user/css/animate.css'/>">
<!-- mean menu css -->
<link rel="stylesheet"
	href="<c:url value='/css/user/css/meanmenu.min.css'/>">
<!-- main style -->
<link rel="stylesheet" href="<c:url value='/css/user/css/main.css'/>">
<!-- responsive -->
<link rel="stylesheet"
	href="<c:url value='/css/user/css/responsive.css'/>">
<link rel="stylesheet" href='<c:url value="/css/bootstrap.min.css"/>'>
<link rel="stylesheet" href='<c:url value="/css/memberDetail.css"/>'>
<%-- <link rel="stylesheet" href="<c:url value='/css/index.css'/>"> --%>
<!-- 開關改成IOS風格(左右滑動按鈕) -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.css">

<!-- 語君評論區css -->
<link rel="stylesheet" href="/FoodMap04/css/comment-style.css">

<script
	src="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.min.js"></script>
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
			size : 'small',
			checked : false,
			onChange : function() {
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

.wrap {
	margin-top: 50px;
}

.mapHeader {
	margin-bottom: 50px;
}

#mapImg {
	width: 100%;
	height: 100%;
	/* margin-top: 30px; */
	padding-top: 55px;
	/* padding-bottom: 30px */
}

#comments {
	text-align: center;
}

img.user-avatar {
	width: 40px;
}

fieldset {
	font-weight: bold;
	color: black;
	background-color: #ffffcc;
	border: 1px solid #cccccc;
	padding: 4px 2px;
}
</style>
</head>
<body>
	<input type="hidden" id="isAdmin" value="${isAdmin}">
	<input type="hidden" id="userName" value="${user}">
	<!--PreLoader-->
	<div class="loader">
		<div class="loader-inner">
			<div class="circle"></div>
		</div>
	</div>
	<!--PreLoader Ends-->

	<!-- header -->
	<div class="top-header-area " id="sticker">
		<div class="container">
			<!-- logo -->
			<div class="site-logo">
				<a href="<c:url value='/'/>"> <img
					src="<c:url value='/image/user/logo.png'/>" alt="" width="65%"
					height="65%">
				</a>
			</div>
			<!-- end logo -->
		</div>
		<div>
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-sm-12 text-center">
					<div class="main-menu-wrap">
						<!-- menu start -->
						<nav class="main-menu navbar navbar-expand-lg">
							<ul>
								<li><a href="<c:url value='/'/>">首頁</a></li>
								<li class="current-list-item"><a
									href="<c:url value='/Food/user'/>">商家資訊</a></li>
								<li><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
								<li><a href="<c:url value='/Event/'/>">活動總覽</a></li>
								<li><a href="<c:url value='/comments/list'/>">評論專區</a></li>
								<li><a href="<c:url value='/Recipe/user'/>">食譜規劃</a></li>
								<li><a href="<c:url value='/Shop/'/>">美食商城</a>
								<li>
									<div class="header-icons">
										<!-- <a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a> -->
										<li><a><img class="user-avatar rounded-circle" src="" />
												會員：<span id="user"></span></a>
											<ul class="sub-menu">
												<li id="editNavBtn"><a
													href="<c:url value='/Member/Revise'/>"><i
														class="fas fa-edit"></i> 修改個人資料</a></li>
												<li id="registerNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Register'/>"><i
														class="fas fa-user-plus"></i> 註冊會員</a></li>
												<li id="loginNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Login'/>"><i
														class="fas fa-sign-in-alt"></i> 登入</a></li>
												<li id="logoutNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Logout'/>"><i
														class="fas fa-sign-out-alt"></i> 登出</a></li>
											</ul></li>

										<li>
											<div class="header-icons">
												<a class="shopping-cart"
													href="<c:url value="/Recipe/user/showMyfavorites"/>"> <i
													class="fas fa-heart"></i>
												</a> <a class="shopping-cart" href="<c:url value='/Shop/Cart'/>">
													<i class="fas fa-shopping-cart"></i>
												</a>
											</div>
										</li>
										<!--           <li> -->
										<!--            <a> -->
										<!--             淺色模式 -->
										<!--  <!--            <label for="checkbox-switch" style="">淺色模式</label> -->
										<!--             <input type="checkbox" id="checkbox-switch" class="checkbox-switch" /> -->
										<!--            </a> -->
										<!--           </li> -->
									</div>
								</li>
							</ul>
						</nav>
						<a class="mobile-show search-bar-icon" href="#"><i
							class="fas fa-search"></i></a>
						<div class="mobile-menu"></div>
						<!-- menu end -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end header -->
	<!-- breadcrumb-section -->
	<div class="breadcrumb-section breadcrumb-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="breadcrumb-text">
						<p>商家資訊</p>
						<h1>店家評價</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->

	<div class="mb-150 wrap">
		<div class="container mapHeader">
			<div class="row">
				<div class="col-lg-4 col-md-12" id="mapImg">
					<img src="<c:url value='/Food/user/photo/${mapData.id}'/>">
				</div>
				<div class="col-lg-8 col-md-12" id="comments"></div>
			</div>
		</div>


		<div class="container mb-150">
			<fieldset>
				<legend>店家資料</legend>

				<label>店名:<em> ${mapData.mapname} </em></label>
				<hr>

				<label>地址:<em> ${mapData.mapku} </em></label>
				<hr>

				<label>電話:<em> ${mapData.mapnb}</em></label>
				<hr>

				<label>經緯度:<em> ${mapData.mapxy}</em></label>
				<hr>

				<label>是否為安全商家:<em> ${mapData.mapcheck} </em></label>
				<hr>

				<label>分類:<em> ${mapData.category} </em></label>
				<hr>

			</fieldset>
			
			<input id="backBtn" type="button" name="back" value="回首頁">
		</div>


		<!-- footer -->
		<div class="footer-area">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-12">
						<div class="footer-box about-widget">
							<h2 class="widget-title">網站特色</h2>
							<p>
								1.此平台除了能快速找出使用者想要的美食資訊<br />也會提供google map地圖來讓使用者了解與美食的距離。
							</p>
							<p>
								2.人們在購買東西時，都希望會有折扣，因此平台也會有特約餐廳的優惠券<br />以及針對已接種疫苗之民眾的專屬優惠券。
							</p>
							<p>3.打造適合自己的專屬食譜，實現健康生活。</p>
							<p>4.平台定期舉辦一些會員活動，活絡會員間的感情。</p>
						</div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="footer-box">
							<h2 class="widget-title">Line 智慧客服</h2>
							<ul>
								<li><a href="" id="lineLoginHref"><input type="button"
										class="lineLogin"
										style="width: 151px; height: 44px; border: none" /></a></li>
								<li><img src="<c:url value='/image/lineFoodMap04QR.png'/>"
									alt=""></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-2 col-md-6">
						<div class="footer-box pages">
							<h2 class="widget-title">導覽</h2>
							<ul>
								<li><a href="<c:url value='/'/>">首頁</a></li>
								<li><a href="<c:url value='/aboutUs'/>">關於我們</a></li>
								<li><a href="<c:url value='/Food/Fooddex'/>">商家資訊</a></li>
								<li><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
								<li><a href="<c:url value='/Event/'/>">活動總覽</a></li>
								<li><a href="<c:url value='/comments/list'/>">評論專區</a></li>
								<li><a href="<c:url value='/Recipe/user'/>">食譜規劃</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end footer -->

		<!-- copyright -->
		<div class="copyright">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-12">
						<p>
							Copyrights &copy; 2019 - <a href="https://imransdesign.com/">Imran
								Hossain</a>, All Rights Reserved.
						</p>
					</div>
					<div class="col-lg-6 text-right col-md-12">
						<div class="social-icons">
							<ul>
								<li><a href="#" target="_blank"><i
										class="fab fa-facebook-f"></i></a></li>
								<li><a href="#" target="_blank"><i
										class="fab fa-twitter"></i></a></li>
								<li><a href="#" target="_blank"><i
										class="fab fa-instagram"></i></a></li>
								<li><a href="#" target="_blank"><i
										class="fab fa-linkedin"></i></a></li>
								<li><a href="#" target="_blank"><i
										class="fab fa-dribbble"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end copyright -->
		<!-- count down -->
		<script src="<c:url value='/js/user/js/jquery.countdown.js'/>"></script>
		<!-- isotope -->
		<script src="<c:url value='/js/user/js/jquery.isotope-3.0.6.min.js'/>"></script>
		<!-- waypoints -->
		<script src="<c:url value='/js/user/js/waypoints.js'/>"></script>
		<!-- owl carousel -->
		<script src="<c:url value='/js/user/js/owl.carousel.min.js'/>"></script>
		<!-- magnific popup -->
		<script
			src="<c:url value='/js/user/js/jquery.magnific-popup.min.js'/>"></script>
		<!-- mean menu -->
		<script src="<c:url value='/js/user/js/jquery.meanmenu.min.js'/>"></script>
		<!-- sticker js -->
		<script src="<c:url value='/js/user/js/sticker.js'/>"></script>
		<!-- main js -->
		<script src="<c:url value='/js/user/js/main.js'/>"></script>
		<!-- userNameMain js -->
		<script src="<c:url value='/js/userNameMain.js'/>"></script>
		<!-- vegas js -->
		<script src="<c:url value='/js/vegas.js'/>"></script>

		<script>
			var backBtn = document.getElementById("backBtn");
			backBtn.addEventListener("click", function(e) {
				location.href = "<c:url value='/Food/user'/>";
			})

			$("#comments").load("<c:url value='/comments/list'/> #rate-header")
		</script>

		<script>
			var src = "<c:url value='/Member/user/${user}/photo'/>";
			userNameMain(src);
		</script>
</body>
</html>