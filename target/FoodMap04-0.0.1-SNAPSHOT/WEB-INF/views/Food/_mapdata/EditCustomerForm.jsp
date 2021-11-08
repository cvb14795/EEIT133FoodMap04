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
<script type="text/javascript">
  function confirmDelete(id){
	  var result = confirm("確定刪除此筆記錄(帳號:" + id.trim() + ")?");
	  if (result) {
		  document.forms[0].putOrDelete.name = "_method";
		  document.forms[0].putOrDelete.value = "DELETE";
		  document.forms[0].action = "<c:url value='/Food/_mapdata/customers/" + id + "' />";
	      return true;
	  }
	  return false;
  }
  function confirmUpdate(id){
	  var result = confirm("確定送出此筆記錄(帳號:" + id.trim() + ")?");
	  if (result) {
		  document.forms[0].putOrDelete.name = "_method";
		  document.forms[0].putOrDelete.value = "PUT";
		  document.forms[0].action = "<c:url value='/Food/_mapdata/customers/" + id + "' />";
	      return true;
	  }
	  return false;
  }
</script>

</head>
<body>
	<div align="center">
	
		<form:form method='POST' modelAttribute="mapDataBean" >
			<input type="hidden" name="noname"  id='putOrDelete'   value="" >
    		<c:if test='${mapDataBean.id != null}'>
               <form:hidden path="id" /><br>&nbsp;
			</c:if>
			<fieldset class="fieldset-auto-width">
				<legend>客戶資料</legend>
				<table>
					<tr>
						<td align='right'>店家名稱：<br>&nbsp;</td>
						<td><form:input path="mapname" size="25" /><br>&nbsp;
							<form:errors path="mapname" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>店家地址：<br>&nbsp;</td>
						<td><form:input path="mapku" size="25" /><br>&nbsp;
							<form:errors path="mapku" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>店家電話：<br>&nbsp;</td>
						<td><form:input path="mapnb" size="25" /><br>&nbsp;
							<form:errors path="mapnb" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>店家座標：<br>&nbsp;</td>
						<td><form:input path="mapxy" size="25" /><br>&nbsp;
							<form:errors path="mapxy" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td align='right'>是否為安全商家：<br>&nbsp;</td>
						<td align="center"><form:radiobutton path="mapcheck" value="Y"/>是
										   <form:radiobutton path="mapcheck" value="N"/>否
							<form:errors path="mapcheck" cssClass="error" />
						</td>
					</tr>
					<tr>
						<td colspan='2' align='center'>
							<input type='submit' value='修改' name='updateBtn' onclick="return confirmUpdate('${mapDataBean.id}');">&nbsp; 	
							<input type='submit' value='刪除' name='deleteBtn' onclick="return confirmDelete('${mapDataBean.id}');" >
						</td>
					</tr>

				</table>
			</fieldset>
		</form:form>
		<a href="<c:url value='/Food/_mapdata/index' />">回前頁</a>
	</div>
</body>
</html>