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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js"></script>
<!-- 引入jquery.js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.common.min.js"></script>
</HEAD>
<% long cid = Long.parseLong(request.getParameter("cid")); %>
<% String time = request.getParameter("time"); %>
<body style="margin:80px 0 0px 240px">
	
	
	 <div id="main" style="width: 800px; height: 400px;"></div> 

	
	<script type="text/javascript">
		var myChart = echarts.init(document.getElementById('main'));

		//3.初始化，默认显示标题，图例和xy空坐标轴
		myChart.setOption({

			 title : {
				         text: '<%=session.getAttribute("cname")%>考勤统计',
				         subtext: '<%=request.getParameter("time")%>',
				         x:'center'
				     },
				     tooltip : {
				         trigger: 'item',
				         formatter: "{a} <br/>{b} : {c} ({d}%)"
				     },
				     legend: {
				         orient: 'vertical',
				         left: 'left',
				         data: names
				     },
				     series : [
				         {
				             name: '考勤数据',
				             type: 'pie',
				             radius : '55%',
				             center: ['50%', '60%'],
				             data:[
				             ],
				             itemStyle: {
				                 emphasis: {
				                     shadowBlur: 10,
				                     shadowOffsetX: 0,
				                     shadowColor: 'rgba(0, 0, 0, 0.5)'
				                 }
				             }
				         }
				     ]
			
		});

		//4.设置加载动画(非必须)
		myChart.showLoading(); //数据加载完之前先显示一段简单的loading动画

		//5.定义数据存放数组(动态变)
		var names = []; //建立一个类别数组（实际用来盛放X轴坐标值）
		var count = []; //建立一个销量数组（实际用来盛放Y坐标值）
		
		//6.ajax发起数据请求
		$.ajax({
			type : "post",
			async : true, //异步请求（同步请求将会锁住浏览器，其他操作须等请求完成才可执行）
			url : "${pageContext.request.contextPath}/StatisticsServlet?time=<%=request.getParameter("time")%>&&cid=<%=request.getParameter("cid")%>",
			data : {},
			dataType : "json", //返回数据形式为json

			//7.请求成功后接收数据name+num两组数据
			success : function(result) {
				//result为服务器返回的json对象
				if (result) {
					 for(tmp in result){
						names.push(tmp);
						count.push({value:result[tmp], name:tmp});
					}
	
					

					myChart.hideLoading(); //隐藏加载动画

					//9.覆盖操作-根据数据加载数据图表
					myChart.setOption({
						
						series : [ {
							// 根据名字对应到相应的数据
							name: '考勤数据',
							data : count
						} 
						]
					});

				}

			},
			error : function(errorMsg) {
				//请求失败时执行该函数
				alert("图表请求数据失败!");
				myChart.hideLoading();
			}
		})
	</script>

	


</body>

</HTML>

