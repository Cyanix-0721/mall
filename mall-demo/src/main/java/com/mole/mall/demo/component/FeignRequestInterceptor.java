package com.mole.mall.demo.component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

/**
 * 用于Feign传递请求头的拦截器
 * Created by macro on 2019/10/18.
 * Modified by Cyanix-0721 on 2024/09/29.
 */
public class FeignRequestInterceptor implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate requestTemplate) {
		// 获取当前请求的属性
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null) {
			// 获取当前HTTP请求
			HttpServletRequest request = attributes.getRequest();
			// 获取请求中的所有头信息
			Enumeration<String> headerNames = request.getHeaderNames();
			// 在调用需要认证的内部接口时，需要获取原认证头并设置到requestTemplate中
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String name = headerNames.nextElement();
					// 传递content-length头会导致java.io.IOException: Incomplete output stream问题
					if ("content-length".equalsIgnoreCase(name)) {
						continue;
					}
					// 获取头的值
					String values = request.getHeader(name);
					// 将头信息设置到requestTemplate中
					requestTemplate.header(name, values);
				}
			}
		}
	}
}