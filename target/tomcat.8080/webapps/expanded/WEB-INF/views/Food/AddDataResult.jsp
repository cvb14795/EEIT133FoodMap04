<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="mapData" class="cf.cvb14795.Food.model.MapDataBean" scope="request"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>AddDataResult</title>
</head>

<body BGCOLOR="#FDF5E6">
    <H1 ALIGN=CENTER>新增結果</H1>
    
     <div>
        <img src="../images/${mapData.imgName}.jpg" />
    </div>

    <ul>
        <li>
        	<b>店家名稱：</b><br>
        	<jsp:getProperty name="mapData" property="mapname"/>
        </li>
        <li>
        	<b>店家地址：</b><br>
        	<jsp:getProperty name="mapData" property="mapku"/>
        </li>
        <li>
        	<b>店家電話：</b><br>
        	<jsp:getProperty name="mapData" property="mapnb"/>
        </li>
        <li>
        	<b>經緯度：</b><br>
        	<jsp:getProperty name="mapData" property="mapxy"/>
        </li>
        <li>
        	<b>是否為安全商家：</b><br>
        	<jsp:getProperty name="mapData" property="mapcheck"/>
        </li>
    </ul>

    <input id="confirmBtn" type="button" name="confirm" value="確認">

    <script>
        var confirmBtn = document.getElementById("confirmBtn");
        confirmBtn.addEventListener("click", function (e) {
            location.href = "./RestaurantInfo?mapName=${mapData.mapname}";
        })
    </script>
</body>
</html>