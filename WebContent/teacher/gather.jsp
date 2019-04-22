<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*, cn.justquiet.bean.Teacher, cn.justquiet.bean.Student, cn.justquiet.bean.Task, cn.justquiet.bean.Check, cn.justquiet.daoimpl.GatherDAOImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看学生作业提交情况</title>
<link href="../images/Z.ico" rel="icon" type="image/x-ico">
<link href="../css/teacherhome.css" rel="stylesheet" type="text/css">
<link href="../css/gather.css" rel="stylesheet" type="text/css">
</head>
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
	<%
	List<Task> listnot = (List<Task>)session.getAttribute("tknot");
	List<Task> listok = (List<Task>)session.getAttribute("tkok");

%>
	<div id="pane">
		<div id="scrollPane">
			<div id="drop" class="down_list">
				<h5 class="up">未完成</h5>
				<ul>
					<%
	if(listnot != null) {
		Iterator<Task> itnot = listnot.iterator();
		while(itnot.hasNext()) {
			pageContext.setAttribute("navtknot", itnot.next());
%>
					<li><a href="gather.jsp?tkcodes=${navtknot.tkcodes }&status=0">${navtknot.tkcodes }</a></li>
					<%
		}
	}
%>
				</ul>

				<h5 class="up">已完成</h5>
				<ul>
					<%
	if(listok != null) {
		Iterator<Task> itok = listok.iterator();
		while(itok.hasNext()) {
			pageContext.setAttribute("navtkok", itok.next());
%>
					<li><a href="gather.jsp?tkcodes=${navtkok.tkcodes }&status=1">${navtkok.tkcodes }</a></li>
					<%
		}
	}
