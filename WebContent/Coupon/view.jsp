<%@page import="Coupon.model.QuestionnaireBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理員專用</title>

<style>
	.box1{
		border: 1px solid;
		height:300px;
		padding: 20px;
	}
	
	td,th {
		border: 1px solid;
		width: 200px;
		text-align:center;

	}
	
	table {
		 border: 1px; 
		 border-collapse:collapse;
		
	}
	
	
	
	
	
</style>

</head>

<body>
<form action='administratorcontroller' method='post'>
		<div>
			<p><label><input type='radio' name='action' value='R'>查詢疫苗接種者</label>
			<label><input type='radio' name='action' value='U'>發放折價券</label>
			<label><input type='radio' name='action' value='B'>撤回</label></p>
		</div>

		<div>
			<input type='submit' value='送出'>
			<input type="button" value="回首頁" onclick="location.href='frontpage.html'">
		</div>

</form>

<br>
<br>

<div class='box1'>
<%
	List<QuestionnaireBean> vaccineList = (List<QuestionnaireBean>)request.getAttribute("vaccine");

	if (null!=vaccineList && vaccineList.isEmpty()){
%>
		<h4>無資料</h4>
<%
	} else {
%>
		<table>
		<thead>
		<tr>
			<th>身份證字號</th>
			<th>姓名</th>
			<th>性別</th>
			<th>生日</th>
			<th>電話</th>
			<th>出國</th>
			<th>跨縣市</th>
			<th>接觸</th>
			<th>接種</th>
			<th>發燒</th>
			<th>發送</th>
		</thead>
		<tbody>

<%
		for (QuestionnaireBean qbean:vaccineList){
%>
		
			<tr>
			<td><%= qbean.getId() %></td>
			<td><%=	qbean.getName() %></td>
			<td><%=	qbean.getGender() %></td>
			<td><%=	qbean.getBirth() %></td>
			<td><%=	qbean.getPhone() %></td>
			<td><%=	qbean.getAbroad() %></td>
			<td><%=	qbean.getMoving() %></td>
			<td><%=	qbean.getFamily() %></td>
			<td><%=	qbean.getVaccine() %></td>
			<td><%=	qbean.getFever() %></td>
			<td><%=	qbean.getLabel() %></td>
			</tr>
<%
		}
	
%>
		</tbody>
		</table>

<%
	}
%>





</div>

</body>
</html>