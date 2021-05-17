package com.mscs.app.common.dto.resp;
/**
 * 
 * @author hechunyang
 * @date 2018年5月7日
   @remark TODO
 */
public class RespVerDto {

	private String ver;

	private String pubDate;

	private String des;

	private String code;

	private String appLink;

	private int osType;

	private int otherType;
	
	private String log;
	
	private int needup;

	public int getNeedup() {
		return needup;
	}

	public void setNeedup(int needup) {
		this.needup = needup;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAppLink() {
		return appLink;
	}

	public void setAppLink(String appLink) {
		this.appLink = appLink;
	}

	public int getOsType() {
		return osType;
	}

	public void setOsType(int osType) {
		this.osType = osType;
	}

	public int getOtherType() {
		return otherType;
	}

	public void setOtherType(int otherType) {
		this.otherType = otherType;
	}

}
