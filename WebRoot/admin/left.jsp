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
		<title>left.html</title>

		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="this is my page">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link href="css/style1.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="js/jquery1.js">
</script> 

		<script type="text/javascript">
$(function() {
	//导航切换
	$(".menuson li").click(function() {
		$(".menuson li.active").removeClass("active");
		$(this).addClass("active");
	});

	$('.title').click(function() {
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if ($ul.is(':visible')) {
			$(this).next('ul').slideUp();
		} else {
			$(this).next('ul').slideDown();
		}
	});
});
</script>




	</head>

	<body style="background: #f0f9fd;">
	<s:debug></s:debug>
		<div class="user-box am-hide-sm-only">
			<div class="user-img">
				<img src="img/avatar-1.jpg" alt="user-img" title="Mat Helme"
					class="img-circle img-thumbnail img-responsive">
				<div class="user-status offline">
					<i class="am-icon-dot-circle-o"></i>
				</div>
			</div>
			<h5>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</h5>
			<h5>
				<a>${admin }</a>
			</h5>
		</div>
		<s></s>
		<div class="lefttop">
			<span></span>管理员菜单
		</div>

		<dl class="leftmenu">


			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /> </span>医生管理
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="admin/employee/saveemployee.jsp" target="rightFrame">添加医生</a><i></i>
					</li>
					<li>
						<cite></cite><a href="admin/employee/topemplo.jsp" target="rightFrame">查看修改删除医生</a><i></i>
					</li>
				</ul>
			</dd>

			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /> </span>用户管理
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="alladminuser.action?currentpageNo=1" target="rightFrame">查看搜索用户</a><i></i>
					</li>
					
					

				</ul>
			</dd>
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /> </span>排诊管理
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="admin/savepaizhen.jsp" target="rightFrame">添加排诊</a><i></i>
					</li>
					<li>
						<cite></cite><a href="admin/peremployee.jsp" target="rightFrame">查看医生个人排诊</a><i></i>
					</li>
					
				</ul>
			</dd>
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /> </span>挂号管理
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="admin/yuyue/lookuseryuyue.jsp" target="rightFrame">挂号查询</a><i></i>
					</li>
				</ul>
			</dd>

			



		</dl>
	</body>

</html>
