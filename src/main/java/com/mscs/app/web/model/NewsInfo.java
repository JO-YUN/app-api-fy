package com.mscs.app.web.model;


/**
 * 
* @title: NewsInfo 
* @description：新闻信息
 */
public class NewsInfo {

	private String id; //主键
	private String title;//新闻标题
	private String intro;//新闻简介
	private String context;//新闻内容
	private String userId;//新闻作者
	private String pic;//新闻图片
	private String releaseDate;//发布日期
	private String readTimes;//浏览次数
	private String state;//新闻状态(发布状态:N不发布,Y发布)
	private String topState;//置顶状态；N:不置顶；Y:置顶
	private String topOrderNumber;//置顶序号
	private String typeId;//新闻类别
	private String homePages; //是否首页(首页状态:0不发布,1发布)
	
	public String getHomePages() {
		return homePages;
	}
	public void setHomePages(String homePages) {
		this.homePages = homePages;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
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
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(String readTimes) {
		this.readTimes = readTimes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	
	

	
}
