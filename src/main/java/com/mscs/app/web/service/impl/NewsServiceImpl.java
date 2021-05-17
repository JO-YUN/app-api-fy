package com.mscs.app.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mscs.app.common.dto.req.ReqNewsDto;
import com.mscs.app.common.dto.resp.RespNewsDto;
import com.mscs.app.common.util.Page;
import com.mscs.app.common.util.Result;
import com.mscs.app.common.util.ResultEntity;
import com.mscs.app.common.util.ResultPage;
import com.mscs.app.web.dao.NewsContextMapper;
import com.mscs.app.web.dao.NewsMapper;
import com.mscs.app.web.model.News;
import com.mscs.app.web.model.NewsContext;
import com.mscs.app.web.service.FileService;
import com.mscs.app.web.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsMapper newsMapper;
	@Autowired
	private NewsContextMapper newsContextMapper;
	@Autowired
	private FileService fileService;
	@Override
	public Result queryNewsListPage(Page page, ReqNewsDto obj) {
		ResultPage re = new ResultPage();
		List<RespNewsDto> newsCustomListAll=new ArrayList<RespNewsDto>();
		try {
			if(obj.getColumnid()==null||obj.getColumnid().equals("")) {
				obj.setColumnid(obj.getLmid());
			}
			List<RespNewsDto> newsCustomList = newsMapper.queryNewsListPage(page,obj);
			//循环拼接得到内容
			for (RespNewsDto news : newsCustomList) {
				RespNewsDto newgg = news;
				//将图片过滤掉
				newgg.setContext(this.replaceImg(this.getNr(news.getId())));
				//内容显示100字
				if(newgg.getContext().length()>100) {
					String context=newgg.getContext();
					newgg.setContext(context.substring(0, 100)+"...");
				}
				newsCustomListAll.add(newgg);
			}
			int count = newsMapper.queryNewsForCount(page,obj);
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

	@Override
	public Result queryNewsList(ReqNewsDto obj) {
		ResultEntity re = new ResultEntity();
		List<RespNewsDto> newsCustomListAll=new ArrayList<RespNewsDto>();
		try {
			if(obj.getColumnid()==null||obj.getColumnid().equals("")) {
				obj.setColumnid(obj.getLmid());
			}
			List<RespNewsDto> newsCustomList = newsMapper.queryNewsList(obj);
			//循环拼接得到内容
			for (RespNewsDto news : newsCustomList) {
				RespNewsDto newgg = news;
				newgg.setContext(this.replaceImg(this.getNr(news.getId())));
				newsCustomListAll.add(newgg);
			}
			re.setList(newsCustomListAll);
		} catch (Exception e) {
			e.printStackTrace();
			re.setErrorcode("500");
			re.setErrormsg("操作失败");
		}
		return re;
	}

	@Transactional
	@Override
	public Result queryNewsById(ReqNewsDto obj) {
		ResultEntity adv = new ResultEntity();
		try {
			if(obj.getId()!=null&&!"".equals(obj.getId())){
				RespNewsDto ad = newsMapper.queryNewsById(obj);
				if(ad!=null) {
					ad.setContext(this.getNr(ad.getId()));
					//查看之后增加阅读次数
					String readTimes= String.valueOf(Integer.parseInt(ad.getReadTimes())+1);
					News news = new News();
					news.setId(obj.getId());
					news.setReadTimes(readTimes);
					newsMapper.updateNewsData(news);
					Map<String, String>  map_request=new HashMap<String, String>();
					if (ad.getAttachmentfile()!=null){
						map_request.put("attachId",ad.getAttachmentfile());
						List<Map<String, String>> list_file_attachment = fileService.queryAttrList(map_request);
						ad.setList_file_attachment(list_file_attachment);
					}
					if (ad.getPic()!=null){
					map_request.put("attachId",ad.getPic());
					List<Map<String, String>> list_file_pic = fileService.queryAttrList(map_request);
					ad.setList_file_pic(list_file_pic);
					}
				}else{
					adv.setErrorcode("500");
					adv.setErrormsg("数据已删除");
				}
				adv.setObj(ad);
			}else{
				adv.setErrorcode("500");
				adv.setErrormsg("操作失败");	
			}
		} catch (Exception e) {
			e.printStackTrace();
			adv.setErrorcode("500");
			adv.setErrormsg("操作失败");
		}
		return adv;
	}

	private String replaceImg(String context) {
		context=context.replaceAll("src=\"(.*?)\"","src=\"\"");
		String [] splits=context.split("<img src=\"\"/>");
		String newContext="";
		for(String s:splits) {
			newContext+=s;
		}
		System.out.println(newContext);
		return newContext;
	}
	private String getNr(String id) {
		String nr = "";
		// 根据通知Id找到通知的内容List
		List<NewsContext> list = newsContextMapper.getNRListByID(id);
		// 拼接内容信息
		if (list.size() != 0) {
			for (NewsContext tz : list) {
				nr += tz.getContext();
			}
		}
		return nr;
	}
}
