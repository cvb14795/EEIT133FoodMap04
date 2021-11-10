<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RestaurantInfo</title>
</head>

<body>
    <div>
        <img src="<c:url value='/Food/user/photo/${mapData.id}'/>">
    </div>

    <fieldset>
        <legend>店家資料</legend>
	
        <label>店名:<em> ${mapData.mapname} </em></label>
        <hr>

        <label>地址:<em> ${mapData.mapku}" </em></label>
        <hr>

        <label>電話:<em> ${mapData.mapnb}"</em></label>
        <hr>

        <label>經緯度:<em> ${mapData.mapxy}"</em></label>
        <hr>

        <label>是否為安全商家:<em> ${mapData.mapcheck}" </em></label>
        <hr>

        <label>分類:<em> ${mapData.category}" </em></label>
        <hr>

    </fieldset> 

    <input id="backBtn" type="button" name="back" value="回首頁">

    <script>
        var backBtn = document.getElementById("backBtn");
        backBtn.addEventListener("click", function (e) {
            location.href = "<c:url value='/Food/user'/>";
        })
    </script>
</body>
</body>
</html>