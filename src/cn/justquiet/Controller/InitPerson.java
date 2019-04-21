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

public class InitPerson extends HttpServlet {
	
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
