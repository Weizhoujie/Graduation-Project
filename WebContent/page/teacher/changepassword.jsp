<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</HEAD>
<script type="text/javascript">
        function check(form){
        	if(form.oldpassword.value == ""){  
                alert("旧密码不能为空！");  
                return false;  
            } 
        	
            if(form.password.value == ""){  
                alert("新密码不能为空！");  
                return false;  
            }  
            if(form.repassword.value == ""){  
                alert("确认密码不能为空！");  
                return false;  
            }  
            if(form.password.value != form.repassword.value){  
                alert("两次密码输入不一致！");  
                return false;  
            }  

	     }
</script>
<body>


	<div class="container">
            
            <form action="${pageContext.request.contextPath}/TeacherChangePwServlet" onsubmit="return check(this)" method="POST" role="form" style="margin: 100px auto; width: 520px;">
                <legend>修改密码</legend>
            
                <div class="form-group">
                    <label for="">旧密码</label>
                    <input type="password" class="form-control" id="old" name="oldpassword" placeholder="密码">
                </div>
                
                <div class="form-group">
                    <label for="">新密码</label>
                    <input type="password" class="form-control" id="first" name="password" placeholder="密码">
                </div>

                <div class="form-group">
                    <label for="">确认密码</label>
                    <input type="password" class="form-control" id="second" name="repassword" placeholder="确认密码">
                </div>
            
                <button type="submit" class="btn btn-primary">立即修改</button>
                
                <p style="color:red;">
                
                   ${error }
                </p>
            </form>
            
        </div>
	
</body>
</HTML>

