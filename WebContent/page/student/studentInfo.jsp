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

</HEAD>
<body>
 <%Object message = request.getAttribute("success");
	if(message!= null && !"".equals(message)){	%>
		<script type="text/javascript">
			alert("<%=message%>");
			</script>
 <%} %>
	<table style="margin-top:10px" cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>个人信息</strong>
					</TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="2%">序号</td>
								<td align="center" width="6%">账号</td>
								<td align="center" width="6%">密码</td>
								<td align="center" width="3%">姓名</td>
								<td align="center" width="3%">性别</td>
								<td align="center" width="6%">班级</td>
								<td align="center" width="6%">入学时间</td>
								
								
							</tr>
							
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" >1</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${studentinfo.username }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${studentinfo.password }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="3%">${studentinfo.realname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="3%">${studentinfo.sex }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%">${studentinfo.sclass }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%">${studentinfo.admissionTime }</td>
									
									
								</tr>
							
							
							
							
						</table>
					</td>
				</tr>

			</TBODY>
		</table>
	</body>
</HTML>

