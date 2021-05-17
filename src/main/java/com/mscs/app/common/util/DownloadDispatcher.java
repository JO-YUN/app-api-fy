package com.mscs.app.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DownloadDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1247979242274251018L;
	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		downloadFile(request, response);
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		downloadFile(request, response);
		
	}
	
	private void downloadFile(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String url = request.getRequestURI();

		url = url.substring(url.indexOf("Download/"));
		String[] slipts = url.split("/");
		
		if (slipts != null && slipts.length > 1) {
			
			String fileType = slipts[1];
			String filename = slipts[slipts.length-1];
			
			String filePath = "";
			if(fileType.equals("picture")){
				filePath = ApplicationConstants.get("pictureFilePath");
				filePath = filePath + url.substring("Download/picture/".length());
			}else if(fileType.equals("audio")){
				filePath = ApplicationConstants.get("audioFilePath");
				filePath = filePath + url.substring("Download/audio/".length());
			}
			OutputStream os = response.getOutputStream(); 
			try {
				InputStream is = new FileInputStream(new File(filePath));
				response.setHeader("Content-Disposition", "filename="+filename);
				response.setHeader("Content-Length",is.available()+"");
				response.setContentType("UTF-8"); 
				writeBytes(is, os); 
				
			} catch (IOException e) {
				
				response.sendRedirect("/page/download_error.jsp");
			}
		}
	}
	
	private void writeBytes(InputStream in, OutputStream out) throws IOException { 
		byte[] buffer = new byte[1024]; 
		int length = -1; 
		while ((length = in.read(buffer)) != -1) { 
		out.write(buffer, 0, length); 
		} 
		in.close(); 
		out.close(); 
	} 

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "";
		url = url.substring(url.indexOf("Download/"));
		System.out.print(url);
	}
}
