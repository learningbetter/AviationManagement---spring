<%@ page import="com.entity.Flight" %>
<%@ page import="java.util.List" %><%--
前端设计说明：
所有变量（jsp变量、表单键名、非表单键命）命名规范：驼峰命名法
表单传输方式：post
表单传输地址：见下方具体要求
前后端数据对接接口：任何非表单且需传输到后端的数据，统一使用jsp内置对象session传输，使用setAttribute()方法与getAttribute()方法发送或者接受数据
前后端数据对接命名：任何非表单且需传输到后端的数据，setAttribute()中键的命名必须与值的变量名相同
--%>
<%--
本页面设计说明：
所有航班机票界面：登陆之后跳转到该页面，
会显示所有已经发布的航班，每个航班栏位后面有一个购票按钮，点击购票，
右边还有一个”我的机票“按钮，点击跳转到myTicket.jsp页面

前端到后端变量命名要求：
    （表单传输地址：buyTicketBuy)
    1.用户点击购票按钮时：向后端发送用户购买的机票座位类型，在表单中使用:
        <button name="purchasedFlight" value="1" >购买XX舱<button>
        的方式作为每个机票的航班的购票按钮，1表示头等舱，2表示商务舱，3表示经济舱。
    2.用户点击购票按钮时：向后端发送用户具体购买的航班是哪个即航班id：flightId
        针对1中提到的button，写一个js事件监听点击按钮，当按钮被点击时，向后端发送该按钮所代表的航班id，
        具体代码如下：
        onclick="<%session.setAttribute("flightId",flightList.get(i).getFlightId());%>"
        可以直接将该属性添加到1的button的属性中。
    1与2结合的代码最终应该为（在表单中使用）：
    <button name="purchasedFlight" value="1" 
    onclick="<%session.setAttribute("flightId",flightList.get(i).getFlightId());%>">购买XX舱</button>
    或者为：onclick="${pageContext.session.setAttribute("flightId",flightList.get(order).getFlightId())}"
后端到前端变量命名要求：
    1.从数据库获得的所有已经发布航班数组：命名：flightList，类型：List<Flight>

如果有其他需要传输的变量，告知我再做修改
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
	<base href="${pageContext.request.contextPath}/"> <%--防止路径叠加--%>
<title>购买机票</title>
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

h2	{
color:#FFFFFF;
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



input[type="checkbox"] {
	display: none;
}

.buyTicket {
	display: none;
	height: 150px;
	width: 350px;
	position: fixed;
	z-index: 9;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background: #fff;
	text-align: center;
	border-radius: 5px;
	border: 1px solid rgba(0, 0, 0, 0.5);
}

#show:checked ~ .buyTicket {
	display: block;
}

.text {
	font-size: 30px;
	font-weight: 500;
	padding-top: 5px;
	height: 50px;
}

.data {
	height: 30px;
	margin: 5px 0;
}

.shower {
	height: 30px;
	weight: 100%;
	left: 50%;
	transform: translate(-50%);
	padding-top: 40px;
}

.shower .show-btn {
	margin: 20px;
	padding: 7px 15px;
	border-radius: 5px;
	cursor: pointer;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
}

.show .show-btn:hover {
	background-color: rgba(0, 0, 0, 0.2);
}
</style>
</head>

<body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<aside class="header">
		<div class="container">
			<nav>
				<ul>
					<li><a>购票系统</a></li>
					<li><a href="Client/myTicket">我的机票</a></li>
					<li><a href="">注销</a></li>
				</ul>
			</nav>
			<div class="title"><h2>航空公司购票系统</h2></div>
		</div>
	</aside>
	
	
	<div class="list">
		<table>
			<tr align="center">
				<th>航班编号</th>
				<th>出发时间</th>
				<th>到达时间</th>
				<th>始发地区</th>
				<th>终点地区</th>
				<th>头等舱价格</th>
				<th>商务舱价格</th>
				<th>经济舱价格</th>
				<th>购买机票</th>
			</tr>
			<% List<Flight> flightList = (List<Flight>)request.getAttribute("flightList"); %>
			<% int i=-1; %>
			<c:forEach var="ticket" items="${flightList}" varStatus="order">
				<tr>
					<% i++; %>
					<td>${ticket.flightId}</td>
					<td>${ticket.fromData}</td>
					<td>${ticket.toData}</td>
					<td>${ticket.from}</td>
					<td>${ticket.to}</td>
					<td>${ticket.firstClassPrice}</td>
					<td>${ticket.businessClassPrice}</td>
					<td>${ticket.economyClassPrice}</td>
					<td>
						<input type="checkbox" id="show">
						<div class="show">
							<label for="show" class="show-btn"> 购买机票</label>
						</div>
						<form class="buyTicket" method="POST" action="/Client/buy">
							<div class="text">选择机票类型</div><br>
							<input type="radio" value="1" name="purchasedFlight">头等舱
							<input type="radio" value="2" name="purchasedFlight">商务舱
							<input type="radio" value="3" name="purchasedFlight">经济舱
							<div class="btn">
								<input type="submit" class="flightId" onclick="<% session.setAttribute("flightId",flightList.get(i).getFlightId());%>">
								<script>
									/*不能对一个servlet的参数分别发送表单和json，让其分别接受解析到不同的形参，放弃ajax*/
								</script>
							</div>

						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>

