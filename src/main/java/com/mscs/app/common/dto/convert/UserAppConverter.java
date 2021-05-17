package com.mscs.app.common.dto.convert;

import java.util.UUID;

import com.mscs.app.common.dto.req.ReqAppDto;
import com.mscs.app.web.model.UserApp;


/**
 * 
 * @author hechunyang
 * @date 2018年4月28日
 * @remark TODO
 */
public class UserAppConverter {

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public static UserApp buildSaveObj(ReqAppDto dto) {
		UserApp ua = new UserApp(dto.getAppId(), dto.getUid());
		return ua;
	}
}
