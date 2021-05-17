package com.mscs.app.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.web.dao.AppNewNHMapper;
import com.mscs.app.web.service.AppServiceNewNH;

@Service("AppServiceNewNHImpl")
public class AppServiceNewNHImpl implements AppServiceNewNH{
	@Autowired
	private AppNewNHMapper appNewNHMapper;
	
	@Override
	public List<Map<String, String>> listApps(Map<String, String> param) {
			List<Map<String, String>> apps = appNewNHMapper.listApps(param);
			return apps;
		
	}
}
