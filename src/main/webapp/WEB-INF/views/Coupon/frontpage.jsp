<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>防疫專區首頁</title>

<style>
	.mhw {
		text-align:center;
		width:150px;height:50px;
		background-color:#00AEAE;
		border:3px solid #00AEAE;
		float:right;
		margin: 8px; 
		padding: 5px;
		border-radius: 10px;
	}
	.mhw h4  {
		font-size: 20px;
	}

</style>


<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/memberDetail.css">
<script src="../js/jquery-3.6.0.js"></script>
<script src="../js/bootstrap.js"></script>

<script>
$(function() {
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
		
// 		$("#questionnaire").on("click", function(e){
// 			if(userName != undefined){
// // 				qService.checkAccount(bean.getAccount())
// 				alert("您已填過此問卷!")
// 			}
// 		})
})
</script>

<script type="text/javascript">
	//國內檢驗總計
	var result = {
	    //檢驗人數
	    peopleTotal:"",
	    //檢驗件數
	    caseTotal: "",
	    //資料更新時間 (yyyy/mm/d)
	    updateTime: "",
	};
	$.ajax({
	    url: "https://covid19dashboard.cdc.gov.tw/dash7",
	    success: function (data) {
	        console.log(data)
	        result.peopleTotal = data[0]["檢驗人數"];
	        result.caseTotal = data[0]["檢驗件數"];
	        result.updateTime = data[0]["資料更新時間"];
	    }
});
</script>
</head>
<body>
<!-- 	<div class="text-right memberDetail"> -->
<!-- 		您好，<span id="user"></span>！ -->
<!-- 	</div> -->


<input type="hidden" id="isAdmin" value="${isAdmin}">
<input type="hidden" id="userName" value="${user}">

<div style="width:550px;height:500px;float:right;">
	<div style="text-align:center;width:550px;height:50px;"><h2>疫情相關資訊</h2></div>
	<div class="mhw"><h4><a role=button href='https://sites.google.com/cdc.gov.tw/2019ncov/taiwan' target="_blank" style='color:white'>疫情監測</a></h4></div>
	<div class="mhw"><h4><a role=button href='https://www.mohw.gov.tw/mp-1.html' target="_blank"  style='color:white'>衛福部</a></h4></div>
	<div class="mhw"><h4><a role=button href='https://www.cdc.gov.tw/Category/Page/vleOMKqwuEbIMgqaTeXG8A' target="_blank" style='color:white'>病毒介紹</a></h4></div>
	<div class="mhw"><h4><a role=button href='https://www.cdc.gov.tw/Category/List/SI4DlKBGujaYVJVSVKmwJw' target="_blank" style='color:white'>疫苗資訊</a></h4></div>	
	<div class="mhw"><h4><a role=button href='https://www.cdc.gov.tw/Category/MPage/epjWGimoqASwhAN8X-5Nlw' target="_blank" style='color:white'>疫苗簡介</a></h4></div>	
	<div class="mhw"><h4><a role=button href='https://www.cdc.gov.tw/Category/Page/9mcqWyq51P_aYADuh3rTBA' target="_blank" style='color:white'>接種對象</a></h4></div>	
	<div class="mhw"><h4><a role=button href='https://1922.gov.tw/' target="_blank" style='color:white'>疫苗預約平台</a></h4></div>	
	<div class="mhw"><h4><a role=button href='https://www.mohw.gov.tw/np-15-1.html' target="_blank" style='color:white'>最新消息</a></h4></div>	
	<div class="mhw"><h4><a role=button href='https://covid19.mohw.gov.tw/ch/np-5187-205.html' target="_blank" style='color:white'>紓困4.0</a></h4></div>	
	<div style="text-align:center;width:550px;height:50px;float:right;"> <h5>* 防疫專線: 1922， 國外民眾可撥打: +886-800-001922 </h5></div>
</div>
<div>
	<h1>防疫專區首頁</h1>

	<br>
	<h2>歡迎大家來填問卷</h2>
	<input type="button" value="填問卷" id='questionnaire'
		onclick="location.href='<c:url value='/Coupon/questionnaire' />'">


	<h2>管理員專用</h2>
	<input type="button" id="admin" value="功能" /> 
<%-- 	onclick="location.href='<c:url value='/Coupon/admin' />'"> --%>

	<br>
	
	<h2>回美食地圖首頁</h2>
	<input type="button" value="首頁"
		onclick="location.href='<c:url value='/' />'">		
</div>		




</body>
</html>