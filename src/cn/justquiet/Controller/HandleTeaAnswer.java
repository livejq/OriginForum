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
import cn.justquiet.Beans.Teacher;
import cn.justquiet.ServiceImpl.AnswerBusinessImpl;
import cn.justquiet.ServiceImpl.QuestionBusinessImpl;
import cn.justquiet.Utils.ConvertUtils;

public class HandleTeaAnswer extends HttpServlet{
	
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
		String answer = request.getParameter("answer");//此传过来的数据的长度最大是多少？？？？？？？
		int qid = Integer.parseInt(request.getParameter("qid"));
		String index = request.getParameter("index");
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Teacher> listtea = (List<Teacher>) session.getAttribute("teacher");
		Teacher tea = listtea.get(0);
		Answer as = new Answer();
		String date = ConvertUtils.getTime();
		AnswerBusinessImpl abi = new AnswerBusinessImpl();
		as.setQid(qid);
		as.setNickname(tea.getTnickname());
		String tlevel = tea.getTlevel();
		as.setLevel(tlevel);
		as.setDate(date);
		as.setAnswer(answer);
		if(abi.SetAnswer(as)) {
			QuestionBusinessImpl qbi = new QuestionBusinessImpl();
			if(qbi.SetQstAnswer(qid, true)) {
				request.setAttribute("index", index);
				request.setAttribute("qid", qid);
				request.getRequestDispatcher("viewAnswer").forward(request, response);
			}
			else
				response.sendRedirect("teacher/solution.jsp?index="+index);
		}else {
			response.sendRedirect("teacher/solution.jsp?index="+index);
		}
	}
	
}
