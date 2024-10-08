package com.mole.mall.auth.controller;

import com.mole.mall.auth.dto.UmsAdminLoginDto;
import com.mole.mall.auth.dto.UmsMemberLoginDto;
import com.mole.mall.auth.service.UmsAdminService;
import com.mole.mall.auth.service.UmsMemberService;
import com.mole.mall.common.api.CommonResult;
import com.mole.mall.common.constant.AuthConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther macrozheng
 * @description 统一认证授权接口
 * @date 2024/1/30
 * <p>
 * Modified by Cyanix-0721 on 2024/10/08.
 */
@Controller
@Tag(name = "AuthController", description = "统一认证授权接口")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private UmsMemberService memberService;

    @Operation(summary = "登录以后返回token")
    @PostMapping(value = "/login")
    @ResponseBody
    public CommonResult<?> login(@RequestParam String clientId, @RequestParam String username, @RequestParam String password) {
        if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)) {
            UmsAdminLoginDto loginDto = new UmsAdminLoginDto();
            loginDto.setUsername(username);
            loginDto.setPassword(password);
            return adminService.login(loginDto);
        } else if (AuthConstant.PORTAL_CLIENT_ID.equals(clientId)) {
            UmsMemberLoginDto loginDto = new UmsMemberLoginDto();
            loginDto.setUsername(username);
            loginDto.setPassword(password);
            return memberService.login(loginDto);
        } else {
            return CommonResult.failed("clientId不正确");
        }
    }
}