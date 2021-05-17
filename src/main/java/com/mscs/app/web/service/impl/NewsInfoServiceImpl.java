package com.mscs.app.web.service.impl;

import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.req.ReqNewsInfoDot;
import com.mscs.app.common.dto.resp.RespNewsInfoDto;
import com.mscs.app.common.util.Page;
import com.mscs.app.common.util.Result;
import com.mscs.app.common.util.ResultEntity;
import com.mscs.app.common.util.ResultPage;
import com.mscs.app.web.dao.NewsInfoMapper;
import com.mscs.app.web.model.NewsInfo;
import com.mscs.app.web.service.INewsFileService;
import com.mscs.app.web.service.NewsInfoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class NewsInfoServiceImpl implements NewsInfoService {
	// 新闻
	@Autowired
	private NewsInfoMapper newsInfoMapper;
	// 图片
	@Autowired
	private INewsFileService newsFileService;

	/**
	 * 新闻列表(分页)
	 */
	@Override
	public Result queryNewsInfoListPage(Page page, ReqNewsInfoDot obj) {
		ResultPage re = new ResultPage();
		List<RespNewsInfoDto> newsCustomListAll = new ArrayList<RespNewsInfoDto>();
		try {
			newsCustomListAll = newsInfoMapper.queryNewsInfoListPage(page, obj);
			for (RespNewsInfoDto info : newsCustomListAll) {
				// 图回显
				Map<String, String> map_request = new HashMap<String, String>();
				if (info.getPic() != null) {
					map_request.put("attachId", info.getPic());
					List<Map<String, String>> list_file_pic = newsFileService.queryAttrList(map_request);
					info.setList_file_pic(list_file_pic);
				}
			}
			int count = newsInfoMapper.queryNewsInfoForCount(page, obj);
			System.out.println(count);
			re.setPageNo(page.getPageNo());
			re.setPageSize(page.getPageSize());
			re.setData(newsCustomListAll);
			re.setTotalCount(count);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrorcode("500");
			re.setErrormsg("操作失败");
		}
		return re;
	}

	/**
	 * 新闻列表-未分页
	 */
	@Override
	public Result queryNewsInfoList(ReqNewsInfoDot obj) {
		ResultEntity re = new ResultEntity();
		List<RespNewsInfoDto> newsCustomListAll = new ArrayList<RespNewsInfoDto>();
		try {
			newsCustomListAll = newsInfoMapper.queryNewsInfoList(obj);
			for (RespNewsInfoDto info : newsCustomListAll) {
				// 图回显
				Map<String, String> map_request = new HashMap<String, String>();
				if (info.getPic() != null) {
					map_request.put("attachId", info.getPic());
					List<Map<String, String>> list_file_pic = newsFileService.queryAttrList(map_request);
					info.setList_file_pic(list_file_pic);
				}
			}
			re.setList(newsCustomListAll);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrorcode("500");
			re.setErrormsg("操作失败");
		}
		return re;
	}

	/**
	 * 查询单条新闻
	 */
	@Override
	public Result queryNewsInfoById(ReqNewsInfoDot obj) {
		ResultEntity re = new ResultEntity();
		try {
			if (obj.getId() != null && !"".equals(obj.getId())) {
				RespNewsInfoDto info = newsInfoMapper.queryNewsInfoById(obj);
				if (info != null) {
					// 查看之后增加阅读次数
					String readTimes = String.valueOf(Integer.parseInt(info.getReadTimes()) + 1);
					NewsInfo news = new NewsInfo();
					news.setId(info.getId());
					news.setReadTimes(readTimes);
					newsInfoMapper.updateNewsInfoData(news);
					// 图回显
					Map<String, String> map_request = new HashMap<String, String>();
					if (info.getPic() != null) {
						map_request.put("attachId", info.getPic());
						List<Map<String, String>> list_file_pic = newsFileService.queryAttrList(map_request);
						info.setList_file_pic(list_file_pic);
					}
				} else {
					re.setErrorcode("500");
					re.setErrormsg("数据已删除");
				}
				re.setObj(info);
			} else {
				re.setErrorcode("500");
				re.setErrormsg("操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrorcode("500");
			re.setErrormsg("操作失败");
		}
		return re;
	}

}
