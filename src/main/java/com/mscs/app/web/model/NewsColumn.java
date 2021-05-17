package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

/**
 * 
* @title: NewsColumn 
* @description：新闻栏目
 */
public class NewsColumn extends BIZModel{

	private String id;//LMID栏目ID
	private String columnName;//mc名称
	private String level;//jb级别 1：一级；2：二级
	private String prentid;//sjlmid上级栏目ID
	private String orderNumber;//xh序号
	private String state;//zt状态N：停用；Y：使用
	private String releaseState;//fbzt状态N：编辑；Y：发布
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getReleaseState() {
		return releaseState;
	}
	public void setReleaseState(String releaseState) {
		this.releaseState = releaseState;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPrentid() {
		return prentid;
	}
	public void setPrentid(String prentid) {
		this.prentid = prentid;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
