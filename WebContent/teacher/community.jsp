<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="cn.justquiet.Beans.Teacher" import="cn.justquiet.Beans.Student" import="cn.justquiet.Beans.Question"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>期待您的帮助</title>
<link href="../images/Z.ico" rel="icon" type="image/x-ico">
<link href="../css/teacherhome.css" rel="stylesheet" type="text/css">
<link href="../css/community.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%
	List<Teacher> listtea = (List<Teacher>)session.getAttribute("teacher");
	if(listtea!=null){
		Iterator iterator = listtea.iterator();
		if(iterator.hasNext()){
			pageContext.setAttribute("tea",iterator.next());
		}
	}
%>
	<div id="nav">
		<ul>
			<li class="first"><a href="teacher.jsp">首页</a></li>
			<li><a href="forum.jsp">论坛答疑</a></li>
			<li><a href="task.jsp">布置作业</a></li>
			<li><a href="gather.jsp?page_count=1">收取作业</a></li>
			<li class="user"><svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="64" height="64" viewBox="0 0 64 64">
					<title>个人中心</title>
					<path d="M36 44.163v-3.299c4.406-2.483 8-8.673 8-14.864 0-9.941 0-18-12-18s-12 8.059-12 18c0 6.191 3.594 12.382 8 14.864v3.299c-13.569 1.109-24 7.776-24 15.837h56c0-8.060-10.431-14.727-24-15.837z"></path>
				</svg><span title="${tea.tnickname }">${tea.tnickname }</span>
			</li>
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
	<div id="stay-time">
		<div>
		<ul>
			<li><a href="#" class="stay">最近</a></li><li>
			<a href="#">三天前</a></li><li>
			<a href="#">一周前</a></li><li>
			<a href="#">一个月前</a></li><li>
			<a href="#">已解决</a></li>
		</ul>
		</div>
	</div><div id="like-bookshelf"><!-- 每页显示8个问题  -->
<%
	List<Question> listqst = (List<Question>)session.getAttribute("question");
	int sum_row = listqst.size();
	int page_count = 1;
	int row = 8;
	int sum_page = 1;
	page_count = Integer.parseInt(request.getParameter("page_count"));
	int start = row*(page_count-1)+1;
	if(listqst!=null){
		for(;start<=(row*page_count)&&start<=sum_row;start++){
		pageContext.setAttribute("qst",listqst.get(start-1));
%>			
		<div id="questions-detail">
			<div class="q_time">
				<span>${qst.date }来自</span>
			    <a class="user_name" href="#" target="_blank">${qst.nickname }</a>
			</div>
			<a href="../viewAnswer?index=<%=start %>&qid=${qst.qid }" target="_blank" class="question-title">
				<div><p>${qst.title }</p></div>
			</a><!-- 限50字以内 -->
		  	<div class="share_bar_con">
			  	<span class="browse">浏览${qst.browse }</span>
			</div>
			<div class="answer_num ">
		    	<span>${qst.answer }</span>
		    	<p>回答</p>
		  	</div>
		</div>
<%
		}
	}
%>
	</div>
<%
	sum_page = (sum_row + row - 1) / row;
	if(sum_row>= 1) {
%>
	<div id="question-pages-index">
		<ul>
			<li><input type="button" onClick="openPage(1)" <%=page_count == 1 ? "disabled" : ""%> value="首页"></li>
		    <li><input type="button" onClick="openPage(<%=sum_page%>)" <%=page_count == sum_page ? "disabled" : ""%> value="尾页"></li>
		    <li><input type="button" onClick="openPage(<%=page_count - 1%>)" <%=page_count == 1 ? "disabled" : ""%> value="Prev"></li>
		    <li style="font:14px sans-serif;"><%=page_count%>&nbsp;/&nbsp;<%=sum_page%></li>
		    <li><input type="button" onClick="openPage(<%=page_count + 1%>)" <%=page_count == sum_page ? "disabled" : ""%> value="Next"></li>
		</ul>
	</div>
<%
	}
%>
<script type="text/javascript" src="../js/home.js"></script>
<script type="text/javascript" src="../js/community.js"></script>
</body>
</html>