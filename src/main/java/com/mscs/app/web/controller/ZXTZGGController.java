package com.mscs.app.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.req.ReqTzggDto;
import com.mscs.app.common.dto.resp.RespTzggDto;
import com.mscs.app.common.util.ExceptionUtil;
import com.mscs.app.common.util.Page;
import com.mscs.app.common.util.Result;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.TZGGService;


/**
 * @author LiuGuoHui
 * @date 2018-10-09
 *
 */
@Controller
@RequestMapping("zxtzgg")
public class ZXTZGGController extends BIZController {
	public static final Logger logger = LoggerFactory
			.getLogger(ZXTZGGController.class);
	@Qualifier("TZGGServiceImpl")
	@Autowired
	private TZGGService tzggService;
	@Autowired
	private ExceptionUtil exceptionUtil;
	
	/**
	 * 获取最新通知公告列表的前两条
	 * @return
	 */
	@RequestMapping(value="getzxtzgg", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public ResponseDto getzxtzgg(ReqTzggDto dto) { 	
		List<RespTzggDto> dtos=tzggService.getZxTzgg(dto);
		return ResponseDto.buildSuccess().setData(dtos);
	}
	
	/**
	 * 获取全部通知公告列表
	 * @return
	 */
	@RequestMapping(value="queryAllTzgg", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Result queryAllTzgg(Page page,ReqTzggDto dto) { 	
		return tzggService.queryAllTzgg(page,dto);
	}
	/**
	 * 获取通知公告详情
	 * @return
	 */
	@RequestMapping(value="queryTzggById", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public ResponseDto queryTzggById(ReqTzggDto dto) {
		RespTzggDto reqdto=tzggService.queryTzggById(dto);
		return ResponseDto.buildSuccess().setData(reqdto);
	}
	
}
