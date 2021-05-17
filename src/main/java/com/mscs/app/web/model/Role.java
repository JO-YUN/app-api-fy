package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

public class Role extends BIZModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2130393544573705428L;

	private String id;

	private String name;

	private String des;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}