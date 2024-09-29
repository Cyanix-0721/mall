package com.mole.mall.demo.config;

import com.mole.mall.demo.component.FeignRequestInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign客户端的配置类
 * Created by macro on 2019/9/5.
 * Modified by Cyanix on 2024/09/29.
 */
@Configuration
public class FeignConfig {
	/**
	 * 配置Feign的日志级别
	 *
	 * @return FULL日志级别
	 */
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	/**
	 * 配置Feign请求拦截器
	 *
	 * @return FeignRequestInterceptor实例
	 */
	@Bean
	RequestInterceptor requestInterceptor() {
		return new FeignRequestInterceptor();
	}
}