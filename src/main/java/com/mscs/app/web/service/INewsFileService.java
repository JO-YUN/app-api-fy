package com.mscs.app.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.mscs.app.common.util.Result;



public interface INewsFileService {
	// 文件上上传
	String upload(MultipartFile file, Map<String, String> map_request)throws Exception;
	// 图片上传
	String serverUpload(MultipartFile file,Map<String, String> map_request)throws Exception;
	//查询文件列表
	List<Map<String, String>> queryAttrList(Map<String, String> map_request);
	//查询无效的
	List<Map<String, String>> queryStateInvalidList(); 
	//根据文件ids组 更新文件状态    1为有效， 0为无效（是删除的对象）
	public Result updateFileStateByIds(String ids,String state);
	//根据context图文编辑器内容查找ids组，更新文件状态    1为有效， 0为无效（是删除的对象）
	public Result updateFileStateByContext(String context,String state);
	//根据AttachId删除数据库表数据
	public Result deleteStateInvalidByAttachId(String attachId);
}
