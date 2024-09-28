package com.mole.mall.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 * Modify by Cyanix on 2024/9/27.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultCode implements IErrorCode {
	SUCCESS(200, "操作成功"),
	FAILED(500, "操作失败"),
	VALIDATE_FAILED(404, "参数检验失败"),
	UNAUTHORIZED(401, "暂未登录或token已经过期"),
	FORBIDDEN(403, "没有相关权限");

	private long code;
	private String message;
}
