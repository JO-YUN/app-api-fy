package com.mscs.app.web.service;

import java.util.HashMap;

import com.mscs.app.common.dto.ResponseDto;


public interface PassWordService {
	
	public ResponseDto updatePassword(HashMap<String, Object> resmap);

}
