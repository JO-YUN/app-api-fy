package com.mscs.app.web.service;

import com.mscs.app.common.util.Page;
import com.mscs.app.common.util.Result;
import com.mscs.app.web.model.NewsInfoNH;

public interface NewsInfoNHService {
	
	Result queryNewsInfoListPage(Page page, NewsInfoNH obj);

	Result queryNewsInfoList(NewsInfoNH obj);

	Result queryNewsInfoById(NewsInfoNH obj);

}
