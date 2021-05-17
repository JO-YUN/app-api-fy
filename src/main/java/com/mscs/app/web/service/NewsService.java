package com.mscs.app.web.service;

import com.mscs.app.common.dto.req.ReqNewsDto;
import com.mscs.app.common.util.Page;
import com.mscs.app.common.util.Result;

public interface NewsService {
     Result queryNewsListPage(Page page, ReqNewsDto obj);
     Result queryNewsList(ReqNewsDto obj);
	 Result queryNewsById(ReqNewsDto obj);
	

}
