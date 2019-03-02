package cn.justquiet.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.justquiet.Beans.Task;
import cn.justquiet.Beans.Teacher;
import cn.justquiet.ServiceImpl.GatherBusinessImpl;

public class InitGather extends HttpServlet{
	
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
		List<Teacher> listtea = (List<Teacher>) session.getAttribute("teacher");
		Teacher tea = listtea.get(0);
		GatherBusinessImpl gbi = new GatherBusinessImpl();
		List<Task> tknot = gbi.QueryTaskByTid(tea.getTid(), 0);
		List<Task> tkok = gbi.QueryTaskByTid(tea.getTid(), 1);
		
//		List<Check> done = gbi.QueryCheckByTkcodes(, 1);
//		List<Student> notdo = gbi.QueryCheckNotDone(.get(0).getTkcodes(), 0);
		session.setAttribute("tknot", tknot);
		session.setAttribute("tkok", tkok);
		response.sendRedirect("teacher/teacher.jsp");
	}
	
}
