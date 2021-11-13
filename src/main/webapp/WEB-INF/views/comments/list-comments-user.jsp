<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
								<li><a href="<c:url value='/'/>">首頁</a></li>
								<li><a href="<c:url value='/Food/user'/>">商家資訊</a></li>
								<li><a href="<c:url value='/Coupon/frontpage'/>">防疫專區</a></li>
								<li><a href="<c:url value='/Event/'/>">活動總覽</a>
								<li class="current-list-item"><a href="<c:url value='/comments/list'/>">評論專區</a></li>
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
											  <a class="shopping-cart" href="<c:url value="/Recipe/user/showMyfavorites"/>">
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
	
	
<!-- another rating diagram -->

<div class="container-fluid px-1 py-5 mx-auto">
    <div class="row justify-content-center">
        <div class="col-xl-7 col-lg-8 col-md-10 col-12 text-center mb-5">
            <div class="card" id="rate-header">
                <div class="row justify-content-left d-flex">
                    <div class="col-md-4 d-flex flex-column">
                        <div class="rating-box">
                            <h1 class="pt-5">4.0</h1>
                            <p class = "rating" id = "rating"></p>
                        </div>
                        <div> <span class="fa fa-star star-active mx-1"></span> <span class="fa fa-star star-active mx-1"></span> <span class="fa fa-star star-active mx-1"></span> <span class="fa fa-star star-active mx-1"></span> <span class="fa fa-star star-inactive mx-1"></span> </div>
                    </div>
                    <div class="col-md-8">
                        <div class="rating-bar0 justify-content-center">
                            <table class="text-left mx-auto">
                                <tr>
                                    <td class="rating-label">5<span class="fa fa-star star-active mx-1"></span></td>
                                    <td class="rating-bar">
                                        <div class="bar-container">
                                            <div class="bar-5" style="width: 70%"></div>
                                        </div>
                                    </td>
                                    <td class="text-right">123</td>
                                </tr>
                                <tr>
                                    <td class="rating-label">4<span class="fa fa-star star-active mx-1"></span></td>
                                    <td class="rating-bar">
                                        <div class="bar-container">
                                            <div class="bar-4" style="width: 30%"></div>
                                        </div>
                                    </td>
                                    <td class="text-right">23</td>
                                </tr>
                                <tr>
                                    <td class="rating-label">3<span class="fa fa-star star-active mx-1"></span></td>
                                    <td class="rating-bar">
                                        <div class="bar-container">
                                            <div class="bar-3" style="width: 20%"></div>
                                        </div>
                                    </td>
                                    <td class="text-right">10</td>
                                </tr>
                                <tr>
                                    <td class="rating-label">2<span class="fa fa-star star-active mx-1"></span></td>
                                    <td class="rating-bar">
                                        <div class="bar-container">
                                            <div class="bar-2" style="width: 10%"></div>
                                        </div>
                                    </td>
                                    <td class="text-right">3</td>
                                </tr>
                                <tr>
                                    <td class="rating-label">1<span class="fa fa-star star-active mx-1"></span></td>
                                    <td class="rating-bar">
                                        <div class="bar-container">
                                            <div class="bar-1" style="width: 5%"></div>
                                        </div>
                                    </td>
                                    <td class="text-right" id = "star1">0</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            
            
            
            
 			<a href="<c:url value='/comments/sortByUserDate'/>" class="btn btn-primary btn-sm mb-3">
			按時間排序
			</a>
			<a href="<c:url value='/comments/sortByUserScore'/>" class="btn btn-primary btn-sm mb-3">
			高分至低分
			</a>
			<a href="<c:url value='/comments/sortByUserScoreAsc'/>" class="btn btn-primary btn-sm mb-3">
			低分至高分
			</a>
          	<a href="<c:url value='/comments/sortByUserLikes'/>" class="btn btn-primary btn-sm mb-3">
			依關聯性排序
			</a>
          
          
 
            <c:forEach items="${comments}" var="tempComment">
     
            <div class="card">
                <div class="row d-flex">
                    <div class=""> <img class="profile-pic" src="https://randomuser.me/api/portraits/women/44.jpg"> </div>
                    <div class="d-flex flex-column">
                        <h3 class="text-left"><a href="sortByUserAccount?userAccount=${tempComment.userAccount}">${tempComment.userName}</a></h3>
                        <div class="rating">
                            <img src="<c:url value='/image/star${tempComment.score}.png'/>" />
                        </div>
                    </div>
                    <div class="ml-auto">
                        <p class="text-muted pt-5 pt-sm-3">${tempComment.userDate}</p>
                    </div>
                </div>
               		
                
               
                
                <div class="row text-left">
                    <h4 class="blue-text mt-4"><a href="sortByMapName?mapName=${tempComment.mapName}">${tempComment.mapName}</a></h4>
                </div>
                
                    <div class="row text-left">
                    <p class="content">${tempComment.userComment}</p>
               		</div>
               		<br><br>
               		<form:form action="" id="likesForm" method="POST">
               		<div class="heart"></div><p class = "heartnum" id = heartnum>${tempComment.userLikes}</p>
                   	<input type="hidden" name="userLikes" value="${tempComment.userLikes}">
                	</form:form>
            </div>
        </c:forEach>
        
        
        
        
        </div>
    </div>
