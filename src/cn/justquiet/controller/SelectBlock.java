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

public class SelectBlock extends HttpServlet{
	
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
		String type = request.getParameter("type");
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Student> liststu = (List<Student>)session.getAttribute("student");
		BlockDAOImpl bbs = new BlockDAOImpl();
		QuestionDAOImpl qbi = new QuestionDAOImpl();
		int qtype = qbi.executeQueryQstType(type);
		List<Question> listqst = bbs.executeQueryQuestion(qtype);
		session.setAttribute("question",listqst);
		if(liststu!=null)
			response.sendRedirect("student/community.jsp?page_count=1");
		else
			response.sendRedirect("teacher/community.jsp?page_count=1");	
	}
	
}
