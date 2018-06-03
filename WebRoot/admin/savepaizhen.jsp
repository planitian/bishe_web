<%@page import="com.zfh.Po.Employee"%>
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

<title>My JSP 'savepaizhen.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/style2.css" type="text/css"></link>
<script type="text/javascript"
	src="<%=basePath%>/timejs/My97DatePicker/calendar.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/timejs/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
</head>
<script type="text/javascript">
  var count=1;
 <%ArrayList<Employee> emplo=(ArrayList)session.getAttribute("allemployee");%>
 function biaoge(){
 var table=document.getElementById("tbo");
 var tt=document.createElement("tr");
 var td1=document.createElement("td");
 td1.setAttribute("align","center");
 td1.innerHTML="<select id=\"employee"+count+"\" name=\"saveemployee\"> </select>"; 

 var td2=document.createElement("td");
 td2.setAttribute("align","center");
 var inpu=document.createElement("input");
 inpu.setAttribute("type","text"); 
 inpu.setAttribute("name","date"); 
 inpu.setAttribute("class","Wdate"); 
 inpu.setAttribute("id","date"+count); 
 inpu.onclick = Function("WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d {%H+1}:%m:%s'})");
 td2.appendChild(inpu);

 var td3=document.createElement("td");
 var input1=document.createElement("input");
 input1.setAttribute("type","text"); 
 input1.setAttribute("name","amstart"); 
 input1.setAttribute("class","Wdate"); 
 input1.setAttribute("id","amstart"+count); 
 input1.onclick = Function("WdatePicker({dateFmt:'HH:mm:ss',minDate:'08:00:00',maxDate:'12:00:00'})");
 var pp=document.createElement("p");
 pp.innerHTML="-->";
 pp.setAttribute("class","cen"); 
 var input2=document.createElement("input");
 input2.setAttribute("type","text"); 
 input2.setAttribute("name","amend"); 
 input2.setAttribute("class","Wdate"); 
 input2.setAttribute("id","amend"+count); 
 input2.onclick = Function("WdatePicker({dateFmt:'HH:mm:ss',minDate:'\#F{\$dp.$D(\\'amstart"+count+"\\')}',maxDate:'12:00:00'})");
 td3.appendChild(input1);
 td3.appendChild(pp);
 td3.appendChild(input2);

 var td4=document.createElement("td");
 var input3=document.createElement("input");
 input3.setAttribute("type","text"); 
 input3.setAttribute("name","pmstart"); 
 input3.setAttribute("class","Wdate"); 
 input3.setAttribute("id","pmstart"+count); 
 input3.onclick = Function("WdatePicker({dateFmt:'HH:mm:ss',minDate:'12:00:00',maxDate:'20:00:00'})"); 
 var pp1=document.createElement("p");
 pp1.innerHTML="-->";
 pp1.setAttribute("class","cen"); 
 var input4=document.createElement("input");
 input4.setAttribute("type","text"); 
 input4.setAttribute("name","pmend"); 
 input4.setAttribute("class","Wdate"); 
 input4.setAttribute("id","pmend"+count); 
 input4.onclick = Function("WdatePicker({dateFmt:'HH:mm:ss',minDate:'\#F{\$dp.$D(\\'pmstart"+count+"\\')}',maxDate:'20:00:00'})");
 td4.appendChild(input3);
 td4.appendChild(pp1);
 td4.appendChild(input4);
  
  tt.appendChild(td1);
  tt.appendChild(td2);
  tt.appendChild(td3);
  tt.appendChild(td4);
  table.appendChild(tt);
  shuju();
  count++;
  
 
}


