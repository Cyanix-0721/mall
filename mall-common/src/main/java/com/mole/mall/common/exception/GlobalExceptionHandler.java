package com.mole.mall.common.exception;

import com.mole.mall.common.api.CommonResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * Created by macro on 2020/2/27.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 处理自定义API异常
	 *
	 * @param e ApiException 异常
	 * @return 包含错误码或错误信息的 CommonResult
	 */
	@ResponseBody
	@ExceptionHandler(value = ApiException.class)
	public CommonResult<?> handle(ApiException e) {
		if (e.getErrorCode() != null) {
			return CommonResult.failed(e.getErrorCode());
		}
		return CommonResult.failed(e.getMessage());
	}

	/**
	 * 处理方法参数验证异常
	 *
	 * @param e MethodArgumentNotValidException 异常
	 * @return 包含验证错误信息的 CommonResult
	 */
	@ResponseBody
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public CommonResult<?> handleValidException(MethodArgumentNotValidException e) {
		return getCommonResult(e.getBindingResult());
	}

	/**
	 * 处理绑定异常
	 *
	 * @param e BindException 异常
	 * @return 包含验证错误信息的 CommonResult
	 */
	@ResponseBody
	@ExceptionHandler(value = BindException.class)
	public CommonResult<?> handleValidException(BindException e) {
		return getCommonResult(e.getBindingResult());
	}

/**
 * 获取通用结果
 *
 * @param bindingResult 绑定结果
 * @return 包含验证错误信息的 CommonResult
 */
private CommonResult<?> getCommonResult(BindingResult bindingResult) {
	String message = null;
	if (bindingResult.hasErrors()) {
		FieldError fieldError = bindingResult.getFieldError();
		if (fieldError != null) {
			message = fieldError.getField() + fieldError.getDefaultMessage();
		}
	}
	return CommonResult.validateFailed(message);
}
}