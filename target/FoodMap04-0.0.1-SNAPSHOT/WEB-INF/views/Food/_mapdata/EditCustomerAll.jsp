<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Insert title here</title>
</head>
<body>
	<div align="center">
				<table>
			<thead>
				<tr>
					<th>店家名稱:</th>
					<th>店家地址:</th>
					<th>店家電話:</th>
					<th>店家座標:</th>
					<th>是否為安全商家:</th>
				</tr>
			</thead>
			<tbody>
					<c:forEach items='${MapDataBean}' var='AllMapDataBean'>
						<tr>
							<td>${MapDataBean.mapname}</td>
							<td>${MapDataBean.mapku}</td>
							<td>${MapDataBean.mapnb}</td>
							<td>${MapDataBean.mapxy}</td>
							<td>${MapDataBean.mapcheck}</td>
						</tr>
					</c:forEach>
				</table>
		<a href="<c:url value='/Food/_mapdata/index' />">回前頁</a>
	</div>
</body>
</html>