package com.mscs.app.common.dto.req;

import com.mscs.app.web.model.News;

public class ReqNewsDto extends News {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lmid;//二级栏目ID
	public String getLmid() {
		return lmid;
	}
	public void setLmid(String lmid) {
		this.lmid = lmid;
	}
	

}
