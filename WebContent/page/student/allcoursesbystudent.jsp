<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function check() {
		if(form1.coursename.value=='' && form1.teachername.value==''){
			alert("搜索条件不能为空！！！");
			return false;			
		}
	}
	function choose(){
		if(confirm("是否选择本门课程？")){
		return true;
		}else{
		return false;
		}
	}
</script>
<style type="text/css">
	#choosecourse{
                background: #3385FF;
                color: #FFFFFF;
                border: none;
                
            }
</style>
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
	<form id="form1" name="Form1" onsubmit="return check()" action="${pageContext.request.contextPath}/QueryCourseByStuServlet" method="post">
		课程名称：<input type="text" name="coursename" value="${param.coursename}">&nbsp;&nbsp;
		教师名字：<input type="text" name="teachername" value="${param.teachername}">&nbsp;&nbsp;
		<input type="submit" value="搜索">
		<br/>
	
		<table style="margin-top:10px" cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>课程列表</strong>
					</TD>
				</tr>
				
				
				<tr>
				
					<td class="ta_01" align="center" bgColor="#f5fafe">
					<c:if test="${not empty allcourses}">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="3%">序号</td>
								<td align="center" width="6%">课程名称</td>
								<td align="center" width="6%">任课老师</td>
								<td align="center" width="17%">课程介绍</td>
								
								<td width="2%" align="center">申请选课</td>
								
							</tr>
							
							
							
							<c:forEach items="${allcourses }" var="course" varStatus="vs">
								<%String result = "未选";%>
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<c:if test="${not empty pageBean}">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="3%" >${10*(pageBean.currentPage-1)+vs.count }</td>
									</c:if>
									<c:if test="${empty pageBean}">
									 <td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="3%" >${vs.count }</td>
									</c:if>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${course.cname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${course.realname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" 
										width="17%">${course.cintro }</td>
									<c:forEach items="${studentchosecourses}" var="studentcourse" >
								    	<c:if test="${(course.id eq studentcourse.tcid)}">
								    			<c:if test="${(studentcourse.flag eq '0')}">
													<%result="审批中";%>
												</c:if>
												<c:if test="${(studentcourse.flag eq '1')}">
													<%result="已选";%>
												</c:if>
	
										</c:if>
									
									</c:forEach>
									<%if(result.equals("未选")){ %>
									<td align="center" style="HEIGHT: 2px">
									<a href="${pageContext.request.contextPath}/StudentChooseCourseServlet?tc_id=${course.id }" onclick="return choose()">
											<input type="button" id="choosecourse"  value="选择">
									</a></td>
									<%} %>
									<%if(result.equals("已选")){%>
										<td align="center" style="HEIGHT: 2px;color: red;" style="CURSOR: hand">已选</td>
									<%} %>
									<%if(result.equals("审批中")){%>
										<td align="center" style="HEIGHT: 2px;color: green;" style="CURSOR: hand">审批中</td>
									<%} %>
									
									
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
		<c:if test="${not empty pageBean}">
		<div style="width: 380px; margin: 0 auto; margin-top: 50px;">
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
					<a href="${pageContext.request.contextPath }/AllCourseByStuServlet?page=${pageBean.currentPage-1}" aria-label="Previous">
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
				<li><a href="${pageContext.request.contextPath }/AllCourseByStuServlet?page=${page}">${page }</a></li>
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
					<a href="${pageContext.request.contextPath }/AllCourseByStuServlet?page=${pageBean.currentPage+1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>
		</ul>
	</div>
	</c:if>

	
	</form>
</body>
</HTML>

