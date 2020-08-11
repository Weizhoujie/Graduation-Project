<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>课堂考勤系统登录</title>
<link href="css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<link href="css/demo.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>

<script type="text/javascript">
//用户输入非空判断
function check() {
	if(form1.username.value==""){
		alert("用户名不能为空！");
		return false;
	}
	if(form1.password.value==""){
		alert("密码不能为空！");
		return false;
	}
	if(form1.checkcode.value==""){
		alert("验证码不能为空！");
		return false;
	}
}





</script>


</head>

<body>


<div class="header">
  <h1 class="headerLogo"><a title="后台管理系统" target="_blank" href="#"><img alt="logo" src="images/logo.png"/></a></h1>
	<div class="headerNav">
		<a target="_blank" href="#">企业官网</a>
		<a target="_blank" href="#">关于我们</a>
		<a target="_blank" href="#">开发团队</a>
		<a target="_blank" href="#">意见反馈</a>
		<a target="_blank" href="#">帮助</a>	
	</div>
</div>

<div class="banner">

<div class="login-aside">
  <div id="o-box-up"></div>
  <div id="o-box-down"  style="table-layout:fixed;">
   <div class="error-box"></div>
   <span style="display:block;color:red;font-size: 15px">${loginInfo }</span>
   <form class="login-form" name="form1" action="${pageContext.request.contextPath }/LoginServlet" onsubmit="return check()" method="post">
   <div class="fm-item">
	   <label for="logonId" class="form-label">用户名：</label>
	   <input type="text" name="username" value="${param.username }" placeholder="请输入账号" maxlength="100" id="username" class="i-text"  />    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label">登陆密码：</label>
	   <input type="password" name="password" placeholder="请输入密码" maxlength="100" id="password" class="i-text" />    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item pos-r">
	   <label for="logonId" class="form-label">验证码</label>
	   <input type="text" name="checkcode" placeholder="请输入验证码" maxlength="100" id="checkcode" class="i-text yzm"/>    
       <div class="ui-form-explain">
       		<img src="http://localhost:8080/${pageContext.request.contextPath }/checkImg.servlet" style="position:absolute; left:130px; top:32px; " />
       </div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label">身份选择：</label>
	   	<label style="color: white;">
	       <input type="radio" name="identity" value="管理员" checked="checked"/>管理员&nbsp&nbsp&nbsp&nbsp
           <input type="radio" name="identity" value="教师"/>教师&nbsp&nbsp&nbsp&nbsp
           <input type="radio" name="identity" value="学生"/>学生&nbsp&nbsp&nbsp&nbsp
       	</label>
       <div class="ui-form-explain"></div>
  </div>
  
  
  <div class="fm-item">
	   <label for="logonId" class="form-label"></label>
	   <input type="submit" value="" tabindex="4" id="send-btn" class="btn-login"/> 
       <div class="ui-form-explain"></div>
  </div>
  
  </form>
  
  </div>

</div>

	<div class="bd">
		<ul>
			<li style="background:url(images/bg1.jpg) #CCE1F3 center 0 no-repeat;"></li>
			<li style="background:url(images/bg2.jpg) #BCE0FF center 0 no-repeat;"></li>
		</ul>
	</div>

	<div class="hd"><ul></ul></div>
</div>
<script type="text/javascript">jQuery(".banner").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",  autoPlay:true, autoPage:true, trigger:"click" });</script>


<div class="banner-shadow"></div>

<div class="footer">
   <p>&copy; 成都信息工程大学版权所有 ©2003-2016 蜀ICP备05006389号-1 </p>
</div>

</body>
</html>
