package com.mscs.app.common.dto.convert;

import com.mscs.app.common.dto.req.ReqUserInfroDto;
import com.mscs.app.web.model.User;

public class UserInforConverter {
	
	public static User buildUserInforObj(ReqUserInfroDto dto) {
		User usr = new User();
		usr.setUserId(dto.getUserId());
		usr.setName(dto.getName());
		usr.setGender(dto.getGender());
		usr.setEmail(dto.getEmail());
		usr.setSfzjh(dto.getSfzjh());
		usr.setAddress(dto.getAddress());
		return usr;
	}
}
