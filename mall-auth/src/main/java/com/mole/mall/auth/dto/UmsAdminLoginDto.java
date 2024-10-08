package com.mole.mall.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 管理员登录参数
 * Created by macro on 2018/4/26.
 * Modified by Cyanix-0721 on 2024/10/08.
 */
@Data
public class UmsAdminLoginDto {
    @NotEmpty
    @Schema(title = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @NotEmpty
    @Schema(title = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}