package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

/**
 * 通知公告
 * @author LiuGuoHui
 * @date 2018-10-09
 *
 */

public class TZGG extends BIZModel {
	private String tzid;//通知ID
	private String bt;	//标题
	private String wjlj1;//文件1
	private String wjlj2;//文件2
	private String zt;//1编辑 2发布 0作废
	private Integer ydrs;//使用人数
	private String bjsj;//编辑时间
	private String bjyhid;//编辑用户ID
	private String fbsj;//发布时间
	private String fbyhid;//发布用户Id
	private String nr;//内容
	private String cityCode;//城市代码
	private String editUser;
	private String publishUser;
	
	public String getEditUser() {
		return editUser;
	}
	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}
	public String getPublishUser() {
		return publishUser;
	}
	public void setPublishUser(String publishUser) {
		this.publishUser = publishUser;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	public String getTzid() {
		return tzid;
	}
	public void setTzid(String tzid) {
		this.tzid = tzid;
	}
	public String getBt() {
		return bt;
	}
	public void setBt(String bt) {
		this.bt = bt;
	}
	public String getWjlj1() {
		return wjlj1;
	}
	public void setWjlj1(String wjlj1) {
		this.wjlj1 = wjlj1;
	}
	public String getWjlj2() {
		return wjlj2;
	}
	public void setWjlj2(String wjlj2) {
		this.wjlj2 = wjlj2;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public Integer getYdrs() {
		return ydrs;
	}
	public void setYdrs(Integer ydrs) {
		this.ydrs = ydrs;
	}
	public String getBjsj() {
		return bjsj;
	}
	public void setBjsj(String bjsj) {
		this.bjsj = bjsj;
	}
	public String getBjyhid() {
		return bjyhid;
	}
	public void setBjyhid(String bjyhid) {
		this.bjyhid = bjyhid;
	}
	public String getFbsj() {
		return fbsj;
	}
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	public String getFbyhid() {
		return fbyhid;
	}
	public void setFbyhid(String fbyhid) {
		this.fbyhid = fbyhid;
	}
	
	
}
