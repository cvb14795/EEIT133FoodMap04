<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <meta name="google-signin-client_id"
            content="532249608397-co7fll33vdrk1l991fsco6ko85jrlpi1.apps.googleusercontent.com"> -->
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
            margin: 200px 100px;
        }
        .card-block{
            padding: 50px;
            width: 500px;
            height: 600px;
            margin: auto;
        }
        .field{
            width: 100%;
            padding: 0 30px;
            margin: 20px 0;
            display: flex;
        }
        .t1 {
            width: 60px;
            height: 40px;
            line-height: 40px;
            font-size: 18px;
        }
        .input {
            width: 300px;
            height: 40px;
        }
        .text-area {
            height: 120px;
            max-height: 120px;
            width: 300px;
            max-width: 300px;

        }
        .btn-layout {
            width: 100%;
            padding: 0 30px;
            margin: 20px 0;
            display: flex;
            justify-content: center;
        }
        .form-btn{
            width: 100px;
            height: 40px;
            line-height: 40px;
            margin: 0 20px;
        }
        
        img.user-avatar {
		width:40px;
	}
    </style>
    <title>??????</title>

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
    <!-- responsive -->
    <link rel="stylesheet" href="<c:url value='/css/user/css/responsive.css'/>">
    <!-- bootstrap -->
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/lineLogin.css'/>">
    <!-- vegas -->
    <link rel="stylesheet" href="<c:url value='/css/vegas.css'/>"/>

    <!-- <link href="./test/pre.css" rel="stylesheet" /> -->
    <!-- ????????????IOS??????(??????????????????) -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.css">
    <!-- main style -->
    <link rel="stylesheet" href="<c:url value='/css/user/css/main.css'/>">
    <script src="https://cdn.jsdelivr.net/npm/weatherstar-switch@1.0.7/dist/switch.min.js"></script>
    <!-- ???????????? -->
    <script src="https://cdn.jsdelivr.net/npm/darkmode-js@1.5.7/lib/darkmode-js.min.js"></script>
    <script src="<c:url value='/js/jquery-3.6.0.js"'/>"></script>
    <script src="<c:url value='/js/bootstrap.js'/>"></script>
    <script src="<c:url value='/js/lineLogin.js'/>"></script>
    <%-- <script src="<c:url value='/vegas/vegas.js'/>"></script> --%>
    <!-- aboutUs -->
    <!-- <script src="./js/aboutUs.js"></script> -->

    <!-- <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script> -->

    <script>
        /* ????????? */
        //?????????????????????alert
        /* ????????? */

        // function signOut() {
        //     var auth2 = gapi.auth2.getAuthInstance();
        //     auth2.signOut().then(function () {
        //         console.log('User signed out.');
        //     });
        // }
        $('document').ready(function() {
            $('#tabs a').click(function(e) { //????????? id=tabs ??????????????????
                e.preventDefault(); //???????????????????????? (????????????)
                var url = $(this).attr("data-url"); //?????? data-url ?????????
                // var href = this.hash; //?????? href ???
                // var pane = $(this); //?????? DOM ??????
                // ?????????????????????
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
								<li><a href="<c:url value='/'/>">??????</a></li>
								<li><a href="<c:url value='/Food/user'/>">????????????</a></li>
								<li><a href="<c:url value='/Coupon/frontpage'/>">????????????</a></li>
								<li class="current-list-item"><a href="<c:url value='/Event/'/>">????????????</a>
								<li><a href="<c:url value='/comments/list'/>">????????????</a></li>
								<li><a href="<c:url value='/Recipe/user'/>">????????????</a></li>
								<li><a href="<c:url value='/Shop/'/>">????????????</a>
								<li>
									<div class="header-icons">
										<!-- <a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a> -->
										<li><a><img class="user-avatar rounded-circle" src=""/> ?????????<span id="user"></span></a>
											<ul class="sub-menu">
												<li id="editNavBtn"><a
													href="<c:url value='/Member/Revise'/>"><i
														class="fas fa-edit"></i> ??????????????????</a></li>
												<li id="registerNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Register'/>"><i class="fas fa-user-plus"></i>
														????????????</a></li>
												<li id="loginNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Login'/>"><i class="fas fa-sign-in-alt"></i>
														??????</a></li>
												<li id="logoutNavBtn"><a class="nav-link"
													href="<c:url value='/Member/Logout'/>"><i class="fas fa-sign-out-alt"></i>
														??????</a></li>
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
<!-- 												???????????? -->
<!-- 	<!-- 											<label for="checkbox-switch" style="">????????????</label> --> 
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
                    <p>????????????</p>
                    <h1>????????????</h1>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end breadcrumb section -->


<div class="modal" id="modal-message" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">????????????</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div  class="modal-body">
                <p id="modal-body"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="gohome()">??????, ?????????</button>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="isAdmin" value="${isAdmin}">
<input type="hidden" id="userName" value="${user}">

<!-- ???????????? -->
<div class="main">
<%--        <div style="text-align:center;width:550px;height:50px;"><h2>??????????????????</h2></div>--%>
        <div class="container">
            <form id="event-form" class="card card-block" >

                <h1>????????????
                    <%--            <span>?????????????????????</span>--%>
                </h1>

                <div>
                    <div class="field">
                        <label for="" class="t1">??????:</label>
                        <input class="input" maxlength="10" type="text" id="eventname" name="peoplename" required placeholder="???????????????"/>
                    </div>
                    <div class="field">
                        <label for="" class="t1">??????:</label>
                        <input class="input" min="0" max="100" type="number" id="eventpeople" name="eventpeople" required placeholder="????????????: "/>
                    </div>
                    <div class="field">
                        <label for="" class="t1">??????:</label>
                        <input class="input"  type="date" id="eventtime" name="eventtime" required placeholder=""/>
                    </div>
                    <div class="field">
                        <label for="" class="t1">??????:</label>
                        <textarea class="text-area" type="text" id="eventcontent" name="eventcontent" placeholder="????????????: "></textarea>
                    </div>


                </div>
                <div class="btn-layout">
<%--                    <input  type="reset" name="reset" value="?????????">--%>
                    <input  type="submit" name="submit" value="??????">
                </div>

            </form>
        </div>
</div>
<!-- end ???????????? -->

<!-- footer -->
<div class="footer-area">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-12">
                <div class="footer-box about-widget">
                    <h2 class="widget-title">????????????</h2>
                    <p>
                        1.????????????????????????????????????????????????????????????<br />????????????google map????????????????????????????????????????????????
                    </p>
                    <p>
                        2.????????????????????????????????????????????????????????????????????????????????????????????????<br />?????????????????????????????????????????????????????????
                    </p>
                    <p>3.?????????????????????????????????????????????????????????</p>
                    <p>4.??????????????????????????????????????????????????????????????????</p>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="footer-box">
                    <h2 class="widget-title">Line ????????????</h2>
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
                    <h2 class="widget-title">??????</h2>
                    <ul>
                        <li><a href="<c:url value='/'/>">??????</a></li>
                        <li><a href="<c:url value='/aboutUs'/>">????????????</a></li>
                        <li><a href="<c:url value='/Food/user'/>">????????????</a></li>
                        <li><a href="<c:url value='/Coupon/frontpage'/>">????????????</a></li>
                        <li><a href="<c:url value='/Event/'/>">????????????</a></li>
                        <li><a href="<c:url value='/comments/list'/>">????????????</a></li>
                        <li><a href="<c:url value='/Recipe/user'/>">????????????</a></li>
                        <li><a href="<c:url value='/Shop'/>">????????????</a></li>
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
    function gohome(){
        console.log('go home')
        location.href="/FoodMap04"
    }
    $(document).ready(function () {
        let today = new Date();
        let dd = today.getDate();
        let mm = today.getMonth() + 1; //January is 0!
        let yyyy = today.getFullYear();

        if (dd < 10) {
            dd = '0' + dd;
        }

        if (mm < 10) {
            mm = '0' + mm;
        }

        today = yyyy + '-' + mm + '-' + dd;
        $('#eventtime').attr("min", today);

        $('#event-form').submit(function (e) {
            console.log('ajax')
            e.preventDefault();

            $('input[name="submit"]').prop('disabled', true)



            let req = {
                eventpeople: $('#eventpeople').val(),
                eventname: $('#eventname').val(),
                eventtime: $('#eventtime').val().replaceAll('-', ''),
                eventcontent: $('#eventcontent').val(),
            }
            $.ajax({
                method: "POST",
                // datatype: "text", // ????????????
                contentType: 'application/json;',
                data: JSON.stringify(req),
                url: `/FoodMap04/Event/registration`,
                success: function () {
                    $('#event-form').trigger('reset')
                    $('#modal-message').modal('show')
                    $('#modal-body').text("????????????")
                },
                error: function () {
                    console.log('????????????')
                },
                complete: function () {
                    $('input[name="submit"]').prop('disabled', false)
                },
            })
        })
    })

    $(function() {
    	var src = "<c:url value='/Member/user/${user}/photo'/>";
		userNameMain(src);

        const cookies = document.cookie;
        console.log("cookies:'" + cookies + "'");
        let userName = cookies.split("user=")[1];   //??????
        $("#admin").on("click", function(e){
            if(userName != undefined){
                location.href="<c:url value='/Coupon/admin' />";
            } else {
                alert("????????????!")
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