<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.zfh.Po.Department"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'topemplo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="css/style1.css" type="text/css"></link>
     <script type="text/javascript" src="js/jquery-3.3.1.js"></script>

<script type="text/javascript">
  function test(){
  alert("点击了 最外面的页面");
  }
  
  
<%ArrayList<Department> depart=(ArrayList)session.getAttribute("alldepartment");%>
$(document).ready(function() {
	    $("#tijiao").click(function(){
	console.log("执行");
	var url="departemp.action";
		var gg=$("#keshi2").val();
		if(gg=="请选择科室"){
		alert("请选择科室 再提交");
		return false;
		}
		console.log(gg);
		var params={departmentid:gg};
		console.log(params);
	    $("#main").load(url,params);
	    
	});	
	}
	
	);



</script>
 </head>
  
  <body>
 <div  style="display: block;
	margin: 20px 25% 10px 25%;
	width: 50%;
	height: auto;font-size: 9pt;
	border: 0;">
       <table >
       <tr><td><select id="keshi2"
			style="width: 134px;height: 30px;font-size: 120%;"
			>
			<option>请选择科室</option>
			<%
				for (Department dep : depart) {
			%>
			<option value="<%=dep.getDepartment()%>"><%=dep.getDepartment()%></option>
			<%
				}
			%>
		</select></td>  
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><input type="button" style="width: 134px;height: 30px;font-size: 120%;" value="确定" id="tijiao"/></td>
		</tr>
	
		
     </table>
	</div>
	<div  id="main">
	
	</div>
  </body>
</html>
