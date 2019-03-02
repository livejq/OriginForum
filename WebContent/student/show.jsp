<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="cn.justquiet.Beans.Student" import="cn.justquiet.Beans.Task"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的作业</title>
<link href="../images/Z.ico" rel="icon" type="image/x-ico">
<link href="../css/studenthome.css" rel="stylesheet" type="text/css">
<link href="../css/show.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
	List<Student> liststu = (List<Student>)session.getAttribute("student");
	if(liststu!=null){
		Iterator itstu = liststu.iterator();
		if(itstu.hasNext()){
			pageContext.setAttribute("stu",itstu.next());
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
<%
	List<Task> listnot = (List<Task>)session.getAttribute("tknot");
	List<Task> listok = (List<Task>)session.getAttribute("tkok");
%>
	<div id="pane">
		<div id="scrollPane">
			<div id="drop-in-show" class="down_list">
				<h5 class="up">未完成</h5>
				<ul>
<%
	if(listnot!=null){
		Iterator<Task> itnot = listnot.iterator();
		while(itnot.hasNext()){
			pageContext.setAttribute("navtknot",itnot.next());
%>
			        <li><a href="show.jsp?tkcodes=${navtknot.tkcodes }&status=0">${navtknot.tkcodes }</a></li> 
<%
		}
	}
%>
			    </ul>
				<h5 class="up">已完成</h5>
				<ul>
<%
	if(listok!=null){
		Iterator<Task> itok = listok.iterator();
		while(itok.hasNext()){
			pageContext.setAttribute("navtkok",itok.next());
%>
			        <li><a href="show.jsp?tkcodes=${navtkok.tkcodes }&status=1">${navtkok.tkcodes }</a></li> 
<%
		}
	}
%>
			    </ul>
			</div>
		</div>
<%
	String tkcodes = null;int status = -1;
	tkcodes = request.getParameter("tkcodes");
	String str = request.getParameter("status");
	if(str!=null)
		status = Integer.parseInt(str);
	if(listnot.size()!=0||listok.size()!=0){
	if(tkcodes!=null&&status!=-1){
		if(listnot.size()!=0&&status==0){
			Iterator itnot = listnot.iterator();
			while(itnot.hasNext()){
				Task tk = (Task)itnot.next();
				if(tk.getTkcodes().equals(tkcodes)){
					pageContext.setAttribute("tknot",tk);
					break;
				}
			}
%>
		<div id="showContent">
		    <div class="task-title">
		    	<h4>${tknot.tktitle }</h4>
		    	<em>来自&nbsp;${tknot.tname }&nbsp;老师</em>
		    	<span>截止时间：${tknot.cutoff }</span>
		    </div>
		      <div class="a-total">
			      <span>详细说明：</span>
			  </div>
			  <div class="b-total">
				<p>${tknot.tkcontent }</p>
			  </div>
			  <div class="panel">
			  	 <div class="d-total">
			      <span>附件下载：</span>
			      <span><a href="../attach?filename=${tknot.attach }" title="下载">${tknot.attach }</a></span>
			  	</div>
		     </div>
		</div>
<%
		}else{
			Iterator<Task> itok = listok.iterator();
			Task tk = null;
			while(itok.hasNext()){
				tk = (Task)itok.next();
				if(tk.getTkcodes().equals(tkcodes)){
					pageContext.setAttribute("tkok",tk);
					break;
				}
			}
%>
		<div id="showContent">
		    <div class="task-title">
		    	<h4>${tk.tktitle }</h4>
		    <%
		    	if(tk!=null&&tk.getStatus()==0){
		    %>	
		    	<em>来自&nbsp;${tk.tname }&nbsp;老师</em>
		    <%
		    	}
		    %>
		    	<span></span>
		    </div>
		      <div class="a-total">
			      <span>详细说明：</span>
			  </div>
			  <div class="b-total">
				<p>${tk.tkcontent }</p>
			  </div>
			  <div class="panel">
			  	 <div class="d-total">
			      <span>附件下载：</span>
			      <span><a href="../attach?filename=${tk.attach }" title="下载">${tk.attach }</a></span>
			  	</div>
		     </div>
		</div>
<%
		}
	}else{
		if(listnot.size()!=0){
			pageContext.setAttribute("ftknot",listnot.get(0));
%>
	<div id="showContent">
	    <div class="task-title">
	    	<h4>${ftknot.tktitle }</h4>
	    	<em>来自&nbsp;${ftknot.tname }&nbsp;老师</em>
	    	<span>截止时间：${ftknot.cutoff }</span>
	    </div>
	      <div class="a-total">
		      <span>详细说明：</span>
		  </div>
		  <div class="b-total">
			<p>${ftknot.tkcontent }</p>
		  </div>
		  <div class="panel">
		  	 <div class="d-total">
		      <span>附件下载：</span>
		      <span><a href="../attach?filename=${ftknot.attach }" title="下载">${ftknot.attach }</a></span>
		  	</div>
	     </div>
	</div>
<%
		}else{
			Task tk = listok.get(0);
			pageContext.setAttribute("ftkok",listok.get(0));
%>
	<div id="showContent">
	    <div class="task-title">
	    	<h4>${ftkok.tktitle }</h4>
	    	<%
		    	if(tk!=null&&tk.getStatus()==0){
		    %>
	    	<em>来自&nbsp;${ftkok.tname }&nbsp;老师</em>
	    	<%
		    	}
	    	%>
	    	<span></span>
	    </div>
	      <div class="a-total">
		      <span>详细说明：</span>
		  </div>
		  <div class="b-total">
			<p>${ftkok.tkcontent }</p>
		  </div>
		  <div class="panel">
		  	 <div class="d-total">
		      <span>附件下载：</span>
		      <span><a href="../attach?filename=${ftkok.attach }" title="下载">${ftkok.attach }</a></span>
		  	</div>
	     </div>
	</div>
<%
		}
	}
}
%>
	</div>
<script type="text/javascript" src="../js/home.js"></script>
<script type="text/javascript" src="../js/show.js"></script>
</body>
</html>