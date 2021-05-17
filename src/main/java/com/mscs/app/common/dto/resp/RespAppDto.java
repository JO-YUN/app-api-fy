package com.mscs.app.common.dto.resp;

/**
 * @author hechunyang
 * @date 2018年3月9日
 * @remark TODO
 */
public class RespAppDto {

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
	// private int state;

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
	 * 图片类型
	 */
	private String urltype;
	
	/**
	 * 应用类型ID
	 */
	private String appType_Id;

	/**
	 * 1--系统应用 2--非系统应用
	 */
	private int sysType;

	/**
	 * 是否已经被收藏 1--已经被收藏 0--未被收藏
	 */
	private int isCol;

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

	public int getIsCol() {
		return isCol;
	}

	public void setIsCol(int isCol) {
		this.isCol = isCol;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNeedLogin() {
		return needLogin;
	}

	public void setNeedLogin(int needLogin) {
		this.needLogin = needLogin;
	}

	public String getUrltype() {
		return urltype;
	}

	public void setUrltype(String urltype) {
		this.urltype = urltype;
	}

	public String getAppType_Id() {
		return appType_Id;
	}

	public void setAppType_Id(String appType_Id) {
		this.appType_Id = appType_Id;
	}

}
