package com.mscs.app.web.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.resp.RespAppLogDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.AppLogService;

/**
 * 訪問統計
 * 
 * @author hechunyang
 * @date 2018年5月29日
 * @remark TODO
 */
@Controller
@RequestMapping
public class CountController extends BIZController {

	@Qualifier("AppLogServiceImpl")
	@Autowired
	private AppLogService appLogService;

	/**
	 * 获取未登录的系统APP
	 * 
	 * @return
	 * @throws ParseException
	 * @throws AppException
	 */
	@RequestMapping(value = "/applog/add", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto newLog(@RequestParam String appId)
			throws ParseException, AppException {
		appLogService.addAppLog(appId);
		return ResponseDto.buildSuccess();
	}

	/**
	 * 获取未登录的系统APP
	 * 
	 * @return
	 * @throws ParseException
	 * @throws AppException
	 */
	@RequestMapping(value = "/applog/count", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto countAppLog() throws ParseException, AppException {
		List<RespAppLogDto> dtos = appLogService.getAppLogCountInfo();
		return ResponseDto.buildSuccess().setData(dtos);
	}

	/**
	 * 獲取app使用情況
	 * 
	 * @return
	 * @throws ParseException
	 * @throws AppException
	 */
	@RequestMapping(value = "/user/app/count", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto countUserAppInfo() throws ParseException, AppException {
		return ResponseDto.buildSuccess().setData(
				appLogService.getUserAppCountInfo());
	}

}
