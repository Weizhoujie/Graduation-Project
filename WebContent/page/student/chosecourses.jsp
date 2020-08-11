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
	
	function choose(){
		if(confirm("是否退选本门课程？")){
		return true;
		}else{
		return false;
		}
	}
</script>
<style type="text/css">
	#choosecourse{
                background: #FF0000;
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
	
	
		<table style="margin-top:10px" cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>课程列表</strong>
					</TD>
				</tr>
				
				
				<tr>
				
					<td class="ta_01" align="center" bgColor="#f5fafe">
					<c:if test="${not empty chosecourses}">
						<table cellspacing="0"  cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								
								<td align="center" width="2%">序号</td>
								<td align="center" width="6%">课程名字</td>
								<td align="center" width="6%">任课教师</td>
								<td align="center" width="40%">课程介绍</td>
								
								
								<td width="4%" align="center">操作</td>
								
							</tr>
							
							
							
							<c:forEach items="${chosecourses }" var="course" varStatus="vs">
								
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
					
									
									 <td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" >${vs.count }</td>
								
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${course.cname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${course.realname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="40%" >${course.cintro }</td>
									
									
									
									<td align="center" style="HEIGHT: 4px">
									
										<a href="${pageContext.request.contextPath}/StudentGiveupCourseServlet?id=${course.id }" onclick="return choose()">
											<input type="button" id="choosecourse"  value="退选">
									</a></td>
									
									
								</tr>
							
							</c:forEach>
							
							
							
							
						</table>
						</c:if>
						<c:if test="${ empty chosecourses}">
					       <h2>暂无数据</h2>   
				         </c:if>
					</td>
				</tr>

			</TBODY>
		</table>
		
		
</body>
</HTML>

