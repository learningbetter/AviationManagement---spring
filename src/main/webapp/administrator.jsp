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
管理员界面：两个按钮：增加飞机、发布航班。并显示所有航班信息：
直接在管理员界面显示所有已经增加的飞机和发布的航班信息，发布新航班后自动刷新页面以显示新增航班信息

前端到后端变量命名要求：
    一、管理员点击删除航班按钮时：
        （表单传输地址：administratorDeleteFlight）
        向后端发送要删除航班的id：flightId，在表单中使用：
        <button name="deleteFlight" value="${flightList.get(i).getFlightId()}">删除航班</button>
        的方式作为删除航班按钮
    二、管理员使用增加飞机功能，点击增加飞机时：
        （表单传输地址：administratorAddAirplane）
        1.在表单中使用input标签提交飞机型号：飞机型号命名：airplaneType
        2.在表单中使用input标签提交头等舱个数：头等舱个数命名：firstClassLimit
        3.在表单中使用input标签提交商务舱个数：商务舱个数命名：businessClassLimit
        4.在表单中使用input标签提交经济舱个数：经济舱个数命名：economyClassLimit
    三、管理员使用创建航班功能，点击创建航班时：
        （表单传输地址：administratorCreateFlight）
        1.在表单中使用input标签提交飞机型号：飞机型号命名：airplaneType
        2.在表单中使用input标签type属性"datetime-local"提交出发时间：出发时间命名：fromData
        3.在表单中使用input标签type属性"datetime-local"提交到达时间：到达时间命名：toData
        4.在表单中使用input标签提交始发地区：始发地区命名：from
        5.在表单中使用input标签提交终点地区：终点地区命名：to
        6.在表单中使用input标签提交头等舱价格：头等舱价格命名：firstClassPrice
        7.在表单中使用input标签提交商务舱价格：商务舱价格命名：businessClassPrice
        8.在表单中使用input标签提交经济舱价格：经济舱价格命名：economyClassPrice
后端到前端变量命名要求：
    1.从数据库获得的所有已经发布的航班：命名：flightList，类型：List<Flight>
    2.从Ticket数据表获得每个已发布航班的购票客户人数：命名：flightNumber，类型：int []      注：精力有限，不会细分三种舱位分别的购票人数
    注：1和2的两个数组一一对应，flightNumber[0]的数值对应于flightList.get(0)表示的航班的购票人数

如果有其他需要传输的变量，告知我再做修改
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
<title>管理员界面</title>
<style>
body {
	background: linear-gradient(to top, #6ECAE9 0%, #FFFFFF 100%)
}

.header {
	height: 70px;
	width: 100%;
	background-color: black;
	position: relative;
}

.title {
	position: absolute;
	top: -9px;
	left: 5%;
}

.log {
	position: absolute;
	top: 3%;
	right: 4%;
}

h1, h2 {
	color: #FFFFFF;
}

input[type="checkbox"] {
	display: none;
}

.banner {
	position: relative;
}

.addPlane {
	display: none;
	height: 280px;
	width: 350px;
	position: fixed;
	z-index:9;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background: #fff;
	text-align: center;
	border-radius: 5px;
	border: 1px solid rgba(0, 0, 0, 0.5);
}

#show1:checked ~ .addPlane {
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

button {
	height: 30px;
	margin: 10px 0;
	cursor: pointer;
}

.createPlane {
	display: none;
	height: 400px;
	width: 350px;
	position: fixed;
	top: 50%;
	left: 50%;
	z-index:9;
	transform: translate(-50%, -50%);
	background: #fff;
	text-align: center;
	border-radius: 5px;
	border: 1px solid rgba(0, 0, 0, 0.5);
}

#show2:checked ~ .createPlane {
	display: block;
}

.show {
	position: absolute;
	height: 60px;
	weight: 100%;
	left: 50%;
	transform: translate(-50%);
	padding-top: 40px;
}

.show .show-btn {
	margin: 25px;
	padding: 7px 15px;
	border-radius: 5px;
	cursor: pointer;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
}

.show .show-btn:hover {
	background-color: rgba(0, 0, 0, 0.2);
}

.list {
	top: 25%;
	position: absolute;
	margin: 0 5%;
}

th, td {
	width: 140px;
	border: 2px solid rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<!-- header -->
	<div class="header">
		<div class="title">
			<h1>管理员系统</h1>
		</div>
		<div class="log">
			<h2>航空公司购票系统</h2>
		</div>
	</div>
	<!-- banner -->
	<div class="banner">
		<input type="checkbox" id="show1"> <input type="checkbox"
			id="show2">
		<div class="show">
			<label for="show1" class="show-btn">增加飞机</label> 
			<label for="show2" class="show-btn">发布航班</label>
		</div>
		<span style="color:red">${message}</span>
		<form class="addPlane" method="POST" action="/Administrator/addAirplane">

			<div class="text">增加飞机</div>
			<div class="data">
				<label>飞机型号：</label> <input type="text" name="airplaneType" />
			</div>
			<div class="data">
				<label>头等舱个数：</label><input type="number" name="firstClassLimit" />
			</div>
			<div class="data">
				<label>商务舱个数：</label><input type="number" name="businessClassLimit" />
			</div>
			<div class="data">
				<label>经济舱个数：</label><input type="number" name="economyClassLimit" />
			</div>
			<div class="btn">
				<button type="submit">提交</button>
			</div>
		</form>
		
		<form class="createPlane" method="POST" action="/Administrator/createFlight">
			<div class="text">发布航班</div>
			<div class="data">
				<label>飞机型号：</label> <input type="text" name="airplaneType" />
			</div>
			<div class="data">
				<label>出发时间：</label><input type="datetime-local" name="fromData" />
			</div>
			<div class="data">
				<label>到达时间：</label><input type="datetime-local" name="toData" />
			</div>
			<div class="data">
				<label>始发地区：</label><input type="text" name="from" />
			</div>
			<div class="data">
				<label>终点地区：</label><input type="text" name="to" />
			</div>
			<div class="data">
				<label>头等舱价格：</label><input type="number" name="firstClassPrice" />
			</div>
			<div class="data">
				<label>商务舱价格：</label><input type="number" name="businessClassPrice" />
			</div>
			<div class="data">
				<label>经济舱价格：</label><input type="number" name="economyClassPrice" />
			</div>
			<div class="btn">
				<button type="submit">提交</button>
			</div>
		</form>
	</div>
	<!-- list -->
	<div class="list">
		<table>
			<tr align="center">
				<th>序号</th>
				<th>出发时间</th>
				<th>到达时间</th>
				<th>始发地区</th>
				<th>终点地区</th>
				<th>头等舱价格</th>
				<th>商务舱价格</th>
				<th>经济舱价格</th>
				<th>已售座位数</th>
			</tr>
			<c:forEach var="list" items="${flightsList}" varStatus="order">
				<td><${order}/td>
				<td>${list.fromData}</td>
				<td>${list.toData}</td>
				<td>${list.from}</td>
				<td>${list.to}</td>
				<td>${list.firstClassPrice}</td>
				<td>${list.businessClassPrice}</td>
				<td>${list.economyClassPrice}</td>
				<td>${flightsList.get(order-1)}</td>
			</c:forEach>
		</table>
	</div>

</body>
</html>
