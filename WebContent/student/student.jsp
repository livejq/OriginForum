<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="cn.justquiet.Beans.Student" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../images/Z.ico" rel="icon" type="image/x-ico">
<link href="../css/studenthome.css" rel="stylesheet" type="text/css">
<title>欢迎进入学生主页</title>
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
	<input type="button" id="returnTop">
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
	<div id="adv">
		<div class="banner">
			<div class="ban-img">
				<img class="on" src="../images/1.jpg" alt=""/>
				<img src="../images/2.jpg" alt=""/>
				<img src="../images/3.jpg" alt=""/>
				<img src="../images/4.jpg" alt=""/>
			</div>
			<div class="btn">
				<div class="btn-left"> &lt; </div>
				<div class="btn-right"> &gt; </div>
			</div>
			<div class="tab">
				<ul>
					<li class="on"></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="user-msg">
		<ul>
			<li>等级:<span>${stu.slevel }</span></li>
			<li>经验值:<span>${stu.sexperience }</span></li>
			<li>活跃值:<span>${stu.sactive }</span></li>
			<li class="exit"><a href="../exit">退出</a></li>
		</ul>
	</div>
	<div id="content">
		<div class="card">
			<ul>
				<li><img src="../images/java.png" alt="java" class="active" id="showFirst"/></li>
				<li><img src="../images/android.png" alt="android"/></li>
				<li><img src="../images/linux.png" alt="linux"/></li>
				<li><img src="../images/sphere.png" alt="sphere"/></li>
				<li><img src="../images/php.png" alt="php"/></li>
				<li><img src="../images/database.png" alt="database"/></li>
			</ul>
			<span class="line"></span>
		</div>
		<div class="textBox">
			<div class="box-center">
			<div class="box-content">
				<div class="alive">
					<ul class="box-img">
						<li><img src="../images/xml_java.png"/></li>
					</ul>
					<ul class="box-details">
						<li><h2>技术交流</h2></li><li class="box-info">
						JavaOne是首要的Java开发者大会，您可以在其中了解最新的Java技术，加深您的技术理解，并直接向您的战略家和开发人员提问。Oracle每年举办一次JavaOne会议，包括旧金山的旗舰JavaOne和区域会议。</li><li class="btn-details">
						<a href="https://www.oracle.com/javaone/" target="_blank"><input type="button" value="查看详情"></a></li>
					</ul>
				</div>
			</div>
			<div class="box-content">
				<div>
					<ul class="box-img">
						<li><img src="../images/xml_android.jpg"/></li>
					</ul>
					<ul class="box-details">
						<li><h2>Android 8.1 Oreo：不再需要关于Wi-Fi速度的猜测</h2></li><li class="box-info">
						开放式开发模型将红帽工程师与开源社区联系起来。这些社区的成员共同努力，以确定和提升最佳想法。Red Hat通过为代码做出贡献以及通过从上游项目创建产品来支持这些社区。从操作系统到存储，中间件和容器 ,一直到管理和自动化,红帽正在为企业构建带有认证和服务的开源解决方案。</li><li class="btn-details">
						<a href="https://developer.android.google.cn/" target="_blank"><input type="button" value="查看详情"></a></li>
					</ul>
				</div>
			</div>
			<div class="box-content">
				<div>
					<ul class="box-img">
						<li><img src="../images/xml_linux.jpg"/></li>
					</ul>
					<ul class="box-details">
						<li><h2>为企业构建开源解决方案</h2></li><li class="box-info">
						开放式开发模型将红帽工程师与开源社区联系起来。这些社区的成员共同努力，以确定和提升最佳想法。Red Hat通过为代码做出贡献以及通过从上游项目创建产品来支持这些社区。从操作系统到存储，中间件和容器 ,一直到管理和自动化,红帽正在为企业构建带有认证和服务的开源解决方案。</li><li class="btn-details">
						<a href="https://www.redhat.com/" target="_blank"><input type="button" value="查看详情"></a></li>
					</ul>
				</div>
			</div>
			<div class="box-content">
				<div>
					<ul class="box-img">
						<li><img src="../images/xml_web.jpg"/></li>
					</ul>
					<ul class="box-details">
						<li><h2>MVC+DAO开发模式</h2></li><li class="box-info">
						客户层：因为现在都采用了B/S开发架构，所以一般都使用浏览器进行访问。显示层：使用JSP/Servlet进行页面效果的显示。业务层（Business Object，业务对象）：会将多个原子性的DAO操作进行组合，组合成一个完整的业务逻辑。数据层（DAO）：提供多个原子性的DAO操作，例如：增加、修改、删除等，都属于原子性的操作。</li><li class="btn-details">
						<a href="https://www.apache.com/" target="_blank"><input type="button" value="查看详情"></a></li>
					</ul>
				</div>
			</div>
			<div class="box-content">
				<div>
					<ul class="box-img">
						<li><img src="../images/xml_php.jpg"/></li>
					</ul>
					<ul class="box-details">
						<li><h2>PHP 7.0.33发布</h2></li><li class="box-info">
						PHP是一种流行的通用脚本语言，特别适用于Web开发。快速，灵活和实用，PHP支持从您的博客到世界上最受欢迎的网站的所有内容。</li><li class="btn-details">
						<a href="https://www.php.net/" target="_blank"><input type="button" value="查看详情"></a></li>
					</ul>
				</div>
			</div>
			<div class="box-content">
				<div>
					<ul class="box-img">
						<li><img src="../images/xml_mysql.png"/></li>
					</ul>
					<ul class="box-details">
						<li><h2>MySQL文档存储</h2></li><li class="box-info">
						MySQL文档存储允许开发人员使用SQL关系表和无架构JSON集合。NoSQL + SQL = MySQL,MySQL文档存储为用户提供了开发传统SQL关系应用程序和NoSQL无架构文档数据库应用程序的最大灵活性。这消除了对单独的NoSQL文档数据库的需要。开发人员可以在同一个数据库和同一个应用程序中混合和匹配关系数据和JSON文档。例如，可以在同一个应用程序中查询这两个数据模型，结果可以是表格，表格或JSON格式。</li><li class="btn-details">
						<a href="https://dev.mysql.com/" target="_blank"><input type="button" value="查看详情"></a></li>
					</ul>
				</div>
			</div>
		</div>
		</div>
	</div>
	<div id="footer">
		<div class="favourite">
			<ul>
				<li><img src="../images/logo-wechat.png" alt=""/><div><a href="#">微信</a></div></li>
				<li><img src="../images/google_plus_48dp.png" alt=""/><div><a href="#">Google+</a></div></li>
				<li><img src="../images/youtube_48dp.png" alt=""/><div><a href="#">YouTube</a></div></li>
				<li><svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
					width="64px" height="64px" viewBox="0 0 64 64" enable-background="new 0 0 64 64" xml:space="preserve">
					<path fill="#5FA9DD" d="M64,12.157c-2.355,1.044-4.885,1.75-7.541,2.068c2.711-1.625,4.793-4.198,5.773-7.264
						c-2.537,1.505-5.347,2.597-8.338,3.186C51.499,7.595,48.086,6,44.31,6c-7.252,0-13.131,5.879-13.131,13.13
						c0,1.029,0.116,2.031,0.34,2.992C20.607,21.575,10.932,16.347,4.455,8.403c-1.13,1.939-1.778,4.195-1.778,6.601
						c0,4.555,2.318,8.574,5.841,10.929c-2.152-0.068-4.177-0.659-5.947-1.642c-0.001,0.055-0.001,0.11-0.001,0.165
						c0,6.362,4.526,11.669,10.533,12.875c-1.102,0.3-2.262,0.46-3.459,0.46c-0.846,0-1.669-0.082-2.47-0.235
						c1.671,5.216,6.52,9.013,12.266,9.119c-4.494,3.522-10.155,5.621-16.307,5.621c-1.06,0-2.105-0.062-3.132-0.183
						c5.811,3.725,12.713,5.899,20.128,5.899c24.152,0,37.359-20.008,37.359-37.359c0-0.569-0.013-1.136-0.038-1.699
						C60.013,17.103,62.24,14.79,64,12.157z"/></svg><div><a href="#">Twitter</a></div></li>
			</ul>
		</div>
		<div class="designer"><div><span>Copyright&nbsp;<svg version="1.1" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><title>copyright</title>
			<path d="M12 20.016c4.406 0 8.016-3.609 8.016-8.016s-3.609-8.016-8.016-8.016-8.016 3.609-8.016 8.016 3.609 8.016 8.016 8.016zM12 2.016c5.531 0 9.984 4.453 9.984 9.984s-4.453 9.984-9.984 9.984-9.984-4.453-9.984-9.984 4.453-9.984 9.984-9.984zM11.859 9.141c-1.442 0-1.875 1.276-1.875 2.719v0.281c0 1.442 0.434 2.719 1.875 2.719 0.88 0 1.641-0.537 1.641-1.406h1.781c0 0.915-0.495 1.603-1.031 2.063-0.604 0.518-1.308 0.844-2.391 0.844-2.559 0-3.844-1.691-3.844-4.219v-0.281c0-1.212 0.34-2.318 0.938-3 0.619-0.707 1.61-1.266 2.906-1.266 1.038 0 1.904 0.358 2.438 0.891 0.516 0.516 0.984 1.312 0.984 2.297h-1.781c0-0.234-0.047-0.422-0.141-0.609s-0.188-0.422-0.328-0.563c-0.254-0.254-0.688-0.469-1.172-0.469z"></path>
			</svg>&nbsp;2018&nbsp;<i><a href="https://www.justquiet.cn" target="_blank">www.justquiet.cn.</a></i>&nbsp;Powered by JSP Core on Windows 10</span></div></div>
		</div>
<!--  <script src="jquery/jquery.min.js"></script>-->
<script type="text/javascript" src="../js/home.js"></script>
</body>
</html>