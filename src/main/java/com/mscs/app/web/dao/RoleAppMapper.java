package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.model.App;

@Repository
@Mapper
public interface RoleAppMapper {

	List<App> findAppIdByRole(@Param("roleId") int roleId);
}