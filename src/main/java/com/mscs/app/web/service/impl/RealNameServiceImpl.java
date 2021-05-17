package com.mscs.app.web.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscs.app.common.dto.req.ReqRealNameDto;
import com.mscs.app.common.util.DateTimeUtils;
import com.mscs.app.web.dao.RealNameMapper;
import com.mscs.app.web.service.RealNameService;



@Service
public class RealNameServiceImpl implements RealNameService{

	
	
	@Autowired
	private RealNameMapper realNameMapper;
	
	/**
	 * 保存
	 */
	@Override
	public void saveRealName(ReqRealNameDto dto) {
		// TODO Auto-generated method stub
		String id = UUID.randomUUID().toString();
		String dateTime = DateTimeUtils.now().toDateTimeString();
		dto.setId(id);
		dto.setDatetime(dateTime);
		dto.setState("1");
		realNameMapper.saveRealNameInfo(dto); 
	}
	
	@Override
	public void saveRealNameNh(ReqRealNameDto dto) {
		// TODO Auto-generated method stub
		String id = UUID.randomUUID().toString();
		String dateTime = DateTimeUtils.now().toDateTimeString();
		dto.setWid(id);
		dto.setCsrq(dateTime);
		dto.setState("1");
		realNameMapper.saveRealNameInfoNh(dto); 
	}
	
	@Override
	public void updateRealNameNh(ReqRealNameDto dto) {
		// TODO Auto-generated method stub
		String id = UUID.randomUUID().toString();
		String dateTime = DateTimeUtils.now().toDateTimeString();
		dto.setWid(id);
		dto.setCsrq(dateTime);
		dto.setState("1");
		realNameMapper.updateRealNameInfoNh(dto); 
	}
	

	@Override
	public int checkOpenidFy(ReqRealNameDto dto) {
		int i = realNameMapper.checkOpenidFy(dto);
		return i;
	}

	@Override
	public int checkOpenidNh(ReqRealNameDto dto) {
		int i = realNameMapper.checkOpenidNh(dto);
		return i;
	}
}
