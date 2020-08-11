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

	function checkdata() {
		if(form1.cname.value==''){
			alert("搜索条件不能为空！！！");
			return false;			
		}
	}
	
		
</script>





<%
    String mess=(String)session.getAttribute("message");
    if(!"".equals(mess)  && mess != null){%>
    	<script type="text/javascript">
        alert("<%=mess%>");
	   </script> 
	  <%} %>
	<%session.setAttribute("message", "");%>
    
 
 


</HEAD>
<body>
	<br>
	<form id="form1" name="Form1" onsubmit="return checkdata()" action="${pageContext.request.contextPath}/QueryAttendanceByStuServlet" method="post">
		课程名字：<input type="text" name="cname" value="${param.cname}">&nbsp;&nbsp;
		<input type="submit" value="搜索">
		<br/>
	
		<table style="margin-top:10px" cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>考勤列表</strong>
					</TD>
				</tr>
				
				
				<tr>
				
					<td class="ta_01" align="center" bgColor="#f5fafe">
					<c:if test="${not empty allattendance}">
						<table cellspacing="0"  cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								
								<td align="center" width="2%">序号</td>
								<td align="center" width="6%">账号</td>
								<td align="center" width="6%">姓名</td>
								<td align="center" width="3%">性别</td>
								<td align="center" width="10%">班级</td>
								<td align="center" width="6%">课程</td>
								<td align="center" width="6%">任课教师</td>
								<td align="center" width="6%">考勤时间</td>
								<td align="center" width="4%">结果</td>
								
								
							</tr>
							
							
							
							<c:forEach items="${allattendance }" var="attendance" varStatus="vs">
								
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
								
									<c:if test="${not empty pageBean}">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" >${10*(pageBean.currentPage-1)+vs.count }</td>
									</c:if>
									<c:if test="${empty pageBean}">
									 <td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" >${vs.count }</td>
									</c:if>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${attendance.username }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${attendance.sname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="3%">${attendance.sex }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${attendance.sclass }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%">${attendance.cname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%">${attendance.tname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%">${attendance.time }</td>
									<c:choose>
										<c:when test="${attendance.status eq '到勤'}">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="4%">${attendance.status }</td>
										</c:when>
										<c:otherwise>
											<td style="CURSOR: hand; HEIGHT: 22px;color: red;" align="center"
												width="4%">${attendance.status }</td>
										</c:otherwise>
									</c:choose>
									
									
									
									
									
								</tr>
							
							</c:forEach>
							
							
							
							
						</table>
						</c:if>
						<c:if test="${ empty allattendance}">
					       <h2>暂无数据</h2>   
				         </c:if>
					</td>
				</tr>

			</TBODY>
		</table>
		
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
					<a href="${pageContext.request.contextPath }/AllAttendancesByStuServlet?page=${pageBean.currentPage-1}" aria-label="Previous">
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
				<li><a href="${pageContext.request.contextPath }/AllAttendancesByStuServlet?page=${page}">${page }</a></li>
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
					<a href="${pageContext.request.contextPath }/AllAttendancesByStuServlet?page=${pageBean.currentPage+1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>
		</ul>
	</div>
	</c:if>

	
	</form>
	
	
	
	
</body>
</HTML>

