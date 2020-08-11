<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="org.springframework.web.context.request.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="renderer" content="webkit">
    <title>课堂考勤管理系统</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/admin/admin.css">
    <script src="${pageContext.request.contextPath }/admin/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1 style="color: black"><img src="${pageContext.request.contextPath }/admin/logo.png" class="radius-circle rotate-hover" alt="" height="50">课堂考勤管理系统</h1>
  </div>
  <div class="head-l">
  	<c:if test="${identitycheck == '管理员' }">
  		<a class="button button-little bg-green" href="#">${admin.username }</a>  &nbsp;&nbsp;
  	</c:if>
  	<c:if test="${identitycheck == '教师' }">
  		<a class="button button-little bg-green" href="#">${teacher.realname }</a>  &nbsp;&nbsp;
  	</c:if>
  	<c:if test="${identitycheck == '学生' }">
  		<a class="button button-little bg-green" href="#">${student.realname }</a>  &nbsp;&nbsp;
  	</c:if>
  	<a class="button button-little bg-red" href="${pageContext.request.contextPath }/UserExitServlet">
  		<span class="icon-power-off"></span> 退出登录</a> </div>
</div>

<c:if test="${identitycheck == '管理员' }">	
<div class="leftnav">
  <div class="leftnav-title" style="color: black"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2 class=""><span class="icon-user"></span>基本设置</h2>
  		<ul style="display: none;">
    		<li><a href="javascript:void(0);" target="right"><span class="icon-caret-right"></span>修改密码</a></li>    
    		<li><a href="javascript:void(0);" target="right"><span class="icon-caret-right"></span>个人资料</a></li>
 		</ul>
    <h2 class=""><span class="icon-pencil-square-o"></span>课程管理</h2>
    	<ul>
    		<li><a href="${pageContext.request.contextPath }/CourseServlet" target="right"><span class="icon-caret-right"></span>课程列表</a></li> 
    		<li><a href="${pageContext.request.contextPath }/page/admin/addCourse.jsp" target="right"><span class="icon-caret-right"></span>添加课程</a></li> 
    	</ul>
    <h2 class=""><span class="icon-pencil-square-o"></span>教师管理</h2>
 		<ul style="display: none;">
    		<li><a href="${pageContext.request.contextPath }/TeacherServlet" target="right"><span class="icon-caret-right"></span>教师列表</a></li> 
  			<li><a href="${pageContext.request.contextPath }/page/admin/addTeacher.jsp" target="right"><span class="icon-caret-right"></span>添加教师</a></li>
  		</ul>  
  	<h2 class=""><span class="icon-pencil-square-o"></span>学生管理</h2>
    	<ul style="display: none;">
    		<li><a href="${pageContext.request.contextPath }/StudentServlet" target="right"><span class="icon-caret-right"></span>学生列表</a></li>  
     		<li><a href="${pageContext.request.contextPath }/page/admin/addStudent.jsp" target="right"><span class="icon-caret-right"></span>添加学生</a></li>
     	</ul>
     
</div>
</c:if>

<c:if test="${identitycheck == '教师' }">	
<div class="leftnav">
  <div class="leftnav-title" style="color: black"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2 class=""><span class="icon-user"></span>基本设置</h2>
  		<ul style="display: none;">
    		<li><a href="${pageContext.request.contextPath }/TeacherInfoServlet" target="right"><span class="icon-caret-right"></span>个人资料</a></li>    
    		<li><a href="${pageContext.request.contextPath }/page/teacher/changepassword.jsp" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
 		</ul>
    <h2 class=""><span class="icon-pencil-square-o"></span>课程管理</h2>
    	<ul>
    		<li><a href="${pageContext.request.contextPath }/AllCourseByTeaServlet" target="right"><span class="icon-caret-right"></span>课程列表</a></li> 
    		<li><a href="${pageContext.request.contextPath }/QueryChoseCourseServlet" target="right"><span class="icon-caret-right"></span>我的课程</a></li> 
    		<li><a href="${pageContext.request.contextPath }/PendingCourseServlet" target="right"><span class="icon-caret-right"></span>选课审批</a></li>
    	</ul>
    <h2 class=""><span class="icon-pencil-square-o"></span>考勤管理</h2>
 		<ul style="display: none;">
    		<li><a href="${pageContext.request.contextPath }/CourseForAttendenceServlet" target="right"><span class="icon-caret-right"></span>开始考勤</a></li> 
  			<li><a href="${pageContext.request.contextPath }/AllAttendanceByTeaServlet" target="right"><span class="icon-caret-right"></span>考勤记录</a></li>
  			<li><a href="${pageContext.request.contextPath }/CourseBeforeStatisticsServlet" target="right"><span class="icon-caret-right"></span>考勤统计</a></li>
  		</ul>  
  	<h2 class=""><span class="icon-pencil-square-o"></span>请假管理</h2>
 		<ul style="display: none;">
    		<li><a href="${pageContext.request.contextPath }/ProcessingApplicationsServlet" target="right"><span class="icon-caret-right"></span>请假审批</a></li> 
  		</ul> 
  	
</div>
</c:if>

<c:if test="${identitycheck == '学生' }">	
<div class="leftnav">
  <div class="leftnav-title" style="color: black"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2 class=""><span class="icon-user"></span>基本设置</h2>
  		<ul style="display: none;">
    		<li><a href="${pageContext.request.contextPath }/StudentInfoServlet" target="right"><span class="icon-caret-right"></span>个人资料</a></li>    
    		<li><a href="${pageContext.request.contextPath }/page/student/changepassword.jsp" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
 		</ul>
    <h2 class=""><span class="icon-pencil-square-o"></span>课程管理</h2>
    	<ul>
    		<li><a href="${pageContext.request.contextPath }/AllCourseByStuServlet" target="right"><span class="icon-caret-right"></span>课程列表</a></li> 
    		<li><a href="${pageContext.request.contextPath }/StudentChoseCourseServlet" target="right"><span class="icon-caret-right"></span>我的课程</a></li>
    		<li><a href="${pageContext.request.contextPath }/ApproveCourseServlet" target="right"><span class="icon-caret-right"></span>课程审批</a></li> 
    	</ul>
    <h2 class=""><span class="icon-pencil-square-o"></span>考勤管理</h2>
    	<ul>
    		<li><a href="${pageContext.request.contextPath }/AllAttendancesByStuServlet" target="right"><span class="icon-caret-right"></span>我的考勤</a></li> 
    		<li><a href="${pageContext.request.contextPath }/AllLeaveAppByStuServlet" target="right"><span class="icon-caret-right"></span>请假申请</a></li>
    	</ul>
    
    
</div>
</c:if>

<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
  
    $(".icon-home").click(function(){
	    $("#a_leader_txt").text("所有公告");
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
  
});


</script>
<ul class="bread">
  <li><a href="javascript:void(0);" target="right" class="icon-home"> 首页</a></li>
  <li><a href="##" id="a_leader_txt">公告信息</a></li>
</ul>
<div class="admin">
   <iframe scrolling="auto" rameborder="0" src="" name="right" width="100%" height="100%"></iframe> 
</div>
<div style="text-align:center;">
</div>

</body></html>