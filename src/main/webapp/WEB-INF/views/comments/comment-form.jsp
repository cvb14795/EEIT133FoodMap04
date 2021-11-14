<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<style>
	.mhw {
		text-align:center;
		width:150px;height:50px;
		background-color:#fd7e14;
		border:3px solid #fd7e14;
		float:right;
		margin: 8px; 
		padding: 5px;
		border-radius: 10px;
	}
	.mhw:hover{
		background-color:#f17612;
		border:3px solid #f17612;
	}
	.mhw a  {
		font-size: 20px;
	}
	.mhw a:hover {
		text-decoration: none;
	}
	.mhw:active{
		background-color:#e95714;
		border:3px solid #e95714;
	}
	.main {
		padding-left: 150px;
		padding-right: 150px;
	}
</style>
<title>評論區</title>

<!-- favicon -->
<link rel="shortcut icon" type="image/png" href="<c:url value='/image/user/favicon.png'/>">
<!-- google font -->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
<!-- fontawesome -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" -->
<!-- 	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" -->
<!-- 	crossorigin="anonymous" /> -->
<link rel="stylesheet" href="<c:url value='/css/user/css/all.min.css'/>">
<!-- owl carousel -->
<link rel="stylesheet" href="<c:url value='/css/user/css/owl.carousel.css'/>">
<!-- magnific popup -->
<link rel="stylesheet" href="<c:url value='/css/user/css/magnific-popup.css'/>">
<!-- animate css -->
<link rel="stylesheet" href="<c:url value='/css/user/css/animate.css'/>">
<!-- mean menu css -->
<link rel="stylesheet" href="<c:url value='/css/user/css/meanmenu.min.css'/>">
<!-- main style -->
<link rel="stylesheet" href="<c:url value='/css/user/css/main.css'/>">
<!-- responsive -->
<link rel="stylesheet" href="<c:url value='/css/user/css/responsive.css'/>">
<!-- bootstrap -->
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/lineLogin.css'/>">
<link rel="stylesheet" href="<c:url value='/css/comment-style.css'/>">
<!-- vegas -->
<link rel="stylesheet" href="<c:url value='/css/vegas.css'/>"/>

<!-- <link href="./test/pre.css" rel="stylesheet" /> -->
<!-- 開關改成IOS風格(左右滑動按鈕) -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.css">
<script src="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.min.js"></script>
<!-- 暗黑模式 -->
<script src="https://cdn.jsdelivr.net/npm/darkmode-js@1.5.7/lib/darkmode-js.min.js"></script>
<script src="<c:url value='/js/jquery-3.6.0.js"'/>"></script>
<script src="<c:url value='/js/bootstrap.js'/>"></script>
<script src="<c:url value='/js/lineLogin.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/comment-validation.js'/>"></script>			
<link rel="stylesheet" href="<c:url value='/css/jquery.rating.css'/>" />
<script src='<c:url value="/js/jquery.js"/>'></script>
<script src='<c:url value="/js/jquery.rating.js"/>'></script>

<%-- <script src="<c:url value='/vegas/vegas.js'/>"></script> --%>
<!-- aboutUs -->
<!-- <script src="./js/aboutUs.js"></script> -->

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
			// var href = this.hash; //取得 href 值
			// var pane = $(this); //轉成 DOM 物件
			// 跳轉至外部頁面
			location.href = url;
		});
	});
	
</script>
</head>

<body>
	<!--PreLoader-->
	<div class="loader">
		<div class="loader-inner">
			<div class="circle"></div>
		</div>
	</div>
	<!--PreLoader Ends-->
	
	<!-- header -->
	<div class="top-header-area" id="sticker">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-sm-12 text-center">
					<div class="main-menu-wrap">
						<!-- logo -->
						<div class="site-logo">
							<a href="<c:url value='/'/>">
								<img src="<c:url value='/image/user/logo.png'/>" alt="">
							</a>
						</div>
						<!-- logo -->

						<!-- menu start -->
						<nav class="main-menu">
							<ul>
								<li class="current-list-item"><a href="<c:url value='/'/>">首頁</a></li>
								<li><a href="<c:url value='/Food/Fooddex'/>">商家資訊</a></li>
								<li><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
