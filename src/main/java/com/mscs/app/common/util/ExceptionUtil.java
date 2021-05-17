package com.mscs.app.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mscs.app.web.dao.ExceptionMapper;
import com.mscs.app.web.model.RZ;

@Component
public class ExceptionUtil {
	@Autowired(required=true)
	private ExceptionMapper exceptionMapper;
	
	public void addRz(RZ rz) {
		exceptionMapper.addRz(rz);
	}
	public  RZ buildRZ(String code,String mess) {
		SimpleDateFormat ymd=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hms=new SimpleDateFormat("HH-mm-ss");
		Date date=new Date();
		RZ r=new RZ();
		String rzid=UUID.randomUUID().toString().replaceAll("-", "");
		r.setRzid(rzid);
		r.setCzrq(ymd.format(date));
		r.setCzsj(hms.format(date));
		r.setYcdm(code);
		r.setYcxx(mess);
		return r;
	}
}
