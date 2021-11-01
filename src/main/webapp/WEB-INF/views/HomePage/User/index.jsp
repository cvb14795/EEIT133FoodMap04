<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="<c:url value='/vegas/vegas.css'/>"type="text/css" />

<!-- <link href="./test/pre.css" rel="stylesheet" /> -->
<!-- 開關改成IOS風格(左右滑動按鈕) -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.css">
<script src="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.min.js"></script>
<!-- 暗黑模式 -->
<script src="https://cdn.jsdelivr.net/npm/darkmode-js@1.5.7/lib/darkmode-js.min.js"></script>
<script src="./js/jquery-3.6.0.js"></script>
<script src="./js/bootstrap.js"></script>
<script src="./js/lineLogin.js"></script>
<!-- <script src="./js/getBackgroundImageSize.js"></script> -->
<%-- <script src="<c:url value='/vegas/vegas.js'/>"></script> --%>
<!-- Timeline -->
<script src="./test/console-ban.min.js"></script>
<script src="./test/previewjs.js"></script>
<!-- aboutUs -->
<script src="./js/aboutUs.js"></script>

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
							<a href="index.html">
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
                <li><a href="<c:url value='/Event/'/>">活動總覽</a>
                  <ul class="sub-menu">
										<li><a href="<c:url value='/Event/buy'/>">付費活動購票</a></li>
										<li><a href="<c:url value='/Event/entry'/>">免費活動報名</a></li>
									</ul>
                </li>
                <li><a href="<c:url value='/comments/list'/>">評論專區</a></li>
                <li><a href="<c:url value='/Recipe/user'/>">食譜規劃</a></li>
								<li>
									<div class="header-icons">
                    <!-- <a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a> -->
                    <a class="shopping-cart" href="<c:url value='/Cart/'/>"><i class="fas fa-shopping-cart"></i> 已購票券</a>
                    <li><a href="">會員：<span id="user"></span></a>
                      <ul class="sub-menu">
                        <li id="editNavBtn">
                          <a href="<c:url value='/Member/Revise'/>"><i class="fas fa-edit"></i> 修改個人資料</a>
                        </li>
                        <li id="registerNavBtn" >
                          <a class="nav-link" href="./Member/Register"><i class="fas fa-user-plus"></i> 註冊會員</a>
                        </li>
                        <li id="loginNavBtn" >
                          <a class="nav-link" href="./Member/Login"><i class="fas fa-sign-in-alt"></i> 登入</a>
                        </li>
                        <li id="logoutNavBtn" >
                          <a class="nav-link" href="./Member/Logout"><i class="fas fa-sign-out-alt"></i> 登出</a>
                        </li>
                      </ul>
                    </li>
                    <li>
                      <a href="#">
                        <label for="checkbox-switch">淺色模式</label>
                        <input type="checkbox" id="checkbox-switch" class="checkbox-switch"/>							
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
    <a class="aboutUs" href="#contact">關 於 我 們</a>
    <article id="contact">
      <h2 class="major">想食 What！</h2>
      <form method="post" action="#">
        <div class="fields">
          <div class="field">
              <label>製作團隊</label>
              <h5>鄧峻豪、陳貴宏</h5>
              <h5>林語君、張晉豪</h5>
              <h5>張耿豪、伍玴辰</h5>
          </div>
          <div class="field">
              <label>網站發想</label>
              <p>1.每次想要查詢美食時，都會耗費太多時間，希望有一個平台能夠找出各地美食資訊，也藉由大家的評論來了解當地人推薦的道地美食。<br/>
      2.因為疫情關係，雖然部分餐廳已開放內用，但民眾還是並不放心，希望能在品嘗美食之前先了解餐廳的防疫措施做的好不好，免得發現不如預期而白跑一趟。<br/>
      3.現代的人注重健康飲食，因此希望有一些健康食譜能提供參考，也能針對自己的需求來去做食譜的調配。</p>
          </div>
          <div class="field">
              <label>網站特色</label>
              <p>1.此平台除了能快速找出使用者想要的美食資訊，也會提供google map地圖來讓使用者了解與美食的距離。<br/> 
                  2.人們在購買東西時，都希望會有折扣，因此平台也會有特約餐廳的優惠券，以及針對已接踵疫苗之民眾的專屬優惠券。<br/> 
                  3.打造適合自己的專屬食譜，實現健康生活。<br/> 
                  4.平台定期舉辦一些會員活動，活絡會員間的感情。</p>
          </div>
        </div>
      </form>
      <div id="close"></div>
    </article>
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
						<h3><span class="orange-text">Our</span> Products</h3>
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
						<a href="cart.html" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 text-center">
					<div class="single-product-item">
						<div class="product-image">
							<a href="single-product.html"><img src="<c:url value='/image/user/products/product-img-2.jpg'/>" alt=""></a>
						</div>
						<h3>Berry</h3>
						<p class="product-price"><span>Per Kg</span> 70$ </p>
						<a href="cart.html" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 offset-md-3 offset-lg-0 text-center">
					<div class="single-product-item">
						<div class="product-image">
							<a href="single-product.html"><img src="<c:url value='/image/user/products/product-img-3.jpg'/>" alt=""></a>
						</div>
						<h3>Lemon</h3>
						<p class="product-price"><span>Per Kg</span> 35$ </p>
						<a href="cart.html" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
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
						<p>1.此平台除了能快速找出使用者想要的美食資訊<br/>也會提供google map地圖來讓使用者了解與美食的距離。</p> 
            <p>2.人們在購買東西時，都希望會有折扣，因此平台也會有特約餐廳的優惠券<br/>以及針對已接種疫苗之民眾的專屬優惠券。</p>
            <p>3.打造適合自己的專屬食譜，實現健康生活。</p>
            <p>4.平台定期舉辦一些會員活動，活絡會員間的感情。</p>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="footer-box">
						<h2 class="widget-title">Line 智慧客服</h2>
						<ul>
              <li><a href="" id="lineLoginHref"><input type="button" class="lineLogin" style="width: 151px;height: 44px;border:none"/></a></li>
              <li><div class="line-it-button" data-lang="zh_Hant" data-type="friend" data-lineid="@413ghgmq" style="display: none;"></div></li>      
              <li><img src="<c:url value='/image/lineFoodMap04QR.png'/>" alt=""></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-2 col-md-6">
					<div class="footer-box pages">
						<h2 class="widget-title">Pages</h2>
						<ul>
							<li><a href="index.html">Home</a></li>
							<li><a href="about.html">About</a></li>
							<li><a href="services.html">Shop</a></li>
							<li><a href="news.html">News</a></li>
							<li><a href="contact.html">Contact</a></li>
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
		    var $contact = $("#contact");
		    $contact.addClass("close");
		    $contact.hide();
			contentInit();
			setLineOAuthUrl();
