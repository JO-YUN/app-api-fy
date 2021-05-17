package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;

public class AlipayUser extends BIZModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String address;//地址
    private String certNo;//证书号
    private String city;//城市
    private String collegeName;//学院名称
    private String degree;//等级
    private String mobile;//移动电话
    private String phone;//固定电话
    private String province;//省份
    private String userName;//用户名
    private String nickName;//昵称
    private String alipayId;//支付宝Id
    private String token;
    private String alipayheadurl;//头像
    private String sex;
    private String email;//15
	public String getAlipayheadurl() {
		return alipayheadurl;
	}
	public void setAlipayheadurl(String alipayheadurl) {
		this.alipayheadurl = alipayheadurl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getAlipayId() {
		return alipayId;
	}
	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
