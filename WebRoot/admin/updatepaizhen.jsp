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

<title>My JSP 'updatepaizhen.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/style2.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/jquery.searchableSelect.js"></script>
<script type="text/javascript" src="<%=basePath%>/timejs/My97DatePicker/calendar.js"></script>
  <script type="text/javascript" src="<%=basePath%>/timejs/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript">
function submit(){
var xin=true;


var amstart;
var amend;
 amstart=document.getElementById("amstart0").value;
amend=document.getElementById("amend0").value;
if((amstart.length==0)!==(amend.length==0)){
 alert("上午参数错误");
 xin=false;
}

var pmstart;
var pmend;
pmstart=document.getElementById("pmstart0").value;
pmend=document.getElementById("pmend0").value;
 if((pmstart.length==0)!==(pmend.length==0)){
  alert("下午参数错误");
 xin=false;
 }
 
 if((amstart.length==0)&&(amend.length==0)&&(pmstart.length==0)&&(pmend.length==0)){
 alert("确保时间选择");
 xin=false;
 }
 alert(xin);
 console.log(xin);
 alert("<%=request.getParameter("name") %>");
 console.log("<%=request.getParameter("name") %>");
alert($("#amstart0").val());
var ff={
       id:"<%=request.getParameter("id") %>",
       employeename:"<%=request.getParameter("name") %>",
       date:"<%=request.getParameter("data1") %>",
       amstart:$("#amstart0").val(),
       amend:$("#amend0").val(),
       pmstart:$("#pmstart0").val(),
       pmend:$("#pmend0").val(),
       };
   console.log(ff);
   console.log(xin);
if(xin){

$.ajax({
 type:"POST",
 url:"ajax/updatepaizhen.action",
 async:false,
 data:ff,
 dataType:"json",
 success:function(data){
    alert(data.updatasucc);
     if(data.updatasucc){
       var url="getempjsp.action";
	
		var params={employeeid:"<%=request.getParameter("employeeid") %>"};
		console.log(params);
     $("#bianhua2").load(url,params);
     }else{
     alert("出现错误，更新排诊数据失败");
     };
 }

});
}

}
function reset1(){
document.forms[0].reset();
}

</script>
  </head>

<body>
<div class="rightinfo" align="center" id="bianhua2">
<div class="roww"><input type="reset" id="reset" value="重置" class="btn btn-small submit" onclick=" reset1()" /> <input type="submit" id="tijiao" value="提&nbsp;&nbsp;交" class="btn btn-medium info" onclick="submit()" />  </div>
	<div class="rightinfo" align="center">
	<s:debug></s:debug>
	<s:form action="updatepaizhen" namespace="/ajax">
		<table class="tablelist" id="table">
			<thead>
				<tr bgcolor="#E6E6E6">
					<th>排诊编号<i class="sort"><img src="images/px.gif"></img> </i>
					</th>
					<th>医生</th>
					<th>日期</th>
					<th>上午</th>
					<th>下午</th>

				</tr>
			</thead>
			<tbody id="tbo">
			<tr>
        <td align="center" width="11%"><input name="id" type="hidden" value="<%=request.getParameter("id") %>" readonly="readonly" /><%=request.getParameter("id") %></td>
        <td align="center" width="11%"><input name="employeename"  type="hidden" value="${param.name }"/>${param.name }</td>
        <td  align="center"> <input name="date"  type="hidden" value="${param.data1 }"/>${param.data} </td>
        <td width="400px"> <input  name="amstart" class="Wdate" type="text" id="amstart0" onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'08:00:00',maxDate:'12:00:00'})" />&nbsp;&nbsp; --><input  name="amend"   class="Wdate" type="text" id="amend0" onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'amstart0\')}',maxDate:'12:00:00'})" /></td>
        <td width="400px"><input name="pmstart"  class="Wdate" type="text" id="pmstart0" onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'12:00:00',maxDate:'19:00:00'})" />&nbsp;&nbsp;--><input  name="pmend"  class="Wdate" type="text" id="pmend0" onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'pmstart0\')}',maxDate:'19:00:00'})" /></td>
        </tr> 
		</tbody>
		</table>
    </s:form>
	</div>
	</div>
</body>
</html>
