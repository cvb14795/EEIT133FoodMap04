<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <meta name="google-signin-client_id"
		content="196642336489-5j9n6rtmidbccrubh6vf406gve5cejrn.apps.googleusercontent.com"> -->
<title>首頁</title>

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
<link rel="stylesheet" href="<c:url value='/css/index.css'/>">
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
<style>
	img.user-avatar {
		width:40px;
	}
	
	.aboutUs {
		font-size: 25px;
		background-color:#BEBEBE;
		opacity: .9;
	}
	
	body {
		background-color:#FFFCEC;
	}
	
</style>
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
	<div class="top-header-area " id="sticker">
		<div class="container">
			<!-- logo -->
			<div class="site-logo">
				<a href="<c:url value='/'/>">
					<img src="<c:url value='/image/user/logo.png'/>" alt="" width="65%" height="65%">
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
								<li class="current-list-item"><a href="<c:url value='/'/>">首頁</a></li>
								<li><a href="<c:url value='/Food/user'/>">商家資訊</a></li>
								<li><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
								<li><a href="<c:url value='/Event/'/>">活動總覽</a>
								<li><a href="<c:url value='/comments/list'/>">評論專區</a></li>
								<li><a href="<c:url value='/Recipe/user'/>">食譜規劃</a></li>
								<li><a href="<c:url value='/Shop/'/>">美食商城</a>
								<li>
									<div class="header-icons">
										<!-- <a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a> -->
										<li><a><img class="user-avatar rounded-circle" src=""/> 會員：<span id="user"></span></a>
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
											<div class="header-icons">
											  <a class="shopping-cart" href="<c:url value='/Recipe/user/showMyfavorites'/>">
											  	<i class="fas fa-heart"></i>
											  </a>
											  <a class="shopping-cart" href="<c:url value='/Shop/Cart'/>">
											  	<i class="fas fa-shopping-cart"></i>
											  </a>
											</div>
										</li>
<!-- 										<li> -->
<!-- 											<a> -->
<!-- 												淺色模式 -->
<!-- 	<!-- 											<label for="checkbox-switch" style="">淺色模式</label> --> 
<!-- 												<input type="checkbox" id="checkbox-switch" class="checkbox-switch" /> -->
<!-- 											</a> -->
<!-- 										</li> -->
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
	
  <div class="wrap">
	<a class="aboutUs" href="<c:url value='/aboutUs'/>">關 於 我 們</a>
	<div class="title">
	  <h1>想食</h1>
	  <img alt="What" src="<c:url value='./image/what4.png'/>">
	</div>
  </div>

  <div class="contactUs">
	<a href="<c:url value='/chat/index'/>" class="rounded-circle" style="position: fixed;bottom: 2rem;right: 2em;">
		<i class="fas fa-comments"></i>聯絡客服
	</a>
  </div>
	
	<!-- 網站發想 -->
	<div class="abt-section mt-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-12">
					<a href="<c:url value='/aboutUs'/>">
						<img src="<c:url value='/image/user/logo.png'/>" alt="">
					</a>
				</div>
				<div class="col-lg-6 col-md-12">
					<div class="abt-text">
						<p class="top-sub">EEIT133—跨域Java班—第四組期末專題</p>
						<h2>網站<span class="orange-text">發想</span></h2>
						<p>每次想要查詢美食時，都會耗費太多時間，希望有一個平台能夠找出各地美食資訊，也藉由大家的評論來了解當地人推薦的道地美食。</p>
						<p>因為疫情關係，雖然部分餐廳已開放內用，但民眾還是並不放心，希望能在品嘗美食之前先了解餐廳的防疫措施做的好不好，免得發現不如預期而白跑一趟。</p>
						<p>現代的人注重健康飲食，因此希望有一些健康食譜能提供參考，也能針對自己的需求來去做食譜的調配。</p>
						<a href="<c:url value='/aboutUs'/>" class="boxed-btn mt-4">詳細資訊</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end 網站發想 section -->

	<!-- 商家預覽 -->
	<div class="latest-news pt-150 pb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="section-title">	
						<h3><span class="orange-text">商家</span>預覽</h3>
						<p>有了商家資訊？</p>
						<p>真是太剛好了！</p>
					</div>
				</div>
			</div>
			<div class="row product-lists">
				<c:choose>
					<c:when test="${mapDatalists.size() != 0}">
						<c:forEach var="i" begin="0" end="2">
							<div class="col-lg-4 col-md-6 text-center ${mapDatalists.get(i).mapcheck}">
								<div class="single-product-item">
									<div class="product-image">
										<a href="single-product.html">
