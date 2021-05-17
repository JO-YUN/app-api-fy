package com.mscs.app.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*import com.google.common.collect.Maps;*/
import com.mscs.app.common.dto.req.ReqVerDto;
import com.mscs.app.common.dto.resp.RespVerDto;
import com.mscs.app.common.util.BeanUtils;
import com.mscs.app.web.dao.VerMapper;
import com.mscs.app.web.model.Ver;
import com.mscs.app.web.service.VerService;

@Service("VerServiceImpl")
public class VerServiceImpl implements VerService {

	@Autowired
	private VerMapper verMapper;

	// @Transactional
	@Override
	public List<RespVerDto> getLocalVer() {
		List<Ver> vers = verMapper.queryVer();
		List<RespVerDto> dtos = BeanUtils.mapList(vers, RespVerDto.class);
		return dtos;
	}

	@Override
	public List<RespVerDto> getLocalVer(ReqVerDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Transactional
	/*@Override
	public List<RespVerDto> getLocalVer(ReqVerDto dto) {
		Map<String, Object> condtion = Maps.newHashMap();
		condtion.put("osType", dto.getOs());
		condtion.put("otherType", dto.getType());
		List<Ver> vers = verMapper.queryVerByCondition(condtion);
		List<RespVerDto> dtos = BeanUtils.mapList(vers, RespVerDto.class);
		return dtos;
	}*/

}
