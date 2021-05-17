package com.mscs.app.web.controller;
 
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mscs.app.common.util.PropertiesUtil;
import com.mscs.app.web.service.INewsFileService;




	@Controller
	@RequestMapping("NewsFileService")
	public class NewsFileController {
	public static Logger logger = LoggerFactory.getLogger(NewsFileController.class);
	@Autowired
	private INewsFileService 	newsFileService;

	//文件上传功能
	@RequestMapping("upload")
	public void upload(HttpServletResponse response, @RequestParam  MultipartFile upfile,@RequestParam Map<String,String>  map_request ) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		//设置文件保存路径
		map_request.put("code", "allfile");
		map_request.put("url", "/NewsFileService/showImg.do?attachId=");
		map_request.put("downloadUrl", "/NewsFileService/download.do?attachId=");
		String  json=newsFileService.upload(upfile,map_request);
		response.getWriter().write(json);
		response.getWriter().close();
	}
	/**
	 * 图片上传
	 * @Title: serverUpload 
	 * @param response
	 * @param upfile
	 * @param map_request
	 * @throws Exception  
	 * @author: Mr.Zhao
	 */
	@RequestMapping("serverUpload")
	public void serverUpload(HttpServletResponse response, @RequestParam  MultipartFile upfile,@RequestParam Map<String,String>  map_request ) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		//设置文件保存路径
		map_request.put("code", "img");
		map_request.put("url", "/NewsFileService/showImg.do?attachId=");
		String  json=newsFileService.serverUpload(upfile,map_request);
		response.getWriter().write(json);
		response.getWriter().close();
	}
	/**
	 * 
	* @MethodName: download 
	* @description : 文件下载
	* @author：lijingyu
	* @date： 2018年1月15日 上午10:54:07
	* @param response
	* @param obj
	* @throws Exception void
	 */
	@RequestMapping("download")
	public void  download(HttpServletResponse response,  @RequestParam Map<String,String>  map_request,String modid) throws  Exception {
		String filePath="";
		String oldName="";
		if(!"".equals(map_request.get("attachId"))) {
			//查询数据
			List<Map<String,String>> list=newsFileService.queryAttrList(map_request);
			Map<String,String> map_result=list.get(0);
			filePath= map_result.get("physicalPath");
			oldName=map_result.get("originalName");
		}else {
			Properties  properties=PropertiesUtil.getKey("system.properties");
			String rootDir=properties.getProperty("baseDir");
			filePath=rootDir+map_request.get("relativePath");
			oldName=map_request.get("originalName");
			
			
		}
		
 		//下载文件
		File file = new File(filePath);
		if(file.exists()){
				response.setHeader("Content-Disposition", "attachment;filename="+new String(oldName.getBytes("utf-8"), "iso8859-1"));
				FileInputStream in = new FileInputStream(file);
				byte[] buffer = new byte[1000];
				int len=0;
 				while ((len = in.read(buffer)) > 0){
 					response.getOutputStream().write(buffer,0,len);
 				}
				response.getOutputStream().flush();
		}
	}
	/**
	 * 
	* @MethodName: showImg 
	* @description : 图片预览接口
	* @param response
	* @param obj
	* @throws Exception void
	 */
	@RequestMapping("showImg")
	public void showImg(HttpServletResponse response,  @RequestParam Map<String,String>  map_request)throws  Exception {
		//根据文件id查询文件对象
		System.err.println(map_request.toString());
		List<Map<String,String>> list=newsFileService.queryAttrList(map_request);
		Map<String,String> map_result = null;
		if(null!=list && list.size()>0) {
			map_result=list.get(0);
			//根路径+相对路径+文件名字
			response.setContentType("image/jpeg;charset=UTF-8"); 
			response.setHeader("Content-Disposition","inline; filename=\"" + map_result.get("originalName") + "\"");
			
		}
		OutputStream out = response.getOutputStream();  
		//2查看数据库已经存在的
		try {
			if(null!=map_result){
				// 根据图片地址构造file对象  
				File file = new File(map_result.get("physicalPath"));  
				InputStream is = new FileInputStream(file);  
				Image image = ImageIO.read(is);// 读图片  
				String imageType = map_result.get("name").substring(map_result.get("name").lastIndexOf(".") + 1);  
				RenderedImage img = (RenderedImage) image;  
				ImageIO.write(img, imageType, out);   
				is.close();
			}else {
				 logger.warn("FileNotFoundException:"+map_request.get("attachId"));
				 out.write("{\"errmsg\":\"invalid param\",\"errcode\":\"-1\"}".getBytes());
			}
		} catch (Exception e) {
			logger.warn("FileNotFoundException:"+map_result.get("physicalPath"));
			out = response.getOutputStream(); 
			out.write("{\"errmsg\":\"FileNotFoundException\",\"errcode\":\"-2\"}".getBytes());
		}  
		out.flush();  
		out.close(); 
	}
	/**返回附件列表
	 * @param map_request
	 * @return
	 */
/*	@RequestMapping("view")
	@ResponseBody
	public Result  queryAttrList(@RequestParam Map<String,String> map_request) {
		FileVo result=new FileVo();
		List<Map<String, String>> list_file = newsFileService.queryAttrList(map_request);
		result.setUpfile(list_file);
		return result;
	}*/
	

}
