package cn.justquiet.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExitSystem extends HttpServlet{
	
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
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doPost(request,response);
	}
	
	/**
	 * 使保存用户信息的session无效，即实现退出功能
	 * 
	 * @param request 接收用户请求
	 * @param response 响应用户请求
	 * 
	 * @exception IOException
	 * @exception ServletException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");	
	}
	
}
