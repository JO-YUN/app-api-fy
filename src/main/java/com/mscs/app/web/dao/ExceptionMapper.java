package com.mscs.app.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.model.RZ;

@Repository
@Mapper
public interface ExceptionMapper {
	public void addRz(RZ rz);
}
