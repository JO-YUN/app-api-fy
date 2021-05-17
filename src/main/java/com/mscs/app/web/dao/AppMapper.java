package com.mscs.app.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mscs.app.web.dao.pojo.AppVo;
import com.mscs.app.web.model.App;

@Repository
@Mapper
public interface AppMapper {

	/**
	 * 获取APP
	 * @param cityCode 
	 * 
	 * @return
	 */
	List<App> queryAppWithoutSys(@Param("citycode")String cityCode,@Param("apple")String apple);
	
	List<App> queryAppWithoutSyshg(@Param("citycode")String cityCode,@Param("apple")String apple);

	/**
	 * 获取
	 * @param cityCode 
	 * 
	 * @return
	 */
	List<App> querySysApp(@Param("citycode") String cityCode,@Param("sys") String sys);
	
	/**
	 * 获取题头
	 * @param cityCode 
	 * 
	 * @return
	 */
	List<App> queryAppWithoutFirstSys(@Param("citycode")String cityCode,@Param("sys")String sys);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<App> findAppByUserId(@Param("userId") String userId);

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	List<App> findAppByRoleId(@Param("roleId") int roleId);

	/**
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	List<AppVo> findAppByUserIdAndType(@Param("userId") String userId,
			@Param("type") int type);

	/**
	 * 
	 * @return
	 */
	List<String> queryAppType();
	/**
	 * @param cityCode 
	 * 
	 * @Title: fetchAdvertisingInforApps   
	 * @Description: TODO(广告位)   
	 * @param: @return      
	 * @return: List<App>
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	List<App> fetchAdvertisingInforApps(@Param("citycode")String cityCode);
	/**
	 * 头部广告
	 * @Title: fetchThirdInforApps   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param cityCode
	 * @param: @return      
	 * @return: List<App>
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	List<App> fetchThirdInforApps(String cityCode);

	List<App> fetchAffairsActive(String cityCode);

	List<App> allforapp(@Param("cityCode")String cityCode, @Param("sys")String sys);

	

}