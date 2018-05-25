<%@ page language="java" import="java.util.*,java.sql.Timestamp" pageEncoding="UTF-8" import="java.text.SimpleDateFormat"%>
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

<title>My JSP 'content.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link rel="stylesheet" href="css/style2.css" type="text/css"></link></head>
<script type="text/javascript">
 function yan(){
 var shu=document.getElementById("ye").value;
 var uri="Yuyuea.action?currentpageNo=";
 uri+=shu;
 window.parent.rightFrame.location.href=uri;
 }

</script>
<body>
   
	<div class="rightinfo" align="center">
		<table class="tablelist">
			<thead>
				<tr bgcolor="#E6E6E6">
					<th>预约码<i class="sort"><img src="<%=path%>/images/px.gif"></img>
					</i>
					</th>
					<th>用户</th>
					<th>身份证</th>
					<th>预约时间</th>
					<th>星期</th>
					<th>创建时间</th>
					<th>联系方式</th>
				</tr>
			</thead>
			<tbody>
     
        <s:iterator value="data" status="status">
        <tr>
        <td><s:property value="yuyuema"></s:property></td>
        <td><s:property value="user.name"></s:property></td>
        <td><s:property value="user.personId"></s:property></td>
        <td><s:property value="time"></s:property></td>
        <td><s:property value="xingqi"></s:property></td>
        <td><s:property value="createtime"></s:property></td>
        <td><s:property value="user.phone"></s:property></td>
        </tr> 
        </s:iterator>
        </tbody>
		</table>
		<div class="pagin">
		<div class="message" align="left">共<i class="blue"><s:property value="getTotalpage()"></s:property></i>页记录，当前显示第&nbsp;<i class="blue"><s:property value="currentpageNo"></s:property>&nbsp;</i>页</div>
		<div class="notify-row">
		<p class="yema">跳转页码:</p>
		<input type="text" value="1"class="yema" id="ye" onclick="JavaScript:this.value=''"/>
		<input type="button" class="yema" value="跳转" onclick="yan()"  />
		</div>
		<ul class="paginList">
     
         <li class="paginItem"><s:if test="hasPreviousPage()"><a  href="Yuyuea.action?currentpageNo=1"target="rightFrame">首页</a></s:if><s:else><a href="javascript:;">首页</a></s:else></li>
         <li class="paginItem"><s:if test="hasPreviousPage()"><a  href="Yuyuea.action?currentpageNo=<s:property value="currentpageNo-1"></s:property>"target="rightFrame">上一页</a></s:if><s:else><a href="javascript:;">上一页</a></s:else></li>
         <li class="paginItem"><s:if test="hasNextPage()"><a  href="Yuyuea.action?currentpageNo=<s:property value="currentpageNo+1"></s:property>"target="rightFrame">下一页</a></s:if><s:else><a href="javascript:;">下一页</a></s:else></li>
         <li class="paginItem"><s:if test="hasNextPage()"><a  href="Yuyuea.action?currentpageNo=<s:property value="getTotalpage()"></s:property>"target="rightFrame">尾页</a></s:if><s:else><a href="javascript:;">尾页</a></s:else></li>
         
		
		
		</div>
	</div>
	
	
</body>
</html>
