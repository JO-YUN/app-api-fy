package com.mscs.app.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.resp.RespGuideImgDto;
import com.mscs.app.common.dto.resp.RespImgDto;
import com.mscs.app.common.util.BeanUtils;
import com.mscs.app.web.dao.GuideImgMapper;
import com.mscs.app.web.dao.ImgMapper;
import com.mscs.app.web.model.GuideImg;
import com.mscs.app.web.model.Img;
import com.mscs.app.web.service.ImgService;


@Service("ImgServiceImpl")
public class ImgServiceImpl implements ImgService {

	@Autowired
	private ImgMapper imgMapper;

	@Autowired
	private GuideImgMapper guideImgMapper;

	
	@Override
	public List<RespImgDto> fetchImgs() {
		List<Img> imgs = imgMapper.queryImg();
		List<RespImgDto> resps = BeanUtils.mapList(imgs, RespImgDto.class);
		return resps;
	}

	
	@Override
	public List<RespGuideImgDto> fetchGuideImgs() {
		List<GuideImg> list = guideImgMapper.queryGuideImg();
		return BeanUtils.mapList(list, RespGuideImgDto.class);
	}

}
