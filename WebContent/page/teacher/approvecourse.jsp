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


<%
    String mess=(String)session.getAttribute("message");
    if(!"".equals(mess)  && mess != null){%>
    	<script type="text/javascript">
        alert("<%=mess%>");
	   </script> 
	  <%} %>
	<%session.setAttribute("message", "");%>
    
 
 
<style>
            #dupUser{
                background: #1caf9a;
                color: #FFFFFF;
                border: none;
                
            }
            #delUser{
            	background: #FF0000;
                color: #FFFFFF;
                border: none;
                margin-left: 12px;
            }
            
</style>

</HEAD>
<body>
	<br>
	
	
		<table style="margin-top:10px" cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>请求列表</strong>
					</TD>
				</tr>
				
				
				<tr>
				
					<td class="ta_01" align="center" bgColor="#f5fafe">
					<c:if test="${not empty allcourses}">
						<table cellspacing="0"  cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="2%">选择</td>
								<td align="center" width="2%">序号</td>
								<td align="center" width="6%">课程名字</td>
								<td align="center" width="6%">学生姓名</td>
								<td align="center" width="12%">学生班级</td>
								<td align="center" width="40%">课程介绍</td>
								
								
								<td width="6%" align="center">操作</td>
								
							</tr>
							
							
							
							<c:forEach items="${allcourses }" var="course" varStatus="vs">
								
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" ><input  type="checkbox"  class="all" name="all" value="${course.id}"></td>
									<c:if test="${not empty pageBean}">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" >${10*(pageBean.currentPage-1)+vs.count }</td>
									</c:if>
									<c:if test="${empty pageBean}">
									 <td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" >${vs.count }</td>
									</c:if>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${course.cname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${course.realname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="12%" >${course.sclass }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="40%" >${course.cintro }</td>
									
									
									
									<td align="center" style="HEIGHT: 6px">
									
									<a href="${pageContext.request.contextPath}/ApproveCourseByTeaServlet?approvecoursesid=${course.id }">
										<input type="button" class="btn btn-info btn-xs" id="dupUser" onclick="return appCheck()" value="通过">
									</a>
									<a href="${pageContext.request.contextPath}/RejectCourseServlet?rejectid=${course.id }">
										<input type="button" class="btn btn-info btn-xs" id="delUser" onclick="return delCheck()" value="驳回">
									</a>
									</td>
									
								</tr>
							
							</c:forEach>
							
							
							
							
						</table>
						</c:if>
						<c:if test="${ empty allcourses}">
					       <h2>暂无数据</h2>   
				         </c:if>
					</td>
				</tr>

			</TBODY>
		</table>
		<div style="width: 380px; margin: 0 auto; margin-top: 50px;margin-left: 20px;">
			<input type="button"  id="yes" class="btn btn-info btn-xs" value="全选" >&nbsp;&nbsp;
			<input type="button" id="no"  class="btn btn-info btn-xs" value="全不选" >&nbsp;&nbsp;
			<input type="button"  class="btn btn-info btn-xs btn-danger" value="批量通过" onclick="return check()">
		</div>
		<c:if test="${not empty pageBean}">
		<div style="width: 380px; margin: 0 auto; margin-top: 0px;">
		<ul class="pagination" style="text-align: center; margin-top: 10px;">
			
			 			<!-- 当前页面为第一页时,不允许向前点击 -->
			<c:if test="${pageBean.currentPage==1 }">
				<li class="disabled">
					<a href="javascript:void(0)" aria-label="Previous">
						 <span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
						<!-- 当前页面为第一页时,向前点击时,当前页面连接减1 -->
			<c:if test="${pageBean.currentPage!=1 }">
				<li>
					<a href="${pageContext.request.contextPath }/PendingCourseServlet?page=${pageBean.currentPage-1}" aria-label="Previous">
						 <span aria-hidden="true">&laquo;</span>
					</a>
				</li>
			</c:if>
 
			<c:forEach begin="1" end="${pageBean.totalPage }" var="page">
					<!-- 判断当前页 -->
				<c:if test="${pageBean.currentPage==page }">
					<!-- 选择当前页时,不允许再次点击按钮 -->
				<li class="active"><a href="javascript:void(0)">${page }</a></li>
				</c:if>
				<c:if test="${pageBean.currentPage!=page }">
				<li><a href="${pageContext.request.contextPath }/PendingCourseServlet?page=${page}">${page }</a></li>
				</c:if>
			</c:forEach>
			
						 <!-- 当前页面为最后一页时,不允许向后点击 -->
			<c:if test="${pageBean.totalPage==pageBean.currentPage }">
				<li class="disabled">
					<a href="javascript:void(0)" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>
			<c:if test="${pageBean.totalPage!=pageBean.currentPage }">
				<li>
					<a href="${pageContext.request.contextPath }/PendingCourseServlet?page=${pageBean.currentPage+1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>
		</ul>
	</div>
	</c:if>
	
	<script type="text/javascript">
		//全选
		$("#yes").click(function(){
			$("input[name='all']").each(function () {
				$(this).attr("checked", 'checked');
			});
		})
		//全不选
		$("#no").click(function(){
			$("input[name='all']").each(function () {
				$(this).attr("checked", false);
			});
		})
		
function check() {
	 
    var msg = "您真的确定要通过审批吗？";   
    if (confirm(msg)==true){   
        var allcheckbox = "";
        var becheckbox = "";
        $("input[name=all]").each(function(){ //遍历table里的全部checkbox
            allcheckbox += $(this).val() + ","; //获取所有checkbox的值
            if($(this).attr("checked")) //如果被选中
                becheckbox += $(this).val() + ","; //获取被选中的值
        });

        if(becheckbox.length > 0){
            becheckbox = becheckbox.substring(0, becheckbox.length - 1); //把最后一个逗号去掉
            //console.log(becheckbox);
            window.location = "${pageContext.request.contextPath}/ApproveCourseByTeaServlet?approvecoursesid="+becheckbox;
        }else {
			alert("请选择需要通过审批的选课请求！");
			return false;
		}
    }else{   
    	return false;   
    }   
}
function delCheck() {
	var msg = "您真的确定要驳回申请吗？";
	if (confirm(msg)==true){
		return true;
	}else{
		return false;
	}
}
function appCheck() {
	var msg = "您真的确定要通过审批吗？";
	if (confirm(msg)==true){
		return true;
	}else{
		return false;
	}
}

	</script>
</body>
</HTML>

