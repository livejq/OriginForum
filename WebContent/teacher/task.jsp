<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, cn.justquiet.bean.Teacher"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>布置作业</title>
</head>
<link href="../images/Z.ico" rel="icon" type="image/x-ico">
<link href="../css/teacherhome.css" rel="stylesheet" type="text/css">
<link href="../css/task.css" rel="stylesheet" type="text/css">
<body>
	<!-- 将老师信息迭代出来，放在当前页面中，通过使用JSTL来取值 -->
	<%
	List<Teacher> listtea = (List<Teacher>)session.getAttribute("teacher");
	if(listtea != null) {
		Iterator<Teacher> iterator = listtea.iterator();
		if(iterator.hasNext()) {
			pageContext.setAttribute("tea", iterator.next());
		}
	}
%>
	<div id="nav">
		<ul>
			<li class="first"><a href="teacher.jsp">首页</a></li>
			<li><a href="forum.jsp">论坛答疑</a></li>
			<li><a href="task.jsp">布置作业</a></li>
			<li><a href="gather.jsp?page_count=1">收取作业</a></li>
			<li class="user"><svg version="1.1"
					xmlns="http://www.w3.org/2000/svg" width="64" height="64"
					viewBox="0 0 64 64">
				<title>个人中心</title>
				<path
					d="M36 44.163v-3.299c4.406-2.483 8-8.673 8-14.864 0-9.941 0-18-12-18s-12 8.059-12 18c0 6.191 3.594 12.382 8 14.864v3.299c-13.569 1.109-24 7.776-24 15.837h56c0-8.060-10.431-14.727-24-15.837z"></path>
				</svg><span title="${tea.tnickname }">${tea.tnickname }</span></li>
		</ul>
	</div>
	<div id="user-msg">
		<ul>
			<li>头衔:<span>${tea.tlevel }</span></li>
			<li>经验值:<span>${tea.texperience }</span></li>
			<li>活跃值:<span>${tea.tactive }</span></li>
			<li class="exit"><a href="../exit">退出</a></li>
		</ul>
	</div>
	<div id="writtenPane">
		<form action="../handleTask" method="post"
			enctype="multipart/form-data">
			<div class="title-box">
				<input type="text" id="taskTitle" name="inputTitle" maxlength="60"
					placeholder="输入作业标题(必填)">
			</div>
			<div class="input-content">
				<textarea name="inputContent" placeholder="输入作业内容(可选)"></textarea>
			</div>
			<div class="s-target">
				<label>专业：</label>
				<div class="top-m">
					<select name="s-major" id="s-major">
						<option selected="selected" value="start">请选择</option>
					</select>
				</div>
				<label id="label-c">班级：</label>
				<div class="top-c">
					<select name="s-class" id="s-class">
						<option id="classNull" selected="selected" value="start">请选择</option>
					</select>
				</div>
				<div class="deadline">
					<input type="text" id="deadline" name="deadline" maxlength="2"
						placeholder="输入期限(必填)">
				</div>
				<label>(天数)</label>
			</div>
			<div class="attach">
				<div class="addFile">
					<label for="upload">添加附件</label> <input type="file" id="upload"
						name="file" />
				</div>
				<em>(不超过100MB)</em> <span id="fileName"></span>
			</div>
			<div class="sent-mess">
				<input id="btnPublish" type="submit" value="发布" title=":(">
			</div>
			<div class="taskmess">
				<span>${param.taskmess }</span>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="../jquery/jquery.min.js"></script>
	<script type="text/javascript" src="../js/home.js"></script>
	<script type="text/javascript" src="../js/task.js"></script>
</body>
</html>