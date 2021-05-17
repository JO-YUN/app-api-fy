package com.mscs.app.common.help;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.exception.AppException;


/**
 * 对象验证帮助类，包含对象的组合验证和日期的比较验证
 * 
 * @author hechunyang
 * 
 */
public class EntityValidateHelper {
	/**
	 * 加入日志
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(EntityValidateHelper.class);

	/**
	 * 校验参数，抛出HotelServiceException，如果正常，则通过
	 * 
	 * @param pojo
	 * @throws HotelServiceException
	 */
	public static void checkEntity(Object pojo) throws AppException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator
				.validate(pojo);
		if (constraintViolations.size() != 0) {
			StringBuffer sb = new StringBuffer();
			for (Iterator<ConstraintViolation<Object>> i = constraintViolations
					.iterator(); i.hasNext();) {
				ConstraintViolation<Object> cv = i.next();
				sb.append(cv.getMessage() + " ");
			}
			logger.error("参数校验异常__", sb.toString());
			throw new AppException(ErrorCode.PARAM_VALIDATE_CODE,
					ErrorCode.PARAM_VALIDATE_MSG + ":" + sb.toString());
		}
	}
}
