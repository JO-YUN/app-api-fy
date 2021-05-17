package com.mscs.app.common.dto.resp;

import java.util.List;
import java.util.Map;

import com.mscs.app.web.model.NewsTypeNH;


public class RespNewsTypeNHDto extends NewsTypeNH{
	
	private List<Map<String, String>> list_file_pic;//图片

	public List<Map<String, String>> getList_file_pic() {
		return list_file_pic;
	}

	public void setList_file_pic(List<Map<String, String>> list_file_pic) {
		this.list_file_pic = list_file_pic;
	}
	
}
