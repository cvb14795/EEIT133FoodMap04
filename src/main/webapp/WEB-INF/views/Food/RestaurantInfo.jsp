<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mapData" class="cf.cvb14795.Food.model.MapData" scope="request"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RestaurantInfo</title>
</head>

<body>
    <div>
        <img src="../images/${mapData.imgName}.jpg" />
    </div>

    <fieldset>
        <legend>店家資料</legend>
        <!--em  strong s 語意標籤 用來強調-->
        <label>店名:<em> <jsp:getProperty name="mapData" property="mapname"/> </em></label>
        <hr>

        <label>地址:<em> <jsp:getProperty name="mapData" property="mapku"/> </em></label>
        <hr>

        <label>電話:<em> <jsp:getProperty name="mapData" property="mapnb"/></em></label>
        <hr>

        <label>經緯度:<em> <jsp:getProperty name="mapData" property="mapxy"/></em></label>
        <hr>

        <label>是否為安全商家:<em> <jsp:getProperty name="mapData" property="mapcheck"/></em></label>
        <hr>
        <!-- <input type="button" id="idbut" value="checkPassword" onclick="checkPwd(); checkid(); checkDate()" /> -->

    </fieldset> 

    <input id="backBtn" type="button" name="back" value="回首頁">

    <script>
        var backBtn = document.getElementById("backBtn");
        backBtn.addEventListener("click", function (e) {
            location.href = "./FoodMap";
        })
    </script>
</body>
</body>
</html>