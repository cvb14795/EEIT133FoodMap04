<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>店家資料修改</title>
    <link rel="stylesheet" href="<c:url value='/css/sweetalert2-9.17.2.css'/>">
	<script src="<c:url value='/js/jquery-3.6.0.js'/>"></script>
	<script src="<c:url value='/js/sweetalert2-9.17.2.js'/>"></script>
    <style>
        body {
            font-size: 20px;
            font-family: Arial, Helvetica, sans-serif;
            background-color: white;
            line-height: 1.5em;
        }

        header {
            background: #FFBB77;
            color: white;
            padding: 20px;
            text-align: center;
            margin-bottom: 10px;
        }

        .container {
            width: 90%;
            /* 外距:元素跟元素的間距 */
            margin: auto;
            /* 內距:元素內跟邊界的間距 */
            padding: 10px;
        }

        fieldset {
            /*因為是包住 div 所以要比 div 大*/
            width: 500px;
            margin: auto;
            border: 3px solid #FF0080;
            border-radius: 20px;
        }

        legend {
            font-size: 24px;
            color: #66B3FF;
            /* text-align: center; */
            margin-left: 10px;
        }

        .st1 {
            width: 450px;
            border-bottom: 2px dashed #FF95CA;
            margin: 20px;
            padding-bottom: 20px;
        }

        .sub {
            width: 450px;
            margin: auto;
            text-align: center;
        }

        .t1 {
            width: 100px;
            float: left;
            text-align: right;
        }
    </style>
</head>
<body>
    <header>
        <h1>修改店家資料</h1>
    </header>
    <form id="form" modelAttribute="updataFood" method="POST" enctype="multipart/form-data">
<%--     <form id="form"> --%>
        <fieldset>
            <legend>店家資訊</legend>
            <div class="st1">
                <label for="" class="t1">店家名稱:</label>
<%--                 <form:input id="" path="name"/> --%>
                <input id="name" name="mapname" value="${updataFood.mapname}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">店家地址:</label>
<%--                 <form:input id="" path="category"/> --%>
                <input id="name" name="mapku" value="${updataFood.mapku}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">店家電話:</label>
<%--                 <form:input id="" path="food1"/> --%>
                <input id="name" name="mapnb" value="${updataFood.mapnb}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">店家座標位置:</label>
<%--                 <form:input id="" path="food2"/> --%>
                <input id="name" name="mapxy" value="${updataFood.mapxy}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">是否為安全店家:</label>
<%--                 <form:input id="" path="food3"/> --%>
                <input id="name" name="mapcheck" value="${updataFood.mapcheck}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">分類:</label>
<%--                 <form:input id="" path="food4"/> --%>
                <input id="name" name="category" value="${updataFood.category}"/>
            </div>
            <div class="st1">
                <label for="" class="t1">照片:</label>
                
                <img id="src" src="data:image/jpg;base64,${base64String}" width="100" height="100" >
                <input type="text" id="" name="filename" value="${base64String}" hidden>
            </div>
            <div>
<%--              	<form:input  id="id" path="id" hidden="true"/> --%>
                <input type="number" id="id" name="id" value="${updataFood.id}" hidden>
            </div>
        </fieldset>
        <div class="sub">
            <input type="submit" id="send" name="submit" value="儲存">
        </div>
    </form>
    
    <script>
    	var form = document.getElementById("form");
    	$("#form").on("submit", function(e){
			/* =====for formData&MultipartFile =====*/
			var formData = new FormData(form);
			
			/* =====for JSON =====*/
			
			var html = "";
			var inputData = $(".st1 input").slice(0, 9);
			// html += + "name" + $("#name").val() + "<br/>";
			for (let i = 0; i < inputData.length; i++) {
				let name = inputData.eq(i).attr("name");
				let value = (inputData.eq(i).val() != "") ? inputData.eq(i).val() : "無";
				if(inputData.eq(i).attr("name") != "filename"){
				html += name+": "+value+"</br>";
				}
			}
			console.log(html);
			
			//改用ajax傳送 棄用原本的form傳送
			e.preventDefault();
			
			Swal.fire({
				title: '您確定要儲存嗎？',
				icon: 'question',
				html: html,
				imageUrl: $("#src").attr("src"),
				imageWidth: 400,
				imageHeight: 200,
				showCancelButton: true,
			}).then((result) => {
				if (result.isConfirmed) {
					let url = "<c:url value='/Food/admin/AdminViewFood/id=" + ${updataFood.id} +"'/>"
					console.log(url)
					$.ajax({
						type:"post",
						url:url,
						data: formData,
						contentType: false, //required
						processData: false, // required
						/*一定要加*/
						mimeType: 'multipart/form-data', //有圖片就要加這行
						/*一定要加*/
						success: function(data){
							var jsonData = JSON.parse(data);
							console.log("Success:" + "\nID:" +jsonData.id + "\nName:" +jsonData.name) ;

							var html1 = "";
							for (const key in jsonData) {
								let val = (jsonData[key] != "") ? jsonData[key] : "無";
								if (!(key == "base64String" || key == "filename")) {
									console.log(key);
									console.log(jsonData[key]);
									html1 += key+": "+val;
									html1 += "<br/>";
								} 
							};

							Swal.fire({
								title: '已儲存成功！',
								icon: 'success',
								html: html1,
								imageUrl: 'data:image;base64,'+jsonData.base64String,
								imageWidth: 400,
								imageHeight: 200,
							})
							setTimeout("window.location.pathname = 'FoodMap04/Food/admin'",1500);
						},
						error: function(e){
							console.log(e);
						}
					})
				} else if (result.dismiss === Swal.DismissReason.cancel) {
					Swal.fire({
						icon: 'error',
						title: '已取消送出請求',
						text: '您的變更將不會被儲存!'
					})
				}
			})
		})
    </script>
</body>
</html>