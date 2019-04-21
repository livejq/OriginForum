package cn.justquiet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.justquiet.bean.Answer;
import cn.justquiet.bean.Student;
import cn.justquiet.daoimpl.AnswerDAOImpl;
import cn.justquiet.daoimpl.QuestionDAOImpl;

public class ViewAnswer extends HttpServlet{
	
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
		String index = request.getParameter("index");
		int qid = Integer.parseInt(request.getParameter("qid"));
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Student> liststu = (List<Student>)session.getAttribute("student");
		QuestionDAOImpl qbi = new QuestionDAOImpl();
		int answer = qbi.executeQueryQstAnswer(qid);
		if(answer>0) {
			AnswerDAOImpl abi = new AnswerDAOImpl();
			List<Answer> listans = abi.executeQueryAnsByQid(qid);
			session.setAttribute("answer",listans);
			if(liststu!=null)
				response.sendRedirect("student/solution.jsp?index="+index);
			else
				response.sendRedirect("teacher/solution.jsp?index="+index);
		}else {
			if(liststu!=null)
				response.sendRedirect("student/solution.jsp?index="+index);
			else
				response.sendRedirect("teacher/solution.jsp?index="+index);
		}
	}
	
}
