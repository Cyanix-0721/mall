package com.mole.common.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "密码DTO")
public class password {
	@Schema(description = "旧密码")
	private String oldPassword;
	@Schema(description = "新密码")
	private String newPassword;
}