// 			var navbarHeight = $(".navbar:eq(0)").height()
			// $(".header").height(innerHeight - navbarHeight);
// 			console.log($(".header").attr("height"));
// 			var darkmode = new Darkmode({
// 				saveInCookies: true, // default: true,
// 			});
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
	<script src="https://www.line-website.com/social-plugins/js/thirdparty/loader.min.js" async defer></script>
	<!-- count down -->
	<script src="<c:url value='js/user/js/jquery.countdown.js'/>"></script>
	<!-- isotope -->
	<script src="<c:url value='js/user/js/jquery.isotope-3.0.6.min.js'/>"></script>
	<!-- waypoints -->
	<script src="<c:url value='js/user/js/waypoints.js'/>"></script>
	<!-- owl carousel -->
	<script src="<c:url value='js/user/js/owl.carousel.min.js'/>"></script>
	<!-- magnific popup -->
	<script src="<c:url value='js/user/js/jquery.magnific-popup.min.js'/>"></script>
	<!-- mean menu -->
	<script src="<c:url value='js/user/js/jquery.meanmenu.min.js'/>"></script>
	<!-- sticker js -->
	<script src="<c:url value='js/user/js/sticker.js'/>"></script>
	<!-- main js -->
	<script src="<c:url value='js/user/js/main.js'/>"></script>
	
	
	
	<!-- vegas -->
	<script>

