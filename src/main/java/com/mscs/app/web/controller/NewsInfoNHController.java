package com.mscs.app.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.util.Page;
import com.mscs.app.common.util.Result;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.model.NewsInfoNH;
import com.mscs.app.web.service.NewsInfoNHService;


/**
 * 
 * <p>Title: NewsInfoController</p>  
 * <p>Description: 新闻信息</p>  
 */

@Controller
@RequestMapping("newsInfoNH")
public class NewsInfoNHController extends BIZController{
	
	public static final Logger logger = LoggerFactory
			.getLogger(NewsInfoNHController.class);
	
	@Autowired
	private NewsInfoNHService newsInfoNHService;
	
	/**
	 * @Title: queryNewsInfoListPage  
	 * @Description: TODO(新闻信息列表-含分页)
	 * @param page
	 * @param newsInfo
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("queryNewsInfoListPage")
	public Result queryNewsInfoListPage(Page page, NewsInfoNH obj) {
		return newsInfoNHService.queryNewsInfoListPage(page,obj);
	}
	
	/**
	 * @Title: queryNewsInfoList  
	 * @Description: TODO(新闻列表-未分页)
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("queryNewsInfoList")
	public Result queryNewsInfoList(NewsInfoNH obj) {
		return newsInfoNHService.queryNewsInfoList(obj);
	}
	
	/**
	 * @Title: queryNewsInfoById  
	 * @Description: TODO(查询单条新闻)
	 * @param obj
	 * @return  
	 * @author: Mr.Zhao
	 */
	@ResponseBody
	@RequestMapping("queryNewsInfoById")
	public Result queryNewsInfoById(NewsInfoNH obj) {
		return newsInfoNHService.queryNewsInfoById(obj);
	}
}
