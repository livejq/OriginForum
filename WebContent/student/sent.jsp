<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="cn.justquiet.Beans.Student" import="cn.justquiet.Beans.Task"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交作业</title>
<link href="../images/Z.ico" rel="icon" type="image/x-ico">
<link href="../css/studenthome.css" rel="stylesheet" type="text/css">
<link href="../css/sent.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%
	List<Student> liststu = (List<Student>)session.getAttribute("student");
	if(liststu!=null){
		Iterator iterator = liststu.iterator();
		if(iterator.hasNext()){
			pageContext.setAttribute("stu",iterator.next());
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
			<li class="user"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="64" height="64" viewBox="0 0 64 64">
					<title>个人中心</title>
					<path d="M36 44.163v-3.299c4.406-2.483 8-8.673 8-14.864 0-9.941 0-18-12-18s-12 8.059-12 18c0 6.191 3.594 12.382 8 14.864v3.299c-13.569 1.109-24 7.776-24 15.837h56c0-8.060-10.431-14.727-24-15.837z"></path>
				</svg><span title="${stu.snickname }">${stu.snickname }</span>
			</li>
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
	<form action="../handleSent" method="post" enctype="multipart/form-data">
	<div id="writtenPane">
		<div class="attach">
	        <div class="addFile">
				<label for="uploadtk"><img src="../images/upload.png"/></label>
				<input type="file" id="uploadtk" name="file"/>
	    	</div>
	    	<div class="attach-mess-center">
		    	<em>(不超过100MB)</em>
		    	<span id="fileName"></span>
	    	</div>
		</div>
<%
	List<Task> listnot = (List<Task>)session.getAttribute("tknot");
%>
        <div class="s-target">
        	<label>作业编号：</label>
        	<div class="top-m">
        	<select name="tkcodes" id="s-major">
        		<option id="sentNull" selected="selected" value="start">请选择</option>
<%
	if(listnot!=null){
		Iterator itnot = listnot.iterator();
		while(itnot.hasNext()){
			pageContext.setAttribute("tknot",itnot.next());
%>
        		<option value="${tknot.tkcodes }">${tknot.tkcodes }</option>
<%
		}
	}
%>
        	</select>
        	</div>
        </div>
		<div class="sent-mess">
			<input id="btnSent" type="submit" value="提交" title=":+">
		</div>
		<div class="sentmess"><span>${param.sentmess }</span></div>
	</div>
	</form>
<script type="text/javascript" src="../js/home.js"></script>
<script type="text/javascript" src="../js/sent.js"></script>
</body>
</html>