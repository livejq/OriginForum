package cn.justquiet.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.justquiet.Beans.Student;
import cn.justquiet.Beans.Task;
import cn.justquiet.ServiceImpl.TaskBusinessImpl;

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
		TaskBusinessImpl tbi = new TaskBusinessImpl();
		List<Task> tknot = tbi.QueryTaskByCid(stu.getCid(), 0);
		List<Task> tkok = tbi.QueryTaskByCid(stu.getCid(), 1);
		session.setAttribute("tknot", tknot);
		session.setAttribute("tkok", tkok);
		response.sendRedirect("student/student.jsp");
	}
	
}
