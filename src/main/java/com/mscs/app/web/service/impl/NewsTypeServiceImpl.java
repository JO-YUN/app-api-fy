package com.mscs.app.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.resp.RespNewsTypeDto;
import com.mscs.app.common.util.Result;
import com.mscs.app.common.util.ResultEntity;
import com.mscs.app.web.dao.NewsTypeMapper;
import com.mscs.app.web.model.NewsType;
import com.mscs.app.web.service.INewsFileService;
import com.mscs.app.web.service.NewsTypeService;


@Service
public class NewsTypeServiceImpl implements NewsTypeService{
	
	
	@Autowired
	private NewsTypeMapper newsTypeMapper;
	// 图片
	@Autowired
	private INewsFileService newsFileService;

	@Override
	public Result queryNewsTypeList(NewsType obj) {
		// TODO Auto-generated method stub
		ResultEntity re = new ResultEntity();
		List<RespNewsTypeDto> typeList = new ArrayList<RespNewsTypeDto>();
		try {
			typeList = newsTypeMapper.queryNewsTypeList(obj);
			for (RespNewsTypeDto info : typeList) {
				// 图回显
				Map<String, String> map_request = new HashMap<String, String>();
				if (info.getPic() != null) {
					map_request.put("attachId", info.getPic());
					List<Map<String, String>> list_file_pic = newsFileService.queryAttrList(map_request);
					info.setList_file_pic(list_file_pic);
				}
			}
			re.setList(typeList);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrorcode("500");
			re.setErrormsg("操作失败");
		}
		return re;
	}

}
