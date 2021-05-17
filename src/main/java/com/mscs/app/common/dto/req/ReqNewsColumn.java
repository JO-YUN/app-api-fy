package com.mscs.app.common.dto.req;

import com.mscs.app.web.model.NewsColumn;

public class ReqNewsColumn extends NewsColumn {
	private String prentName;

	public String getPrentName() {
		return prentName;
	}
	public void setPrentName(String prentName) {
		this.prentName = prentName;
	}

}
