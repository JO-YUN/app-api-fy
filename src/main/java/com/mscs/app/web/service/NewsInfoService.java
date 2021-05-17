package com.mscs.app.web.service;

import com.mscs.app.common.dto.req.ReqNewsInfoDot;
import com.mscs.app.common.util.Page;
import com.mscs.app.common.util.Result;

public interface NewsInfoService {
	
	Result queryNewsInfoListPage(Page page, ReqNewsInfoDot obj);

	Result queryNewsInfoList(ReqNewsInfoDot obj);

	Result queryNewsInfoById(ReqNewsInfoDot obj);

}
