package com.mscs.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.util.Result;
import com.mscs.app.web.model.NewsTypeNH;
import com.mscs.app.web.service.NewsTypeNHService;




@Controller
@RequestMapping("newsTypeNH")
public class NewsTypeNHController {

	
	@Autowired
	private NewsTypeNHService newsTypeNHService;
	
	@ResponseBody
	@RequestMapping("queryNewsTypeList")
	public Result queryNewsTypeList(NewsTypeNH obj) {
		return newsTypeNHService.queryNewsTypeList(obj);
	}
	
}
