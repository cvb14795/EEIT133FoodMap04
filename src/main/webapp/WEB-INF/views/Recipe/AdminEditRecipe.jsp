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
    <title>資料修改</title>
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
        <h1>修改食譜</h1>
    </header>
    <form:form action="./adminEditRecipeAction" modelAttribute="recipe" method="POST">
        <fieldset>
            <legend>官方食譜</legend>
            <div class="st1">
                <label for="" class="t1">品項:</label>
<%--                 <form:input path="name"/> --%>
                <input id="name" name="name" value=""/>
            </div>
            <div class="st1">
                <label for="" class="t1">分類:</label>
                <form:input path="category"/>
            </div>
            <div class="st1">
                <label for="" class="t1">食材1:</label>
                <form:input path="food1"/>
            </div>
            <div class="st1">
                <label for="" class="t1">食材2:</label>
                <form:input path="food2"/>
            </div>
            <div class="st1">
                <label for="" class="t1">食材3:</label>
                <form:input path="food3"/>
            </div>
            <div class="st1">
                <label for="" class="t1">食材4:</label>
                <form:input path="food4"/>
            </div>
            <div class="st1">
                <label for="" class="t1">調味料1:</label>
                <form:input path="sauce1"/>
            </div>
            <div class="st1">
                <label for="" class="t1">調味料2:</label>
                <form:input path="sauce2"/>
            </div>
            <div class="st1">
                <label for="" class="t1">調味料3:</label>
                <form:input path="sauce3"/>
            </div>
            <div class="st1">
                <label for="" class="t1">照片:</label>
                
                <img src="data:image/jpg;base64,${base64String}" width="100" height="100" >
                <input type="text" name="photo" value="${base64String}" hidden>
            </div>
            <div>
                <input type="text" id="id" name="id" value="${param.id}" hidden>
            </div>
        </fieldset>
        <div class="sub">
            <input type="submit" name="submit" value="儲存">
        </div>
    </form:form>
    
    <script>
		$.ajax({
			type, "get"
			url: "./AdminViewRecipe/"+$("#id").val(), 
			success: function(data){
				//放進form
				$("#name").val(data.name);
				
			}
		})
    </script>
</body>
</html>