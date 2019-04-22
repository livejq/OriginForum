package cn.justquiet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.justquiet.bean.Student;
import cn.justquiet.bean.Teacher;
import cn.justquiet.factory.PersonDAOFactory;

/**
 * 对于不同身份用户的登录做分别的处理
 * 
 */
public class InitPerson extends HttpServlet {

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
	 * @param request 登录请求
	 * @param response 响应登录请求
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
	 * 首先判断用户身份，之后分别验证用户名和密码输入是否正确，若错误，则回到登录页面并显示错误信息；若正确并且身份为学生，则查询并保存此学生的个人信息，然后跳转到
	 * 另一个Servlet来查询其对应的老师所派发的作业任务。若正确并且身份为老师，则查询并保存此老师的个人信息，然后跳转到另一个Servlet来查询并保存此老师所教班级的学生
	 * 完成作业任务的情况
	 * 
	 * @param request 登录请求
	 * @param response 响应登录请求
	 * 
	 * @exception IOException
	 * @exception ServletException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String id = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		HttpSession session = request.getSession();
		String mess = "";
		if(role.equals("学生")) {
			int check = PersonDAOFactory.getPersonDAOInstance().isStudent(id, password);
			if(check==0) {
				mess = "用户名和密码不对！";
				request.setAttribute("loginmess", mess);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else if(check==1) {
				mess = "用户名不对！";
				request.setAttribute("loginmess", mess);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else if(check==2) {
				mess = "密码不对！";
				request.setAttribute("loginmess", mess);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else {
				try {
					List<Student> liststu = new ArrayList<Student>();
					liststu = PersonDAOFactory.getPersonDAOInstance().executeQueryStuById(id);
					session.setAttribute("student",liststu);
					request.getRequestDispatcher("obligatory").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			int check = PersonDAOFactory.getPersonDAOInstance().isTeacher(id, password);
			if(check==0) {
				mess = "用户名和密码不对！";
				request.setAttribute("loginmess", mess);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else if(check==1) {
				mess = "用户名不对！";
				request.setAttribute("loginmess", mess);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else if(check==2) {
				mess = "密码不对！";
				request.setAttribute("loginmess", mess);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else {
				try {
					List<Teacher> listtea = new ArrayList<Teacher>();
					listtea = PersonDAOFactory.getPersonDAOInstance().executeQueryTeaById(id);
					session.setAttribute("teacher",listtea);
					request.getRequestDispatcher("gather").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
