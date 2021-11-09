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
	<title>想食What商城</title>

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
						<p>購物天堂</p>
						<h1>想食商城</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end breadcrumb section -->

	<!-- cart -->
	<div class="cart-section mt-150 mb-150">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-md-12">
					<div class="cart-table-wrap">
						<table class="cart-table">
							<thead class="cart-table-head">
								<tr class="table-head-row">
									<th class="product-remove"></th>
									<th class="product-image">商品圖片</th>
									<th class="product-name">商品名稱</th>
									<th class="product-price">價錢</th>
									<th class="product-quantity">數量</th>
									<th class="product-total">小計</th>
								</tr>
							</thead>
						
							<tbody>
								<c:choose>
									<c:when test="${cart.size() > 0}">
										<c:forEach items="${entrySet}" var="entry" varStatus="status">
											<input type="hidden" value="${entry.getKey()}"/>
											<tr class="table-body-row">
												<input class="product-id" type="hidden" value="${entry.getValue().item.id}" >
												<td class="product-remove"><a href="javascript:void(0)" onclick="removeFromCart(${entry.getKey()}, ${status.index})"><i class="far fa-window-close"></i></a></td>
												<td class="product-image"><img src="<c:url value='/Shop/Item/photo/${entry.getValue().item.id}'/>" alt=""></td>
												<td class="product-name">${entry.getValue().item.name}</td>
												<td class="product-price">$${entry.getValue().item.price}</td>
												<td class="product-quantity"><input type="number" placeholder="0" value="${entry.getValue().qty}"></td>
												<td class="product-total">${entry.getValue().subTotal}</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="6">
												<span style="text-align:center;font-weight: bold;font-size: large;">您尚未購買任何商品！</span>
												<a href="<c:url value='/Shop'/>">
	                                                <span style="font-size: large;">去看看 Go <i class="fas fa-arrow-right"></i></span>
	                                            </a>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>

				<div class="col-lg-4">
					<div class="total-section">
						<table class="total-table">
							<thead class="total-table-head">
								<tr class="table-total-row">
									<th>項目</th>
									<th>費用</th>
								</tr>
							</thead>
							<tbody>
								<tr class="total-data">
									<td><strong>小計: </strong></td>
									<td id="subTotal">$${total}</td>
								</tr>
								<tr class="total-data">
									<td><strong>運費: </strong></td>
									<td id="shippingFee">$${shippingFee}</td>
								</tr>
								<tr class="total-data">
									<td><strong>總計: </strong></td>
									<td id="total">$${total + shippingFee}</td>
								</tr>
							</tbody>
						</table>
						<div class="cart-buttons">
<!-- 							<a href="cart.html" class="boxed-btn">更新</a> -->
							<a href="<c:url value='/Shop/checkout'/>" class="boxed-btn black">結帳</a>
						</div>
					</div>

					<div class="coupon-section">
						<h3>您有優惠券嗎？</h3>
						<div class="coupon-form-wrap">
							<form>
								<p><input type="text" id="coupon" name="coupon" placeholder="輸入優惠券代碼"></p>
								<p id="couponStatusMsg"></p>
								<p><a href="javascript:void(0)" onclick="useCoupon()" class="boxed-btn black">使用優惠券</a></p>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end cart -->


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
	
	<script>
		$(function(){
			userNameMain();
		})
		
		$(".product-quantity input").on("blur", function (e) {
			var qtyElem = e.currentTarget;
			var orderItemIdElem = qtyElem.parentElement.parentElement.querySelector(".product-id");
			var orderItemSubTotalElem = qtyElem.parentElement.parentElement.querySelector(".product-total");
			var data = {
				qty: qtyElem.value,
				orderItemId: orderItemIdElem.value,
			};
			console.log(data)
			console.log(JSON.stringify(data))
			$.ajax({
				url: "<c:url value='/Shop/Cart/update/'/>",
				method: "post",
				data: JSON.stringify(data),
				dataType: "json",
				contentType: 'application/json; charset=UTF-8',
				success: function (data, textStatus, jqXHR) {
					console.log(data);
					orderItemSubTotalElem.innerText = data.subTotal;
					//小計
					$("#subTotal").text(data.total)
					//總計
					$("#total").text(data.total)
				}
			})
		})
					//運費（如有免運優惠券需修改）
// 					$("#shippingFee").text("0")
		
		
		function removeFromCart(id, index){
			var url = "<c:url value='/Shop/Cart/Remove/'/>"+id;
			$.ajax({
				url: url,
				method: "get",
				success: function(){
					$(".table-body-row").eq(index).remove();
					console.log("移除成功")
				},
				error: function(){
					console.log("移除失敗")
				}
			})		
		}
		
		function useCoupon() {
			$.ajax({
				url: "<c:url value='/Shop/applyCoupon'/>",
				method: "post",
				data: {coupon: $("#coupon").val()},
				success: function () {
					console.log("使用優惠券"+ $("#coupon").val() + "成功！");
					$("#couponStatusMsg").text("使用優惠: "+ "" +"成功!");
				},
				error: function (e) {
					console.log(e.statusText);
					$("#couponStatusMsg").text("此優惠券無效，可能已過期或輸入了不正確的優惠碼!");
				}
			})
		}
	</script>

</body>
</html>