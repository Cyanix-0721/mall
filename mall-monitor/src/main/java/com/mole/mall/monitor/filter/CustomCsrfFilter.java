package com.mole.mall.monitor.filter;

/*
 * Copyright 2014-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;

/**
 * @auther macrozheng
 * @description 自定义CSRF过滤器
 * @date 2024/1/16
 * @github https://github.com/macrozheng
 *
 * @modified by Cyanix-0721
 * @modification This class has been modified to add comments and retain existing annotations.
 */
public class CustomCsrfFilter extends OncePerRequestFilter {

    // CSRF令牌的Cookie名称
    public static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";

    /**
     * 过滤器的核心方法，处理每个请求
     *
     * @param request     HTTP请求
     * @param response    HTTP响应
     * @param filterChain 过滤器链
     * @throws ServletException 可能抛出的Servlet异常
     * @throws IOException      可能抛出的IO异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 从请求属性中获取CSRF令牌
        CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (csrf != null) {
            // 从请求中获取名为"XSRF-TOKEN"的Cookie
            Cookie cookie = WebUtils.getCookie(request, CSRF_COOKIE_NAME);
            String token = csrf.getToken();
            // 如果Cookie不存在或令牌不匹配，则创建新的Cookie
            if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                cookie = new Cookie(CSRF_COOKIE_NAME, token);
                cookie.setPath("/");
                // 将新的Cookie添加到响应中
                response.addCookie(cookie);
            }
        }
        // 继续过滤器链
        filterChain.doFilter(request, response);
    }

}
