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

<title>My JSP 'xiugaipassword.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//input 复原函数 
		function reset() {
			$("#xinpassword").val(null);
			$("#fupassword").val ("");
		}
		//给下方的reset 按钮绑定事件  
		$('#reset').click(function(){
		console.log("我运行的");
		   reset();
		});

		//给提交按钮绑定事件提交数据 通过ajax
		$('#tijiao').click(function() {
			var xin = $("#xinpassword").val();
			var chongfu = $("#fupassword").val();
			console.log(xin);
			if (xin == chongfu&&xin!=null&&chongfu!=null&&xin.length!=0&&chongfu.length!=0) {
				$.ajax({
					type : "POST",
					url : "ajax/updateEmppass.action",
					data : {password : chongfu},
					dataType : "json",
					success:function(data){
					    if(data.success)
					    alert("密码更新成功");
					    else
					    alert("密码更新失败");
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
                     alert("服务器出现问题，请稍后重试");
                    // 状态码
                    console.log("状态码"+XMLHttpRequest.status);
                    // 状态
                    console.log("状态"+XMLHttpRequest.readyState);
                    // 错误信息   
                    console.log(textStatus);
                }
				});
			} else {
				alert("两次密码不一致");
				reset();
			}

		});

	});
</script>

</head>

<body>
	<div align="center"  style="margin-top: 12%">
		<table style="border-collapse:separate; border-spacing:0px 30px;">
			<tr>
				<td align="right"><label>请输入新的密码:</label></td>
				<td><input type="text" name="xinpassword" id="xinpassword">
				</td>
			</tr>
			<tr>
				<td align="right"><label>重复新的密码:</label></td>
				<td><input type="text" name="fupassword" id="fupassword">
				</td>
			</tr>
			
			<tr >
				<td align="center"><input type="button" name="chongzhi"
					value="重置" id="reset"
					style="background-color: #FF7F50;border: #F4F4F4;color: #C0FF3E;border-radius:5px;height: 30px;width: 100px;">
				</td>
				<td align="center"><input type="button" name="tijiao"
					id="tijiao" value="提交"
					style="background-color: #FF7F50;border: #F4F4F4; color: #C0FF3E;border-radius:5px;height: 30px;width: 100px;">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
