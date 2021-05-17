package com.mscs.app.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.mscs.app.common.ErrorCode;
import com.mscs.app.common.dto.ResponseDto;
import com.mscs.app.common.util.ExceptionUtil;


@ControllerAdvice
public class SpringExceptionHandler {

	private static Logger logger = LoggerFactory
			.getLogger(SpringExceptionHandler.class);
	@Autowired
	private ExceptionUtil exceptionUtil;
	
	/**
	 * 全局处理Exception 错误的情况下返回500
	 * 
	 * @param ex
	 * @param req
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class })
	@ResponseBody
	public ResponseDto handleOtherExceptions(final Exception ex,
			final WebRequest req) {
		System.out.println("class"+ex.getStackTrace()[0].getClassName());
		/*StackTraceElement[] st = ex.getStackTrace();
		for (StackTraceElement stackTraceElement : st) {
		String exclass = stackTraceElement.getClassName();
		String method = stackTraceElement.getMethodName();
		System.out.println("class"+exclass);
		System.out.println("method"+method);
		}*/
		String classname = ex.getStackTrace()[0].getClassName(); //获取调用者的类名
		String method_name = ex.getStackTrace()[0].getMethodName(); //获取调用者的方法名
		if (ex instanceof AppException) {
			logger.error("业务逻辑异常", ex);
			//记录日志
//			
//			RZ rz=exceptionUtil.buildRZ(((AppException) ex).getCode(), ex.getMessage()+"发生在"+classname+"的"+method_name+"方法上");
//			exceptionUtil.addRz(rz);
			return ResponseDto.buildFail().setErrormsg(ex.getMessage())
					.setErrorcode(((AppException) ex).getCode());
		}
		logger.error("系统异常,请查看日志" + ex.getLocalizedMessage(), ex);
		//记录日志
//				RZ rz=exceptionUtil.buildRZ(ErrorCode.SYS_ERROR_CODE,ErrorCode.SYS_ERROR_MSG+"发生在"+classname+"的"+method_name+"方法上");
//				exceptionUtil.addRz(rz);
		return ResponseDto.buildFail().setErrormsg(ErrorCode.SYS_ERROR_MSG)
				.setErrorcode(ErrorCode.SYS_ERROR_CODE);
	}
}