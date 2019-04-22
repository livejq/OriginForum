package cn.justquiet.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.justquiet.bean.Check;
import cn.justquiet.bean.Student;
import cn.justquiet.daoimpl.TaskDAOImpl;
import cn.justquiet.exception.RuntimeException;
import cn.justquiet.util.ConvertUtils;

public class HandleSent extends HttpServlet {
	
	private static final String parentDir = "I:/data";
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
	 * 这里用到了一个第三方的文件上传commons-fileupload-1.4.jar包
	 * 当学生点击发布问题按钮时，
	 * 
	 * @param request 接收用户请求
	 * @param response 响应用户请求
	 * 
	 * @exception IOException
	 * @exception ServletException
	 */
	@Override
	@SuppressWarnings("static-access")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
		servletFileUpload.setHeaderEncoding("UTF-8");
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Student> liststu = (List<Student>) session.getAttribute("student");
		Student stu = liststu.get(0);
		Check ck = new Check();
		ck.setSid(stu.getSid());
		String date = ConvertUtils.getTime();
		ck.setDate(date);
		TaskDAOImpl tbi = new TaskDAOImpl();
		String folder = null;
		if(!servletFileUpload.isMultipartContent(request)) {
			try {
				throw new RuntimeException("此请求不是多数据的上传！");
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for(FileItem fileItem:list) {
				if(fileItem.isFormField()) {
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString("UTF-8");
					if(fieldName.equals("tkcodes")) {
						folder = fieldValue;
						ck.setTkcodes(folder);
					}
				}
			}//输入流->源；输出流->目的地
			for(FileItem fileItem: list) {
				if(!fileItem.isFormField()) {
					String fileName = fileItem.getName();
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
					ck.setStkname(fileName);
				    ck.setPath(parentDir + "/" + folder);
					InputStream in = fileItem.getInputStream();
					File file = new File(parentDir + "/" + folder, fileName);
					if(!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();//mkdirs与mkdir不同的是如果上级目录没有的话也一并创建
					}
					FileOutputStream out = new FileOutputStream(file);
					byte[] buf = new byte[1024 * 1024];//缓存大小
					int len = -1;
					while((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					out.close();
					in.close();
				}
			}
			if(tbi.executeUpdateClassTask(ck)) {
				String sentmess = "提交成功！";
			    sentmess = URLEncoder.encode(sentmess, "UTF-8");
				response.sendRedirect("student/sent.jsp?sentmess=" + sentmess);
			}else {
				String sentmess = "提交失败！请检查网络连接状态";
				sentmess = URLEncoder.encode(sentmess, "UTF-8");
				response.sendRedirect("student/sent.jsp?sentmess=" + sentmess);
			}
		}catch(FileUploadException e) {	
			e.printStackTrace();
		}
	}
	
}