<%-- 								<li><a href="<c:url value='/Event/'/>">活動總覽</a> --%>
								<li><a href="<c:url value='/comments/list'/>">評論專區</a></li>
								<li><a href="<c:url value='/Recipe/user'/>">食譜規劃</a></li>
								<li><a href="<c:url value='/Shop/'/>">美食商城</a>
								<li>
									<div class="header-icons">
										<!-- <a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a> -->
										<li><a href="">會員：<span id="user"></span></a>
											<ul class="sub-menu">
												<li id="editNavBtn"><a
													href="<c:url value='/Member/Revise'/>"><i
														class="fas fa-edit"></i> 修改個人資料</a></li>
												<li id="registerNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Register'/>"><i class="fas fa-user-plus"></i>
														註冊會員</a></li>
												<li id="loginNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Login'/>"><i class="fas fa-sign-in-alt"></i>
														登入</a></li>
												<li id="logoutNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Logout'/>"><i class="fas fa-sign-out-alt"></i>
														登出</a></li>
											</ul>
										</li>
										<li>
										  <a class="shopping-cart" href="<c:url value='/Shop/Cart/'/>">
											 <i class="fas fa-shopping-cart"></i>
										  </a>
										</li>
										<li><a>
											<label for="checkbox-switch" style="">淺色模式</label>
											<input type="checkbox" id="checkbox-switch" class="checkbox-switch" />
										</a></li>
									</div>
								</li>
							</ul>
						</nav>
						<a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
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
						<p>分享心得</p>
						<h1>評論專區</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->

	<input type="hidden" id="isAdmin" value="${isAdmin}">
	<input type="hidden" id="userName" value="${user}">
	
	<div class="container-fluid px-1 py-5 mx-auto">
    <div class="row justify-content-center">
    <div class="col-xl-7 col-lg-8 col-md-10 col-12 text-center mb-5">
	
		<h3>新增評論</h3>
		<hr>
		<div>
		
		<form:form action="" id="commentForm"
					modelAttribute="comment" method="POST" name="commentForm"
					onSubmit="return validateForm()">
						 
			<!-- Add hidden form field to handle update -->
			<form:input type="hidden" path="id" />
<%-- 			<input type="hidden" value="${#dates.format(now, 'yyyy-MM-dd')}" name="userDate" > --%>
			<form:input type="hidden" path="userDate" id="userDate"/>
			<input type="hidden" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${now}"/>' id="now"/>
			<input type="hidden" name="userLikes" value="0">
					
			 
			<form:input type="hidden" path="userName" id="name"
					class="form-control mb-4 col-4" value="阿宏" />
			<span id="idsp"></span><br />
			
			<!-- 測試用 -->

					
			

			<form:input type="hidden" path="mapName" id="mapName"
					class="form-control mb-4 col-4" />
					
			<form:input type="hidden" path="userAccount" 
					class="form-control mb-4 col-4" value="test"/>
					
			<form:input type="hidden" path="userPhotoTest" 
					class="form-control mb-4 col-4" value="https://imgur.dcard.tw/tn729YT.gif"/>

		
					
			<!-- Ratting system -->
			<label>評分(不可空白)：</label>	<br>
			<!-- <div class="rate"> -->
