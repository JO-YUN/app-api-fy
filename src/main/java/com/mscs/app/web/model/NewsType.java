package com.mscs.app.web.model;

public class NewsType {

	
	private String id; //主键
	private String name;//类型名称
	private String pic;//类别图片
	private String state;//新闻状态(发布状态:N不发布,Y发布)
	private String px;//排序
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPx() {
		return px;
	}
	public void setPx(String px) {
		this.px = px;
	}
	@Override
	public String toString() {
		return "NewsType [id=" + id + ", name=" + name + ", pic=" + pic + ", state=" + state + ", px=" + px + "]";
	}
	
	
}
