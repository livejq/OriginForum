package cn.justquiet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.justquiet.bean.Task;
import cn.justquiet.bean.Teacher;
import cn.justquiet.daoimpl.GatherDAOImpl;

public class InitGather extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 初始化一个Servlet，如果没有调用super.init(config)语句，
	 * 任何使用GenericServlet的getInitParameter()方法的行为或者是调用ServletConfig的方法都将会抛出NullPointerException的异常
	 * 
	 * @param config Servlet配置信息
	 * 
	 * @exception ServletException
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	/**
	 * 处理Get请求
	 * 
	 * @param request 接收老师端Get请求
	 * @param response 响应老师端Get请求
	 * 
	 * @exception IOException
	 * @exception ServletException
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doPost(request,response);
	}
	
	/**
	 * 处理Post请求
	 * 首先从全局内置变量session中取出老师的信息，然后根据老师的编号查找并保存所有学生完成任务的情况（分为已完成任务和未完成任务），然后跳转到老师首页
	 * 
	 * @param request 接收老师端内部分发请求
	 * @param response 响应老师端内部分发请求
	 * 
	 * @exception IOException
	 * @exception ServletException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Teacher> listtea = (List<Teacher>) session.getAttribute("teacher");
		Teacher tea = listtea.get(0);
		GatherDAOImpl gbi = new GatherDAOImpl();
		List<Task> tknot = gbi.executeQueryTaskByTid(tea.getTid(), 0);
		List<Task> tkok = gbi.executeQueryTaskByTid(tea.getTid(), 1);

		session.setAttribute("tknot", tknot);
		session.setAttribute("tkok", tkok);
		response.sendRedirect("teacher/teacher.jsp");
	}
	
}
