<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css"></link></link>
  </head>
  
  <body>
   <section id="container" >
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><a href="profile.html"><img src="${session.empimgurl}?t=<%=Math.random() %>" class="img-circle" width="100"></img></a></p>
              	  <h5 class="centered" style="margin-top: 20px;font-size: 25px;""><%=session.getAttribute("empname") %> </h5>
              	  	
                  <li class="mt">
                      <a  href="Yuyuea.action?currentpageNo=1"target="rightFrame">
                          <i class="fa fa-dashboard"></i>
                          <span>用户预约</span>
                      </a>
                  </li>

                  <li class="sub-menu">
                       <a  href="employee/xiugaipassword.jsp" target="rightFrame">
                          <i class="fa fa-desktop"></i>
                          <span>修改密码</span>
                      </a>
                     
                  </li>

                  <li class="sub-menu">
                      <a  href="employee/left.jsp" target="leftFrame">
                          <i class="fa fa-cogs"></i>
                          <span>1</span>
                      </a>
                      <ul class="sub"> 
                          <li><a  href="calendar.html">Calendar</a></li>
                          <li><a  href="gallery.html">Gallery</a></li>
                          <li><a  href="todo_list.html">Todo List</a></li>
                      </ul>
                  </li>
               
                
                 
                

              </ul>
            
          </div>
      </aside>
  
   
  </section>

  </body>
</html>
