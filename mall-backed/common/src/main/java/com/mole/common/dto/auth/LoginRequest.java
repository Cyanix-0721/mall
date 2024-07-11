package com.mole.common.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录DTO")
public class LoginRequest {
	@Schema(description = "用户名")
	private String username;
	@Schema(description = "密码")
	private String password;
}
