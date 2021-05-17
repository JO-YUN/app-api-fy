package com.mscs.app.web.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.web.service.AppServiceNew;


@Controller
@RequestMapping
public class AppControllerNew {
	@Qualifier("AppServiceNewImpl")
	@Autowired
	private AppServiceNew appServiceNew;
	
	@RequestMapping(value = "listApps", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto listApps(@RequestParam Map<String,String> param)  {
		List<Map<String, String>> apps = appServiceNew.listApps(param);
		return ResponseDto.buildSuccess().setData(apps);
	}
	
	
	
}
