package cn.justquiet.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
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
import cn.justquiet.bean.Task;
import cn.justquiet.bean.Teacher;
import cn.justquiet.daoimpl.TaskDAOImpl;
import cn.justquiet.exception.RuntimeException;
import cn.justquiet.factory.PersonDAOFactory;
import cn.justquiet.util.ConvertUtils;

public class HandleTask extends HttpServlet{
	/**
	 * 上传和下载根目录
	 */
	private static final String parentDir = "I:/data";

	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doPost(request,response);
	}
	
	@SuppressWarnings("static-access")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
		servletFileUpload.setHeaderEncoding("UTF-8");
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Teacher> listtea = (List<Teacher>) session.getAttribute("teacher");
		Teacher tea = listtea.get(0);
		Task tk = new Task();
		tk.setTid(tea.getTid());
		tk.setTname(tea.getTname());
		String date = ConvertUtils.getTime();
		tk.setDate(date);
		TaskDAOImpl tbi = new TaskDAOImpl();
		List<Student> liststu = null;
		int tid = tea.getTid();
		String tkcodes = null;
		String cname = null;
		int deadline = 0;
		String cutoff = null;
		while(true) {
			tkcodes = ConvertUtils.getRandom(8);
			if(!tbi.executeQueryTkcodes(tkcodes)) {
				tk.setTkcodes(tkcodes);
				break;
			}
		}
		if(!servletFileUpload.isMultipartContent(request)) {
			try {
				throw new RuntimeException("此请求不是多数据的上传！");// 加不加没差（前台已经有束缚）
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
					if(fieldName.equals("inputTitle")) {
						tk.setTktitle(fieldValue);
					}else if(fieldName.equals("inputContent")) {
						tk.setTkcontent(fieldValue);
					}else if(fieldName.equals("s-class")){
						int cid = tbi.executeQueryCidByClassname(fieldValue);
						tk.setCid(cid);
						cname = fieldValue;
						liststu = PersonDAOFactory.getPersonDAOInstance().executeQueryStuByCid(cid);
					}else if(fieldName.equals("deadline")) {
						deadline = Integer.parseInt(fieldValue);
						tk.setDeadline(deadline);
					}
				}else {
					String fileName = fileItem.getName();
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);// IE浏览器:C:\Users\xxx\Desktop\abc.txt;其它：abc.txt
					tk.setAttach(fileName);
					tk.setPath(parentDir);
					InputStream in = fileItem.getInputStream();
					File file = new File(parentDir, fileName);
					if(!file.getParentFile().exists()) {
						file.getParentFile().mkdirs();// mkdirs与mkdir不同的是如果上级目录没有的话也一并创建
					}
					FileOutputStream out = new FileOutputStream(file);
//					long sizeInBytes = fileItem.getSize();// long型的数据不超过2的31次方时强制类型转换int没问题，否则会转换后数据会不正确的，数组不可以转换
					byte[] buf = new byte[1024*1024];//缓存大小
					int len = -1;
					while((len = in.read(buf))!=-1) {
						out.write(buf, 0, len);
					}
					out.close();
					in.close();
					if(tbi.executeSetTask(tk)) {
						String taskmess = "发布成功！";
						taskmess = URLEncoder.encode(taskmess,"UTF-8");
						response.sendRedirect("teacher/task.jsp?taskmess="+taskmess);
					}else {
						String taskmess = "发布失败！请检查网络连接状态";
						taskmess = URLEncoder.encode(taskmess,"UTF-8");
						response.sendRedirect("teacher/task.jsp?taskmess="+taskmess);
					}
				}
			}// 输入流->源；输出流->目的地
			cutoff = ConvertUtils.getCutoffTimeDirect(new Date(), deadline);
			if(liststu!=null){
				Iterator<Student> iterator = liststu.iterator();
				while(iterator.hasNext()){
					Check ck = new Check();
					Student stu = (Student) iterator.next();
					ck.setTid(tid);
					ck.setTkcodes(tkcodes);
					ck.setCname(cname);
					ck.setSid(stu.getSid());
					ck.setSname(stu.getSname());
					ck.setCutoff(cutoff);
					tbi.executeSetClassTask(ck);
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
