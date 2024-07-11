package com.mole.payment;

import com.mole.common.config.FeignConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.mole.payment.mapper")
@ComponentScan(basePackages = {"com.mole.payment", "com.mole.common"})
@EnableFeignClients(basePackages = "com.mole.common.client", defaultConfiguration = FeignConfig.class)
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("payment")
				.pathsToMatch("/payments/**")
				.build();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Payment Module API"));
	}

	// /v3/api-docs/{groupname}
	@Bean
	public OpenApiCustomiser sortTagsAlphabetically() {
		return openApi -> {
			String groupName = "payment"; // 替换为你的分组名称
			String pathPrefix = "/" + groupName; // 计算路径前缀

			openApi.getPaths().entrySet().stream()
					.sorted(Map.Entry.comparingByKey())
					.forEach(entry -> {
						String path = entry.getKey();
						if (path.startsWith(pathPrefix)) {
							// 移除路径前缀
							String newPath = path.substring(pathPrefix.length());
							// 将路径添加到新的 Map 中
							openApi.getPaths().put(newPath, entry.getValue());
							// 从原始 Map 中移除路径
							openApi.getPaths().remove(path);
						}
					});
		};
	}
}