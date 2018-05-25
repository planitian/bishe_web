<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'namelookuser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zfh.Po.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'lookuser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/style1.css" type="text/css"></link>
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
 $("#searc").bind("input propertychange",function(){
var inhh=$('#searc').val();
alert("有用");
$('#pp').val(inhh);

});
 
 function yan(){
 var shu=document.getElementById("ye").value;
 var uri="Yuyuea.action?currentpageNo=";
 uri+=shu;
 window.parent.rightFrame.location.href=uri;
 }
 $(document).ready(function() {
 var left=$('#searc').offset().left;
 var top=$('#searc').offset().top+$('#searc').outerHeight()+10;
 var par=$('#searc').offsetParent();
console.log(left);
  console.log(top);
  console.log(par);
 $('#suggest').css("left",left);
 $('#suggest').css("top",top);
$('#tes').click(function(){
  console.log(left);
  console.log(10);
  console.log(par);
 $('#result').fadeToggle();
});
document.getElementById("searc").attachEvent("onporpertychange",function(e){
console.log("inputting!!");
});

$("#searc").bind("input propertychange",function(){
var inhh=$('#searc').val();
alert("有用");
$('#pp').val(inhh);

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
       <tr><td><input type="text" value="" id="searc"></td>  
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><input type="button" style="width: 134px;height: 30px;font-size: 120%;" value="确定" id="tijiao"/></td>
		</tr>
		<tr>
		<td> </td><td><input type="button" id="tes" value="点击"></td>
		</tr>
	
		
     </table>
     <div id="suggest" style="border: blue 1px solid;position: absolute;"> 
     <p id="pp"> </p>
		  <ul id="result">
		     <li>z站点</li>
		     <li>湿答答</li>
		     <li><十多万/li>
		  </ul>
		</div>
	</div>
    <div class="rightinfo" align="center" id="bianhua">
		<table class="tablelist" style="font-size: 19px;">
			<thead>
				<tr bgcolor="#E6E6E6">
					<th>用户编号<i class="sort"><img src="<%=path%>/images/px.gif"></img>
					</i>
					</th>
					<th>姓名</th>
					<th>密码</th>
					<th>性别</th>
					<th>电话</th>
					<th>地址</th>	
				</tr>
			</thead>
			<tbody>
     
       
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
     
         <li class="paginItem"><s:if test="hasPreviousPage()"><a  href="alladminuser.action?currentpageNo=1"target="rightFrame">首页</a></s:if><s:else><a href="javascript:;">首页</a></s:else></li>
         <li class="paginItem"><s:if test="hasPreviousPage()"><a  href="alladminuser.action?currentpageNo=<s:property value="currentpageNo-1"></s:property>"target="rightFrame">上一页</a></s:if><s:else><a href="javascript:;">上一页</a></s:else></li>
         <li class="paginItem"><s:if test="hasNextPage()"><a  href="alladminuser.action?currentpageNo=<s:property value="currentpageNo+1"></s:property>"target="rightFrame">下一页</a></s:if><s:else><a href="javascript:;">下一页</a></s:else></li>
         <li class="paginItem"><s:if test="hasNextPage()"><a  href="alladminuser.action?currentpageNo=<s:property value="getTotalpage()"></s:property>"target="rightFrame">尾页</a></s:if><s:else><a href="javascript:;">尾页</a></s:else></li>
         
		
		
		</div>
		</div>
  </body>
</html>
	
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
