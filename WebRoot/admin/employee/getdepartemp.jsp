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
    
    <title>My JSP 'getdepartemp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/style1.css" type="text/css"></link>
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>

<script type="text/javascript">
   function test(){
  alert("点击了 中间的页面");
  }
  
 
 function delet(arg){
 $.ajax({
 type:"POST",
 url:"ajax/deleteemployee.action",
 async:false,
 data:{"employeeid":arg},
 dataType:"json",
 success:function(data){
    if(data.deleteok){
    alert("数据删除成功");
   dd();
    }else{
      alert("删除数据失败，请联系管理人员");
    }
 }
 }); 
  }
  
   function dd(){
 alert($("#keshi2").val());
    
	console.log("dd执行");
	var url="departemp.action";
		var gg=$("#keshi2").val();
		console.log(gg);
		if(gg=="请选择科室"){
		alert("请选择科室 再提交");
		return false;
		}
		var params={departmentid:gg};
		console.log(params);
	    $("#main").load(url,params);	
 }
 function xiugai(bianhao,touxiang,xingming,mima){
 console.log("进入修改");
 var url="admin/employee/updateemployee.jsp";
 var data={employeeid:bianhao,imgurl:touxiang,name:xingming,password:mima};
 console.log(data);
 $("#main").load(url,data);
 }

</script>
  </head>
  
  <body>
    <div class="rightinfo" align="center" id="bianhua">
		<table class="tablelist" style="font-size: 19px;">
			<thead>
				<tr bgcolor="#E6E6E6">
					<th>医生编号<i class="sort"><img src="<%=path%>/images/px.gif"></img>
					</i>
					</th>
					<th >头像</th>
					<th>姓名</th>
					<th>密码</th>
					<th>科室</th>
					<th>删除</th>
					<th>修改</th>
					
				</tr>
			</thead>
			<tbody>
     
        <s:iterator value="employees" status="status">
        <tr>
        <td style="text-align:center;font-size: 19px;"><s:property value="employeeId"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><img src="<s:property value="imgurl"></s:property>?t=<%=Math.random() %>" width="80px" height="80px" style="border:5px solid white"></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="name"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="password"></s:property></td>
        <td style="text-align:center;font-size: 19px;"><s:property value="department.department"></s:property></td>
        <td style="text-align:center;font-size: 19px;" ><input type="button"onclick="delet(<s:property value="employeeId" ></s:property>)" value="删除" style="font-size: 19px;width: 80px;height: 30px;background-color:#a6a9a6;border-radius: 10px; color: #ff0047;font-weight: bold;font-family: 黑体;"></td>
        <td style="text-align:center;font-size: 19px;" ><input type="button"onclick="xiugai('<s:property value="employeeId"></s:property>','<s:property value="imgurl" ></s:property>','<s:property value="name" ></s:property>','<s:property value="password"></s:property>')" value="修改"style="font-size: 19px;width: 80px;height: 30px;background-color:#a6a9a6;border-radius: 10px;color: #152f57;font-weight: bold;font-family: 黑体;"></td>
        </tr> 
        
        </s:iterator>
        </tbody>
		</table>
		</div>
  </body>
</html>
