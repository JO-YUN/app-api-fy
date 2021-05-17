package com.mscs.app.web.model;

import java.math.BigDecimal;
/**
 * 
* @title: News 
* @description：新闻
 */
public class News {
	
	private String id;//NRID	VARCHAR(50)	内容ID；主键
	private String columnid;//	LMID	VARCHAR(50)	栏目ID
	private String title;//BT;标题
	private String state;//ZT;状态；Y发布N编辑
	private String editTime;//BJSJ;编辑时间
	private String editUserid;//BJYHID;编辑用户ID
	private String publishTime;//FBSJ;发布时间
	private String publishUserid;//FBYHID;发布用户ID
	private String source;//LY	VARCHAR(100)	来源
	private String topState;//ZDZT	VARCHAR(1)	置顶状态；N：不置顶；Y：置顶
	private String topOrderNumber;//ZDXH	VARCHAR(5)	置顶序号
	private String filePath;//WJLJ1;文件路径1
	private String pic;//WJ1;图片
	private String attachmentPath;//WJLJ2;附件路径2
	private String attachmentfile;//WJ2;附件2
	private String context;//NR;内容
	private String readTimes;//llcs;浏览次数
	
	public String getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(String readTimes) {
		this.readTimes = readTimes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getColumnid() {
		return columnid;
	}
	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	
	
	public String getEditUserid() {
		return editUserid;
	}
	public void setEditUserid(String editUserid) {
		this.editUserid = editUserid;
	}
	public String getPublishUserid() {
		return publishUserid;
	}
	public void setPublishUserid(String publishUserid) {
		this.publishUserid = publishUserid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTopState() {
		return topState;
	}
	public void setTopState(String topState) {
		this.topState = topState;
	}
	public String getTopOrderNumber() {
		return topOrderNumber;
	}
	public void setTopOrderNumber(String topOrderNumber) {
		this.topOrderNumber = topOrderNumber;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	public String getAttachmentfile() {
		return attachmentfile;
	}
	public void setAttachmentfile(String attachmentfile) {
		this.attachmentfile = attachmentfile;
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	
	
	
	
}
