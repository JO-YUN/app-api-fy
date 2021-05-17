package com.mscs.app.web.model;

import java.util.List;
import java.util.Map;

import com.mscs.app.web.model.base.BIZModel;


public class User extends BIZModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8919418089082211111L;
	private String wid;// 注册时间
	private String userId;// 学工号
	private String name;//姓名
	private String gender;//性别 1 男 2 女
	private String dept;//所在单位 教师所在院系 学生所在院系
	private int state;//状态
	private int type;//类型
	private String passWord;//密码
	private int appState;//1 已经在app登陆 0 未在app登陆
	private String email;//电子邮箱
	private String address;//地址 
	private String sfzjh;//身份证件号
	private String pic;//图片头像
	private List<Map<String, String>> list_file_pic;//附件信息
	
	private String alipayid;//支付宝ID
	private String openid;//微信头像
	private String nickname;//昵称
	private String language;//语言
	private String city;//市
	private String province;//省
	private String country;//国家
	private String headimgurl;//微信头像
	private String unionid;//微信UNIONID
	private String alipaynickname;//支付宝昵称
	private String alipaycity;//支付宝用户城市
	private String alipayprovince;//支付宝用户省份
	private String alipayheadurl;//支付宝头像
	private String alipaydegree;//支付宝等级
	private String alipaymobile;//支付宝移动电话
	private String alipayusername;//支付宝用户名
	private String alipayemail;//支付宝email
	private String alipayadress;//支付宝地址
	private String carid;
	
	
	
	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public List<Map<String, String>> getList_file_pic() {
		return list_file_pic;
	}

	public void setList_file_pic(List<Map<String, String>> list_file_pic) {
		this.list_file_pic = list_file_pic;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}*/

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAppState() {
		return appState;
	}

	public void setAppState(int appState) {
		this.appState = appState;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSfzjh() {
		return sfzjh;
	}

	public void setSfzjh(String sfzjh) {
		this.sfzjh = sfzjh;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAlipayid() {
		return alipayid;
	}

	public String getOpenid() {
		return openid;
	}

	public String getNickname() {
		return nickname;
	}

	public String getLanguage() {
		return language;
	}

	public String getCity() {
		return city;
	}

	public String getProvince() {
		return province;
	}

	public String getCountry() {
		return country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public String getUnionid() {
		return unionid;
	}

	public String getAlipaynickname() {
		return alipaynickname;
	}

	public String getAlipaycity() {
		return alipaycity;
	}

	public String getAlipayprovince() {
		return alipayprovince;
	}


	public String getAlipaydegree() {
		return alipaydegree;
	}

	public String getAlipaymobile() {
		return alipaymobile;
	}

	public String getAlipayusername() {
		return alipayusername;
	}

	public void setAlipayid(String alipayid) {
		this.alipayid = alipayid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public void setAlipaynickname(String alipaynickname) {
		this.alipaynickname = alipaynickname;
	}

	public void setAlipaycity(String alipaycity) {
		this.alipaycity = alipaycity;
	}

	public void setAlipayprovince(String alipayprovince) {
		this.alipayprovince = alipayprovince;
	}

	public void setAlipaydegree(String alipaydegree) {
		this.alipaydegree = alipaydegree;
	}

	public void setAlipaymobile(String alipaymobile) {
		this.alipaymobile = alipaymobile;
	}

	public void setAlipayusername(String alipayusername) {
		this.alipayusername = alipayusername;
	}

	public String getAlipayheadurl() {
		return alipayheadurl;
	}

	public void setAlipayheadurl(String alipayheadurl) {
		this.alipayheadurl = alipayheadurl;
	}

	public String getAlipayemail() {
		return alipayemail;
	}

	public void setAlipayemail(String alipayemail) {
		this.alipayemail = alipayemail;
	}

	public String getAlipayadress() {
		return alipayadress;
	}

	public void setAlipayadress(String alipayadress) {
		this.alipayadress = alipayadress;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}


}