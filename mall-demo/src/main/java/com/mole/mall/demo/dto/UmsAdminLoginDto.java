package com.mole.mall.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 * Modified by Cyanix-0721 on 2024/09/29
 */
@Getter
@Setter
public class UmsAdminLoginDto {
    @Schema(title = "用户名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @Schema(title = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
