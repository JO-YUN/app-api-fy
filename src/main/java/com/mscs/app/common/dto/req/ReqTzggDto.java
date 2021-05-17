package com.mscs.app.common.dto.req;

import com.mscs.app.common.dto.BaseDto;

/**
 * 通知公告
 * 
 * @author LiuGuoHui
 * @date 2018年10月09日
 * @remark TODO
 */
public class ReqTzggDto extends BaseDto{
	private static final long serialVersionUID = 653480215507553928L;

	private String token;

	/**
	 * 用戶名 用戶名可以由前台传，也可以在数据库中查出来
	 */
	private String username;
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
	public String getFbyhid() {
		return fbyhid;
	}
	public void setFbyhid(String fbyhid) {
		this.fbyhid = fbyhid;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getFbsj() {
		return fbsj;
	}
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	
	

}
