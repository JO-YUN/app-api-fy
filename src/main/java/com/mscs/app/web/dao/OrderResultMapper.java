package com.mscs.app.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mscs.app.common.dto.req.ReqOrderResultDto;

@Repository
@Mapper
public interface OrderResultMapper {


	void saveOrder(ReqOrderResultDto dto);
	void saveOrderqqhr(ReqOrderResultDto dto);
	void saveOrderhg(ReqOrderResultDto dto);
	

}
