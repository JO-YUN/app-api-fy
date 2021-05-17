package com.mscs.app.common.dto.resp;

import java.util.List;
import java.util.Map;

import com.mscs.app.web.model.NewsInfoNH;


public class RespNewsInfoNHDto extends NewsInfoNH{

	private static final long serialVersionUID = 1L;
	private String publishUser;//出版作者
	private String publishUserName;//出版作者
	private List<Map<String, String>> list_file_pic;//图片
	private List<Map<String, String>> list_file_attachment;//附件
	private String typeName;//类别名称
	public String getPublishUser() {
		return publishUser;
	}
	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}
	public String getPublishUserName() {
		return publishUserName;
	}
	public void setPublishUserName(String publishUserName) {
		this.publishUserName = publishUserName;
	}
	public List<Map<String, String>> getList_file_pic() {
		return list_file_pic;
	}
	public void setList_file_pic(List<Map<String, String>> list_file_pic) {
		this.list_file_pic = list_file_pic;
	}
	public List<Map<String, String>> getList_file_attachment() {
		return list_file_attachment;
	}
	public void setList_file_attachment(List<Map<String, String>> list_file_attachment) {
		this.list_file_attachment = list_file_attachment;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
