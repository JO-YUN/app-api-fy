package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

/**
 * app
 */
public class App extends BIZModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2069856881745116131L;

	/**
	 * id
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 链接
	 */
	private String link;

	/**
	 * 状态
	 */
	private int state;

	/**
	 * 是否需要登陆 1--需要登陆 0--不需要登陆
	 */
	private int needLogin;

	/**
	 * 打开类型
	 */
	private int openType;

	/**
	 * appType 分类
	 */
	private String appType;
	
	/**
	 * apptypeID
	 */
	private String appType_Id;
	
	/**
	 * 图片类型
	 */
	private String urltype;

	/**
	 * 1--系统应用 2--非系统应用
	 */
	private int sysType;

	private int weight;
	
	private int apple;

	public int getApple() {
		return apple;
	}

	public void setApple(int apple) {
		this.apple = apple;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getNeedLogin() {
		return needLogin;
	}

	public void setNeedLogin(int needLogin) {
		this.needLogin = needLogin;
	}

	public int getOpenType() {
		return openType;
	}

	public void setOpenType(int openType) {
		this.openType = openType;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public int getSysType() {
		return sysType;
	}

	public void setSysType(int sysType) {
		this.sysType = sysType;
	}

	public String getAppType_Id() {
		return appType_Id;
	}

	public void setAppType_Id(String appType_Id) {
		this.appType_Id = appType_Id;
	}

	public String getUrltype() {
		return urltype;
	}

	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}

}