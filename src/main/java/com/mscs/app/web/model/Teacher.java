package com.mscs.app.web.model;

/**
 * 教职工
 * 
 * @author hechunyang
 * @date 2018年5月29日
 * @remark TODO
 */
public class Teacher {

	private String id;
	private String name;
	private int appState;

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

	public int getAppState() {
		return appState;
	}

	public void setAppState(int appState) {
		this.appState = appState;
	}
}
