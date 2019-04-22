package cn.justquiet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.justquiet.bean.Question;
import cn.justquiet.bean.Student;
import cn.justquiet.daoimpl.BlockDAOImpl;
import cn.justquiet.daoimpl.QuestionDAOImpl;

public class SelectBlock extends HttpServlet {

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
	 * @param request 接收用户Get请求
	 * @param response 响应用户Get请求
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
	 * 当点击论坛中的其中一个模块时，首先取出传过来的参数type(问题类型)，然后先通过type查找问题类型表中对应的该问题编号，然后再通过该问题编号查找所有问题返回一个问题集合，
	 * 最后从全局内置变量session中试着取出学生的信息，然后判断不为空时，则跳转到学生端的community页面中的第一页，否则跳转到老师端
	 * 
	 * @param request 接收用户内部分发请求
	 * @param response 响应用户内部分发请求
	 * 
	 * @exception IOException
	 * @exception ServletException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String type = request.getParameter("type");
		HttpSession session = request.getSession();
		
		BlockDAOImpl bbs = new BlockDAOImpl();
		QuestionDAOImpl qbi = new QuestionDAOImpl();
		int qtype = qbi.executeQueryQstType(type);
		List<Question> listqst = bbs.executeQueryQuestion(qtype);
		session.setAttribute("question", listqst);
		@SuppressWarnings("unchecked")
		List<Student> liststu = (List<Student>)session.getAttribute("student");
		if(liststu != null) {
			response.sendRedirect("student/community.jsp?page_count=1");
		}else {
			response.sendRedirect("teacher/community.jsp?page_count=1");
		}
	}
	
}
