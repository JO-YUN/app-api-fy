package com.mscs.app.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.mscs.app.common.util.Result;


/**
 * 
 * @date:   2018年10月16日 
 * @author: LiuGuoHui 
 *
 */
public interface FileService {
	/**
	 * 
	* @MethodName: upload 
	* @description : 文件上上传
	* @param file  文件
	* @param map_request 传入参数
	* @return String
	 */
	String upload(MultipartFile file, Map<String, String> map_request)throws Exception;
	/**
	 * 
	* @MethodName: queryFileInlfoList 
	* @description : 查询文件列表
	* @param map_request
	* @return List<Map<String,String>>
	 */
	List<Map<String, String>> queryAttrList(Map<String, String> map_request);
    //查询无效的图片
	List<Map<String, String>> queryStateInvalidList(); 
	
	//根据文件ids组 更新文件状态    1为有效， 0为无效（是删除的对象）
	public Result updateFileStateByIds(String ids,String state);
	//根据context图文编辑器内容查找ids组，更新文件状态    1为有效， 0为无效（是删除的对象）
	public Result updateFileStateByContext(String context,String state);
	//根据AttachId删除数据库表数据
	public Result deleteStateInvalidByAttachId(String attachId);
}