</div>

<!-- ends here -->
	
	<!-- show comments from user side -->
	
	<div class="container">

	<h3>評論區</h3>
	<hr>
	<form:form action="" class="form-inline" id="searchForm" method="get">
	<a href="<c:url value='/comments/showFormForAdd'/>" class="btn btn-primary btn-sm mb-3">
		新增評論
	</a>
	
	<input class="form-control  ml-5 mr-sm-2 mb-3" type="search" name="commentName" placeholder="輸入關鍵字" />

		<button class="btn btn-success mb-3" type="submit">搜尋</button>
	
	<br>
	
	</form:form>		
	
	<!-- 
	排序方式： <select class="form-control" name="sortingType" id="sortingType" th:action="@{/comments/sorting}">
                <option th:value="''">請選擇排序方式</option>
                <option th:value="'high'">評分高 → 低</option>
    			<option th:value="'lowToHigh'">評分低 → 高</option>
    			<option th:value="'new'" >時間新 → 舊</option>
    			<option th:value="'oldToNew'" >時間舊 → 新</option>
            	</select>
     -->        	
            	
            	
    <br><br>
	
	<table class="table table-bordered table-striped">
		<thead class="thead-dark">
			<tr>
				<th>姓名</th>
				<th>評分</th>
				<th>評論</th>
				<th>評論時間</th>
				<th>編輯/刪除</th>
			</tr>
		</thead>	
		<tbody>
			<c:forEach items="${comments}" var="tempComment">
				<tr>
					<td>${tempComment.userName}</td>
					<!-- <td th:text="${tempComment.score}" />  -->
					
					<td><img src="<c:url value='/image/star${tempComment.score}.png'/>" /></td>
					<td>${tempComment.userComment}</td>
					<td>${tempComment.userDate}</td>
					
					<td>
						<!-- Add "update" button/link -->
						<a href="<c:url value='/comments/showFormForUpdate/${tempComment.id}'/>"
							class="btn btn-info btn-sm">
							更新					
						</a>
						
						<!-- Add "delete" button/link -->
						<a href="<c:url value='/comments/delete/${tempComment.id}'/>"
							class="btn btn-danger btn-sm"
							onclick="if (!(confirm('確定要刪除嗎？'))) return false">
							刪除					
						</a>
						
					</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>

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
	
	<script>
	var clicks = 0;
	var userLikes = clicks;
	var userLikes = $("#userLikes").val()
	
	<!-- like button js -->
	
	var hasClicked = false;
	
	
		
    function onClick() 
    {
        if(!hasClicked)
        {
           clicks += 1;
           document.getElementById("heartnum").innerHTML = clicks;
           hasClicked = true;
           userLikes = clicks;
           
        }else {
        	clicks -= 1;
            document.getElementById("heartnum").innerHTML = clicks;
        	hasClicked = false;
        	userLikes = clicks;
        }

    }
		
	
    </script>
    
    <script>
    // 計算有幾則評論
    
    var numItems = $('.rating').length;
    document.getElementById("rating").innerHTML ="共" + (numItems-1) + "則評論";
    
    </script>
    
    <script>
    // 統計分數
    
    
    var matches = 0;
    $('.rating').each(function(i, val) {
      if ($(this).val() == '1') {
        matches++;
      }
    });
    document.getElementById("star1").innerHTML = matches;
    </script>
    
    <script>
    $(function() {
		
		var clicks = 0;
		var userLikes = clicks;
		var userLikes = $("#userLikes").val()
		
		<!-- like button js -->
		
		var hasClicked = false;
		
		  $(".heart").on("click", function() {
			  if(!hasClicked)
		        {
				  clicks += 1;
				  document.getElementById("heartnum").innerHTML = clicks;
				  hasClicked = true;
				  userLikes = clicks;
		           $(this).toggleClass("is-active");
		           
		        }else {
		        	 clicks -= 1;
		        	 document.getElementById("heartnum").innerHTML = clicks;
		        	 hasClicked = false;
		        	 userLikes = clicks;
		        	 $(this).toggleClass("is-active");
		        }
		   
		  });
		});
	</script>
	
	
	
	
	<script>
	function
	 $("#searchForm").attr("action", "<c:url value='/comments/search'/>")
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







