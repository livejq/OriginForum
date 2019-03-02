package cn.justquiet.Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadGather extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException ,IOException {
		String[] path = new String[11];
		for(int i=1;i<=10;i++) {
			path[i] = request.getParameter(String.valueOf(i));
		}
		for(int i = 1;i<=10;i++) {
			if(path[i]==null)continue;
					String fileName = path[i].substring(path[i].lastIndexOf("/")+1);
//					String userAgent = request.getHeader("User-Agent");
//					 // 针对IE或者以IE为内核的浏览器：
//			        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
//			        	fileName = URLEncoder.encode(fileName, "UTF-8");
//			        } else {
//			            // 非IE浏览器的处理：
//			        	fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
//			        }
					response.setContentType("application/x-msdownload");
					response.setHeader("Content-disposition", "attachment;filename="+fileName);
				   try {
					   File f = new File(path[i]);
					   FileInputStream in = new FileInputStream(f);
					   OutputStream out = response.getOutputStream();
					   byte[] b = new byte[1024*10];
					   int n = -1;
					   while((n=in.read(b))!=-1) {
						   out.write(b,0,n);
					   }
					   in.close();
					   out.close();
				   }catch(IOException e) {
					   e.printStackTrace();
				   }
		}
	}
}
