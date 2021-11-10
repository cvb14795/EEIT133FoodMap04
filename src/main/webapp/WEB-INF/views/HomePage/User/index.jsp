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
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-sm-12 text-center">
					<div class="main-menu-wrap">
						<!-- logo -->
						<div class="site-logo">
							<a href="<c:url value='/'/>">
								<img src="<c:url value='/image/user/logo.png'/>" alt="">
							</a>
						</div>
						<!-- logo -->

						<!-- menu start -->
						<nav class="main-menu navbar navbar-expand-lg">
							<ul>
								<li class="current-list-item"><a href="<c:url value='/'/>">首頁</a></li>
								<li><a href="<c:url value='/Food/user'/>">商家資訊</a></li>
								<li><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
<%-- 								<li><a href="<c:url value='/Event/'/>">活動總覽</a> --%>
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
										  <a class="shopping-cart" href="<c:url value='/Shop/Cart'/>">
										  	<i class="fas fa-shopping-cart"></i>
										  </a>
										</li>
										<li>
											<a>
												淺色模式
	<!-- 											<label for="checkbox-switch" style="">淺色模式</label> -->
												<input type="checkbox" id="checkbox-switch" class="checkbox-switch" />
											</a>
										</li>
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
	<!-- <a class="aboutUs" href="#contact">關 於 我 們</a> -->
	<a class="aboutUs" href="<c:url value='/aboutUs'/>">關 於 我 們</a>
	<div class="title">
	  <h1>想食</h1>
	  <img alt="What" src="<c:url value='./image/what4.png'/>">
	</div>
  </div>

	
	<!-- 網站發想 -->
	<div class="abt-section mt-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-12">
					<div class="abt-bg">
						<a href="https://www.youtube.com/watch?v=DBLlFWYcIGQ" class="video-play-btn popup-youtube"><i class="fas fa-play"></i></a>
					</div>
				</div>
				<div class="col-lg-6 col-md-12">
					<div class="abt-text">
						<p class="top-sub">EEIT133—跨域Java班—第四組期末專題</p>
						<h2>網站<span class="orange-text">發想</span></h2>
						<p>每次想要查詢美食時，都會耗費太多時間，希望有一個平台能夠找出各地美食資訊，也藉由大家的評論來了解當地人推薦的道地美食。</p>
			<p>因為疫情關係，雖然部分餐廳已開放內用，但民眾還是並不放心，希望能在品嘗美食之前先了解餐廳的防疫措施做的好不好，免得發現不如預期而白跑一趟。</p>
			<p>現代的人注重健康飲食，因此希望有一些健康食譜能提供參考，也能針對自己的需求來去做食譜的調配。</p>
						<a href="about.jsp" class="boxed-btn mt-4">詳細資訊</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end 網站發想 section -->

	<!-- 活動總覽 -->
	<div class="latest-news pt-150 pb-150">
		<div class="container">

			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="section-title">	
						<h3><span class="orange-text">活動</span>總覽</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, fuga quas itaque eveniet beatae optio.</p>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="single-latest-news">
						<a href="single-news.html"><div class="latest-news-bg news-bg-1"></div></a>
						<div class="news-text-box">
							<h3><a href="single-news.html">You will vainly look for fruit on it in autumn.</a></h3>
							<p class="blog-meta">
								<span class="author"><i class="fas fa-user"></i> Admin</span>
								<span class="date"><i class="fas fa-calendar"></i> 27 December, 2019</span>
							</p>
							<p class="excerpt">Vivamus lacus enim, pulvinar vel nulla sed, scelerisque rhoncus nisi. Praesent vitae mattis nunc, egestas viverra eros.</p>
							<a href="single-news.html" class="read-more-btn">read more <i class="fas fa-angle-right"></i></a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="single-latest-news">
						<a href="single-news.html"><div class="latest-news-bg news-bg-2"></div></a>
						<div class="news-text-box">
							<h3><a href="single-news.html">A man's worth has its season, like tomato.</a></h3>
							<p class="blog-meta">
								<span class="author"><i class="fas fa-user"></i> Admin</span>
								<span class="date"><i class="fas fa-calendar"></i> 27 December, 2019</span>
							</p>
							<p class="excerpt">Vivamus lacus enim, pulvinar vel nulla sed, scelerisque rhoncus nisi. Praesent vitae mattis nunc, egestas viverra eros.</p>
							<a href="single-news.html" class="read-more-btn">read more <i class="fas fa-angle-right"></i></a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 offset-md-3 offset-lg-0">
					<div class="single-latest-news">
						<a href="single-news.html"><div class="latest-news-bg news-bg-3"></div></a>
						<div class="news-text-box">
							<h3><a href="single-news.html">Good thoughts bear good fresh juicy fruit.</a></h3>
							<p class="blog-meta">
								<span class="author"><i class="fas fa-user"></i> Admin</span>
								<span class="date"><i class="fas fa-calendar"></i> 27 December, 2019</span>
							</p>
							<p class="excerpt">Vivamus lacus enim, pulvinar vel nulla sed, scelerisque rhoncus nisi. Praesent vitae mattis nunc, egestas viverra eros.</p>
							<a href="single-news.html" class="read-more-btn">read more <i class="fas fa-angle-right"></i></a>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12 text-center">
					<a href="news.html" class="boxed-btn">More News</a>
				</div>
			</div>
		</div>
	</div>
	<!-- end 活動總覽 -->

  <!-- 食譜 -->
	<div class="product-section mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="section-title">	
						<h3><span class="orange-text">食譜</span>規劃</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, fuga quas itaque eveniet beatae optio.</p>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-4 col-md-6 text-center">
					<div class="single-product-item">
						<div class="product-image">
							<a href="single-product.html"><img src="<c:url value='/image/user/products/product-img-1.jpg'/>" alt=""></a>
						</div>
						<h3>Strawberry</h3>
						<p class="product-price"><span>Per Kg</span> 85$ </p>
						<a href="cart.html" class="cart-btn"><i class="fas fa-heart"></i> 加入我的最愛</a>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 text-center">
					<div class="single-product-item">
						<div class="product-image">
							<a href="single-product.html"><img src="<c:url value='/image/user/products/product-img-2.jpg'/>" alt=""></a>
						</div>
						<h3>Berry</h3>
						<p class="product-price"><span>Per Kg</span> 70$ </p>
						<a href="cart.html" class="cart-btn"><i class="fas fa-heart"></i> 加入我的最愛</a>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 offset-md-3 offset-lg-0 text-center">
					<div class="single-product-item">
						<div class="product-image">
							<a href="single-product.html"><img src="<c:url value='/image/user/products/product-img-3.jpg'/>" alt=""></a>
						</div>
						<h3>Lemon</h3>
						<p class="product-price"><span>Per Kg</span> 35$ </p>
						<a href="cart.html" class="cart-btn"><i class="fas fa-heart"></i> 加入我的最愛</a>
					</div>
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
				 transition: 'fade', 
				 transitionDuration: 2000,
				 delay: 6000,
				 color: '-',
				 cover: true,
				 animation: 'fade',
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