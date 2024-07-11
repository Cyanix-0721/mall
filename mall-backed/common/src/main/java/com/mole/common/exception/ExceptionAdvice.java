package com.mole.common.exception;

import com.mole.common.entity.Response;
import com.mole.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;

import java.net.BindException;
import java.util.Objects;
import java.util.stream.Collectors;

// 使用@RestControllerAdvice注解，使得这个类可以处理所有Controller抛出的异常
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

	// 处理CommonException异常，返回包含错误信息的ResponseEntity
	@ExceptionHandler(CommonException.class)
	public ResponseEntity<Response<Void>> handleCommonException(CommonException e) {
		log.error("通用异常 -> {}", e.getMessage());
		return processResponse(e);
	}

	// 处理DbException异常，返回包含错误信息的ResponseEntity
	@ExceptionHandler(DbException.class)
	public ResponseEntity<Response<Void>> handleDbException(DbException e) {
		log.error("数据库操作异常 -> ", e);
		return processResponse(e);
	}

	// 处理MethodArgumentNotValidException异常，返回包含错误信息的ResponseEntity
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		String msg = e.getBindingResult().getAllErrors()
				.stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("|"));
		log.error("请求参数校验异常 -> {}", msg);
		log.debug("", e);
		return processResponse(new BadRequestException(msg));
	}

	// 处理BindException异常，返回包含错误信息的ResponseEntity
	@ExceptionHandler(BindException.class)
	public ResponseEntity<Response<Void>> handleBindException(BindException e) {
		log.error("请求参数绑定异常 ->BindException， {}", e.getMessage());
		log.debug("", e);
		return processResponse(new BadRequestException("请求参数格式错误"));
	}

	// 处理NestedServletException异常，返回包含错误信息的ResponseEntity
	@ExceptionHandler(NestedServletException.class)
	public ResponseEntity<Response<Void>> handleNestedServletException(NestedServletException e) {
		log.error("参数异常 -> NestedServletException，{}", e.getMessage());
		log.debug("", e);
		return processResponse(new BadRequestException("请求参数处理异常"));
	}

	// 处理其他Exception异常，返回包含错误信息的ResponseEntity
	@ExceptionHandler(Exception.class)
	public Object handleRuntimeException(Exception e) {
		log.error("其他异常 uri : {} -> ", Objects.requireNonNull(WebUtils.getRequest()).getRequestURI(), e);
		return processResponse(new CommonException("服务器内部异常", 500));
	}

	// 创建一个ResponseEntity，包含错误信息
	private ResponseEntity<Response<Void>> processResponse(CommonException e) {
		return ResponseEntity.status(e.getCode()).body(Response.error(e));
	}
}