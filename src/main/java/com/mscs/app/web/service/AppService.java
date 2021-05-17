package com.mscs.app.web.service;

import java.util.List;

import com.mscs.app.common.dto.req.ReqAppDto;
import com.mscs.app.common.dto.resp.RespAppDto;
import com.mscs.app.common.exception.AppException;


/**
 * 
 * @author hechunyang
 * @date 2018年3月9日
 * @remark TODO
 */
public interface AppService {

	/**
	 * 根据Token获取用户名权限 获取数据
	 * 
	 * @param token
	 * @return
	 * @throws AppException
	 */
	List<RespAppDto> fetchApps(String uid) throws AppException;

	/**
	 * 根据Token获取用户名权限 获取数据
	 * @param cityCode 
	 * 
	 * @param token
	 * @return
	 */
	List<RespAppDto> fetchSysApps(String cityCode,String sys) throws AppException;

	/**
	 * 
	 * @param uid
	 * @return
	 */
	List<RespAppDto> fetchCollectApps(String uid) throws AppException;

	/**
	 * 
	 * @param dto
	 */
	void addCollectApp(ReqAppDto dto) throws AppException;

	/**
	 * 
	 * @param dto
	 */
	void delCollectApp(ReqAppDto dto) throws AppException;

	/**
	 * 
	 * @return
	 */
	List<String> fetchAppType();
	
	/**
	 * 
	 * @return
	 * @throws AppException 
	 */
	List<RespAppDto> fetchFirstApps(String cityCode,String apple) throws AppException;
	/**
	 * @param cityCode 
	 * @throws AppException 
	 * 
	 * @Title: fetchAdvertisingInforApps   
	 * @Description: TODO(获取广告)   
	 * @param: @return      
	 * @return: List<RespAppDto>
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	List<RespAppDto> fetchAdvertisingInforApps(String cityCode) throws AppException;
	/**
	 * 查询所有应用
	 * @Title: fetchAllApps   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param cityCode
	 * @param: @return
	 * @param: @throws AppException      
	 * @return: List<RespAppDto>
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	List<RespAppDto> fetchAllApps(String cityCode,String apple) throws AppException;
	/**
	 * @throws AppException 
	 * 中部广告
	 * @Title: fetchThirdApps   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param cityCode
	 * @param: @return      
	 * @return: List<RespAppDto>
	 * @author: CHIUCLOUD(云)        
	 * @throws
	 */
	List<RespAppDto> fetchThirdApps(String cityCode) throws AppException;

	List<RespAppDto> fetchAllAppshg(String cityCode, String apple) throws AppException;

	List<RespAppDto> fetchAffairsActive(String cityCode) throws AppException;

	List<RespAppDto> allforapp(String cityCode, String sys) throws AppException;
	
	
}
