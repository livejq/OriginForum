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
import cn.justquiet.util.ConvertUtils;

public class HandleStuAnswer extends HttpServlet {

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request,response);
	}
	
	/**
	 * 处理Post请求
	 * 当用户点击回答按钮来给出问题的见解时，首先取出用户在textarea中输入的answer数据和该问题的编号还有index显示索引，然后根据从session中取出的该回答问题的学生个人信息，
	 * 设置一个新的答案模板，接着将这个数据模板放入tb_ans数据表中,接着还得根据该问题对tb_qst数据表进行一次更新，更新其中的回答数（也就是加1），最后回到对应的该问题的显示页面中
	 * 
	 * @param request 接收用户请求
	 * @param response 响应用户请求
	 * 
	 * @exception IOException
	 * @exception ServletException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String answer = request.getParameter("answer");// 此传过来的数据的长度最大是多少？？？？？？？
		int qid = Integer.parseInt(request.getParameter("qid"));
		String index = request.getParameter("index");
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Student> liststu = (List<Student>) session.getAttribute("student");
		Student stu = liststu.get(0);
		Answer as = new Answer();
		String date = ConvertUtils.getTime();
		AnswerDAOImpl adi = new AnswerDAOImpl();
		as.setQid(qid);
		as.setNickname(stu.getSnickname());
		String slevel = String.valueOf(stu.getSlevel());
		as.setLevel(slevel);
		as.setDate(date);
		as.setAnswer(answer);
		if(adi.executeSetAnswer(as)) {
			QuestionDAOImpl qbi = new QuestionDAOImpl();
			if(qbi.executeSetQstAnswer(qid, true)) {
				request.setAttribute("index", index);
				request.setAttribute("qid", qid);
				request.getRequestDispatcher("viewAnswer").forward(request, response);
			}else {
				response.sendRedirect("student/solution.jsp?index="+index);
			}
		}else {
			response.sendRedirect("student/solution.jsp?index="+index);
		}
	}
	
}
