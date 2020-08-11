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

<script type="text/javascript">
function query(id) {
    $.ajax({
        url : "${pageContext.request.contextPath}/QuerySclassServlet",
        async : true,
        type : "POST",
        data : {
            "id" : id
        },
        // 成功后开启模态框
        success : showQuery,
        error : function() {
            alert("请求失败");
        },
        dataType : "json"
    });
}

function showQuery(data){
	$("#dataPadding").html("");
	var jlength=0;
	for(var i = 0; i < 1; i++){
		for(var j=0;j<data[i].length;j++){
			jlength++;
		}
	}
	var tcid=0;
	for(var i = 1; i < 2; i++){
		for(var j=0;j<data[i].length;j++){
			tcid=data[i][j].tcid;
		}
	}
	if (jlength == 0) {
		$("#dataPadding").append("<h2>暂无数据</h2>");
	}else {
		for(var i = 0; i < 1; i++){
			for(var j=0;j<data[i].length;j++){
		$("#dataPadding").append("<td >\n" +
            	"\t\t\t\t\t\t\t\t\t\t\t<a  href=\"${pageContext.request.contextPath}/StudentListForAttendanceServlet?sclass="+data[i][j].sclass+"&&tcid="+tcid+"\">\n" +
        		"\t\t\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"button\" name=\"cname-1\" class=\"form-control\" id=\"cname-1\" value=\""+data[i][j].sclass+"\"/>\n"+
    		    "\t\t\t\t\t\t\t\t\t\t\t</a>\n" +
    	        "\t\t\t\t\t\t\t\t</td>");
		}
	        }
	}
    //数据显示在模态框
	$('#myModal-1').modal('show');

}


 </script>
 <style type="text/css">
 th, td {
display: block;
}
tr {
display: block;
float: center;
}
table {
display: block;

}
 </style>


</HEAD>



<body>

<div class="container">
	<div class="row">
        <c:forEach items="${chosecourses}" var="course">
            <div class="col-md-3">
                <div class="row">
                    <div class="col-md-2" style="margin-top: 36px;">
                        <h4 class="glyphicon glyphicon-send" style="color:blue"></h4>
                    </div>
                    <div class="col-md-10" style="color:blue;margin-top: 40px;">
                        <a href="javascript:void(0)" style="font-size: 20px;" onclick="query(${course.id})"  id="dupUser" data-toggle="modal">${course.cname}</a>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
</div>

<!-- Modal 编辑模态框-->
    <div class="modal fade" id="myModal-1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document" style="width:500px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel-1">选择班级</h4>
                </div>
                <div class="modal-body">
                    
                        <table class="table  table-bordered">
                            <tr style="width: 467px;" id="dataPadding">
                                
                            </tr>
                        </table>
                        <div class="modal-footer">
                    		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    		
                		</div>
                    
                </div>
                
            </div>
        </div>
    </div>
<!-- jQuery -->
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <!-- Bootstrap JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</body>
</HTML>

