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
        	var tname=document.getElementById("tname-1").value;
    		
    		var filename=document.getElementById("filename").value;
    		var date=document.getElementById("date").value;
        	if(tname == ""){  
                alert("教师不能为空！");  
                return false;  
            } 
        	if(date == ""){  
                alert("请假时间不能为空！");  
                return false;  
            }
            if(filename == ""){  
                alert("未上传请假证明！");  
                return false;  
            }  

	     }
</script>


</HEAD>
<body>
	<br>

                    <form class="fm" action="${pageContext.request.contextPath }/InsertLeaveApplicationServlet" method="post" enctype="multipart/form-data">
                        <table class="table table-hover table-bordered" style="width: 600px;margin-left: 330px;">
                            
                            <tr>
                                <th>课程</th>
                                <td>
                                	<select data-placeholder="选择性别..." class="form-control" name="course" id="course-1">
                                    	<c:forEach items="${chosecourses }" var="course" varStatus="vs">
                                    		<option value="${course.cid }" hassubinfo="true">${course.cname }</option>
                                    		<option value="${course.tid }" style="display: none;" hassubinfo="true">${course.realname }</option>
                                    	</c:forEach>
                                    
                                	</select>
                                </td>
                            </tr>
                            <tr>
                                <th>教师</th>
                                <td>
                                	<input type="text" readonly="readonly" name="tname" autocomplete="off" class="form-control" id="tname-1" value=""/>
                                	<input type="hidden" name="tid" autocomplete="off" class="form-control" id="tid-1" value=""/>
                                </td>
                            </tr>
                            <tr>
                                <th>请假时间</th>
                                <td>
                                	<input type="text" id="date" name="date" autocomplete="off" class="form-control" value="${param.date}" /> 

                                </td>
                            </tr>
                            <tr>
                                <th>请假证明</th>
                                <td><input type="file" name="filename" class="form-control" id="filename" /></td>
                            </tr>
                        </table>
                        <div >
                        	<img style="display: none;margin-left: 330px;"  src="" id="img" width="600px" height="300px" >
                        </div>
                        
                    	<input type="submit" style="width: 100px;font-size: 20px;margin-left: 570px;margin-top: 30px;" id="button1-1" onclick="return check()" class="btn btn-primary" value="保存" >
                			
                    </form>
                    
                
                <p style="color:red;margin-left: 530px;margin-top: 30px;">
                
                   				${error }
                </p>
         
                
<script type="text/javascript">
	$(document).ready(function () {
		$("#filename").change(function () {
			var current_pic = this.files[0];
			preview_picture(current_pic);
		});
	});
	
	function preview_picture(pic) {
		var r = new FileReader();
		r.readAsDataURL(pic);
		r.onload = function (e) {
			$("#img").attr("src",this.result).show();
		}
	}
</script>	
<script type="text/javascript">
layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#date' //指定元素
	    ,btns: ['clear', 'now','confirm']
	    ,trigger: 'click'
	    ,type: 'date'
	  });
	});
	
</script>

<script type="text/javascript">
function changeteacher() { 　　 
	var   mySelect = document.getElementById("course-1");//定位id(获取select)
	var   index =mySelect.selectedIndex;// 选中索引(选取select中option选中的第几个)
	var   tname =mySelect.options[index+1].text; // 选中文本
	var   tid =mySelect.options[index+1].value; // 选中值
	$("input[id='tname-1']").val(tname);
	$("input[id='tid-1']").val(tid);

} 　　　　
window.onload = changeteacher;

</script>
<script type="text/javascript">
$(function(){
	$("#course-1").click(function(){
		var   mySelect = document.getElementById("course-1");//定位id(获取select)
		var   index =mySelect.selectedIndex;// 选中索引(选取select中option选中的第几个)
		var   tname =mySelect.options[index+1].text; // 选中文本
		var   tid =mySelect.options[index+1].value; // 选中值
		$("input[id='tname-1']").val(tname);
		$("input[id='tid-1']").val(tid);
	})
})
</script>

</body>
</HTML>

