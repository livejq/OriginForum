<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*, cn.justquiet.bean.Student, cn.justquiet.bean.Question, cn.justquiet.bean.Answer"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>英雄所见略同</title>
</head>
<link href="../images/Z.ico" rel="icon" type="image/x-ico">
<link href="../css/studenthome.css" rel="stylesheet" type="text/css">
<link href="../css/solution.css" rel="stylesheet" type="text/css">
<body>
	<!-- 将学生信息迭代出来，放在当前页面中，通过使用JSTL来取值 -->
	<%
	List<Student> liststu = (List<Student>)session.getAttribute("student");
	if(liststu != null) {
		Iterator<Student> itstu = liststu.iterator();
		if(itstu.hasNext()) {
			pageContext.setAttribute("stu", itstu.next());
		}
	}
%>
	<input type="button" id="returnTop">
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
	<%	
	int index = Integer.parseInt(request.getParameter("index"));
	List<Question> listqst = (List<Question>)session.getAttribute("question");
	int count = 0;
	if(listqst.get(index - 1) != null) {
		pageContext.setAttribute("qst", listqst.get(index-1));
		count = listqst.get(index - 1).getAnswer();
	}
%>
	<div id="personal-msg">
		<div class="personal-title">
			<h4>${qst.title }</h4>
		</div><dl>
			<dt>
				<a href="javascript:void(0)">${qst.nickname }</a>
			</dt>
			<dd>等级：</dd>
			<dd>
				<span>${qst.slevel }</span>
			</dd>
		</dl>
	</div>
	<div id="paper-sheet">
		<div class="paper-sheet-code">
			<p>${qst.codes }</p>
		</div>
		<div class="paper-sheet-illustration">
			<p>${qst.details }</p>
		</div>
	</div>
	<div id="count-answer"></div>
	<div id="all-answer-comment">
		<%	
		if(count > 0) {
			List<Answer> listans = (List<Answer>)session.getAttribute("answer");
			if(listans != null) {
				Iterator<Answer> itans = listans.iterator();
			for(int i = 1; itans.hasNext(); i++) {
				pageContext.setAttribute("ans", itans.next());
%>
		<div class="one-of-those">
			<dl>
				<dd>
					#<span><%=i %></span>楼&nbsp;
				</dd>
				<dd>
					<a href="javascript:void(0)" title="等级：${ans.level }">${ans.nickname }</a>
				</dd>
				<dd>&nbsp;@${ans.date }</dd>
			</dl>
			<div>${ans.answer }</div>
			<dl class="support-or-oppose">
				<dt>
					<a href="javascript:void(0)">赞成(<span>${ans.support }</span>)
					</a>
				</dt>
				<dt>
					<a href="javascript:void(0)">反对(<span>${ans.against }</span>)
					</a>
				</dt>
			</dl>
		</div>
		<!-- 赞成或反对数超过四位数会溢出，凡是如此，则 统一变成999+ -->
		<%
			}
		}
	}
%>
	</div>
	<form action="../handleStuAnswer?index=<%=index %>&qid=${ans.qid }"
		method="post">
		<div class="answer-content">
			<textarea name="answer" placeholder="我的见解"></textarea>
		</div>
		<div class="sent-answer">
			<input id="btnPublish" type="submit" value="回答" title="^O^">
		</div>
	</form>
	<script type="text/javascript" src="../js/home.js"></script>
	<script type="text/javascript" src="../js/solution.js"></script>
</body>
</html>