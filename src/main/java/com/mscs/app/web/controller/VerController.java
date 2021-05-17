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
import com.mscs.app.common.dto.req.ReqVerDto;
import com.mscs.app.common.dto.resp.RespVerDto;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.VerService;


/**
 * 
 * @author hechunyang
 * @date 2018年3月12日
 * @remark TODO
 */
@Controller
@RequestMapping
public class VerController extends BIZController {

	public static final Logger logger = LoggerFactory.getLogger(VerController.class);

	@Qualifier("VerServiceImpl")
	@Autowired
	private VerService verService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ver/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDto getVer() {
		List<RespVerDto> dto = verService.getLocalVer();
		return ResponseDto.buildSuccess().setData(dto);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ver/get", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDto getVer(@RequestBody ReqVerDto dto) {
		List<RespVerDto> respDto = verService.getLocalVer(dto);
		return ResponseDto.buildSuccess().setData(respDto);
	}

}