(function ($) {
  'use strict';

  var defaults = {
    slide:                   0,
    delay:                   5000,
    loop:                    true,
    preload:                 false,
    preloadImage:            false,
    preloadVideo:            false,
    timer:                   true,
    overlay:                 false,
    autoplay:                true,
    shuffle:                 false,
    cover:                   true,
    color:                   null,
    align:                   'center',
    valign:                  'center',
    firstTransition:         null,
    firstTransitionDuration: null,
    transition:              'fade',
    transitionDuration:      1000,
    transitionRegister:      [],
    animation:               null,
    animationDuration:       'auto',
    animationRegister:       [],
    slidesToKeep:            1,
    init:  function () {},
    play:  function () {},
    pause: function () {},
    walk:  function () {},
    slides: [
      // {
      //  src:                null,
      //  color:              null,
      //  delay:              null,
      //  align:              null,
      //  valign:             null,
      //  transition:         null,
      //  transitionDuration: null,
      //  animation:          null,
      //  animationDuration:  null,
      //  cover:              true,
      //  video: {
      //      src: [],
      //      mute: true,
      //      loop: true
      // }
      // ...
    ]
  };

  var videoCache = {};

  var Vegas = function (elmt, options) {
    this.elmt         = elmt;
    this.settings     = $.extend({}, defaults, $.vegas.defaults, options);
    this.slide        = this.settings.slide;
    this.total        = this.settings.slides.length;
    this.noshow       = this.total < 2;
    this.paused       = !this.settings.autoplay || this.noshow;
    this.ended        = false;
    this.$elmt        = $(elmt);
    this.$timer       = null;
    this.$overlay     = null;
    this.$slide       = null;
    this.timeout      = null;
    this.first        = true;

    this.transitions = [
      'fade', 'fade2',
      'blur', 'blur2',
      'flash', 'flash2',
      'negative', 'negative2',
      'burn', 'burn2',
      'slideLeft', 'slideLeft2',
      'slideRight', 'slideRight2',
      'slideUp', 'slideUp2',
      'slideDown', 'slideDown2',
      'zoomIn', 'zoomIn2',
      'zoomOut', 'zoomOut2',
      'swirlLeft', 'swirlLeft2',
      'swirlRight', 'swirlRight2'
    ];

    this.animations = [
      'kenburns',
      'kenburnsLeft', 'kenburnsRight',
      'kenburnsUp', 'kenburnsUpLeft', 'kenburnsUpRight',
      'kenburnsDown', 'kenburnsDownLeft', 'kenburnsDownRight'
    ];

    if (!(this.settings.transitionRegister instanceof Array)) {
      this.settings.transitionRegister = [ this.settings.transitionRegister ];
    }

    if (!(this.settings.animationRegister instanceof Array)) {
      this.settings.animationRegister = [ this.settings.animationRegister ];
    }

    this.transitions = this.transitions.concat(this.settings.transitionRegister);
    this.animations  = this.animations.concat(this.settings.animationRegister);

    this.support = {
      objectFit:  'objectFit'  in document.body.style,
      transition: 'transition' in document.body.style || 'WebkitTransition' in document.body.style,
      video:      $.vegas.isVideoCompatible()
    };

    if (this.settings.shuffle === true) {
      this.shuffle();
    }

    this._init();
  };

  Vegas.prototype = {
    _init: function () {
      var $content,
        $contentScroll,
        $overlay,
        $timer,
        isBody  = this.elmt.tagName === 'BODY',
        timer   = this.settings.timer,
        overlay = this.settings.overlay,
        self    = this;

      // Preloading
      this._preload();

      // Div with scrollable content
      if (!isBody) {
        $contentScroll = $('<div class="vegas-content-scrollable">');

        $content = $('<div class="vegas-content">')
          .css('overflow', this.$elmt.css('overflow'))
          .css('padding',  this.$elmt.css('padding'));

        // Some browsers don't compute padding shorthand
        if (!this.$elmt.css('padding')) {
          $content
            .css('padding-top',    this.$elmt.css('padding-top'))
            .css('padding-bottom', this.$elmt.css('padding-bottom'))
            .css('padding-left',   this.$elmt.css('padding-left'))
            .css('padding-right',  this.$elmt.css('padding-right'));
        }

        this.$elmt.css('padding', 0);

        this.$elmt.clone(true).children().appendTo($content);
        this.elmt.innerHTML = '';
      }

      // Timer
      if (timer && this.support.transition) {
        $timer = $('<div class="vegas-timer"><div class="vegas-timer-progress">');
        this.$timer = $timer;
        this.$elmt.prepend($timer);
      }

      // Overlay
      if (overlay) {
        $overlay = $('<div class="vegas-overlay">');

        if (typeof overlay === 'string') {
          $overlay.css('background-image', 'url(' + overlay + ')');
        }

        this.$overlay = $overlay;
        this.$elmt.prepend($overlay);
      }

      // Container
      this.$elmt.addClass('vegas-container');

      if (!isBody) {
        this.$elmt.append($contentScroll);
        $contentScroll.append($content);
      }

      setTimeout(function () {
        self.trigger('init');
        self._goto(self.slide);

        if (self.settings.autoplay) {
          self.trigger('play');
        }
      }, 1);
    },

    _preload: function () {
      var img, i;

      for (i = 0; i < this.settings.slides.length; i++) {
        if (this.settings.preload || this.settings.preloadImages) {
          if (this.settings.slides[i].src) {
            img = new Image();
            img.src = this.settings.slides[i].src;
          }
        }

        if (this.settings.preload || this.settings.preloadVideos) {
          if (this.support.video && this.settings.slides[i].video) {
            if (this.settings.slides[i].video instanceof Array) {
              this._video(this.settings.slides[i].video);
            } else {
              this._video(this.settings.slides[i].video.src);
            }
          }
        }
      }
    },

    _random: function (array) {
      return array[Math.floor(Math.random() * array.length)];
    },

    _slideShow: function () {
      var self = this;

      if (this.total > 1 && !this.ended && !this.paused && !this.noshow) {
        this.timeout = setTimeout(function () {
          self.next();
        }, this._options('delay'));
      }
    },

    _timer: function (state) {
      var self = this;

      clearTimeout(this.timeout);

      if (!this.$timer) {
        return;
      }

      this.$timer
        .removeClass('vegas-timer-running')
        .find('div')
        .css('transition-duration', '0ms');

      if (this.ended || this.paused || this.noshow) {
        return;
      }

      if (state) {
        setTimeout(function () {
          self.$timer
            .addClass('vegas-timer-running')
            .find('div')
            .css('transition-duration', self._options('delay') - 100 + 'ms');
        }, 100);
      }
    },

    _video: function (srcs) {
      var video,
        source,
        cacheKey = srcs.toString();

      if (videoCache[cacheKey]) {
        return videoCache[cacheKey];
      }

      if (!(srcs instanceof Array)) {
        srcs = [ srcs ];
      }

      video = document.createElement('video');
      video.preload = true;

      srcs.forEach(function (src) {
        source = document.createElement('source');
        source.src = src;
        video.appendChild(source);
      });

      videoCache[cacheKey] = video;

      return video;
    },

    _fadeOutSound: function (video, duration) {
      var self   = this,
        delay  = duration / 10,
        volume = video.volume - 0.09;

      if (volume > 0) {
        video.volume = volume;

        setTimeout(function () {
          self._fadeOutSound(video, duration);
        }, delay);
      } else {
        video.pause();
      }
    },

    _fadeInSound: function (video, duration) {
      var self   = this,
        delay  = duration / 10,
        volume = video.volume + 0.09;

      if (volume < 1) {
        video.volume = volume;

        setTimeout(function () {
          self._fadeInSound(video, duration);
        }, delay);
      }
    },

    _options: function (key, i) {
      if (i === undefined) {
        i = this.slide;
      }

      if (this.settings.slides[i][key] !== undefined) {
        return this.settings.slides[i][key];
      }

      return this.settings[key];
    },

    _goto: function (nb) {
      if (typeof this.settings.slides[nb] === 'undefined') {
        nb = 0;
      }

      this.slide = nb;

      var $slide,
        $inner,
        $video,
        $slides       = this.$elmt.children('.vegas-slide'),
        src           = this.settings.slides[nb].src,
        videoSettings = this.settings.slides[nb].video,
        delay         = this._options('delay'),
        align         = this._options('align'),
        valign        = this._options('valign'),
        cover         = this._options('cover'),
        color         = this._options('color') || this.$elmt.css('background-color'),
        self          = this,
        total         = $slides.length,
        video,
        img;

      var transition         = this._options('transition'),
        transitionDuration = this._options('transitionDuration'),
        animation          = this._options('animation'),
        animationDuration  = this._options('animationDuration');

      if (this.settings.firstTransition && this.first) {
        transition = this.settings.firstTransition || transition;
      }

      if (this.settings.firstTransitionDuration && this.first) {
        transitionDuration = this.settings.firstTransitionDuration || transitionDuration;
      }

      if (this.first) {
        this.first = false;
      }

      if (cover !== 'repeat') {
        if (cover === true) {
          cover = 'cover';
        } else if (cover === false) {
          cover = 'contain';
        }
      }

      if (transition === 'random' || transition instanceof Array) {
        if (transition instanceof Array) {
          transition = this._random(transition);
        } else {
          transition = this._random(this.transitions);
        }
      }

      if (animation === 'random' || animation instanceof Array) {
        if (animation instanceof Array) {
          animation = this._random(animation);
        } else {
          animation = this._random(this.animations);
        }
      }

      if (transitionDuration === 'auto' || transitionDuration > delay) {
        transitionDuration = delay;
      }

      if (animationDuration === 'auto') {
        animationDuration = delay;
      }

      $slide = $('<div class="vegas-slide"></div>');

      if (this.support.transition && transition) {
        $slide.addClass('vegas-transition-' + transition);
      }

      // Video

      if (this.support.video && videoSettings) {
        if (videoSettings instanceof Array) {
          video = this._video(videoSettings);
        } else {
          video = this._video(videoSettings.src);
        }

        video.loop  = videoSettings.loop !== undefined ? videoSettings.loop : true;
        video.muted = videoSettings.mute !== undefined ? videoSettings.mute : true;

        if (video.muted === false) {
          video.volume = 0;
          this._fadeInSound(video, transitionDuration);
        } else {
          video.pause();
        }

        $video = $(video)
          .addClass('vegas-video')
          .css('background-color', color);

        if (this.support.objectFit) {
          $video
            .css('object-position', align + ' ' + valign)
            .css('object-fit', cover)
            .css('width',  '100%')
            .css('height', '100%');
        } else if (cover === 'contain') {
          $video
            .css('width',  '100%')
            .css('height', '100%');
        }

        $slide.append($video);

        // Image

      } else {
        img = new Image();

        $inner = $('<div class="vegas-slide-inner"></div>')
          .css('background-image',    'url("' + src + '")')
          .css('background-color',    color)
          .css('background-position', align + ' ' + valign);

        if (cover === 'repeat') {
          $inner.css('background-repeat', 'repeat');
        } else {
          $inner.css('background-size', cover);
        }

        if (this.support.transition && animation) {
          $inner
            .addClass('vegas-animation-' + animation)
            .css('animation-duration',  animationDuration + 'ms');
        }

        $slide.append($inner);
      }

      if (!this.support.transition) {
        $slide.css('display', 'none');
      }

      if (total) {
        $slides.eq(total - 1).after($slide);
      } else {
        this.$elmt.prepend($slide);
      }

      $slides
        .css('transition', 'all 0ms')
        .each(function () {
          this.className  = 'vegas-slide';

          if (this.tagName === 'VIDEO') {
            this.className += ' vegas-video';
          }

          if (transition) {
            this.className += ' vegas-transition-' + transition;
            this.className += ' vegas-transition-' + transition + '-in';
          }
        }
        );

      self._timer(false);

      function go () {
        self._timer(true);

        setTimeout(function () {
          if (transition) {
            if (self.support.transition) {
              $slides
                .css('transition', 'all ' + transitionDuration + 'ms')
                .addClass('vegas-transition-' + transition + '-out');

              $slides.each(function () {
                var video = $slides.find('video').get(0);

                if (video) {
                  video.volume = 1;
                  self._fadeOutSound(video, transitionDuration);
                }
              });

              $slide
                .css('transition', 'all ' + transitionDuration + 'ms')
                .addClass('vegas-transition-' + transition + '-in');
            } else {
              $slide.fadeIn(transitionDuration);
            }
          }

          for (var i = 0; i < $slides.length - self.settings.slidesToKeep; i++) {
            $slides.eq(i).remove();
          }

          self.trigger('walk');
          self._slideShow();
        }, 100);
      }
      if (video) {
        if (video.readyState === 4) {
          video.currentTime = 0;
        }

        video.play();
        go();
      } else {
        img.src = src;

        if (img.complete) {
          go();
        } else {
          img.onload = go;
        }
      }
    },

    _end: function () {
      this.ended = !this.settings.autoplay;
      this._timer(false);
      this.trigger('end');
    },

    shuffle: function () {
      var temp,
        rand;

      for (var i = this.total - 1; i > 0; i--) {
        rand = Math.floor(Math.random() * (i + 1));
        temp = this.settings.slides[i];

        this.settings.slides[i] = this.settings.slides[rand];
        this.settings.slides[rand] = temp;
      }
    },

    play: function () {
      if (this.paused) {
        this.paused = false;
        this.next();
        this.trigger('play');
      }
    },

    pause: function () {
      this._timer(false);
      this.paused = true;
      this.trigger('pause');
    },

    toggle: function () {
      if (this.paused) {
        this.play();
      } else {
        this.pause();
      }
    },

    playing: function () {
      return !this.paused && !this.noshow;
    },

    current: function (advanced) {
      if (advanced) {
        return {
          slide: this.slide,
          data:  this.settings.slides[this.slide]
        };
      }
      return this.slide;
    },

    jump: function (nb) {
      if (nb < 0 || nb > this.total - 1 || nb === this.slide) {
        return;
      }

      this.slide = nb;
      this._goto(this.slide);
    },

    next: function () {
      this.slide++;

      if (this.slide >= this.total) {
        if (!this.settings.loop) {
          return this._end();
        }

        this.slide = 0;
      }

      this._goto(this.slide);
    },

    previous: function () {
      this.slide--;

      if (this.slide < 0) {
        if (!this.settings.loop) {
          this.slide++;
          return;
        } else {
          this.slide = this.total - 1;
        }
      }

      this._goto(this.slide);
    },

    trigger: function (fn) {
      var params = [];

      if (fn === 'init') {
        params = [ this.settings ];
      } else {
        params = [
          this.slide,
          this.settings.slides[this.slide]
        ];
      }

      this.$elmt.trigger('vegas' + fn, params);

      if (typeof this.settings[fn] === 'function') {
        this.settings[fn].apply(this.$elmt, params);
      }
    },

    options: function (key, value) {
      var oldSlides = this.settings.slides.slice();

      if (typeof key === 'object') {
        this.settings = $.extend({}, defaults, $.vegas.defaults, key);
      } else if (typeof key === 'string') {
        if (value === undefined) {
          return this.settings[key];
        }
        this.settings[key] = value;
      } else {
        return this.settings;
      }

      // In case slides have changed
      if (this.settings.slides !== oldSlides) {
        this.total  = this.settings.slides.length;
        this.noshow = this.total < 2;
        this._preload();
      }
    },

    destroy: function () {
      clearTimeout(this.timeout);

      this.$elmt.removeClass('vegas-container');
      this.$elmt.find('> .vegas-slide').remove();
      this.$elmt.find('> .vegas-wrapper').clone(true).children().appendTo(this.$elmt);
      this.$elmt.find('> .vegas-wrapper').remove();

      if (this.settings.timer) {
        this.$timer.remove();
      }

      if (this.settings.overlay) {
        this.$overlay.remove();
      }

      this.elmt._vegas = null;
    }
  };

  $.fn.vegas = function(options) {
    var args = arguments,
      error = false,
      returns;

    if (options === undefined || typeof options === 'object') {
      return this.each(function () {
        if (!this._vegas) {
          this._vegas = new Vegas(this, options);
        }
      });
    } else if (typeof options === 'string') {
      this.each(function () {
        var instance = this._vegas;

        if (!instance) {
          throw new Error('No Vegas applied to this element.');
        }

        if (typeof instance[options] === 'function' && options[0] !== '_') {
          returns = instance[options].apply(instance, [].slice.call(args, 1));
        } else {
          error = true;
        }
      });

      if (error) {
        throw new Error('No method "' + options + '" in Vegas.');
      }

      return returns !== undefined ? returns : this;
    }
  };

  $.vegas = {};
  $.vegas.defaults = defaults;

  $.vegas.isVideoCompatible = function () {
    return !/(Android|webOS|Phone|iPad|iPod|BlackBerry|Windows Phone)/i.test(navigator.userAgent);
  };

})(window.jQuery || window.Zepto || window.m4q);

	</script>
</body>

</html>