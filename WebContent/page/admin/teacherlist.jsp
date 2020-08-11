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
		if(form1.username.value==''){
			alert("搜索条件不能为空！！！");
			return false;			
		}
	}
	
		
</script>



<script type="text/javascript">
$(function(){
	$(".editUser").click(function(){ 
		
		console.log($(this).next().next().val());
		$("input[id='id-1']").val($(this).next().val());
		$("input[id='page-1']").val($(this).next().next().val());
		$("input[id='username-1']").val($(this).parent().prevAll()[7].innerHTML);
        $("input[id='pwd-1']").val($(this).parent().prevAll()[6].innerHTML);
        $("input[id='realname-1']").val($(this).parent().prevAll()[5].innerHTML);
        var sex=$(this).parent().prevAll()[4].innerHTML;
        $(" #sex-1 option[value='"+sex+"']").attr("selected","selected");
        $("input[id='faculty-1']").val($(this).parent().prevAll()[3].innerHTML);
        $("input[id='position']").val($(this).parent().prevAll()[2].innerHTML);
        $("input[id='email']").val($(this).parent().prevAll()[1].innerHTML);
        $("input[id='intro']").val($(this).parent().prevAll()[0].innerHTML);
        //数据显示在模态框
        $(this).attr("data-target","#myModal-1");

     })
})

 </script>

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
                margin-left: 12px;
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
	<form id="form1" name="Form1" onsubmit="return checkdata()" action="${pageContext.request.contextPath}/QueryTeaByUsernameServlet" method="post">
		教师账号：<input type="text" name="username" value="${param.username}">&nbsp;&nbsp;
		<input type="submit" value="搜索">
		<br/>
	
		<table style="margin-top:10px" cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>教师列表</strong>
					</TD>
				</tr>
				
				
				<tr>
				
					<td class="ta_01" align="center" bgColor="#f5fafe">
					<c:if test="${not empty allteachers}">
						<table cellspacing="0"  cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
								<td align="center" width="2%">选择</td>
								<td align="center" width="2%">序号</td>
								<td align="center" width="6%">账号</td>
								<td align="center" width="6%">密码</td>
								<td align="center" width="3%">姓名</td>
								<td align="center" width="3%">性别</td>
								<td align="center" width="6%">学院</td>
								<td align="center" width="6%">职位</td>
								<td align="center" width="6%">邮箱</td>
								<td align="center" width="10%">介绍</td>
								
								<td width="6%" align="center">操作</td>
								
							</tr>
							
							
							
							<c:forEach items="${allteachers }" var="teacher" varStatus="vs">
								
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
								<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" ><input  type="checkbox"  class="all" name="all" value="${teacher.id}"></td>
									<c:if test="${not empty pageBean}">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" >${10*(pageBean.currentPage-1)+vs.count }</td>
									</c:if>
									<c:if test="${empty pageBean}">
									 <td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="2%" >${vs.count }</td>
									</c:if>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${teacher.username }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%" >${teacher.password }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="3%">${teacher.realname }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="3%">${teacher.sex }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%">${teacher.faculty }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%">${teacher.position }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="6%">${teacher.email }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%">${teacher.intro }</td>
									
									
									<td align="center" style="HEIGHT: 6px">
									
										<button type="button" class="editUser btn btn-info btn-xs" id="dupUser" data-toggle="modal">修改</button>
										<input type="hidden"  id="teacherid" class="teacherid" name="teacherid" value="${teacher.id}">
										<input type="hidden"  id="page" class="page" name="page" value="${pageBean.currentPage}">
									<a href="BatchDelTeaServlet?delteachersid=${teacher.id}"><input type="button" id="delUser" class="btn btn-info btn-xs" onclick="return delCheck()" value="删除"></a>
									</td>
									
								</tr>
							
							</c:forEach>
							
							
							
							
						</table>
						</c:if>
						<c:if test="${ empty allteachers}">
					       <h2>暂无数据</h2>   
				         </c:if>
					</td>
				</tr>

			</TBODY>
		</table>
		<div style="width: 380px; margin: 0 auto; margin-top: 50px;margin-left: 20px;">
			<input type="button"  id="yes" class="btn btn-info btn-xs" value="全选" >&nbsp;&nbsp;
			<input type="button" id="no"  class="btn btn-info btn-xs" value="全不选" >&nbsp;&nbsp;
			<input type="button"  class="btn btn-info btn-xs btn-danger" value="批量删除" onclick="return check()">
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
					<a href="${pageContext.request.contextPath }/TeacherServlet?page=${pageBean.currentPage-1}" aria-label="Previous">
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
				<li><a href="${pageContext.request.contextPath }/TeacherServlet?page=${page}">${page }</a></li>
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
					<a href="${pageContext.request.contextPath }/TeacherServlet?page=${pageBean.currentPage+1}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
				</li>
			</c:if>
		</ul>
	</div>
	</c:if>

	
	</form>
	
	<!-- Modal 编辑模态框-->
    <div class="modal fade" id="myModal-1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel-1">编辑用户信息</h4>
                </div>
                <div class="modal-body">
                    <form class="fm" action="${pageContext.request.contextPath }/UpdateTeacherServlet">
                        <table class="table table-hover table-bordered">
                        	<tr style="display: none">
                                <th>id</th>
                                <td><input type="hidden" name="id" class="form-control" id="id-1"  /></td>
                            </tr>
                            <tr style="display: none">
                                <th>page</th>
                                <td><input type="hidden" name="page-1" class="form-control" id="page-1"  /></td>
                            </tr>
                            <tr>
                                <th>用户名</th>
                                <td><input type="text" name="username-1" class="form-control" id="username-1" /></td>
                            </tr>
                            <tr>
                                <th>密码</th>
                                <td><input type="password" name="password" class="form-control" id="pwd-1" /></td>
                            </tr>
                            <tr>
                                <th>姓名</th>
                                <td><input type="text" name="realname" class="form-control" id="realname-1" /></td>
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
                                <th>学院</th>
                                <td><input type="text" name="faculty" class="form-control" id="faculty-1" /></td>
                            </tr>
                            <tr>
                                <th>职位</th>
                                <td>
                                	<input type="text" id="position" name="position" class="form-control"  /> 

                                </td>
                            </tr>
                            <tr>
                                <th>邮箱</th>
                                <td>
                                	<input type="text" id="email" name="email" class="form-control"  /> 

                                </td>
                            </tr>
                            <tr>
                                <th>介绍</th>
                                <td>
                                	<input type="text" id="intro" name="intro" class="form-control"  /> 

                                </td>
                            </tr>
                        </table>
                        <div class="modal-footer">
                    		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    		<input type="submit" id="button1-1" class="btn btn-primary" value="保存" >
                		</div>
                    </form>
                </div>
                
            </div>
        </div>
    </div>
	
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
	 
    var msg = "您真的确定要删除吗？";   
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
            window.location = "${pageContext.request.contextPath }/BatchDelTeaServlet?delteachersid="+becheckbox;
        }else {
			alert("请选择需要删除的内容！");
			return false;
		}
    }else{   
    	return false;   
    }   
}
function delCheck() {
	var msg = "您真的确定要删除吗？";
	if (confirm(msg)==true){
		return true;
	}else{
		return false;
	}
}

	</script>
</body>
</HTML>