<!-- 			    <input type="radio" id="star5" name="score" value="5" /> -->
<!-- 
				<form:radiobutton id="star5" path="score" value="5"/>  
			    <label for="star5" title="text"></label>
			    <form:radiobutton id="star4" path="score" value="4" />
			    <label for="star4" title="text"></label>
			    <form:radiobutton id="star3" path="score" value="3" />
			    <label for="star3" title="text"></label>
			    <form:radiobutton id="star2" path="score" value="2" />
			    <label for="star2" title="text"></label>
			    <form:radiobutton id="star1" path="score" value="1" />
			    <label for="star1" title="text"></label>
		 	 </div>  -->
		 	 
		 	 <div>
			<input type="radio" name="score" value="1" class="star">
            <input type="radio" name="score" value="2" class="star">
            <input type="radio" name="score" value="3" class="star">
            <input type="radio" name="score" value="4" class="star">
            <input type="radio" name="score" value="5" class="star">
            </div>
		 	 
		 	 <br><br><br>
			
			<input type="hidden" id="userComment" value="${comment.userComment}"/>
			<form:textarea  id="commentarea" path="userComment" class="form-control input-sm" rows="5" placeholder="寫下你的想法吧！" onblur="'checkcommentarea'" />
			<span id="idsp3"></span><br /><br /><br />			
			
			<button type="submit" class="btn btn-info col-2">送出</button>
			
			<input type="button" class="btn btn-info col-2" name="submit" value="一鍵輸入" id="btn">
		
		</form:form >
		
		<hr>
		<a href="<c:url value='/comments/list'/>">回到評論區</a>
	
	</div>
	</div>
	</div>
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
							<li><img src="<c:url value='/image/lineFoodMap04QR.png'/>" alt=""></li>
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
							<li><a href="<c:url value='/Recipe/user'/>">食譜養成</a></li>
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
					<p>Copyrights &copy; 2019 - <a href="https://imransdesign.com/">Imran Hossain</a>,  All Rights Reserved.</p>
				</div>
				<div class="col-lg-6 text-right col-md-12">
					<div class="social-icons">
						<ul>
							<li><a href="#" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="#" target="_blank"><i class="fab fa-twitter"></i></a></li>
							<li><a href="#" target="_blank"><i class="fab fa-instagram"></i></a></li>
							<li><a href="#" target="_blank"><i class="fab fa-linkedin"></i></a></li>
							<li><a href="#" target="_blank"><i class="fab fa-dribbble"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end copyright -->
	
	<script>
		$(function() {
			userNameMain();

			const cookies = document.cookie;
			console.log("cookies:'" + cookies + "'");
			let userName = cookies.split("user=")[1];   //帳號
			$("#admin").on("click", function(e){
				if(userName != undefined){
					location.href="<c:url value='/Coupon/admin' />";
				} else {
					alert("請先登入!")
					location.href = "<c:url value='/Member/Login'/>";						
				}
			})
// 			 $("#header").vegas({
// 	             slides: [
// 	                 { src: "./image/question.jpeg" },
// 	                 { src: "./image/salad.jpg" },
// 	                 { src: "./image/food1.jpeg" },
// 	                 { src: "./image/food2.jpeg" },
// 	                 { src: "./image/food3.jpeg" },
// 	                 { src: "./image/coupon.jpg" }
// 	             ],
// 	             transition: ['blur', 'zoomOut', 'swirlLeft'],
// 	             delay: 5000
// 	        });
			 $(".wrap").vegas({
				 overlay: true,
				 transition: 'fade', 
				 transitionDuration: 2000,
				 delay: 6000,
				 color: 'red',
				 animation: 'random',
				 animationDuration: 8000,
				 slides: [
					 { src: "./image/question.jpeg" },
					 { src: "./image/salad.jpg" },
					 { src: "./image/food1.jpeg" },
					 { src: "./image/food2.jpeg" },
					 { src: "./image/food3.jpeg" },
					 { src: "./image/coupon.jpg" }
				 ],
			});
		})
	</script>
	
	<script type="text/javascript">
		var userDate = $("#userDate").val()
		var userComment = $("#userComment").val()
		var date = '<fmt:formatDate pattern="yyyy-MM-dd" value="${now}"/>'
		$("#userDate").val(date)
		$("#commentarea").text(userComment)
		$("#commentForm").attr("action", "<c:url value='/comments/save'/>")
	</script>
	
	
	</script>
	<!-- count down -->
	<script src="<c:url value='/js/user/js/jquery.countdown.js'/>"></script>
	<!-- isotope -->
	<script src="<c:url value='/js/user/js/jquery.isotope-3.0.6.min.js'/>"></script>
	<!-- waypoints -->
	<script src="<c:url value='/js/user/js/waypoints.js'/>"></script>
	<!-- owl carousel -->
	<script src="<c:url value='/js/user/js/owl.carousel.min.js'/>"></script>
	<!-- magnific popup -->
	<script src="<c:url value='/js/user/js/jquery.magnific-popup.min.js'/>"></script>
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
		$("#btn").on("click", function (e) {
		   $("#mapName").val("信義霸味薑母鴨（原松仁店）");
		   $("#commentarea").val("比想像中還好吃 肉不柴！");
		})
	</script>
	
	
</body>
</html>











