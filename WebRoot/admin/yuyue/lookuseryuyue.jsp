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
//console.log(left);
  //console.log(top);
 // console.log(par);
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
console.log(data);
if(!data[0].weikong){
$('#result').empty();
 for(var i=1;i<data.length;i++){
 $('#result').append("<li >"+data[i].name+"身份证:"+data[i].personid+":性别:"+data[i].sex+"</li>");
 }
  $("#result li").each(function(){
    $(this).click( function(){
    var arry=$(this).text().split(":");
    console.log(arry);
       $('#suggest').hide();
       url="idadminuser.action";
       var para={personid:arry[1]};
       console.log(para);
       $('#yemian').load(url,para);
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
		<td><input type="hidden" style="width: 134px;height: 30px;font-size: 120%;" value="确定" id="tijiao"/></td>
		</tr>
		
		
     </table>
     <div id="suggest" style="border: blue 1px solid;position: absolute;display: none;font-size: 120%"> 
      <p id="pp"> </p>
		  <ul id="result">
		   
		  </ul>
		</div>
	</div>
    <div class="rightinfo" align="center" id="bianhua">
		<table class="tablelist" style="font-size: 19px;">
			<thead ">
				<tr bgcolor="#E6E6E6" >
					<th  style="text-align:center;font-size: 22px;"   >预约码<i class="sort"><img src="<%=path%>/images/px.gif"></img>
					</i>
					</th>
					<th style="text-align:center;font-size: 22px;">用户</th>
					<th style="text-align:center;font-size: 22px;" >身份证</th>
					<th style="text-align:center;font-size: 22px;" >预约时间</th>
					<th style="text-align:center;font-size: 22px;" >星期</th>
					<th style="text-align:center;font-size: 22px;" >创建时间</th>	
					<th style="text-align:center;font-size: 22px;" >联系方式</th>	
				</tr>
			</thead>
			<tbody>
         <s:iterator value="data" status="status">
        <tr>
        <td style="text-align:center;font-size: 19px;"><s:property value="yuyuema"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="user.name"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="user.personId"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="time"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="xingqi"></s:property></td>
        <td  style="text-align:center;font-size: 19px;"><s:property value="createtime"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="user.phone"></s:property></td>
        </tr> 
        </s:iterator>
       
        </tbody>
		</table>
		
	
		</div>
			
		</div>
  </body>
</html>
