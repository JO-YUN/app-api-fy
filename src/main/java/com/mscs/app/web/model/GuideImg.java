package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

public class GuideImg extends BIZModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2399002299347092747L;

	// ID
	private String id;

	// 標題
	private String title;

	// 權重
	private int weight;

	// 链接
	private String link;

	// 状态 1--启用 0--停用
	private int state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}