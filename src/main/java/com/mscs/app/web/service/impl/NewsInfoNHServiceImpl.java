package com.mscs.app.web.service.impl;

import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.resp.RespNewsInfoNHDto;
import com.mscs.app.common.util.Page;
import com.mscs.app.common.util.Result;
import com.mscs.app.common.util.ResultEntity;
import com.mscs.app.common.util.ResultPage;
import com.mscs.app.web.dao.NewsInfoNHMapper;
import com.mscs.app.web.model.NewsInfo;
import com.mscs.app.web.model.NewsInfoNH;
import com.mscs.app.web.service.NHFileService;
import com.mscs.app.web.service.NewsInfoNHService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class NewsInfoNHServiceImpl implements NewsInfoNHService {
	// 新闻
	@Autowired
	private NewsInfoNHMapper newsInfoNHMapper;
	// 图片
	@Autowired
	private NHFileService fileService;

	/**
	 * 新闻列表(分页)
	 */
	@Override
	public Result queryNewsInfoListPage(Page page, NewsInfoNH obj) {
		ResultPage re = new ResultPage();
		List<RespNewsInfoNHDto> newsCustomListAll = new ArrayList<RespNewsInfoNHDto>();
		try {
			newsCustomListAll = newsInfoNHMapper.queryNewsInfoListPage(page, obj);
			for (RespNewsInfoNHDto info : newsCustomListAll) {
				// 图回显
				Map<String, String> map_request = new HashMap<String, String>();
				if (info.getPic() != null) {
					map_request.put("attachId", info.getPic());
					List<Map<String, String>> list_file_pic = fileService.queryAttrList(map_request);
					info.setList_file_pic(list_file_pic);
				}
			}
			int count = newsInfoNHMapper.queryNewsInfoForCount(page, obj);
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
	public Result queryNewsInfoList(NewsInfoNH obj) {
		ResultEntity re = new ResultEntity();
		List<RespNewsInfoNHDto> newsCustomListAll = new ArrayList<RespNewsInfoNHDto>();
		try {
			newsCustomListAll = newsInfoNHMapper.queryNewsInfoList(obj);
			for (RespNewsInfoNHDto info : newsCustomListAll) {
				// 图回显
				Map<String, String> map_request = new HashMap<String, String>();
				if (info.getPic() != null) {
					map_request.put("attachId", info.getPic());
					List<Map<String, String>> list_file_pic = fileService.queryAttrList(map_request);
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
	public Result queryNewsInfoById(NewsInfoNH obj) {
		ResultEntity re = new ResultEntity();
		try {
			if (obj.getId() != null && !"".equals(obj.getId())) {
				RespNewsInfoNHDto info = newsInfoNHMapper.queryNewsInfoById(obj);
				if (info != null) {
					// 查看之后增加阅读次数
					String readTimes = String.valueOf(Integer.parseInt(info.getReadTimes()) + 1);
					NewsInfo news = new NewsInfo();
					news.setId(info.getId());
					news.setReadTimes(readTimes);
					newsInfoNHMapper.updateNewsInfoData(news);
					// 图回显
					Map<String, String> map_request = new HashMap<String, String>();
					if (info.getPic() != null) {
						map_request.put("attachId", info.getPic());
						List<Map<String, String>> list_file_pic = fileService.queryAttrList(map_request);
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
