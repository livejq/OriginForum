package cn.justquiet.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.justquiet.Beans.Answer;
import cn.justquiet.Beans.Student;
import cn.justquiet.ServiceImpl.AnswerBusinessImpl;
import cn.justquiet.ServiceImpl.QuestionBusinessImpl;

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
		QuestionBusinessImpl qbi = new QuestionBusinessImpl();
		int answer = qbi.QueryQstAnswer(qid);
		if(answer>0) {
			AnswerBusinessImpl abi = new AnswerBusinessImpl();
			List<Answer> listans = abi.QueryAnsByQid(qid);
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
