package com.mole.mall.auth.service;

import com.mole.mall.auth.dto.UmsMemberLoginDto;
import com.mole.mall.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @auther macrozheng
 * @description 前台会员服务远程调用Service
 * @date 2024/1/30
 * @github https://github.com/macrozheng
 *
 * Modified by Cyanix-0721 on 2024/10/08.
 */
@FeignClient("mall-portal")
public interface UmsMemberService {

    @PostMapping("/sso/login")
    CommonResult<?> login(@RequestBody UmsMemberLoginDto umsMemberLoginDto);
}
