package com.mscs.app.web.controller;

import java.text.ParseException;

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
import com.mscs.app.common.dto.req.ReqAdviceDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.AdviceService;


/**
 * 意见反馈
 * 
 * @author hechunyang
 * @date 2018年3月9日
 * @remark TODO
 */
@Controller
@RequestMapping
public class AdviceController extends BIZController {
	public static final Logger logger = LoggerFactory
			.getLogger(AdviceController.class);

	@Qualifier("AdviceServiceImpl")
	@Autowired
	private AdviceService adviceService;

	/**
	 * 
	 * @return
	 * @throws AppException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/advice/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto pubAdvice(@RequestBody ReqAdviceDto dto) throws AppException {
		dto.setUsername(getUsername(dto.getToken()));
		adviceService.addAdvice(dto);
		return ResponseDto.buildSuccess();
	}
}
