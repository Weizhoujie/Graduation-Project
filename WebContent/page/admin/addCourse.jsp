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
        	var cname=document.getElementById("cname-1").value;
    		var cintro=document.getElementById("cintro-1").value;
    		
        	if(cname.trim() == ""){  
                alert("课程名不能为空！");  
                return false;  
            } 
        	
            if(cintro.trim() == ""){  
                alert("课程介绍不能为空！");  
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
                    <h2 class="title" id="Label-1">编辑课程信息</h2>
               </div>
                <button  style="width: 100px;font-size: 20px;margin-left: 1000px;" id="upload" class="btn btn-primary" data-toggle="modal" >批量导入</button>
              
                    <form class="fm" action="${pageContext.request.contextPath }/InsertCourseServlet" >
                        <table class="table table-hover table-bordered" style="width: 600px;margin-left: 330px;">
                        	
                        
                            <tr>
                                <th>课程名字</th>
                                <td><input type="text" name="cname" class="form-control" id="cname-1" value="${param.cname}" /></td>
                            </tr>
                     
                            <tr height="100px;">
                                <th>课程介绍</th>
                                <td><textarea style="overflow:visible;height: 100px;"  name="cintro" class="form-control" id="cintro-1" >${param.cintro}</textarea></td>
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
                    <form class="fm" action="${pageContext.request.contextPath }/UploadFileServlet?filetype=excelforcourse" method="post" enctype="multipart/form-data">
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
                


</body>
</HTML>

