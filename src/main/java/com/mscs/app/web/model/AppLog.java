package com.mscs.app.web.model;

import com.mscs.app.web.model.base.BIZModel;
/**
 * 
 * @author hechunyang
 * @date 2018年5月29日
 * @remark TODO
 */
public class AppLog extends BIZModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8074502293139296718L;

	// appID
	private String appId;

	// 应用访问的日期时间
	private String visitDate;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public AppLog(String appId, String visitDate) {
		super();
		this.appId = appId;
		this.visitDate = visitDate;
	}

	public AppLog() {
		super();
	}

}
