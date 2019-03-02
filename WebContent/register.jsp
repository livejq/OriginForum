<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
<div id="tab">
    <div class="nav">
        <ul>
            <li class="active"><a href="#">学生</a></li>
            <li><a href="#">教师</a></li>
        </ul>
    </div>
    <div class="content">
        <div class="student">
            <ul>
            	<li>
                   <label>昵称：</label><input type="text" name="nickname"/>
                </li>
                <li>
                   <label>学号：</label><input type="text" name="id"/>
                </li>
                <li>
                   <label>密码：</label><input type="password" name="password"/>
                </li>
                <li>
                   <label>确认密码：</label><input type="password" name="password2"/>
                </li>
                <li>
                   <label>学院：</label><input type="text" name="campus"/>
                </li>
                <li>
                   <label>专业：</label><input type="text" name="major"/>
                </li>
                <li>
                   <label>班级：</label><input type="text" name="class"/>
                </li>
                <li>
                   <input type="submit" value="注册"/>
                </li>
            </ul>
        </div>
        <div class="teacher">
            <ul>
            	<li>
                   <label>昵称：</label><input type="text" name="nickname"/>
                </li>
                <li>
                   <label>账号：</label><input type="text" name="id"/>
                </li>
                <li>
                   <label>密码：</label><input type="password" name="password"/>
                </li>
                <li>
                   <label>确认密码：</label><input type="password" name="password2"/>
                </li>
                <li>
                   <label>认证密匙：</label><input type="text" name="authentication"/>
                </li>
                <li>
                   <input type="submit" value="注册"/>
                </li>
            </ul>
        </div>
     </div>
</div>
</body>
</html>