function zhuang(){
var text;
text=document.getElementById("amstart0").value;
var amstart=document.getElementById("pmstart0").value;
alert(text+amstart);
for(var i=0;i<5;i++){
text=document.getElementById("amend3").value;
alert("amend3:"+text);
alert(i);
if(i==2){
 break;
 }
}
 
 
}
function submit(){
var xin=true;
for(var i=0;i<count;i++){
var date=document.getElementById("date"+i).value;
if(date.length==0){
alert("第"+(i+1)+"行，日期没有选择");
xin=false;
break;
}
var amstart;
var amend;
 amstart=document.getElementById("amstart"+i).value;
amend=document.getElementById("amend"+i).value;
if((amstart.length==0)!==(amend.length==0)){
 alert("第"+(i+1)+"行"+"上午参数错误");
 xin=false;
 break;
}
var pmstart;
var pmend;
pmstart=document.getElementById("pmstart"+i).value;
pmend=document.getElementById("pmend"+i).value;
 if((pmstart.length==0)!==(pmend.length==0)){
  alert("第"+(i+1)+"行"+"下午参数错误");
 xin=false;
 break; 
 }
 if((amstart.length==0)&&(amend.length==0)&&(pmstart.length==0)&&(pmend.length==0)){
 alert("第"+(i+1)+"行"+"确保时间选择");
 xin=false;
 break;
 }
 //alert(i);
 //var k=1;
 //alert($("#employee"+k).val());
 $.ajax({
type:"POST",
url:"ajax/datechongfu.action",
async:false,
data:{employeeid:$("#employee"+i).val(),date:$("#date"+i).val()},
dataType:"json",
success:function(date){
if(date.chongfu){
  alert("第"+(i+1)+"data日期已存在");
  xin=false;

}
//alert(date.chongfu);
}
});
}
if(xin){
document.forms[0].submit();
}

}
function tan(){
<%ArrayList<Employee> user=(ArrayList)session.getAttribute("allemployee");
   for(Employee emp:user){%>
alert("<%=emp.getName()%>");
<%}%>

 } 
 
 function shuju(){
var select=document.getElementById("employee"+count);
<%for(Employee emp:emplo){%>
var opt=document.createElement("option");
opt.setAttribute("value","<%=emp.getEmployeeId()%>");
opt.innerHTML="<%=emp.getName()%>";
		select.appendChild(opt);
<%}%>
	}
<%Boolean ii=(Boolean)session.getAttribute("first");
if(ii){%>
	alert("数据已经提交成功");
<%session.setAttribute("first",false);%>
	
<%}%>
	$(document).ready(function() {

	});
</script>
<body>

	<div class="roww">
		<input type="button" id="zengjia" value="增加一行"
			class="btn btn-small submit" onclick="biaoge()" /> 
			<input
			type="submit" id="tijiao" value="提&nbsp;&nbsp;交"
			class="btn btn-medium info" onclick="submit()" />
	</div>
	<div class="rightinfo" align="center">
		<form action="savepaizhen.action" method="post">
			<table class="tablelist" id="table">
				<thead>
					<tr bgcolor="#E6E6E6">
						<th>员工编号<i class="sort"><img src="images/px.gif"></img> </i>
						</th>
						<th>日期</th>
						<th>上午</th>
						<th>下午</th>

					</tr>
				</thead>
				<tbody id="tbo">
					<tr>
						<td align="center" width="11%"><select id="employee0"
							name="saveemployee">
								<%
									for (Employee emp : emplo) {
								%>
								<option value="<%=emp.getEmployeeId()%>"><%=emp.getName()%></option>
								<%
									}
								%>
						</select></td>
						<td align="center"><input name="date" class="Wdate"
							type="text" id="date0"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d {%H+1}:%m:%s'})" />
						</td>
						<td width="400px"><input name="amstart" class="Wdate"
							type="text" id="amstart0"
							onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'08:00:00',maxDate:'12:00:00'})" />&nbsp;&nbsp;
							--><input name="amend" class="Wdate" type="text" id="amend0"
							onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'amstart0\')}',maxDate:'12:00:00'})" />
						</td>
						<td width="400px"><input name="pmstart" class="Wdate"
							type="text" id="pmstart0"
							onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'12:00:00',maxDate:'19:00:00'})" />&nbsp;&nbsp;--><input
							name="pmend" class="Wdate" type="text" id="pmend0"
							onclick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'pmstart0\')}',maxDate:'19:00:00'})" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>

</html>