%>
				</ul>
			</div>
		</div>
		<%
	String tkcodes = null;
	int status = -1;
	tkcodes = request.getParameter("tkcodes");
	String str = request.getParameter("status");
	if(str != null) {
		status = Integer.parseInt(str);
	}
	if(listnot.size() != 0 || listok.size() != 0) {
		if(tkcodes != null && status != -1) {
			if(listnot.size() != 0 && status == 0) {
				Iterator<Task> itnot = listnot.iterator();
				Task tk = null;
				while(itnot.hasNext()) {
					tk = (Task)itnot.next();
					if(tk.getTkcodes().equals(tkcodes)) {
						pageContext.setAttribute("ftknot", tk);
						break;
					}
				}
%>
		<%
		GatherDAOImpl gbi = new GatherDAOImpl();
		List<Check> done = gbi.executeQueryCheckByTkcodes(tk.getTkcodes(), 1);
		List<Student> notdo = gbi.executeQueryCheckNotDone(tk.getTkcodes(), 0);
		int allmember = gbi.executeQueryClassMember(tk.getCid());
%>
		<div id="showContent">
			<!-- 此模块为点击未完成的作业码显示的页面信息 -->
			<div class="task-title">
				<h4>${ftknot.tktitle }</h4>
				<span>截止时间：${ftknot.cutoff }</span>
			</div>
			<div class="panel">
				<form action="../downloadG" method="post">
					<table id="send-list" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th width="90"><label><input type="checkbox">
										全选</label></th>
								<th width="110">学号</th>
								<th width="240">班级姓名</th>
								<th width="360">文件</th>
								<th width="130">提交日期</th>
								<th width="150">评分</th>
							</tr>
						</thead>
						<tbody class="one-line">
							<%
		int sum_row = done.size();
		int page_count = 1;
		int row = 10;
		int sum_page = 1;
		String pc = request.getParameter("page_count");
		if(pc != null) {
			page_count = Integer.parseInt(pc);
		}	
		int start = row * (page_count - 1) + 1;
		if(done != null) {
			for(int i = 1; start <= (row * page_count) && start <= sum_row; start++, i++) {
				Check ck = (Check)done.get(start - 1);
				pageContext.setAttribute("itdone", done.get(start-1));
%>
							<tr>
								<td class="choose"><input type="checkbox"
									value="${itdone.path }/${itdone.stkname}" name="<%=i %>"></td>
								<td class="id">${itdone.sid }</td>
								<td class="className">${itdone.cname }${itdone.sname }</td>
								<td class="file">${itdone.stkname }</td>
								<td class="date">${itdone.date }</td>
								<td>
									<div class="score">
										<ul>
											<%
								if(ck.getScore()==0){
								%>
											<li title="很差"></li>
											<li title="及格"></li>
											<li title="一般"></li>
											<li title="良好"></li>
											<li title="优秀"></li>
											<%
								}else if(ck.getScore()==1){
								%>
											<span>很差</span>
											<%
								}else if(ck.getScore()==3){
								%>
											<span>及格</span>
											<%
								}else if(ck.getScore()==5){
								%>
											<span>一般</span>
											<%
								}else if(ck.getScore()==8){
								%>
											<span>良好</span>
											<%
								}else if(ck.getScore()==10){
								%>
											<span>优秀</span>
											<%
								}
							    %>
										</ul>
									</div>
								</td>
							</tr>
							<%
		}
	}
%>
						</tbody>
					</table>
					<%
		   	if(done.size() > 0) {
		  	%>
					<div class="download">
						<input type="submit" value="download" id="downloadG">
					</div>
					<%
		   	}
			%>
				</form>
				<%
	sum_page = (sum_row + row - 1) / row;
	if(sum_row >= 1) {
%>
				<div class="tfoot">
					<!-- 分页 -->
					<ul>
						<li><input type="button" onClick="openPage(1, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == 1 ? "disabled" : ""%> value="首页"></li>
						<li><input type="button" onClick="openPage(<%=sum_page%>, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == sum_page ? "disabled" : ""%> value="尾页"></li>
						<li><input type="button"
							onClick="openPage(<%=page_count - 1%>, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == 1 ? "disabled" : ""%> value="Prev"></li>
						<li style="font: 14px sans-serif;"><%=page_count%>&nbsp;/&nbsp;<%=sum_page%></li>
						<li><input type="button"
							onClick="openPage(<%=page_count + 1%>, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == sum_page ? "disabled" : ""%> value="Next"></li>
					</ul>
				</div>
				<%	
	}
%>
			</div>
			<div class="a-total">
				<b>已提交:<%=sum_row %>/<%=allmember %></b>
			</div>
			<div class="b-total">
				<b>未提交者名单:</b>
				<ul>
					<%
	Iterator<Student> fnotdo = notdo.iterator();
		while(fnotdo.hasNext()) {
			pageContext.setAttribute("fnotdostu", fnotdo.next());

%>
					<li>${fnotdostu.sname }&nbsp;</li>
					<%
		}
%>
				</ul>
			</div>
		</div>
		<%
		}else {
			Iterator<Task> itok = listok.iterator();
			Task tk = null;
			while(itok.hasNext()) {
				tk = (Task)itok.next();
				if(tk.getTkcodes().equals(tkcodes)) {
					pageContext.setAttribute("ftkok", tk);
					break;
				}
			}	
%>
		<%
			GatherDAOImpl gbi = new GatherDAOImpl();
			List<Check> done = gbi.executeQueryCheckByTkcodes(tk.getTkcodes(),1);
			List<Student> notdo = gbi.executeQueryCheckNotDone(tk.getTkcodes(), 0);
			int allmember = gbi.executeQueryClassMember(tk.getCid());
	%>
		<div id="showContent">
			<!-- 此模块为点击进入已完成作业码时显示的页面信息 -->
			<div class="task-title">
				<h4>${ftkok.tktitle }</h4>
				<span></span>
			</div>
			<div class="panel">
				<form action="../downloadG" method="post">
					<table id="send-list" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th width="90"><label><input type="checkbox">
										全选</label></th>
								<th width="110">学号</th>
								<th width="240">班级姓名</th>
								<th width="360">文件</th>
								<th width="130">提交日期</th>
								<th width="150">评分</th>
							</tr>
						</thead>
						<tbody class="one-line">
							<%
			int sum_row = done.size();
			int page_count = 1;
			int row = 10;
			int sum_page = 1;
			String pc = request.getParameter("page_count");
			if(pc != null) {
				page_count = Integer.parseInt(pc);
			}
			int start = row * (page_count - 1) + 1;
			if(done != null) {
				for(int i = 1; start <= (row * page_count) && start <= sum_row; start++, i++) {
					Check ck = (Check)done.get(start - 1);
					pageContext.setAttribute("itdone", done.get(start - 1));
	%>
							<tr>
								<td class="choose"><input type="checkbox"
									value="${itdone.path }/${itdone.stkname}" name="<%=i %>"></td>
								<td class="id">${itdone.sid }</td>
								<td class="className">${itdone.cname }${itdone.sname }</td>
								<td class="file">${itdone.stkname }</td>
								<td class="date">${itdone.date }</td>
								<td>
									<div class="score">
										<ul>
											<%
									if(ck.getScore()==0){
									%>
											<li title="很差"></li>
											<li title="及格"></li>
											<li title="一般"></li>
											<li title="良好"></li>
											<li title="优秀"></li>
											<%
									}else if(ck.getScore()==1){
									%>
											<span>很差</span>
											<%
									}else if(ck.getScore()==3){
									%>
											<span>及格</span>
											<%
									}else if(ck.getScore()==5){
									%>
											<span>一般</span>
											<%
									}else if(ck.getScore()==8){
									%>
											<span>良好</span>
											<%
									}else if(ck.getScore()==10){
									%>
											<span>优秀</span>
											<%
									}
								    %>
										</ul>
									</div>
								</td>
							</tr>
							<%
			}
		}
	%>
						</tbody>
					</table>
					<%
		   	if(done.size() > 0) {
		  	%>
					<div class="download">
						<input type="submit" value="download" id="downloadG">
					</div>
					<%
		   	}
			%>
				</form>
				<%
		sum_page = (sum_row + row - 1) / row;
		if(sum_row >= 1) {
	%>
				<div class="tfoot">
					<!-- 分页 -->
					<ul>
						<li><input type="button" onClick="openPage(1, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == 1 ? "disabled" : ""%> value="首页"></li>
						<li><input type="button" onClick="openPage(<%=sum_page%>, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == sum_page ? "disabled" : ""%> value="尾页"></li>
						<li><input type="button"
							onClick="openPage(<%=page_count - 1%>, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == 1 ? "disabled" : ""%> value="Prev"></li>
						<li style="font: 14px sans-serif;"><%=page_count%>&nbsp;/&nbsp;<%=sum_page%></li>
						<li><input type="button"
							onClick="openPage(<%=page_count + 1%>, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == sum_page ? "disabled" : ""%> value="Next"></li>
					</ul>
				</div>
				<%	
		}
	%>
			</div>
			<div class="a-total">
				<b>已提交:<%=sum_row %>/<%=allmember %></b>
			</div>
			<div class="b-total">
				<b>未提交者名单:</b>
				<ul>
					<%
		Iterator<Student> fnotdo = notdo.iterator();
			while(fnotdo.hasNext()) {
				pageContext.setAttribute("fnotdostu", fnotdo.next());

	%>
					<li>${fnotdostu.sname }&nbsp;</li>
					<%
			}
	%>
				</ul>
			</div>
		</div>
		<%
		}
%>
		<%		
	}else {
		if(listnot.size() != 0) {
			GatherDAOImpl gdi = new GatherDAOImpl();
			List<Check> done = gdi.executeQueryCheckByTkcodes(listnot.get(0).getTkcodes(), 1);
			String cutoff = gdi.executeQueryCutoff(listnot.get(0).getTkcodes());
			List<Student> notdostu = gdi.executeQueryCheckNotDone(listnot.get(0).getTkcodes(), 0);
			int allmember = gdi.executeQueryClassMember(listnot.get(0).getCid());
			
			pageContext.setAttribute("ftknot", listnot.get(0));
			if(done.size() > 0) {
				pageContext.setAttribute("fdone", done.get(0));
			}
%>
		<div id="showContent">
			<!-- 此模块为点击进入（未传入参数时所默认显示的未完成页面信息） -->
			<div class="task-title">
				<h4>${ftknot.tktitle }</h4>
				<%
			if(done.size() > 0) {
			%>
				<span>截止时间：${fdone.cutoff }</span>
				<%
			}else {
		    %>
				<span>截止时间：<%=cutoff %></span>
				<%
			}
		    %>
			</div>
			<div class="panel">
				<form action="../downloadG" method="post">
					<table id="send-list" cellspacing="0" cellpadding="0">
						<thead>
							<tr>
								<th width="90"><label><input type="checkbox">
										全选</label></th>
								<th width="110">学号</th>
								<th width="240">班级姓名</th>
								<th width="360">文件</th>
								<th width="130">提交日期</th>
								<th width="150">评分</th>
							</tr>
						</thead>
						<tbody class="one-line">
							<%
		int sum_row = done.size();
		int page_count = 1;
		int row = 10;
		int sum_page = 1;
		String pc = request.getParameter("page_count");
		if(pc != null) {
			page_count = Integer.parseInt(pc);
		}
		int start = row * (page_count - 1) + 1;
		if(done != null) {
			for(int i = 1; start <= (row * page_count) && start <= sum_row; start++, i++) {
				Check ck = (Check)done.get(start - 1);
				pageContext.setAttribute("itdone", done.get(start - 1));
%>
							<tr>
								<td class="choose"><input type="checkbox"
									value="${itdone.path }/${itdone.stkname}" name="<%=i %>"></td>
								<td class="id">${itdone.sid }</td>
								<td class="className">${itdone.cname }${itdone.sname }</td>
								<td class="file">${itdone.stkname }</td>
								<td class="date">${itdone.date }</td>
								<td>
									<div class="score">
										<ul>
											<%
								if(ck.getScore()==0){
								%>
											<li title="很差"></li>
											<li title="及格"></li>
											<li title="一般"></li>
											<li title="良好"></li>
											<li title="优秀"></li>
											<%
								}else if(ck.getScore()==1){
								%>
											<span>很差</span>
											<%
								}else if(ck.getScore()==3){
								%>
											<span>及格</span>
											<%
								}else if(ck.getScore()==5){
								%>
											<span>一般</span>
											<%
								}else if(ck.getScore()==8){
								%>
											<span>良好</span>
											<%
								}else if(ck.getScore()==10){
								%>
											<span>优秀</span>
											<%
								}
							    %>
										</ul>
									</div>
								</td>
							</tr>
							<%
			}
		}
%>
						</tbody>
					</table>
					<%
		   if(done.size() > 0) {
		   %>
					<div class="download">
						<input type="submit" value="download" id="downloadG">
					</div>
					<%
		   }
		   %>
				</form>
				<%
	sum_page = (sum_row + row - 1) / row;
	if(sum_row >= 1) {
%>
				<div class="tfoot">
					<!-- 分页 -->
					<ul>
						<li><input type="button" onClick="openPage(1, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == 1 ? "disabled" : ""%> value="首页"></li>
						<li><input type="button" onClick="openPage(<%=sum_page%>, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == sum_page ? "disabled" : ""%> value="尾页"></li>
						<li><input type="button"
							onClick="openPage(<%=page_count - 1%>, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == 1 ? "disabled" : ""%> value="Prev"></li>
						<li style="font: 14px sans-serif;"><%=page_count%>&nbsp;/&nbsp;<%=sum_page%></li>
						<li><input type="button"
							onClick="openPage(<%=page_count + 1%>, ${itdone.tkcodes}, ${itdone.status})"
							<%=page_count == sum_page ? "disabled" : ""%> value="Next"></li>
					</ul>
				</div>
				<%	
	}
%>
			</div>
			<div class="a-total">
				<b>已提交:<%=sum_row %>/<%=allmember %></b>
			</div>
			<div class="b-total">
				<b>未提交者名单:</b>
				<ul>
					<%
	Iterator<Student> fnotdo = notdostu.iterator();
		while(fnotdo.hasNext()) {
			pageContext.setAttribute("fnotdostu", fnotdo.next());

%>
					<li>${fnotdostu.sname }&nbsp;</li>
					<%
		}
%>
				</ul>
			</div>
		</div>
		<%
		}
	}
}
%>
	</div>
	<script type="text/javascript" src="../js/home.js"></script>
	<script type="text/javascript" src="../js/gather.js"></script>
</body>
</html>