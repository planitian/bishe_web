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

<title>My JSP 'saveemployee.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/style1.css" type="text/css"></link>
<link rel="stylesheet" href="css/touxiangstyle.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/cropbox.js"></script>
<script type="text/javascript">
  function tijiao(){
  var form = document.getElementById("biaodan");
  var fd = new FormData(form);
  $.ajax({
     url:"ajax/saveemployee.action",
     type:"POST",
     async:false,
     cache:false,
     data:fd,
     dataType:"json",
     processData: false,
     contentType: false,
     success:function(data){
      if(data.succeetupian){
         alert("保存数据成功");
      }else{
       alert("保存数据失败");
       $('#biaodan')[0].reset();
      }
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
  }
  

  
  $(window).load(function() {
	var options =
	{
		thumbBox: '.thumbBox',
		spinner: '.spinner',
		imgSrc: 'img/avatar.jpg'
	};
	var cropper = $('.imageBox').cropbox(options);
	$('#upload-file').on('change', function(){
		var reader = new FileReader();
		reader.onload = function(e) {
			options.imgSrc = e.target.result;
			cropper = $('.imageBox').cropbox(options);
		};
		reader.readAsDataURL(this.files[0]);
		this.files = [];
	});
	$('#btnCrop').on('click', function(){
		var img = cropper.getDataURL();
		$('.cropped').html('');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
		$('.cropped').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
	});
	$('#btnZoomIn').on('click', function(){
		cropper.zoomIn();
	});
	$('#btnZoomOut').on('click', function(){
		cropper.zoomOut();
	});
	
	$("#tt").click(function(){
	   submit1();
	} );
	
function submit1(){
//alert("进入submit");
console.log("进入");
  var xin=true;
  var emp=$('#employeeid').val();
 console.log(emp);
 console.log((emp.length==0));

  if((emp=="")||(emp.length==0)||(emp.lengtn>30)){
  alert("医生编号，请仔细检查");
  xin=false;
  }else{
  $.ajax({
     type:"POST",
     url:"ajax/employeechongfu.action",
     async:false,
     data:{employeeid:$("#employeeid").val()},
     dataType:"json",
     success:function(data){
     if(data.chongfu){
     alert("医生编号已存在，请修改");
     }
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
  }
  
  var name=$('#name').val();
  if((name==null)||(name.length==0)){
  alert("姓名没有输入");
  }
  
  var pass=$("#password").val();
  console.log(pass);
  if((pass==null)||(pass.length==0)||(pass.length>30)){
   alert("检查密码是否为零 或者大于30字符");
   xin=false;
  }
   
   var depart=$("#keshi").val();
   console.log("depart"+depart);
   if(depart=="请选择科室"){
     alert("科室没有选择");
     xin=false;
   }
   if(xin){
    tijiao();
   }
   }
 
	
	
	
 function tijiao(){
	 //alert("点击了");
	 var img = cropper.getDataURL();
	 console.log(img);
	 var dataa={employeeid:$("#employeeid").val(),name:$("#name").val(),department:$("#keshi").val(),"imgbase":img,password:$("#password").val()};
  $.ajax({
 type:"POST",
 url:"ajax/saveemployee.action",
 async:false,
 data:dataa,
 dataType:"json",
 success:function(data){
   if(data.succeetupian){
         alert("保存数据成功");
         window.location.reload();
      }else{
       alert("保存数据失败");
       $('#biaodan')[0].reset();
       window.location.reload();
      };
  
 }
 });
 }


});
function resetbiaodan(){
         window.location.reload();
}

  </script>
</head>
<body>
	<%
		ArrayList<Department> depart = (ArrayList) session
				.getAttribute("alldepartment");
	%>
	<div class="formbody">
		<form name="emplo" id="biaodan" enctype="multipart/form-data">
			<ul class="forminfo">
				<li><label>医生编号</label><input name="employeeid" id="employeeid"
					type="text" class="dfinput" /><i>不能超过30个字符</i>
				</li>
				<li><label>姓名</label><input name="name" id="name" type="text"
					class="dfinput" /><i></i>
				</li>
				<li><label>密码</label><input name="password" id="password"
					type="text" class="dfinput" /><i>不能超过30个字符</i>
				</li>
				<li><label>所属科室</label><select id="keshi"
					style="width: 134px;height: 30px;font-size: 120%;"
					name="department">
						<option>请选择科室</option>
						<%
							for (Department dep : depart) {
						%>
						<option value="<%=dep.getDepartment()%>"><%=dep.getDepartment()%></option>
						<%
							}
						%>
				</select>
				</li>
				<li><label style="width: 100%;display: block;">头像上传</label>
				<div style="display: block;">
						<div class="container">
							<div class="imageBox">
								<div class="thumbBox"></div>
								<div class="spinner" style="display: none">Loading...</div>
							</div>
							<div class="action">
								<div class="new-contentarea tc" style="text-align: center;">
									<a href="javascript:void(0)" class="upload-img"> <label
										for="upload-file"
										style="font-size: 20px;line-height: 57px;width: 100%;">上传图像</label>
									</a> <input type="file" class="" name="upload-file"
										id="upload-file" />
								</div>
								<input type="button" id="btnCrop" class="Btnsty_peyton"
									value="裁切"> <input type="button" id="btnZoomIn"
									class="Btnsty_peyton" value="+"> <input type="button"
									id="btnZoomOut" class="Btnsty_peyton" value="-">
							</div>
							<div class="cropped" style="margin-top: 33px"></div>
						</div>
					</div></li>
				<li style="margin-top: 80px"><label>&nbsp;</label><input
					onclick="resetbiaodan()" type="button" class="btn" value="重置" />
					
					<input
					 type="button" class="btn"  id="tt"
					value="提交" />
				</li>
			</ul>

		</form>
	</div>
</body>
</html>
