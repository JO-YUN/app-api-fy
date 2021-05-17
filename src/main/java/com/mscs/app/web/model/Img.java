package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

public class Img extends BIZModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1726558581557986460L;

	private String id;

	private String title;

	private String imgLink;

	private String newsLink;

	private int state;

	private int weight;

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

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getNewsLink() {
		return newsLink;
	}

	public void setNewsLink(String newsLink) {
		this.newsLink = newsLink;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}