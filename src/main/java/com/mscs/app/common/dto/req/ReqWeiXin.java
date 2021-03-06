package com.mscs.app.common.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import com.mscs.app.common.dto.BaseDto;


public class ReqWeiXin extends BaseDto{

	/**
	 */
	private static final long serialVersionUID = -3477796884776020453L;
	
	private String token;
	@NotBlank(message = "openid不为空")
	private String openid; //app 微信id
	private String nickname;//昵称
	private String sex;//性别 1 男 2 女
	private String language;//语言
	private String city;//城市
	private String province;// 省份
    private String country;//国家
    private String headimgurl;//微信头像
    private String unionid;//微信唯一id
    private String userId;//注册手机号
	@NotBlank(message = "验证码为空")
	private String verificationCode;//短信验证码
	private String wid;//插入时间
	private String state;//状态
	private String xbdm;//性别 1 男 2 女
	
	//accessToken pc回调参数
	private String access_token;//接口调用凭证
	private String expires_in;//access_token接口调用凭证超时时间，单位（秒）
	private String refresh_token;//用户刷新access_token
	private String passWord;
	
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getXbdm() {
		return xbdm;
	}
	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
   
}
