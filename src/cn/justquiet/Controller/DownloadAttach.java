package cn.justquiet.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadAttach extends HttpServlet {
	
	private static final String path = "I:/data/";
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
	 * 处理Get下载附件请求
	 * 
	 * @param request 接收用户Get请求
	 * @param response 响应用户Get请求
	 * 
	 * @exception IOException
	 * @exception ServletException
	 */
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException {
		   String str = request.getParameter("filename");
		   String endWith = str.substring(str.indexOf(".")+1);
		   response.setContentType("application/x-msdownload");
		   String filename = encodeDownloadFilename(str, request.getHeader("User-Agent"));
		   response.setHeader("Content-disposition", "attachment;filename=" + filename + "." + endWith);
		   try {
			   File f = new File(path + filename);
			   FileInputStream in = new FileInputStream(f);
			   OutputStream out = response.getOutputStream();
			   int n = -1;
			   byte[] b = new byte[1024];
			   while((n = in.read(b)) != -1) {
				   out.write(b, 0, n);
			   }
			   in.close();
			   out.close();
		   }catch(IOException e) {
			   
		   }
	}
	
	private String encodeDownloadFilename(String name, String agent) {
		try {
			// TODO Auto-generated method stub
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
	                && -1 != agent.indexOf("Trident")) {// ie
	            name = java.net.URLEncoder.encode(name, "UTF-8");
	        } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
	            name = new String(name.getBytes("UTF-8"), "ISO-8859-1");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return name;
	}
}
