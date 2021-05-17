package com.mscs.app.web.service;


import com.mscs.app.common.exception.AppException;


public interface DaYuService {
	public String sendSMSCode(String templateCode, String telephone,String code)throws AppException;

}
