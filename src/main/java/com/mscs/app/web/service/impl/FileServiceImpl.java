package com.mscs.app.web.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mscs.app.common.util.Constant;
import com.mscs.app.common.util.PropertiesUtil;
import com.mscs.app.common.util.Result;
import com.mscs.app.common.util.ResultEntity;
import com.mscs.app.common.util.TimeUtil;
import com.mscs.app.web.dao.FileModelMapper;
import com.mscs.app.web.service.FileService;

/**
 * 
 * @ClassName:  FileServiceImpl   
 * @Description:TODO(用户信息)   
 * @date:   2018年10月16日 
 * @author: LiuGuoHui 
 *
 */

@Service("FileServiceImpl")
public class FileServiceImpl implements FileService{
	public static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	@Autowired
	private FileModelMapper fileModelMapper;
	public String upload(MultipartFile file, Map<String, String> map_request) throws Exception {
		/**定义返回参数*/
		String errmsg="上传成功";
		String jsonError="";
		//处理结果集合
		Map<String, String>  map_result=null;
		/**校验参数*/
		//0.1判断文件大小,文件不存在
		if(file.getSize()<=0){
			errmsg="无效文件";
			jsonError="{\"errorcode\":\"1\",\"errormsg\":\""+errmsg+"\"}";
			return  jsonError;
  	    }
		//0.2判断文件代码是否在参数表中配置
		/*if(!checkFileParam(map_request)) {
			errmsg="文件参数不存在，请去附件参数表配置!";
			jsonError="{\"errcode\":\"1\",\"errmsg\":\""+errmsg+"\"}";
			return  jsonError;
		}*/
		
		//0.3判断文件文件后缀
		if(!checkFileType(file.getOriginalFilename(),map_request)){
			errmsg="上传文件类型可能影响系统安全，上传失败！";
			jsonError="{\"errorcode\":\"1\",\"errormsg\":\""+errmsg+"\"}";
			return  jsonError;
		}
		/**处理参数*/
		//1.1文件上传
		map_result = transferToDir(file,map_request);
		//2.1文件入库
		map_result = transferToDb(file,map_result);
		//3.1返回参数到前台
		ObjectMapper mapper = new ObjectMapper();
		String originalName=file.getOriginalFilename();
		
		map_result.put("errorcode", "1");
		map_result.put("errormsg", errmsg);
		map_result.put("state", "SUCCESS");
		map_result.put("originalName", originalName);
		map_result.put("type", originalName.substring(originalName.lastIndexOf(".")));
		return mapper.writeValueAsString(  map_result );
	}
	//文件保存到数据库
	private Map<String, String>   transferToDb(MultipartFile file,Map<String, String>  map_request) throws IOException {
		//1.1保存数据库（上传时候作无效，保存时候要更改为有效）
		String attachId=Constant.getUUID();//主键
		Properties properties = PropertiesUtil.getKey("system.properties");
		String baseHttpPath=properties.getProperty("basePath");//系统访问路径
		
		//1.2判断是否是图片，拼凑图片路径
		String[] temp1= map_request.get("originalName").split("\\.");
		String filefix=temp1[temp1.length-1];
		int a=Constant.IMAGETYPE.indexOf(filefix.toLowerCase());
		if(a!=-1){	
			map_request.put("url",baseHttpPath+map_request.get("url")+attachId);
		}else {
			map_request.put("url","");
		}
		//1.3判断是否是图片，拼凑下载路径(下载路径为当前应用访问地址)
		
		map_request.put("size",file.getSize()+"");
		map_request.put("downloadUrl", baseHttpPath+"/FileService/download?attachId="+attachId);//下载路径（应该考虑不同服务器之间引用）
		map_request.put("state",Constant.SYS_STATE_0+"");//是否启用Y/N
		map_request.put("attachId",attachId);//是否启用Y/N
	
		fileModelMapper.saveFileInfoData(map_request);
		return map_request;
		
	}
	/**
	 * 
	* @MethodName: transferToDir 
	* @description : 文件上传
	* @param file
	* @param map_request
	* @return Map<String,String>
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	private Map<String, String> transferToDir(MultipartFile file, Map<String, String> map_request) throws  Exception {
		/**定义参数*/
		String rootDir="";
		String separator ="";
		String relativePath="";
		String suffix="";
		String name="";
		String oriName ="";
		Properties properties=null;
		File fileDir =null;
		File newFile=null;
		
