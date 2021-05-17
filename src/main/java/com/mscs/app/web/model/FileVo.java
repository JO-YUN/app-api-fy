package com.mscs.app.web.model;

import java.util.List;
import java.util.Map;

import com.mscs.app.common.util.ResultEntity;


/**
 * 
 * @ClassName:  FileVo   
 * @Description:TODO(文件类)   
 * @date:   2018年10月16日 
 * @author: LiuGuoHui 
 *
 */
public class FileVo extends ResultEntity{
	private List<Map<String, String>> upfile;

	public List<Map<String, String>> getUpfile() {
		return upfile;
	}

	public void setUpfile(List<Map<String, String>> upfile) {
		this.upfile = upfile;
	}

	

	

	 
}
