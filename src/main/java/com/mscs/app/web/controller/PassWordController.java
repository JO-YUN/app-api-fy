package com.mscs.app.web.controller;

import java.text.ParseException;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.PassWordService;

/**
 * 修改密码
 * 
 */
@Controller
@RequestMapping
public class PassWordController extends BIZController {
	public static final Logger logger = LoggerFactory
			.getLogger(PassWordController.class);

	@Qualifier("PassWordServiceImpl")
	@Autowired
	private PassWordService passWordService;

	/**
	 * 
	 * @return
	 * @throws AppException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/password/updatePassword", method = { RequestMethod.POST,
			RequestMethod.GET })
			
	@ResponseBody
	public ResponseDto updatePassword(@RequestParam HashMap<String, Object> resmap){
		ResponseDto dto = passWordService.updatePassword(resmap);
		return dto;
	}
}
