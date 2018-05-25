<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'getperpaizhen.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/style2.css" type="text/css"></link>
	<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.searchableSelect.js"></script>

  <script type="text/javascript">
  function delet(arg){
 $.ajax({
 type:"POST",
 url:"ajax/deletepaizhen.action",
 async:false,
 data:{"id":arg},
 dataType:"json",
 success:function(data){
    alert(data.dele);
     dd();
 }
 }); 
  }
 function dd(){
 //alert($("#emloyeeperson").val());
    
	console.log("执行");
	var url="getempjsp.action";
		var gg=$("#emloyeeperson").val();
		console.log(gg);
		var params={employeeid:gg};
		console.log(params);
	    $("#main").load(url,params);
	
 }
 function upda(ID,NAME,EMP,DATA,DATA1){
 alert(ID+NAME+EMP+DATA+DATA1);
  var url="admin/updatepaizhen.jsp";
  console.log(url);
  var params={id:ID,name:NAME,employeeid:EMP,data:DATA,data1:DATA1};
  console.log(params);
 $("#bianhua").load(url,params);
    
 }
  
  </script>
  <body>
  <s:debug></s:debug>
    <div class="rightinfo" align="center" id="bianhua">
		<table class="tablelist">
			<thead>
				<tr bgcolor="#E6E6E6">
					<th>医生名字<i class="sort"><img src="<%=path%>/images/px.gif"></img>
					</i>
					</th>
					<th>排诊日期</th>
					<th>星期</th>
					<th>上午时间</th>
					<th>下午时间</th>
					<th>删除</th>
					<th>修改</th>
					
				</tr>
			</thead>
			<tbody>
     
        <s:iterator value="peremptime" status="status">
      <s:if test="date!=null">
        <tr>
        <td style="text-align:center"><s:property value="employee.name"></s:property></td>
        <td style="text-align:center"><s:property value="date"></s:property></td>
        <td style="text-align:center"><s:property value="xingqi"></s:property></td>
        <td style="text-align:center"><s:property value="am"></s:property></td>
        <td style="text-align:center"><s:property value="pm"></s:property></td>
        <td style="text-align:center" ><input type="button"onclick="delet(<s:property value="id" ></s:property>)" value="删除"></td>
        <td style="text-align:center" ><input type="button"onclick="upda('<s:property value="id"></s:property>','<s:property value="employee.name" ></s:property>','<s:property value="employee.employeeId" ></s:property>','<s:property value="date"></s:property>','<s:property value="xiuday"></s:property>')" value="修改"></td>
        </tr> 
        
    </s:if>
        
        </s:iterator>
        </tbody>
		</table>
		</div>
  </body>
</html>
