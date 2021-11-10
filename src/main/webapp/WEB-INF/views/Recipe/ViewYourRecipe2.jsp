<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

	<!-- title -->
	<title>食譜規劃</title>

	<!-- favicon -->
	<link rel="shortcut icon" type="image/png" href="<c:url value='/image/user/favicon.png'/>">
	<!-- google font -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
	<!-- fontawesome -->
	<link rel="stylesheet" href="<c:url value='/css/user/css/all.min.css'/>">
	<!-- bootstrap -->
	<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
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
	<link rel="stylesheet" href="<c:url value='/css/lineLogin.css'/>">
	<!-- 開關改成IOS風格(左右滑動按鈕) -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.css">
	<script src="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.min.js"></script>
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
								<li><a href="<c:url value='/Event/'/>">活動總覽</a>
									<ul class="sub-menu">
										<li><a href="<c:url value='/Event/buy'/>">付費活動購票</a></li>
										<li><a href="<c:url value='/Event/entry'/>">免費活動報名</a></li>
									</ul></li>
								<li><a href="<c:url value='/comments/list'/>">評論專區</a></li>
								<li><a href="<c:url value='/Recipe/user'/>">食譜規劃</a></li>
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
											<a class="shopping-cart" href="<c:url value='/Cart/'/>">
												<i class="fas fa-shopping-cart"></i> 已購票券
											</a>
										</li>
										<li>
											<a>
												<label for="checkbox-switch" style="">淺色模式</label>
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

	<!-- breadcrumb-section -->
	<div class="breadcrumb-section breadcrumb-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 text-center">
					<div class="breadcrumb-text">
						<p>個人食譜</p>
						<h1>食譜規劃</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->

	<!-- products -->
	<div class="product-section mt-150 mb-150">
		<div class="container">

			<div class="row">
				<div class="col-md-12">
					<div class="product-filters">
						<ul>
<!-- 							<li class="active" data-filter="*">官方食譜</li> -->
							<li><a href="<c:url value="/Recipe/user"/>">官方食譜</a></li>
							<li><a href="<c:url value="/Recipe/user/UserViewMembersRecipe2"/>">所有會員食譜</a></li>
							<li><a href="<c:url value="/Recipe/user/UserInsertRecipe2"/>">新增專屬食譜</a></li>
							<li class="active"><a href="<c:url value="/Recipe/user/ViewYourRecipe2"/>">查詢您的食譜</a></li>
							<li>我的最愛</li>
						</ul>
					</div>
				</div>
			</div>

			<div class="row product-lists">
				<c:choose>
					<c:when test="${lists.size() != 0}">
						<c:forEach var="i" begin="0" end="${lists.size()-1 }">
							<div class="col-lg-4 col-md-6 text-center">
								<div class="single-product-item">
									<div class="product-image">
										<a href="single-product.html"><img src="data:image/jpg;base64,${imgList.get(i)}"></a>
									</div>
									<h3>品名:${lists.get(i).foodName}</h3>
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
									<h6>步驟:
										<p>
											<span>
												${lists.get(i).step}
											</span>
										</p>
									</h6>
									<a href="<c:url value='/Recipe/user/ViewYourRecipe2/${lists.get(i).id}'/>" class="cart-btn"><i class="fas fa-edit"></i> 修改</a>
									<a href="javascript:void(0)" onclick="doDelete('${lists.get(i).id}', '${lists.get(i).foodName}')" class="cart-btn"><i class="fas fa-trash-alt"></i> 刪除</a>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="container">
							<div style="text-align: center;">
								<h2>無資料!</h2>
								<div class="pagination-wrap" style="display: inline;" >
									<ul style="display: inline;">
										<li>
											<a href="<c:url value='/Recipe/user/UserInsertRecipe2'/>">
												<span style="font-size: large;">新增食譜Go <i class="fas fa-arrow-right"></i></span>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>

			<c:if test="${lists.size() != 0}">
				<div class="row">
					<div class="col-lg-12 text-center">
						<div class="pagination-wrap">
							<ul>
								<li><a href="#">前一頁</a></li>
								<li><a href="#">1</a></li>
								<li><a class="active" href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">下一頁</a></li>
							</ul>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<!-- end products -->

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
							<li>
								<a href="" id="lineLoginHref">
									<input type="button"class="lineLogin" style="width: 151px; height: 44px; border: none" />
								</a>
			  				</li>
							<li><img src="<c:url value='/image/lineFoodMap04QR.png'/>"alt=""></li>
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

	<!-- jquery -->
	<script src="<c:url value='/js/user/js/jquery-1.11.3.min.js'/>"></script>
	<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
	<!-- bootstrap -->
	<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
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
	<!-- sweetAlert js -->
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
	<script>
		$(function(){
			userNameMain();
		})
		
		function doDelete(id, name){
			let isAccept = confirm("您確認要從食譜中移除 "+ name +" 嗎？")
			if (isAccept) {
				let url = "<c:url value='/Recipe/user/ViewYourRecipe2/'/>"+id;
				
				$.ajax({
					url: url,
					method: "delete",
					success: function () {
						Swal.fire({
							position : 'center',
							icon : 'success',
							title : '刪除成功'
						})
						location.reload();
					}
				})
			}
		}
	</script>

</body>
</html>