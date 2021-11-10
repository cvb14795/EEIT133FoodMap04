<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' href='<spring:url value='/css/styles.css' />' type="text/css" />
<style type="text/css">
span.error {
	color: red;
	display: inline-block;
	font-size: 8pt;
}

.fieldset-auto-width {
	display: inline-block;
}
</style>
<meta charset="UTF-8">
<title>新增客戶資料</title>
</head>
<body>
	<div align="center">
		<form:form method='POST' modelAttribute="mapDataBean" action='customers'>
			<c:if test='${mapDataBean.id != null}'>
                 <form:hidden path="id" /><br>&nbsp;
			</c:if>
			<fieldset class="fieldset-auto-width">
				<legend>店家資料</legend>
				<table>
					<tr>
						<td align='right'>店家名稱：<br>&nbsp;</td>
						<td width='280'><form:input path="mapname" size="25"/><br>&nbsp;
							<form:errors path="mapname" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>店家地址：<br>&nbsp;</td>
						<td width='280'><form:input path="mapku" size="25"/><br>&nbsp;
							<form:errors path="mapku" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>店家電話：<br>&nbsp;</td>
						<td width='280'><form:input path="mapnb" size="25"/><br>&nbsp;
							<form:errors path="mapnb" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>店家座標：<br>&nbsp;</td>
						<td width='280'><form:input path="mapxy" size="25"/><br>&nbsp;
							<form:errors path="mapxy" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>是否為安全店家：<br>&nbsp;</td>
						<td align="center"><form:radiobutton path="mapcheck" value="Y"/>是
										   <form:radiobutton path="mapcheck" value="N"/>否
							<form:errors path="mapcheck" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td colspan='2' align='center'>
						<input type='submit' value='提交'></td>
					</tr>
				</table>
			</fieldset>
		</form:form>
		<br> <a href="<c:url value='/Food/_mapdata/index' />">回前頁</a>
	</div>
</body>
</html>