package com.mscs.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.dto.resp.RespGuideImgDto;
import com.mscs.app.common.dto.resp.RespImgDto;
import com.mscs.app.web.controller.base.BIZController;
import com.mscs.app.web.service.ImgService;


@Controller
@RequestMapping
public class ImgController extends BIZController {

	@Qualifier("ImgServiceImpl")
	@Autowired
	private ImgService imgService;

	/**
	 * 
	 * @return
	 */

	@RequestMapping(value = "/img", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public ResponseDto listImgs() {
		List<RespImgDto> dto = imgService.fetchImgs();
		return ResponseDto.buildSuccess().setData(dto);
	}

	/**
	 * 
	 * @return
	 */

	@RequestMapping(value = "/guide", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public ResponseDto listGuideImgs() {
		List<RespGuideImgDto> dto = imgService.fetchGuideImgs();
		return ResponseDto.buildSuccess().setData(dto);
	}

}
