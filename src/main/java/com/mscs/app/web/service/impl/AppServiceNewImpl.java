package com.mscs.app.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.web.dao.AppNewMapper;
import com.mscs.app.web.service.AppServiceNew;


@Service("AppServiceNewImpl")
public class AppServiceNewImpl implements AppServiceNew{
	@Autowired
	private AppNewMapper appNewMapper;
	
	@Override
	public List<Map<String, String>> listApps(Map<String, String> param) {
			List<Map<String, String>> apps = appNewMapper.listApps(param);
			return apps;
		
	}
}
