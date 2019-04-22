package cn.justquiet.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.justquiet.bean.Question;
import cn.justquiet.bean.Student;
import cn.justquiet.daoimpl.QuestionDAOImpl;
import cn.justquiet.util.ConvertUtils;

public class HandleQuestion extends HttpServlet{

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
	 * 当学生点击发布问题时，取出其中的所有输入框中的信息，包括标题、问题内容、代码片段、问题类型（专业相关），然后写入tb_qst数据表并返回一个问题编号，
	 * 将这个编号和对应的学生编号存入tb_sqst表中，最后返回发布页面并给出提示信息(成功发布或者失败)
	 * 
	 * @param request 接收用户请求
	 * @param response 响应用户请求
	 * 
	 * @exception IOException
	 * @exception ServletException
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String title = request.getParameter("title");
		String inputContent = request.getParameter("inputContent");
		String inputCodes = request.getParameter("inputCodes");
		String major = request.getParameter("major");
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Student> liststu = (List<Student>) session.getAttribute("student");
		Student stu = liststu.get(0);
		Question jq = new Question();
		String date = ConvertUtils.getTime();
		QuestionDAOImpl qbi = new QuestionDAOImpl();
		int qtype = qbi.executeQueryQstType(major);
		jq.setQtype(qtype);
		jq.setNickname(stu.getSnickname());
		jq.setSlevel(stu.getSlevel());
		jq.setDate(date);
		jq.setTitle(title);
		jq.setDetails(inputContent);
		jq.setCodes(inputCodes);
		int qid = qbi.executeSetQuestion(jq);
		String askmess = "";
		if(qbi.executeSetStuQstId(stu.getSid(), qid)) {
			askmess = "发布成功！";
			askmess = URLEncoder.encode(askmess,"UTF-8");
			response.sendRedirect("student/ask.jsp?askmess="+askmess);
		}else {
			askmess = "发布失败！请检查网络连接状态";
			askmess = URLEncoder.encode(askmess,"UTF-8");
			response.sendRedirect("student/ask.jsp?askmess="+askmess);
		}
	}
	
}
