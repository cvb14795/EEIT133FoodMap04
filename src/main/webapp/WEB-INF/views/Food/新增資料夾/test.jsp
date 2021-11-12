<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="mapData" class="cf.cvb14795.Food.model.MapDataBean" scope="request"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
<title>RestaurantInfo</title>
</head>

<body>
	<input type="hidden" id="address" value="${mapDataBean.mapku}"/>
	
    <fieldset>
        <legend>店家資料</legend>
        <!--em  strong s 語意標籤 用來強調-->
        <label>店名:<em> ${mapDataBean.mapname} </em></label>
        <hr>

        <label>地址:<em> ${mapDataBean.mapku} </em></label>
        <input type="button" id="showGoogleMap" value="顯示google地圖">
        <hr>

        <label>電話:<em> ${mapDataBean.mapnb}</em></label>
        <hr>

        <label>經緯度:<em>${mapDataBean.mapxy}</em></label>
        <hr>

        <label>是否為安全商家:<em> ${mapDataBean.mapcheck}</em></label>
        <hr>
        <!-- <input type="button" id="idbut" value="checkPassword" onclick="checkPwd(); checkid(); checkDate()" /> -->

    </fieldset> 
    
    <input id="backBtn" type="button" name="back" value="回首頁">
	<div id="map" style="width:500px;height:500px;"></div>
	
    <script>
        var backBtn = document.getElementById("backBtn");
        backBtn.addEventListener("click", function (e) {
            location.href = "<c:url value='/Food/_mapdata/index'/>";
        })
        
         // 用戶輸入的地址
	    var address= "";
	    // 地址轉經緯度後結果
	    var data = {
           lat:"",
           lng:""
	    }
	    var map;
	    // 沒點顯示地圖按鈕時為隱藏
	    $("#map").hide();
	    var geocoder;
	    function initMap() {
	        // 建立 geocoder 物件實例
	        map = new google.maps.Map(document.getElementById('map'), {
	          center: {lat: 25.04, lng: 121.512},
	          zoom: 18
	        });
	        geocoder = new google.maps.Geocoder();
	        // 請 Google Maps API 依據地址轉換成經緯度
	    }
	    $("#showGoogleMap").on("click", function(e){
	    	// 點顯示地圖按鈕後秀出地圖框
	    	$("#map").show();
	        address = $("#address").val();
	        console.log("顯示地址:"+address);
	        
	        geocoder.geocode( { 'address': address}, function(results, status) {
	            if (status == 'OK') {
	                // 若轉換成功...
	                data.lat = results[0].geometry.location.lat();
	                data.lng = results[0].geometry.location.lng();
	                map.setCenter({lat: data.lat , lng: data.lng})
	                console.log("lat:"+data.lat+", lng:"+data.lng);
	            } else {
	                // 若轉換失敗...
	                console.log(status)
	            }
	        });
	    })
    
    </script>
    <script async defer
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZ9QTUwXbcsHp5Fx_LbD-HVbPMw8uzBig&callback=initMap">
 	</script>
</body>
</body>
</html>