<%-- 											<img src="data:image/jpg;base64,${imgList.get(i)}"> --%>
											<img src="<c:url value='/Food/user/photo/${mapDatalists.get(i).id}'/>" style="width:300px;height:300px;">
										</a>
									</div>
									<h3>店家名稱:<span class="mapname">${mapDatalists.get(i).mapname}</span></h3>
									<p class="product-price"><span>地區:${mapDatalists.get(i).category}</span></p>
									<h6>是否為安全店家:
										<p>
											<span>
												${mapDatalists.get(i).mapcheck}
											</span>
										</p>
									</h6>
									<a href="<c:url value='/Food/user/info/${mapDatalists.get(i).id}'/>" class="cart-btn">詳細資料</a>
								</div>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
				<div class="col-lg-12 text-center">
					<a href="<c:url value='/Food/user'/>" class="boxed-btn">更多商家</a>
				</div>
			</div>
		</div>
	</div>
	<!-- end 商家預覽 -->

  <!-- 食譜 -->
	<div class="product-section mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="section-title">	
						<h3><span class="orange-text">食譜</span>規劃</h3>
						<p>下廚就來想食what</p>
						<p>萬中選一的神救援</p>
					</div>
				</div>
			</div>
			<div class="row">
				<c:choose>
					<c:when test="${lists.size() != 0}">
						<c:forEach var="i" begin="0" end="2">
							<div class="col-lg-4 col-md-6 text-center">
								<div class="single-product-item">
									<div class="product-image">
<%-- 										<img src="data:image/jpg;base64,${imgList.get(i)}"> --%>
										<img src="<c:url value='/Recipe/admin/photo/${lists.get(i).id}'/>">
									</div>
									<h3>品名:${lists.get(i).name}</h3>
									<p class="product-price"><span>類別:${lists.get(i).category}</span></p>
									<h6>食材:
										<p>
											<span>
												${lists.get(i).food1}<br /> ${lists.get(i).food2}<br />
												${lists.get(i).food3}<br /> ${lists.get(i).food4}
											</span>
										</p>
									</h6>
									<h6>調味料:
										<p>
											<span>
												${lists.get(i).sauce1}<br /> ${lists.get(i).sauce2}<br />
												${lists.get(i).sauce3}
											</span>
										</p>
									</h6>
									<a href="<c:url value='/Recipe/user/UserViewAdminRecipe2/${lists.get(i).id}'/>" class="read-more-btn">詳細資料 <i class="fas fa-angle-right"></i></a>
								</div>
							</div>
						</c:forEach>
					</c:when>
				<c:otherwise>
					<div class="container">
						<div style="text-align: center;">
							<h2>無資料!</h2>
						</div>
					</div>
				</c:otherwise>
				</c:choose>
				
				<div class="col-lg-12 text-center">
					<a href="<c:url value='/Recipe/user'/>" class="boxed-btn">更多食譜</a>
				</div>
			</div>
		</div>
	</div>
	<!-- end 食譜 section -->

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
							<li><a href="<c:url value='/Food/user'/>">商家資訊</a></li>
							<li><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
							<li><a href="<c:url value='/Event'/>">活動總覽</a></li>
							<li><a href="<c:url value='/comments/list'/>">評論專區</a></li>
							<li><a href="<c:url value='/Recipe/user'/>">食譜規劃</a></li>
							<li><a href="<c:url value='/Shop'/>">美食商城</a></li>
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
			var src = "<c:url value='/Member/user/${user}/photo'/>";
			userNameMain(src);
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
				 transition: ['blur', 'zoomOut', 'swirlLeft'], 
				 transitionDuration: 2000,
				 delay: 6000,
				 color: '-',
				 cover: true,
				 animation: "random",
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
	
</body>
</html>