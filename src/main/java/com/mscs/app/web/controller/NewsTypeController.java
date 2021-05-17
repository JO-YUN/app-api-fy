package com.mscs.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.util.Result;
import com.mscs.app.web.model.NewsType;
import com.mscs.app.web.service.NewsTypeService;




@Controller
@RequestMapping("newsType")
public class NewsTypeController {

	
	@Autowired
	private NewsTypeService newsTypeService;
	
	@ResponseBody
	@RequestMapping("queryNewsTypeList")
	public Result queryNewsTypeList(NewsType obj) {
		return newsTypeService.queryNewsTypeList(obj);
	}
	
}
