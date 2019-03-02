<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录</title>
<link href="images/Z.ico" rel="icon" type="image/x-ico">
<link href="css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="determine" method="post">
<div id="bg">
	<div class="login">
		<input type="text" name="username" required="required" placeholder="学号/账号"/>
		<input type="password" name="password" required="required" placeholder="密码"/>
		<div class="loginmess"><span>${requestScope.loginmess }</span></div>
		<div class="person">
			<div>
				<input type="radio" name="role" value="学生" checked="checked" id="student"/><label for="student">&nbsp;学生&nbsp;&nbsp;</label>
				<input type="radio" name="role" value="教师" id="teacher"><label for="teacher">&nbsp;教师</label>
			</div>
		</div>
		<div class="btn">
			<input type="hidden" value="true"/>
			<input src="images/login.png" border="0" type="image"/>
		</div>
	</div>
</div>
</form>
<!-- <script type="text/javascript" src="js/before.js"></script> -->
</body>
</html>