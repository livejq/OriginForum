<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="cn.justquiet.bean.Student"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提出问题</title>
<link href="../images/Z.ico" rel="icon" type="image/x-ico">
<link href="../css/studenthome.css" rel="stylesheet" type="text/css">
<link href="../css/ask.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- 将学生信息迭代出来，放在当前页面中，通过使用JSTL来取值 -->
	<%
	List<Student> liststu = (List<Student>)session.getAttribute("student");
	if(liststu != null) {
		Iterator<Student> iterator = liststu.iterator();
		if(iterator.hasNext()) {
			pageContext.setAttribute("stu", iterator.next());
		}
	}
%>
	<div id="nav">
		<ul>
			<li class="first"><a href="student.jsp">首页</a></li>
			<li><a href="forum.jsp">论坛答疑</a></li>
			<li><a href="ask.jsp">提出问题</a></li>
			<li><a href="show.jsp">我的作业</a></li>
			<li><a href="sent.jsp">提交作业</a></li>
			<li class="user"><svg version="1.1"
					xmlns="http://www.w3.org/2000/svg" width="64" height="64"
					viewBox="0 0 64 64">
				<title>个人中心</title>
				<path
					d="M36 44.163v-3.299c4.406-2.483 8-8.673 8-14.864 0-9.941 0-18-12-18s-12 8.059-12 18c0 6.191 3.594 12.382 8 14.864v3.299c-13.569 1.109-24 7.776-24 15.837h56c0-8.060-10.431-14.727-24-15.837z"></path>
				</svg><span title="${stu.snickname }">${stu.snickname }</span></li>
		</ul>
	</div>
	<div id="user-msg">
		<ul>
			<li>等级:<span>${stu.slevel }</span></li>
			<li>经验值:<span>${stu.sexperience }</span></li>
			<li>活跃值:<span>${stu.sactive }</span></li>
			<li class="exit"><a href="../exit">退出</a></li>
		</ul>
	</div>
	<form action="../handleQuestion" method="post">
		<div id="writtenPane">
			<div class="title-box">
				<input type="text" name="title" title="最多60个字符" id="txtTitle"
					maxlength="60" placeholder="输入问题标题(必填)">
			</div>
			<div class="input-content">
				<textarea name="inputContent" placeholder="详细说明(可选)"></textarea>
			</div>
			<div class="input-code">
				<textarea name="inputCodes" placeholder="代码区(可选)"></textarea>
			</div>
			<div class="s-target">
				<label>问题类型：</label>
				<div class="top-m">
					<select name="major" id="s-major">
						<option id="null" selected="selected" value="start">请选择</option>
						<option value="java">java</option>
						<option value="android">android</option>
					</select>
				</div>
			</div>
			<div class="sent-mess">
				<input id="btnPublish" type="submit" value="发布" title=":)">
			</div>
			<div class="askmess">
				<span>${param.askmess }</span>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="../js/home.js"></script>
	<script type="text/javascript" src="../js/ask.js"></script>
</body>
</html>