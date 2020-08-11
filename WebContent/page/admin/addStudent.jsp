<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/layui/css/layui.css" rel="stylesheet" type="text/css" />
<script  src="${pageContext.request.contextPath}/layui/layui.all.js"></script>
<script  src="${pageContext.request.contextPath}/js/public.js"></script>
<script  src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
        function check(){
        	var username=document.getElementById("username-1").value;
    		var password=document.getElementById("pwd-1").value;
    		var realname=document.getElementById("realname-1").value;
    		var sclass=document.getElementById("sclass-1").value;
    		var date=document.getElementById("date").value;
        	if(username == ""){  
                alert("用户名不能为空！");  
                return false;  
            } 
        	
            if(password == ""){  
                alert("密码不能为空！");  
                return false;  
            }  
            if(realname == ""){  
                alert("姓名不能为空！");  
                return false;  
            }
            
            if(sclass == ""){  
                alert("班级不能为空！");  
                return false;  
            }
            if(date == ""){  
                alert("入学时间不能为空！");  
                return false;  
            }
             

	     }
</script>
<script type="text/javascript">
$(function(){
	$("#upload").click(function(){ 
		
        
        $(this).attr("data-target","#myModal-1");

     });
})

 </script>

</HEAD>
<body>
	<br>
	
    
            <div >
       
              <div style="align-content: center;width: 300px;height: 40px;margin-left: 530px;">
                    <h2 class="title" id="Label-1">编辑学生信息</h2>
               </div>
                <button  style="width: 100px;font-size: 20px;margin-left: 1000px;" id="upload" class="btn btn-primary" data-toggle="modal" >批量导入</button>
              
                    <form class="fm" action="${pageContext.request.contextPath }/InsertStudentServlet" >
                        <table class="table table-hover table-bordered" style="width: 600px;margin-left: 330px;">
                        	
                        
                            <tr>
                                <th>用户名</th>
                                <td><input type="text" name="username" class="form-control" id="username-1" value="${param.username}" /></td>
                            </tr>
                            <tr>
                                <th>密码</th>
                                <td><input type="password" name="password" class="form-control" id="pwd-1" value="${param.password}"/></td>
                            </tr>
                            <tr>
                                <th>姓名</th>
                                <td><input type="text" name="realname" class="form-control" id="realname-1" value="${param.realname}"/></td>
                            </tr>
                            <tr>
                                <th>性别</th>
                                <td>
                                	<select data-placeholder="选择性别..." class="form-control" name="sex" id="sex-1">
                                    
                                    	<option value="男" hassubinfo="true">男</option>
                                    	<option value="女" hassubinfo="true">女</option>
                                    
                                	</select>
                                </td>
                            </tr>
                            <tr>
                                <th>班级</th>
                                <td><input type="text" name="sclass" class="form-control" id="sclass-1" value="${param.sclass}"/></td>
                            </tr>
                            <tr>
                                <th>入学时间</th>
                                <td>
                                	<input type="text" id="date" name="date" class="form-control" value="${param.date}" /> 

                                </td>
                            </tr>
                        </table>
                        <div class="footer" style="margin-left: 570px;">
                    		<input type="submit" style="width: 100px;font-size: 20px;" id="button1-1" onclick="return check()" class="btn btn-primary" value="保存" >
                			
                		</div>
                		
                    </form>
                    
                </div>
                <p style="color:red;margin-left: 530px;margin-top: 30px;">
                
                   				${error }
                </p>
                
          <!-- Modal 编辑模态框-->
    <div class="modal fade" id="myModal-1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel-1">文件上传</h4>
                </div>
                <div class="modal-body">
                    <form class="fm" action="${pageContext.request.contextPath }/UploadFileServlet?filetype=excelforstudent" method="post" enctype="multipart/form-data">
                        <table class="table table-hover table-bordered">
                        	
                            
                            <tr>
                                <th>上传文件</th>
                                <td><input type="file" name="filename" class="form-control" id="filename" /></td>
                            </tr>
                            
                        </table>
                        <div class="modal-footer">
                    		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    		<input type="submit" id="uploadfile" class="btn btn-primary" value="上传" >
                		</div>
                    </form>
                </div>
                
            </div>
        </div>
    </div>
                
	
<script type="text/javascript">
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#date' //指定元素
	    ,btns: ['clear', 'now','confirm']
	    ,trigger: 'click'
	    ,type: 'month'
	  });
	});
	
</script>

<script type="text/javascript">
	var sex= "${param.sex }";
    $(" #sex-1 option[value='"+sex+"']").attr("selected","selected");
</script>


</body>
</HTML>

