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
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>



<%
    String mess=(String)session.getAttribute("message");
    if(!"".equals(mess)  && mess != null){%>
    	<script type="text/javascript">
        alert("<%=mess%>");
	   </script> 
	  <%} %>
	<%session.setAttribute("message", "");%>
    
<script type="text/javascript">
	function save() {
		var tab=document.getElementById("DataGrid1");
		var rows=tab.rows; 
		//alert(rows.length); 
		var txt = "["; 
	    for(var i=1;i<rows.length;i++) 
	    { 
	    var r = "{";    
	        for(var j=0;j<rows[i].cells.length;j++) 
	        { 
	        	if (j==0) {
	        		//alert("第"+(i)+"行，第"+(j+1)+"列的值是:"+rows[i].cells[j].innerHTML); 
	        		r += "\"sid\":";
		            r += "\"" + rows[i].cells[j].innerHTML+"\",";
				}else if (j==1) {
					r += "\"tid\":";
		            r += "\"" + rows[i].cells[j].innerHTML+"\",";
				}else if (j==2) {
					r += "\"cid\":";
		            r += "\"" + rows[i].cells[j].innerHTML+"\",";
				}
	        	else if (j=rows[i].cells.length-1) {
	        		//alert("第"+(i)+"行，第"+(j+1)+"列的值是:"+rows[i].cells[j].children[0].value); 
		            r += "\"attendance\":";
	        		r += "\"" + rows[i].cells[j].children[0].value+"\",";
				}
	        } 
	    r = r.substring(0, r.length - 1); 
	    r += "},"; 
	    txt += r; 
	    } 
	    txt = txt.substring(0, txt.length - 1); 
	    txt += "]"; 
	    //alert(txt); 
	    
	    window.location = "${pageContext.request.contextPath }/InsertAttendanceServlet?attendancelist="+txt;


	   
	}
</script>
 
    

</HEAD>
<body>
	<br>
	<a href="#">
		<input type="button"  id="button1-1" onclick="save()"  class="btn btn-primary btn-sm" value="保存" >
	</a>
		<table style="margin-top:10px" cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>学生列表</strong>
					</TD>
				</tr>
				
				
				<tr>
				
					<td class="ta_01" align="center" bgColor="#f5fafe">
					<c:if test="${not empty studentlist}">
						<table cellspacing="0"  cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								
								<td align="center" width="2%">序号</td>
								<td align="center" width="6%">学生学号</td>
								<td align="center" width="6%">学生姓名</td>
								<td align="center" width="4%">学生性别</td>
								<td align="center" width="10%">学生班级</td>
								
								<td width="4%" align="center">考勤结果</td>
								
							</tr>
							
							
							
							<c:forEach items="${studentlist }" var="student" varStatus="vs">
								
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="display:none">${student.sid }</td>
									<td style="display:none">${student.tid }</td>
									<td style="display:none">${student.cid }</td>
									 <td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" >${vs.count }</td>
								
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${student.username }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${student.sname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="4%" >${student.sex }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%" >${student.sclass }</td>
									
									
									<td align="center" style="HEIGHT: 22px" width="4%">
									    <select data-placeholder="考勤结果..." class="form-control" name="attendance" id="attendance-1">
                                    
                                    		<option value="到勤" hassubinfo="true">到勤</option>
                                    		<option value="迟到" hassubinfo="true">迟到</option>
                                    		<option value="请假" hassubinfo="true">请假</option>
                                    		<option value="缺勤" hassubinfo="true">缺勤</option>
                                    
                                		</select>
									
									</td>
									
									
								</tr>
							
							</c:forEach>
							
							
							
							
						</table>
						</c:if>
						<c:if test="${ empty studentlist}">
					       <h2>暂无数据</h2>   
				         </c:if>
					</td>
				</tr>

			</TBODY>
		</table>
		
		
</body>
</HTML>

