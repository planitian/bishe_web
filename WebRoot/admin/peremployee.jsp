<%@page import="com.zfh.Po.Department"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>peremployee</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/style2.css" type="text/css"></link>
<link rel="stylesheet" href="css/jquery.searchableSelect.css"
	type="text/css"></link>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.searchableSelect.js"></script>
<script type="text/javascript" src="<%=basePath%>/timejs/My97DatePicker/calendar.js"></script>
 <script type="text/javascript" src="<%=basePath%>/timejs/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	
<%ArrayList<Department> depart=(ArrayList)session.getAttribute("alldepartment");%>
	function keshi() {

		var sele = document.getElementById("keshi2").value;

		var seleen = encodeURI(sele);

		var xmlHttpRequest = new XMLHttpRequest();
		var uri = "ajax/getemployee.action";
		var params = "department=" + seleen;


		var ur = uri + "?" + params;
			xmlHttpRequest.onreadystatechange=function (){
		if (xmlHttpRequest.readyState == 4
				&& (xmlHttpRequest.status == 200 || xmlHttpRequest.status == 0)) {
			var m = xmlHttpRequest.responseText;
			console.log(m);
			var json = JSON.parse(m);
			$("#emloyeeperson").empty();
			var emplo = document.getElementById("emloyeeperson");
			for ( var i = 0; i < json.length; i++) {
				var opt = document.createElement("option");
				opt.setAttribute("value", json[i].employeeid);

				console.log(json[i].employeeid);
				opt.innerHTML = json[i].name;
				emplo.appendChild(opt);
			}
			$('.searchable-select').remove();
			console.log("元素删除");
			$("#emloyeeperson").searchableSelect();
			console.log("元素添加");
		} };
		xmlHttpRequest.open("POST", uri, false);
		xmlHttpRequest.setRequestHeader('Content-type',
				'application/x-www-form-urlencoded');

		console.log(uri);
		xmlHttpRequest.send(params);
		console.log(xmlHttpRequest.readyState);
		console.log(xmlHttpRequest.status);
	
	}
	
	$(document).ready(function() {
	    $("#tijiao").click(function(){
	console.log("执行");
	var url="getempjsp.action";
		var gg=$("#emloyeeperson").val();
		console.log(gg);
		var params={employeeid:gg};
		console.log(params);
	    $("#main").load(url,params);
	    
	});
		$("#emloyeeperson").searchableSelect();
		
	});
</script>
</head>

<body>

	<div class="roww">
       <table>
       <tr><td><select id="keshi2"
			style="width: 134px;height: 30px;font-size: 120%;"
			onchange="keshi()">
			<option>请选择科室</option>
			<%
				for (Department dep : depart) {
			%>
			<option value="<%=dep.getDepartment()%>"><%=dep.getDepartment()%></option>
			<%
				}
			%>
		</select></td>  <td > <select id="emloyeeperson" name="employeeid">
			<option>请选择医生</option>
		</select></td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><input type="button" style="width: 134px;height: 30px;font-size: 120%;" value="确定" id="tijiao"/></td>
		</tr>
	
		
     </table>
	</div>
	<div class="rightinfo" align="center" id="main">
	
	</div>
</body>
</html>
