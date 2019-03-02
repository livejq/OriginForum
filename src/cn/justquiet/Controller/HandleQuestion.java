package cn.justquiet.Controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.justquiet.Beans.Question;
import cn.justquiet.Beans.Student;
import cn.justquiet.ServiceImpl.QuestionBusinessImpl;
import cn.justquiet.Utils.ConvertUtils;

public class HandleQuestion extends HttpServlet{
	
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
		QuestionBusinessImpl qbi = new QuestionBusinessImpl();
		int qtype = qbi.QueryQstType(major);
		jq.setQtype(qtype);
		jq.setNickname(stu.getSnickname());
		jq.setSlevel(stu.getSlevel());
		jq.setDate(date);
		jq.setTitle(title);
		jq.setDetails(inputContent);
		jq.setCodes(inputCodes);
		int qid = qbi.SetQuestion(jq);
		String askmess = "";
		if(qbi.SetStuQstId(stu.getSid(), qid)) {
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
