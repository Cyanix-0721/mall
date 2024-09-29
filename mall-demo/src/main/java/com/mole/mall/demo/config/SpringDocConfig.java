package com.mole.mall.demo.config;

import com.mole.mall.common.constant.AuthConstant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringDoc相关配置
 * Created by macro on 2024/3/5.
 * Modify by Cyanix-0721 on 2024/9/29.
 */
@Configuration
public class SpringDocConfig implements WebMvcConfigurer {

	private static final String SECURITY_SCHEME_NAME = AuthConstant.JWT_TOKEN_HEADER;

	/**
	 * 配置OpenAPI的基本信息和安全设置
	 *
	 * @return OpenAPI实例
	 */
	@Bean
	public OpenAPI mallAdminOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("mall-demo系统")
						.version("v1.0.0")
						.license(new License().name("Apache 2.0")
								.url("https://github.com/macrozheng/mall-learning")))
				.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
				.components(new Components()
						.addSecuritySchemes(SECURITY_SCHEME_NAME,
								new SecurityScheme()
										.name(SECURITY_SCHEME_NAME)
										.type(SecurityScheme.Type.HTTP)
										.scheme("bearer ")
										.bearerFormat("JWT")));
	}

	/**
	 * 配置视图控制器，用于将`/swagger-ui/`路径重定向到`/swagger-ui/index.html`
	 *
	 * @param registry
	 * 		视图控制器注册表
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/swagger-ui/").setViewName("redirect:/swagger-ui/index.html");
	}

	/**
	 * 配置全局OpenApi自定义器，解决Knife4j配置认证后无法自动添加认证头的问题
	 *
	 * @return GlobalOpenApiCustomizer实例
	 */
	@Bean
	public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
		return openApi -> {
			if (openApi.getPaths() != null) {
				openApi.getPaths().forEach((s, pathItem) -> {
					pathItem.readOperations().forEach(operation -> {
						operation.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME));
					});
				});
			}
		};
	}

}