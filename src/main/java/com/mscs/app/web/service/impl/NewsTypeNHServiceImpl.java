package com.mscs.app.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.resp.RespNewsTypeNHDto;
import com.mscs.app.common.util.Result;
import com.mscs.app.common.util.ResultEntity;
import com.mscs.app.web.dao.NewsTypeNHMapper;
import com.mscs.app.web.model.NewsTypeNH;
import com.mscs.app.web.service.NHFileService;
import com.mscs.app.web.service.NewsTypeNHService;


@Service
public class NewsTypeNHServiceImpl implements NewsTypeNHService{
	
	
	@Autowired
	private NewsTypeNHMapper newsTypeNHMapper;
	// 图片
	@Autowired
	private NHFileService fileService;

	@Override
	public Result queryNewsTypeList(NewsTypeNH obj) {
		// TODO Auto-generated method stub
		ResultEntity re = new ResultEntity();
		List<RespNewsTypeNHDto> typeList = new ArrayList<RespNewsTypeNHDto>();
		try {
			typeList = newsTypeNHMapper.queryNewsTypeList(obj);
			for (RespNewsTypeNHDto info : typeList) {
				// 图回显
				Map<String, String> map_request = new HashMap<String, String>();
				if (info.getPic() != null) {
					map_request.put("attachId", info.getPic());
					List<Map<String, String>> list_file_pic = fileService.queryAttrList(map_request);
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
