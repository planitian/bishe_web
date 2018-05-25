<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">


<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" href="css/main.css" type="text/css"></link>


<link rel="stylesheet" href="css/style.css" type="text/css"></link>
</head>
<script type="text/javascript">
 
 function yan(){
 if(shuju.value_1.value==''||shuju.value_2.value==''){
alert("请检查数据是否输入完全");
return false;
 }
 }

</script>
<body>


	<div class="login">
		<div class="dianji">
			<p style="color: #b3b3b3; ">员工登陆</p>
			<p>
				<a href="loginadmin.jsp">管理员登陆</a>
			</p>

		</div>

		<form action="loginemployee.action" method="post" onSubmit="return yan();" name="shuju" >
		
			<div class="box png">
		
				<div class="logo png"></div>
				<div class="input">
				
					<div class="log">
						<div class="name">	
						<div class="name"><table><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td> <s:actionerror cssStyle="color:red"/></td></tr></table></div>						       
								<label>员工编号</label> <input type="text" class="text" id="value_1"
								placeholder="请输入你的员工编号" name="EmployeeID" tabindex="1"
								
								>
						</div>
						<div class="pwd">
							<label>密 码</label> <input type="password" class="text"
								id="value_2" placeholder="请输入密码" name="password" tabindex="2">
							<input type="submit" class="submit" tabindex="3" value="登录"  >
							<div class="check"></div>
						</div>
						<div class="tip"></div>
					</div>
				</div>
			</div>
		</form>
	


		<div class="air-balloon ab-1 png"></div>
		<div class="air-balloon ab-2 png"></div>
		<div class="footer"></div>
	</div>

	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/fun.base.js"></script>
	<script type="text/javascript" src="js/script.js"></script>

	<div
		style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
		<p>
			When you do,you can do more
		</p>
	</div>
</body>
</html>
