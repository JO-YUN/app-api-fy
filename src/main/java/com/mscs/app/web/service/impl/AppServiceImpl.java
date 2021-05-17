package com.mscs.app.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.convert.UserAppConverter;
import com.mscs.app.common.dto.req.ReqAppDto;
import com.mscs.app.common.dto.resp.RespAppDto;
import com.mscs.app.common.exception.AppException;
import com.mscs.app.common.help.EntityValidateHelper;
import com.mscs.app.common.util.BeanUtils;
import com.mscs.app.web.dao.AppMapper;
import com.mscs.app.web.dao.UserAppMapper;
import com.mscs.app.web.dao.UserMapper;
import com.mscs.app.web.dao.pojo.AppVo;
import com.mscs.app.web.model.App;
import com.mscs.app.web.model.User;
import com.mscs.app.web.model.UserApp;
import com.mscs.app.web.service.AppService;


@Service("AppServiceImpl")
public class AppServiceImpl implements AppService {

	public static Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private AppMapper appMapper;

	@Autowired
	private UserAppMapper userAppMapper;

	// @Transactional
	@Override
	public List<RespAppDto> fetchApps(String userId) throws AppException {
		try {
			User user = userMapper.findUserByUserId(userId);
			int type = user.getType();
			// List<App> apps = appMapper.findAppByRoleId(type);
			// List<App> colApps = appMapper.findAppByUserId(userId);
			List<AppVo> apps = appMapper.findAppByUserIdAndType(userId, type);
			// getDifferenceAppDto(apps, colApps)
			return BeanUtils.mapList(apps, RespAppDto.class);
		} catch (Exception e) {
			throw new AppException(ErrorCode.APP_ERROR_CODE,
					ErrorCode.APP_ERROR_MSG);
		}
	}

	// @Transactional
	@Override
	public List<RespAppDto> fetchCollectApps(String userId) throws AppException {
		try {
			List<App> apps = appMapper.findAppByUserId(userId);
			return BeanUtils.mapList(apps, RespAppDto.class);
		} catch (Exception e) {
			throw new AppException(ErrorCode.APP_ERROR_CODE,
					ErrorCode.APP_ERROR_MSG);
		}
	}
	/**
	 * 查询所有应用
	 * <p>Title: fetchApps</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws AppException   
	 * @see com.hrbwmxx.app.service.AppService#fetchApps()
	 */
	@Override
	public List<RespAppDto> fetchAllApps(String cityCode,String apple) throws AppException {
		try {
			List<App> apps = appMapper.queryAppWithoutSys(cityCode,apple);
			List<RespAppDto> aa = BeanUtils.mapList(apps, RespAppDto.class);
			return aa;
		} catch (Exception e) {
			throw new AppException(ErrorCode.APP_ERROR_CODE,
					ErrorCode.APP_ERROR_MSG);
		}
	}
	
