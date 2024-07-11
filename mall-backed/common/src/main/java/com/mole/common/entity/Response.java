package com.mole.common.entity;

import com.mole.common.exception.CommonException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
	private int code;
	private String msg;
	private T data;

	public static Response<Void> ok() {
		return ok(null);
	}

	public static <T> Response<T> ok(T data) {
		return new Response<>(200, "OK", data);
	}

	public static <T> Response<T> error(String msg) {
		return new Response<>(500, msg, null);
	}

	public static <T> Response<T> error(int code, String msg) {
		return new Response<>(code, msg, null);
	}

	public static <T> Response<T> error(CommonException e) {
		return new Response<>(e.getCode(), e.getMessage(), null);
	}

	public static <T> Response<T> errors(List<CommonException> exceptions) {
		String msg = exceptions.stream()
				.map(CommonException::getMessage)
				.reduce((m1, m2) -> m1 + "; " + m2)
				.orElse("Unknown error");
		return new Response<>(500, msg, null);
	}
}