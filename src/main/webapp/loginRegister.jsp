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
登陆与注册：两个输入框，正下方有一个按钮，既代表登陆，又代表注册。设计思路与现在的手机验证码登陆相同：输入手机号、验证码，如果已经存在账号就直接登陆，
不存在则自动创建并登陆。我们的同理，只是不用手机号、验证码，改为账号、密码，如果输入的账号密码已经存在就直接登录，不存在则创建并登陆

前端到后端变量命名要求：
    (表单传输地址：loginRegisterLogin)
    1.用户登陆时，在表单中使用input标签提交账号：账号命名：username
    2.用户登陆时，在表单中使用input标签提交密码：密码命名：password
后端到前端变量命名要求：
    无变量

如果有其他需要传输的变量，告知我再做修改
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>登录界面</title>
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

form {
	width: 350px;
	heiht: 300px;
	margin: 100px auto;
	border: 1px solid rgba(0, 0, 0, 1);
	border-radius: 5px;
	text-align: center;
}

.user {
	padding-top: 20px;
}

.footer {
	padding-top: 10px;
	padding-bottom: 20px;
}

form input {
	height: 30px;
	width: 250px;
	margin: 5px auto;
}

.footer .button {
	width: 75px;
	height: 30px;
	border-radius: 4px;
}

input:focus {
	background-color: rgba(0, 0, 0, 0.2);
	overflow: hidden;
}

input[type="submit"] {
	cursor: pointer;
}

.button:hover {
	background-color: rgba(0, 0, 0, 0.2);
}
</style>
</head>
<body>

	<aside class="header">
		<div class="container">
			<nav>
				<ul>
					<li><a class="action">登录页面</a></li>
					<li><a href="buyTicket.jsp">购票系统</a></li>
					<li><a href="myTicket.jsp">我的机票</a></li>
				</ul>
			</nav>
			<div class="title"><h2>航空公司购票系统</h2></div>
		</div>
	</aside>


	<form method="POST" action="/LoginRegister/loginRegister">
		<span style="color:red">${loginError}</span>
		<div class="user">
			<label>账号：</label> <input type="text" name="username" /><br> <label>密码：</label>
			<input type="text" name="password" /><br>
		</div>
		<div class="footer">
			<input class=button type="submit" value="登录/注册" />
		</div>
	</form>
</body>
</html>
