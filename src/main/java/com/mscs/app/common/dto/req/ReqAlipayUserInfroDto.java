package com.mscs.app.common.dto.req;

import org.hibernate.validator.constraints.NotBlank;

import com.mscs.app.common.dto.BaseDto;


public class ReqAlipayUserInfroDto extends BaseDto{
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
    @NotBlank(message = "支付宝Id为空")
    private String alipayId;//支付宝Id
    private String token;
    private String alipayheadurl;//支付宝头像
    private String wid;//插入时间
	private String state;//状态
	@NotBlank(message = "验证码为空")
	private String verificationCode;//短信验证码
	private String userId;//手机号
	private String sex;
	private String xbdm;//性别 1 男 2 女
	private String email;
	private String passWord;
	
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getXbdm() {
		return xbdm;
	}
	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAlipayheadurl() {
		return alipayheadurl;
	}
	public void setAlipayheadurl(String alipayheadurl) {
		this.alipayheadurl = alipayheadurl;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
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
