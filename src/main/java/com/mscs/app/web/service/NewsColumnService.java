package com.mscs.app.web.service;

import java.util.List;

import com.mscs.app.common.dto.req.ReqNewsColumn;
import com.mscs.app.common.dto.resp.RespNewsColumn;


public interface NewsColumnService {
	public List<RespNewsColumn> queryNewsColumnList(ReqNewsColumn obj);
	
}
