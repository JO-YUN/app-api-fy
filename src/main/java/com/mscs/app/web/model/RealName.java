package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

/**
 * 实名认证
* <p>Title: RealName</p>  
* <p>Description: </p>  
* @author Mr.Zhao 
 */
public class RealName extends BIZModel{

	/** serialVersionUID*/ 
	private static final long serialVersionUID = 1L;
	
	private String id; 
	private String name;
	private String numberid;
	private String phone;
	private String unionid;
	private String openid;
	private String headimgurl;
	private String nickname;
	private String language;
	private String country;
	private String province;
	private String city;
	private String state;
	private String datetime;
	private String wid;
	private String xm;
	private String sfzjh;
	private String zgh;
	private String csrq;
	
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSfzjh() {
		return sfzjh;
	}
	public void setSfzjh(String sfzjh) {
		this.sfzjh = sfzjh;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
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
	
	public String getNumberid() {
		return numberid;
	}
	public void setNumberid(String numberid) {
		this.numberid = numberid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "RealName [id=" + id + ", name=" + name + ", numberid=" + numberid + ", phone=" + phone + ", unionid="
				+ unionid + ", openid=" + openid + ", headimgurl=" + headimgurl + ", nickname=" + nickname
				+ ", language=" + language + ", country=" + country + ", province=" + province + ", city=" + city
				+ ", state=" + state + ", datetime=" + datetime + "]";
	}
	
	
	
	
}
