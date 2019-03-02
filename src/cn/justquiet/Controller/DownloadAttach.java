package cn.justquiet.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadAttach extends HttpServlet{
	
	private static final String path = "I:/data/";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException {
		   String fileName = request.getParameter("filename");
		   String endWith = fileName.substring(fileName.indexOf(".")+1);
		   String str = request.getSession().getId();
		   response.setContentType("application/x-msdownload");
		   response.setHeader("Content-disposition", "attachment;filename="+str+"."+endWith);
		   try {
			   File f = new File(path+fileName);
			   FileInputStream in = new FileInputStream(f);
			   OutputStream out = response.getOutputStream();
			   int n = -1;
			   byte[] b = new byte[1024];
			   while((n=in.read(b))!=-1) {
				   out.write(b,0,n);
			   }
			   in.close();
			   out.close();
		   }catch(IOException e) {
			   
		   }
	}
}
