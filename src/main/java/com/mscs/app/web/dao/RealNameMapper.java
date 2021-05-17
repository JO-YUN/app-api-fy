package com.mscs.app.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.req.ReqRealNameDto;
import com.mscs.app.web.model.RealName;

@Repository
@Mapper
public interface RealNameMapper {

	void saveRealNameInfo(RealName obj);

	int checkOpenidFy(RealName obj);

	int checkOpenidNh(RealName obj);

	void saveRealNameInfoNh(ReqRealNameDto dto);

	void updateRealNameInfoNh(ReqRealNameDto dto);	
}
