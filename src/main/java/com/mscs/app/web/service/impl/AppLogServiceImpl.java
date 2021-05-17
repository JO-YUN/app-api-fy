package com.mscs.app.web.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.resp.RespAppLogDto;
import com.mscs.app.common.dto.resp.RespUserAppCountDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.common.util.BeanUtils;
import com.mscs.app.common.util.DateTimeUtils;
import com.mscs.app.web.dao.AppLogMapper;
import com.mscs.app.web.dao.UserMapper;
import com.mscs.app.web.dao.pojo.AppLogVo;
import com.mscs.app.web.dao.pojo.UserAppCountVo;
import com.mscs.app.web.model.AppLog;
import com.mscs.app.web.service.AppLogService;


@Service("AppLogServiceImpl")
public class AppLogServiceImpl implements AppLogService {

	@Autowired
	private AppLogMapper appLogMapper;

	@Autowired
	private UserMapper userMapper;

	public void addAppLog(String appId) throws AppException {
		if (StringUtils.isBlank(appId))
			throw new AppException("100060", "获取应用ID为空");
		System.err.println("appId:"+appId);
		AppLog log = new AppLog(appId, DateTimeUtils.now().toDateTimeString());
		appLogMapper.insert(log);

	}

	@Override
	public List<RespAppLogDto> getAppLogCountInfo() {
		List<AppLogVo> vos = appLogMapper.queryAppLogCountInfo();
		return BeanUtils.mapList(vos, RespAppLogDto.class);
	}

	@Override
	public RespUserAppCountDto getUserAppCountInfo() {
		UserAppCountVo vo = userMapper.countAppNum();
		return BeanUtils.map(vo, RespUserAppCountDto.class);
	}

}
