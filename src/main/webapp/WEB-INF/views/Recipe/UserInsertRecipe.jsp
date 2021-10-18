<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	font-size: 20px;
	font-family: Arial, Helvetica, sans-serif;
	background-color: white;
	line-height: 1.5em;
}

.header {
	background: #9393FF;
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
	margin: auto;
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
	<div class="header">
		<h1>個人食譜</h1>
	</div>
	<form>
		<input type ="button" onclick="history.back()" value="上一頁">
	</form>
	<div class="container">
		<form:form action="./UserInsertRecipe" modelAttribute="userRecipe" method="POST" enctype="multipart/form-data">
			<fieldset>
				<legend>新增</legend>

				<div class="st1">
					<label for="" class="t1">姓名:</label>
					<!-- 在spring taglib裡遇到required、hidden等等原本在HTML標籤不需要加="true"的 一定要補上 -->
					<!-- 否則會出現 equal symbol expected-->
<%-- 					<form:input path="userName" required="true" /> --%>
					<input type="text" id="" name="userName" required>
				</div>

				<div class="st1">
					<label for="" class="t1">品項:</label>
<%-- 					<form:input path="foodName" required="true" /> --%>
					<input type="text" id="" name="foodName" required>
				</div>
				<div class="st1">
					<label for="" class="t1">分類:</label>
<%-- 					<form:input path="category" required="true" /> --%>
					<input type="text" id="" name="category" required>
				</div>
				<div class="st1">
					<label for="" class="t1">食材1:</label>
<%-- 					<form:input path="food1" placeholder="ex:牛肉1斤" /> --%>
					<input type="text" id="" name="food1" />
				</div>
				<div class="st1">
					<label for="" class="t1">食材2:</label>
<%-- 					<form:input path="food2" placeholder="ex:牛肉1斤" /> --%>
					<input type="text" id="" name="food2" />
				</div>
				<div class="st1">
					<label for="" class="t1">食材3:</label>
<%-- 					<form:input path="food3" placeholder="ex:牛肉1斤" /> --%>
					<input type="text" id="" name="food3" />
				</div>
				<div class="st1">
					<label for="" class="t1">食材4:</label>
<%-- 					<form:input path="food4" placeholder="ex:牛肉1斤" /> --%>
					<input type="text" id="" name="food4" />
				</div>
				<div class="st1">
					<label for="" class="t1">調味料1:</label>
<%-- 					<form:input path="sauce1" placeholder="ex:鹽1匙" /> --%>
					<input type="text" id="" name="sauce1" />
				</div>
				<div class="st1">
					<label for="" class="t1">調味料2:</label>
<%-- 					<form:input path="sauce2" placeholder="ex:鹽1匙" /> --%>
					<input type="text" id="" name="sauce2" />
				</div>
				<div class="st1">
					<label for="" class="t1">調味料3:</label>
<%-- 					<form:input path="sauce3" placeholder="ex:鹽1匙" /> --%>
					<input type="text" id="" name="sauce3" />
				</div>
				<div class="st1">
					<img src="" width="450" height="300" alt="請選擇照片">
				</div>
				<div class="st1">
					<label for="" class="t1">照片:</label>
<%-- 					<form:input path="photo" type="file"/> --%>
					<input type="file" name="photo">
				</div>

			</fieldset>
			<div class="sub">
				<input type="reset" name="reset" value="清除"> <input
					type="submit" name="submit" value="確認">
			</div>

		</form:form>
	</div>
	<script type="text/javascript">
		var x = new FileReader;
		document.forms[1].elements[11].onchange = function() {
			x.readAsDataURL(this.files[0]);
		}
		x.onloadend = function() {
			document.images[0].src = this.result;
		}
	</script>

</body>
</html>