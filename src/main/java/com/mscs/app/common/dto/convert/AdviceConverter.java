package com.mscs.app.common.dto.convert;

import java.util.UUID;

import com.mscs.app.common.dto.req.ReqAdviceDto;
import com.mscs.app.common.util.DateTimeUtils;
import com.mscs.app.web.model.Advice;


/**
 * 
 * @author hechunyang
 * @date 2018年4月26日
 * @remark TODO
 */
public class AdviceConverter {

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public static Advice buildAdviceObj(ReqAdviceDto dto) {
		Advice adv = new Advice();
		adv.setId(UUID.randomUUID().toString());
		adv.setPhone(dto.getPhone());
		adv.setSubmitDate(DateTimeUtils.now().toDateTimeString());
		adv.setMail(dto.getMail());
		adv.setAdvice(dto.getAdvice());
		adv.setUsername(dto.getUsername());
		return adv;
	}

}
