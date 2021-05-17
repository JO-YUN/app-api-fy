package com.mscs.app.web.controller;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqAppDto;
import com.mscs.app.common.dto.resp.RespAppDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.AppService;


@Controller
@RequestMapping
public class AppController extends BIZController {

	public static final Logger logger = LoggerFactory
			.getLogger(AppController.class);

	@Qualifier("AppServiceImpl")
	@Autowired
	private AppService appService;

	/**
	 * 获取未登录的系统APP,广告
	 * 
	 * @return
	 * @throws ParseException
	 * @throws AppException
	 */
	@RequestMapping(value = "/app/advertising", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto fetchAdvertisingInforApps(@RequestParam String cityCode) throws ParseException, AppException {
		List<RespAppDto> apps = appService.fetchAdvertisingInforApps(cityCode);
		return ResponseDto.buildSuccess().setData(apps);
	}
	
	/**
	 * 获取未登录的系统APP,中间应用
	 * 
	 * @return
	 * @throws ParseException
	 * @throws AppException
	 */
	@RequestMapping(value = "/app/sys", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto listSysApps(@RequestParam String cityCode,String sys) throws ParseException, AppException {
		List<RespAppDto> apps = appService.fetchSysApps(cityCode,sys);
		return ResponseDto.buildSuccess().setData(apps);
	}

	/**
	 * 获取未登录的系统APP,所有应用
	 * 
	 * @return
	 * @throws ParseException
	 * @throws AppException
	 */
	@RequestMapping(value = "/app/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto listAllApps(@RequestParam String cityCode,String apple) throws ParseException, AppException {
		List<RespAppDto> apps = appService.fetchAllApps(cityCode, apple);
		return ResponseDto.buildSuccess().setData(apps);
	}
	
	@RequestMapping(value = "/app/allforapp", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto allforapp(@RequestParam String cityCode,String sys) throws ParseException, AppException {
		List<RespAppDto> apps = appService.allforapp(cityCode, sys);
		return ResponseDto.buildSuccess().setData(apps);
	}
	
	@RequestMapping(value = "/app/allhg", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto listAllAppshg(@RequestParam String cityCode,String apple) throws ParseException, AppException {
		List<RespAppDto> apps = appService.fetchAllAppshg(cityCode, apple);
		return ResponseDto.buildSuccess().setData(apps);
	}
	/**
	 * 
	 * @Title: listFirstApps   
	 * @Description: TODO(获取未登录的系统APP,上部应用)   
	 * @param: @return
	 * @param: @throws ParseException
	 * @param: @throws AppException      
	 * @return: ResponseDto
	 * @author: Mr丶ZHAO      
	 * @throws
	 */
	@RequestMapping(value = "/app/first", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto listFirstApps(@RequestParam String cityCode,String sys) throws ParseException, AppException {
		List<RespAppDto> apps = appService.fetchFirstApps(cityCode,sys);
		return ResponseDto.buildSuccess().setData(apps);
	}
	/**
	 * 中部连接
	 * @Title: listThirdApps   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param cityCode
	 * @param: @return
	 * @param: @throws ParseException
	 * @param: @throws AppException      
	 * @return: ResponseDto
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	@RequestMapping(value = "/app/third", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto listThirdApps(@RequestParam String cityCode) throws ParseException, AppException {
		List<RespAppDto> apps = appService.fetchThirdApps(cityCode);
		return ResponseDto.buildSuccess().setData(apps);
	}
	
	@RequestMapping(value = "/app/affairs_active", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto affairsActive(@RequestParam String cityCode) throws ParseException, AppException {
		List<RespAppDto> apps = appService.fetchAffairsActive(cityCode);
		return ResponseDto.buildSuccess().setData(apps);
	}
	/**
	 *  *  *  *  *  *  *  *  *  * 分界线 *  *  *  *  *  *  *  *  *  *  *  *  * 
	 */
	/**
	 * 获取app
	 * 
	 * @param token
	 * @return
	 * @throws ParseException
	 * @throws AppException
	 */
	@RequestMapping(value = "/app", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto listTokenApps(@RequestParam String token)
			throws ParseException, AppException {
		List<RespAppDto> apps = appService.fetchApps(this.getUsername(token));
		return ResponseDto.buildSuccess().setData(apps);
	}

	/**
	 * 收藏应用
	 * 
	 * @param token
	 * @return
	 * @throws ParseException
	 * @throws AppException
	 */
	@RequestMapping(value = "/collectapp", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto collectApps(@RequestParam String token)
			throws ParseException, AppException {
		List<RespAppDto> apps = appService.fetchCollectApps(this
				.getUsername(token));
		return ResponseDto.buildSuccess().setData(apps);
	}

	/**
	 * 新增
	 * 
	 * @param condition
	 * @return
	 * @throws ParseException
	 * @throws AppException
	 */
	@RequestMapping(value = "/collectapp/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto addApp(@RequestBody ReqAppDto condition)
			throws AppException {
		String token = condition.getToken();
		if (StringUtils.isBlank(token))
			throw new AppException(ErrorCode.TOKENNULL_FAIL_CODE,ErrorCode.TOKENNULL_FAIL_MSG);
		String uid = this.getUsername(token);
		condition.setUid(uid);
		appService.addCollectApp(condition);
		return ResponseDto.buildSuccess();
	}

	/**
	 * 取消新增
	 * 
	 * @param condition
	 * @return
	 * @throws AppException
	 */
	@RequestMapping(value = "/collectapp/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto delApp(@RequestBody ReqAppDto condition)
			throws AppException {
		if (StringUtils.isBlank(condition.getToken()))
			throw new AppException(ErrorCode.TOKENNULL_FAIL_CODE,
					ErrorCode.TOKENNULL_FAIL_MSG);
		String uid = this.getUsername(condition.getToken());
		condition.setUid(uid);
		appService.delCollectApp(condition);
		return ResponseDto.buildSuccess();
	}
	
	
	
	/**
	 * 取消新增
	 * 
	 * @param condition
	 * @return
	 * @throws AppException
	 */
	@RequestMapping(value = "/app/type", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto getAppType()
			throws AppException {
		List<String> types=appService.fetchAppType();
		return ResponseDto.buildSuccess().setData(types);
	}
	
	
	

}
