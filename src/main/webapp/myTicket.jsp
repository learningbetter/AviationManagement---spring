<%--
前端设计说明：
所有变量（jsp变量、表单键名、非表单键命）命名规范：驼峰命名法
表单传输方式：post
表单传输地址：见下方具体要求
前后端数据对接接口：任何非表单且需传输到后端的数据，统一使用jsp内置对象session传输，使用setAttribute()方法与getAttribute()方法发送或者接受数据
前后端数据对接命名：任何非表单且需传输到后端的数据，setAttribute()中键的命名必须与值的变量名相同
--%>
<%--
本页面设计说明：
我的机票页面：显示所有已经购买过的机票，
每个机票/航班占一个框，框的末尾有一个退票按钮，点击退票。
右边有一个“购买机票”按钮，点击跳转到buyTicket.jsp页面

前端到后端变量命名要求：
    1.用户点击退票按钮时：
    (表单传输地址：myTicketRefund)
    向后端发送用户退票的机票id：ticketId，在表单中使用：
    <button name="refundTheTicket" 
    value="${ticketList.get(i).getTicketId()}">退票</button>
    的方式作为退票按钮。
后端到前端变量命名要求：
    1.从数据库获得该客户所有机票：命名：ticketList，类型：List<Ticket>

如果有其他需要传输的变量，告知我再做修改
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<html>
<head>
	<base href="${pageContext.request.contextPath}/"> <%--防止路径叠加--%>
<title>我的机票</title>
<style>
body {
	background: linear-gradient(to top, #6ECAE9 0%, #FFFFFF 100%)
}

.header {
	height: 70px;
	weight: 100%;
	background-color: black;
	padding: 15px 0;
	position: relative;
}

nav ul li {
	margin: 0 35px;
	display: inline-block;
}

nav ul li a {
	color: #FFFFFF;
	font-size: 1.25em;
	font-weight: 500;
}


.title{
position:absolute;
right:3%;
top:7%;
}

h2{
color:#FFFFFF;
}

.log {
	position: absolute;
	top: 3%;
	right: 4%;
}

h1 {
	font-size: 30px;
	line-height: 40px;
	color: #FFFFFF;
}

.list {
	top: 10%;
	position: center;
	margin: 0 5%;
}

th, td {
	width: 140px;
	border: 3px solid rgba(0, 0, 0, 0.5);
}

button {
	padding: 8px 8px;
	border-radius: 8px;
	cursor: pointer;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
	margin-left: 40px;
	margin-top: 30px;
}
</style>

</head>
<body>

	<aside class="header">
		<div class="container">
			<nav>
				<ul>
					<li><a href="Client/buyTicket">购票系统</a></li>
					<li><a>我的机票</a></li>
					<li><a href="">注销</a></li>
				</ul>
			</nav>
			<div class="title"><h2>航空公司购票系统</h2></div>
		</div>
	</aside>
	
	<div class="list">
		<table>
			<tr align="center">
				<th>机票编号</th>
				<th>乘客编号</th>
				<th>飞行编号</th>
				<th>座位类型</th>
				<th>座位号码</th>
				<th>退票</th>
			</tr>
			<c:forEach var="ticket" items="${ticketList}" varStatus="order">
				<tr>
					<td>${ticket.ticketId}</td>
					<td>${ticket.client.clientId}</td>
					<td>${ticket.flight.flightId}</td>
					<td>${ticket.seatType}</td>
					<td>${ticket.seatNo}</td>
					<td>
						<form class="refundTicket" method="POST"
							  action="/Client/refundTicket">
							<button name="refundTheTicket"
									value="${ticket.ticketId}">退票</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
