package cn.justquiet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.justquiet.bean.Student;
import cn.justquiet.bean.Task;
import cn.justquiet.daoimpl.TaskDAOImpl;

public class InitTask extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Student> liststu = (List<Student>) session.getAttribute("student");
		Student stu = liststu.get(0);
		TaskDAOImpl tbi = new TaskDAOImpl();
		List<Task> tknot = tbi.executeQueryTaskByCid(stu.getCid(), 0);
		List<Task> tkok = tbi.executeQueryTaskByCid(stu.getCid(), 1);
		session.setAttribute("tknot", tknot);
		session.setAttribute("tkok", tkok);
		response.sendRedirect("student/student.jsp");
	}
	
}
