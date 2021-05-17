package com.mscs.app.web.service;

import com.mscs.app.common.dto.req.ReqRealNameDto;

public interface RealNameService {

	void saveRealName(ReqRealNameDto dto);
	
	void saveRealNameNh(ReqRealNameDto dto);

	int checkOpenidFy(ReqRealNameDto dto);
	
	int checkOpenidNh(ReqRealNameDto dto);

	void updateRealNameNh(ReqRealNameDto dto);

}
