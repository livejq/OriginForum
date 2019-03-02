<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="cn.justquiet.Beans.Teacher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一个疑问、一把钥匙</title>
<link href="../images/Z.ico" rel="icon" type="image/x-ico">
<link href="../css/teacherhome.css" rel="stylesheet" type="text/css">
<link href="../css/forum.css" rel="stylesheet" type="text/css">
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
	<section id="section-community">
    <div class="s-container">
      <div class="section_intro">
        <h2 class="text-large"><font>现分为以下6大社区</font></h2>
        <p class="text-normal"><font>每个疑问终究会被解开，就像书籍中的某一页终究会被翻开。&nbsp;—liveJQ</font></p>
      </div>

      <div class="l-section">
        <div class="c-blocks__wrapper">
          <div class="c-blocks">
          	<a class="java-block" href="../selectBlock?type=java" target="_blank">
          		<div>
	                <span class="c-blocks__item-title"><font style="vertical-align: inherit;">Java基础、SSM框架和Redis</font></span>
	                <span class="c-blocks__item-category"><font style="vertical-align: inherit;">JavaSE+JavaEE</font></span>
                </div>
                <img class="c-blocks__item-image" src="/images/f2.png" alt="Java基础、SSM框架和Redis">
             </a>

              <a class="android-block" href="../selectBlock?type=android" target="_blank">
                  <div>
                    <span class="c-blocks__item-title"><font style="vertical-align: inherit;">Android移动开发</font></span>
                    <span class="c-blocks__item-category"><font style="vertical-align: inherit;">从eclipse到Android Studio</font></span>
                  </div>
                <img class="c-blocks__item-image" src="/images/f3.png" alt="Android移动开发">
              </a>

              <a class="linux-block" href="../selectBlock?type=linux">
                  <div>
                    <span class="c-blocks__item-title"><font style="vertical-align: inherit;">Linux操作系统</font></span>
                    <span class="c-blocks__item-category"><font style="vertical-align: inherit;">RedHat、Centos、Ubuntu和kali</font></span>
                  </div>
                <img class="c-blocks__item-image" src="/images/f4.png" alt="Linux操作系统">
              </a>

			<a class="web-block" href="../selectBlock?type=web">
                  <div>
                    <span class="c-blocks__item-title"><font style="vertical-align: inherit;">Web网页设计基础</font></span>
                    <span class="c-blocks__item-category"><font style="font-size:14px;">H5+CSS3+JS+JQ+Node.js</font></span>
                  </div>
                <img class="c-blocks__item-image" src="/images/f1.png" alt="Web网页设计基础">
              </a>

              <a class="arithmetic-block" href="../selectBlock?type=arithmetic">
                  <div>
                    <span class="c-blocks__item-title"><font style="vertical-align: inherit;">算法和数据结构</font></span>
                    <span class="c-blocks__item-category"><font style="vertical-align: inherit;">C/C++</font></span>
                  </div>
                <img class="c-blocks__item-image" src="/images/f5.png" alt="算法和数据结构">
              </a>

              <a class="database-block" href="../selectBlock?type=database">
                  <div>
                    <span class="c-blocks__item-title"><font style="vertical-align: inherit;">数据库系统</font></span>
                    <span class="c-blocks__item-category"><font style="vertical-align: inherit;">MySQL、SQL、Sqlite、NoSQL. . .</font></span>
                  </div>
                <img class="c-blocks__item-image" src="/images/f6.png" alt="数据库系统">
              </a>
          </div>
        </div>
      </div>
    </div>
  </section>
<script type="text/javascript" src="../js/home.js"></script>
<script type="text/javascript" src="../js/forum.js"></script>
</body>
</html>