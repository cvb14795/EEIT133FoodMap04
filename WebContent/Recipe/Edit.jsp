<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
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
	<jsp:useBean id="recipe" class="bean.RecipeBean" scope="session"></jsp:useBean>
    <form action="./EditController" method="post">
        <fieldset>
            <legend>官方食譜</legend>
            <div class="st1">
                <label for="" class="t1">品項:</label>
                <input type="text" id="" name="name" value="${recipe.name}">
            </div>
            <div class="st1">
                <label for="" class="t1">分類:</label>
                <input type="text" id="" name="category" value="${recipe.category}">
            </div>
            <div class="st1">
                <label for="" class="t1">食材1:</label>
                <input type="text" id="" name="food1" value="${recipe.food1}">
            </div>
            <div class="st1">
                <label for="" class="t1">食材2:</label>
                <input type="text" id="" name="food2" value="${recipe.food2}">
            </div>
            <div class="st1">
                <label for="" class="t1">食材3:</label>
                <input type="text" id="" name="food3" value="${recipe.food3}">
            </div>
            <div class="st1">
                <label for="" class="t1">食材4:</label>
                <input type="text" id="" name="food4" value="${recipe.food4}">
            </div>
            <div class="st1">
                <label for="" class="t1">調味料1:</label>
                <input type="text" id="" name="sauce1" value="${recipe.sauce1}">
            </div>
            <div class="st1">
                <label for="" class="t1">調味料2:</label>
                <input type="text" id="" name="sauce2" value="${recipe.sauce2}">
            </div>
            <div class="st1">
                <label for="" class="t1">調味料3:</label>
                <input type="text" id="" name="sauce3" value="${recipe.sauce3}">
            </div>
            <div class="st1">
                <label for="" class="t1">照片:</label>
                
                <img src="data:image/jpg;base64,${sessionScope.imgList.get(param.index)}" width="100" height="100" >
<!--                 <input type="file" name="file1" value=""> -->
            </div>
            <div>
                <input type="text" name="id" value="${param.id}" hidden>
            </div>
        </fieldset>
        <div class="sub">
            <input type="submit" name="submit" value="儲存">
        </div>
    </form>
</body>
</html>