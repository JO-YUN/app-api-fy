package com.mscs.app.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.req.ReqTzggDto;
import com.mscs.app.common.dto.resp.RespTzggDto;
import com.mscs.app.common.util.BeanUtils;
import com.mscs.app.common.util.Page;
import com.mscs.app.common.util.Result;
import com.mscs.app.common.util.ResultPage;
import com.mscs.app.web.dao.TZGGMapper;
import com.mscs.app.web.dao.TZNRMapper;
import com.mscs.app.web.model.TZGG;
import com.mscs.app.web.model.TZNR;
import com.mscs.app.web.service.TZGGService;


/*import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultPage;
import com.hrbwmxx.hrbu.apps.tzgg.dao.TZGGMapper;
import com.hrbwmxx.hrbu.apps.tzgg.service.TZGGService;
import com.hrbwmxx.hrbu.apps.tzgg.vo.TZGGCustom;
import com.hrbwmxx.hrbu.apps.tzgg.vo.TZGGVO;*/

/**
 * @author LiuGuoHui
 * @date 2018-10-09
 *
 */
@Service("TZGGServiceImpl")
public class TZGGServiceImpl implements TZGGService {

	@Autowired
	private TZGGMapper tzggMapper;
	@Autowired
	private TZNRMapper tznrMapper;

	/**
	 * 获取最新通知公告列表前两条
	 */
	@Override
	public List<RespTzggDto> getZxTzgg(ReqTzggDto dto) {
		List<TZGG> tzggs = tzggMapper.queryZxTZGG(dto);
		List<TZGG> tzggList = new ArrayList<TZGG>();
		// 根据新闻ID找到内容
		for (TZGG ztgg : tzggs) {
			TZGG newgg = ztgg;
			newgg.setNr(this.getNr(ztgg.getTzid()));
			tzggList.add(newgg);
		}
		List<RespTzggDto> resps = BeanUtils.mapList(tzggList, RespTzggDto.class);
		return resps;
	}

	/**
	 * 获取通知公告详情
	 */
	@Override
	public RespTzggDto queryTzggById(ReqTzggDto dto) {
		TZGG tz = tzggMapper.queryTzggById(dto.getTzid());
		if(tz!=null) {
			// 根据新闻ID找到内容
			tz.setNr(this.getNr(tz.getTzid()));
			RespTzggDto respTzggDto = BeanUtils.map(tz, RespTzggDto.class);
			//更新阅读人数
			tzggMapper.updateYDRS(tz.getTzid());
			return respTzggDto;
		}else {
			return null;
		}
		
		
	}

	/**
	 * 获取全部通知公告列表
	 */
	@Override
	public Result queryAllTzgg(Page page,ReqTzggDto dto) {
		ResultPage result = new ResultPage();
		List<TZGG> tzggList = new ArrayList<TZGG>();
		try {
			List<TZGG> tzggs = tzggMapper.queryAllTzgg(page, dto);
			// 根据新闻ID找到内容
			for (TZGG ztgg : tzggs) {
				TZGG newgg = ztgg;
				newgg.setNr(this.getNr(ztgg.getTzid()));
				tzggList.add(newgg);
			}
			List<RespTzggDto> resps = BeanUtils.mapList(tzggList, RespTzggDto.class);
			int count = tzggMapper.queryTZGGPageCount(page,dto);
			result.setPageNo(page.getPageNo());
			result.setPageSize(page.getPageSize());
			result.setData(resps);
			result.setTotalCount(count);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorcode("500");
			result.setErrormsg("操作失败");
		}
		return result;
	}

	private String getNr(String tzid) {
		String nr = "";
		// 根据通知Id找到通知的内容List
		List<TZNR> list = tznrMapper.getTZNRListByTZID(tzid);
		// 拼接内容信息
		if (list.size() != 0) {
			for (TZNR tz : list) {
				nr += tz.getNr();
			}
		}
		return nr;
	}


}
