package com.mscs.app.common.dto.resp;

import java.util.ArrayList;
import java.util.List;

import com.mscs.app.web.model.NewsColumn;


public class RespNewsColumn extends NewsColumn {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prentName;
	List<RespNewsColumn> childList=new ArrayList<RespNewsColumn>();

	public String getPrentName() {
		return prentName;
	}

	public void setPrentName(String prentName) {
		this.prentName = prentName;
	}

	public List<RespNewsColumn> getChildList() {
		return childList;
	}

	public void setChildList(List<RespNewsColumn> childList) {
		this.childList = childList;
	}
	
	

}
