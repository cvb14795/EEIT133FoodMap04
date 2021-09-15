<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<%
response.setContentType("text/html;charset=UTF-8");
%>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RecipeForm</title>
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
	margin: 15px;
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
	margin: 20px;
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
		<h1>Recipe</h1>
	</header>
	<div class="container">
		<p>
			<a href="./ViewRecipe">食譜總查詢</a>
			<!-- 超連結 -> Get方法 -->
		</p>
		<form action="./RecipeServlet" method="post"
			enctype="multipart/form-data">
			<!--             <fieldset> -->
			<div>
				<div class="st1">
					<img src="" width="100" height="100" alt="123">
				</div>
				<div class="st1">
					<!-- <label for="" class="t1">照片:</label> -->
					<input type="file" name="photo">
				</div>



				<!--             </fieldset> -->
				<div class="sub">
					<input type="reset" name="reset" value="清除"> <input
						type="submit" name="submit" value="送出">
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var x = new FileReader;
		document.forms[0].elements[0].onchange = function() {

			x.readAsDataURL(this.files[0]);
		}
		x.onloadend = function() {
			document.images[0].src = this.result;
		}

	</script>

</body>

</html>