	@Override
	public List<RespAppDto> allforapp(String cityCode,String sys)  {
		try {
			List<App> apps = appMapper.allforapp(cityCode,sys);
			List<RespAppDto> aa = BeanUtils.mapList(apps, RespAppDto.class);
			return aa;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<RespAppDto> fetchAllAppshg(String cityCode,String apple) throws AppException {
		try {
			List<App> apps = appMapper.queryAppWithoutSyshg(cityCode,apple);
			List<RespAppDto> aa = BeanUtils.mapList(apps, RespAppDto.class);
			return aa;
		} catch (Exception e) {
			throw new AppException(ErrorCode.APP_ERROR_CODE,
					ErrorCode.APP_ERROR_MSG);
		}
	}
	
	/**
	 * 头部连接
	 * <p>Title: fetchFirstApps</p>   
	 * <p>Description: </p>   
	 * @param cityCode
	 * @return
	 * @throws AppException   
	 * @see com.hrbwmxx.app.service.AppService#fetchFirstApps(java.lang.String)
	 */
	@Override
	public List<RespAppDto> fetchFirstApps(String cityCode,String sys) throws AppException {
		try {
			List<App> apps = appMapper.queryAppWithoutFirstSys(cityCode,sys);
			List<RespAppDto> aa = BeanUtils.mapList(apps, RespAppDto.class);
			return aa;
		} catch (Exception e) {
			throw new AppException(ErrorCode.APP_ERROR_CODE,
					ErrorCode.APP_ERROR_MSG);
		}
	}

	/**
	 * 底部连接
	 * <p>Title: fetchSysApps</p>   
	 * <p>Description: </p>   
	 * @param cityCode
	 * @return
	 * @throws AppException   
	 * @see com.hrbwmxx.app.service.AppService#fetchSysApps(java.lang.String)
	 */
	@Override
	public List<RespAppDto> fetchSysApps(String cityCode,String sys) throws AppException {
		try {
			List<App> apps = appMapper.querySysApp(cityCode,sys);
			return BeanUtils.mapList(apps, RespAppDto.class);
		} catch (Exception e) {
			throw new AppException(ErrorCode.APP_ERROR_CODE,
					ErrorCode.APP_ERROR_MSG);
		}
	}

	@Transactional
	@Override
	public void addCollectApp(ReqAppDto dto) throws AppException {
		EntityValidateHelper.checkEntity(dto);
		UserApp uaTemp = userAppMapper.findByUserIdAndAppId(dto.getUid(),
				dto.getAppId());
		if (uaTemp != null)
			throw new AppException(ErrorCode.COL_APP_ERROR_CODE1,
					ErrorCode.COL_APP_ERROR_MSG1);
		try {
			UserApp record = UserAppConverter.buildSaveObj(dto);
			userAppMapper.insert(record);
		} catch (Exception e) {
			throw new AppException(ErrorCode.COL_APP_ERROR_CODE,
					ErrorCode.COL_APP_ERROR_MSG);

		}

	}

	@Transactional
	@Override
	public void delCollectApp(ReqAppDto dto) throws AppException {
		EntityValidateHelper.checkEntity(dto);
		try {
			userAppMapper.deleteRecord(dto.getUid(), dto.getAppId());
		} catch (Exception e) {
			throw new AppException(ErrorCode.DEL_APP_ERROR_CODE,
					ErrorCode.DEL_APP_ERROR_MSG);

		}

	}

	@Override
	public List<String> fetchAppType() {
		return appMapper.queryAppType();
	}
	/**
	 * 获取广告
	 * <p>Title: fetchAdvertisingInforApps</p>   
	 * <p>Description: </p>   
	 * @return
	 * @throws AppException   
	 * @see com.hrbwmxx.app.service.AppService#fetchAdvertisingInforApps()
	 */
	@Override
	public List<RespAppDto> fetchAdvertisingInforApps(String cityCode) throws AppException {
		try {
			List<App> apps = appMapper.fetchAdvertisingInforApps(cityCode);
			List<RespAppDto> aa = BeanUtils.mapList(apps, RespAppDto.class);
			return aa;
		} catch (Exception e) {
			throw new AppException(ErrorCode.APP_ERROR_CODE,
					ErrorCode.APP_ERROR_MSG);
		}
	}
	/**
	 * 中部广告
	 * <p>Title: fetchThirdApps</p>   
	 * <p>Description: </p>   
	 * @param cityCode
	 * @return   
	 * @throws AppException 
	 * @see com.hrbwmxx.app.service.AppService#fetchThirdApps(java.lang.String)
	 */
	@Override
	public List<RespAppDto> fetchThirdApps(String cityCode) throws AppException {
		// TODO Auto-generated method stub
		try {
			List<App> apps = appMapper.fetchThirdInforApps(cityCode);
			List<RespAppDto> aa = BeanUtils.mapList(apps, RespAppDto.class);
			return aa;
		} catch (Exception e) {
			throw new AppException(ErrorCode.APP_ERROR_CODE,
					ErrorCode.APP_ERROR_MSG);
		}
	}
	
	public List<RespAppDto> fetchAffairsActive(String cityCode) throws AppException {
		// TODO Auto-generated method stub
		try {
			List<App> apps = appMapper.fetchAffairsActive(cityCode);
			List<RespAppDto> aa = BeanUtils.mapList(apps, RespAppDto.class);
			return aa;
		} catch (Exception e) {
			throw new AppException(ErrorCode.APP_ERROR_CODE,
					ErrorCode.APP_ERROR_MSG);
		}
	}
	
	

}