		//1.1获得文件根目录配置
		properties=PropertiesUtil.getKey("system.properties");
		rootDir=properties.getProperty("baseDir");//物理根路径
		oriName= file.getOriginalFilename();//源文件 名
		//1.2获得文件分割符
		separator =PropertiesUtil.SEPARATOR;// 也就是/
		//1.3创建相对路径
		relativePath= TimeUtil.getDay();
		fileDir = new File(rootDir +separator+map_request.get("code")  +separator +relativePath );//文件夹
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		
		//1.3获得新文件名字
		suffix = oriName.substring(oriName.lastIndexOf("."));
		name = System.nanoTime() + suffix;
		//spring框架创建新文件
		newFile = new File(fileDir + separator + name);//文件夹+新名字
		newFile.createNewFile();
		file.transferTo(newFile);
		/**返回数据*/
		//1文件的物理路径
		map_request.put("physicalPath", rootDir +separator+map_request.get("code") +separator +relativePath+separator+name);
		map_request.put("originalName",oriName);//原始文件名字
		map_request.put("name",name);//时间戳名字--新文件名
		return map_request;
	}
	/**
	 * 
	* @MethodName: checkFileType 
	* @description : 判断文件类型是否通过
	* @param oriName
	* @param map_request
	* @return boolean
	 */
	private boolean checkFileType(String oriName, Map<String, String> map_request) {
		/**定义参数*/
		boolean bool=true;
		String suffix = oriName.substring(oriName.lastIndexOf("."));
		if(".exe".equals(suffix) || ".bat".equals(suffix)){bool=!bool;}
			
		/**返回参数*/
		return bool;
	}
	/**
	 * 
	* @MethodName: checkFileParam 
	* @description : 检测文件参数是否已经配置过
	* @author：LiuGuoHui 
	* @date： 2018年10月16日
	* @param map_request
	* @return boolean
	 */
	/*private boolean checkFileParam(Map<String, String> map_request) {
		*//**定义参数*//*
		//设置返回参数
		boolean bool=false;
		//查询结果
		Map<String, String> map_query=null;
		
		*//**校验参数*//*
		if(null==map_request||null==map_request.get("code") || "".equals(map_request.get("code"))) { return bool;}
		map_query=fileModelMapper.queryFileParamBycode(map_request);
		if(null!=map_query ) { bool =!bool;}
		
		*//**返回参数*//*
		return bool;
	}*/
	/**
	 * 
	* @MethodName: queryAttrList
	* @description : 查询附件列表
	* @param map_request
	* @return  List<Map<String, String>>
	 */
	public List<Map<String, String>> queryAttrList(Map<String, String> map_request) {
		String[] attrs=map_request.get("attachId").split(",");//可以是多张图片
		return fileModelMapper.queryAttrList(map_request,attrs);
	}
	//查询无效的图片
	@Override
	public List<Map<String, String>> queryStateInvalidList() {
		
		return fileModelMapper.queryStateInvalidList();
	}
	 //根据文件ids组 更新文件状态    1为有效， 0为无效（是删除的对象）
	@Override
		public Result updateFileStateByIds(String ids,String state){
		ResultEntity vo = new ResultEntity();
			try{
				String[] attrs=ids.split(",");
				fileModelMapper.updateFileStateByIds(attrs, state);
				
				vo.setErrorcode("0");
				vo.setErrormsg("操作成功");
				
			}catch(Exception e){
				e.printStackTrace();
				vo.setErrorcode("500");
				vo.setErrormsg("操作失败");
			}
			return vo;
		}
		//根据context图文编辑器内容查找ids组，更新文件状态    1为有效， 0为无效（是删除的对象）
	     @Override
		public Result updateFileStateByContext(String context,String state){
			ResultEntity vo = new ResultEntity();
			try{
				     String ids="";
						  
			        int a = context.indexOf("showImg?attachId=");//*第一个出现的索引位置
			       
			        while (a != -1) {
			            if ("".equals(ids)){
			        		 ids=context.substring(a+20, a+20+32);
			        	}else{
			        		ids=ids+","+context.substring(a+20, a+20+32);	
			        	}
			        	
			            a = context.indexOf("showImg?attachId=", a + 1);//*从这个索引往后开始第一个出现的位置
			        }
			      //  System.out.println(ids);
			        if (!"".equals(ids)){
			            String[] attrs=ids.split(",");
		                fileModelMapper.updateFileStateByIds(attrs, state);
			        }
				vo.setErrorcode("0");
				vo.setErrormsg("操作成功");
				
			}catch(Exception e){
				e.printStackTrace();
				vo.setErrorcode("500");
				vo.setErrormsg("操作失败");
			}
			return vo;
		}
	//根据AttachId删除数据库表数据
	  @Override
		public Result deleteStateInvalidByAttachId(String attachId){
			ResultEntity vo = new ResultEntity();
			try{
		    fileModelMapper.deleteStateInvalidByAttachId(attachId);
			   
				vo.setErrorcode("0");
				vo.setErrormsg("操作成功");
				
			}catch(Exception e){
				e.printStackTrace();
				vo.setErrorcode("500");
				vo.setErrormsg("操作失败");
			}
			return vo;
		}
	  
	  
}
