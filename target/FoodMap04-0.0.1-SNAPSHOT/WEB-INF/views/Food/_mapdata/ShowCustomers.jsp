<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<link rel='stylesheet' href="<c:url value='/css/styles.css' />" type="text/css" />
<meta charset="UTF-8">
<title>Show Customer Data</title>
</head>
<body>
	<div align='center'>
		<h3>客戶資料</h3>
		<hr>
		<table border='1'>
			<tr>
				<th width='60'>編輯</th>
				<th width='160'>店家名稱</th>
				<th width='160'>店家地址</th>
				<th width='160'>店家電話</th>
				<th width='160'>店家座標</th>
				<th width='150'>是否為安全商家</th>
			</tr>
			<c:choose>
				<c:when test="${not empty mapDataBeanList}">
					<c:forEach var='mapDataBean' items="${mapDataBeanList}">
						<tr>
							<td align='center'>
								<a href='./customers/${mapDataBean.id}'>${mapDataBean.id}</a>
							</td>
							<td>${mapDataBean.mapname}</td>
							<td>${mapDataBean.mapku}</td>
							<td>${mapDataBean.mapnb}</td>
							<td>${mapDataBean.mapxy}</td>
							<td>${mapDataBean.mapcheck}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
				查無Customer資料
			</c:otherwise>
			</c:choose>
		</table>
		<br> <a href="<c:url value='/Food/_mapdata/index' />">回前頁</a>
	</div>
</body>
</html>