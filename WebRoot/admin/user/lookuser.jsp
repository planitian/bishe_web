<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
 function yan(){
 var shu=document.getElementById("ye").value;
 var uri="alladminuser.action?currentpageNo=";
 uri+=shu;
 window.parent.rightFrame.location.href=uri;
 }
 
 
 $(document).ready(function() {
 //设定提示框的位置
 var left=$('#searc').offset().left;
 var top=$('#searc').offset().top+$('#searc').outerHeight()+10;
 var par=$('#searc').offsetParent();
console.log(left);
  console.log(top);
  console.log(par);
 $('#suggest').css("left",left);
 $('#suggest').css("top",top);
//input提示框的ajax
$("#searc").bind("input propertychange",function(){
$.ajax({
type:"POST",
url:"ajax/inputtishi.action",
data:{shuru:$('#searc').val()}, 
dataType:"json",
success:function(data){
if(!data[0].weikong){
$('#result').empty();
 for(var i=1;i<data.length;i++){
 $('#result').append("<li >"+data[i].name+"</li>");
 }
  $("#result li").each(function(){
    $(this).click( function(){
      $('#searc').val($(this).text());
       $('#suggest').hide();
    });
  
  });
  $('#suggest').show();
}else{
 $('#suggest').hide();
}
}
});
});
//确定按钮的事件 
$('#tijiao').click(function(){
 var url="nameadminuser.action";
 console.log(url);
 var para={name:$('#searc').val()};
 $('#yemian').load(url,para);
});

//根据session里面的属性值判断是全部用户 还是name决定下面上下面 div是否显示
var leixing="<%=session.getAttribute("leixing")%>";
console.log(leixing);

if(leixing=="name"){
$('.pagin').hide();
}


});
</script>
  </head>
  
  <body>
  <div id="yemian">
   <div  style="display: block;
	margin: 20px 25% 10px 25%;
	width: 50%;
	height: auto;font-size: 9pt;
	border: 0;">
       <table >
       <tr><td><label >搜索：</label><input type="text"    placeholder="请输入姓名"id="searc" style="width: 134px;height: 30px;font-size: 120%;" ></td>  
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><input type="button" style="width: 134px;height: 30px;font-size: 120%;" value="确定" id="tijiao"/></td>
		</tr>
		
		
     </table>
     <div id="suggest" style="border: #FF7F24 3px solid;position: absolute;display: none;font-size: 120%;width: 134px;background-color: #FFAEB9;color: 
#FFFFFF;font-size: 130%;"> 
      <p id="pp"> </p>
		  <ul id="result">
		   
		  </ul>
		</div>
	</div>
    <div class="rightinfo" align="center" id="bianhua">
		<table class="tablelist" style="font-size: 19px;">
			<thead ">
				<tr bgcolor="#E6E6E6" >
					<th  style="text-align:center;font-size: 22px;"   >用户编号<i class="sort"><img src="<%=path%>/images/px.gif"></img>
					</i>
					</th>
					<th style="text-align:center;font-size: 22px;">姓名</th>
					<th style="text-align:center;font-size: 22px;" >密码</th>
					<th style="text-align:center;font-size: 22px;" >性别</th>
					<th style="text-align:center;font-size: 22px;" >电话</th>
					<th style="text-align:center;font-size: 22px;"  >地址</th>	
				</tr>
			</thead>
			<tbody>
             <s:iterator value="user" status="status">
        <tr>
        <td style="text-align:center;font-size: 19px;"><s:property value="personId"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="name"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="password"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="sex"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="phone"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="address"></s:property></td>
        </tr> 
        </s:iterator>
       
        </tbody>
		</table>
		
	
		</div>
			<div class="pagin">
		<div class="message" align="left">共<i class="blue"><s:property value="getTotalpage()"></s:property></i>页记录，当前显示第&nbsp;<i class="blue"><s:property value="currentpageNo"></s:property>&nbsp;</i>页</div>
		<div  style="position: relative;left: 45%;top:-15px;">
		<span><label>请输入页码：</label>
		<input type="text" value="1"class="yema" id="ye"  style="width: 134px;height: 30px;font-size: 120%;text-align: center;" onclick="JavaScript:this.value=''"/>
		<input type="button" value="跳转" onclick="yan()" style="width: 60px;height: 30px;" /> </span>
		
		</div>
		<ul class="paginList">
     
         <li class="paginItem"><s:if test="hasPreviousPage()"><a  href="alladminuser.action?currentpageNo=1"target="rightFrame">首页</a></s:if><s:else><a href="javascript:;">首页</a></s:else></li>
         <li class="paginItem"><s:if test="hasPreviousPage()"><a width="51px" href="alladminuser.action?currentpageNo=<s:property value="currentpageNo-1"></s:property>"target="rightFrame">上一页</a></s:if><s:else><a href="javascript:;">上一页</a></s:else></li>
         <li class="paginItem"><s:if test="hasNextPage()"><a  href="alladminuser.action?currentpageNo=<s:property value="currentpageNo+1"></s:property>"target="rightFrame">下一页</a></s:if><s:else><a href="javascript:;">下一页</a></s:else></li>
         <li class="paginItem"><s:if test="hasNextPage()"><a  href="alladminuser.action?currentpageNo=<s:property value="getTotalpage()"></s:property>"target="rightFrame">尾页</a></s:if><s:else><a href="javascript:;">尾页</a></s:else></li>
         
		
		
		</div>
		</div>
  </body>
</html>
