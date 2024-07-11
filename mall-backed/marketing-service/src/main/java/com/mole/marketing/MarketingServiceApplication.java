package com.mole.marketing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.mole.common")
@EnableDiscoveryClient
@MapperScan("com.mole.marketing.mapper")
@ComponentScan(basePackages = {"com.mole.marketing", "com.mole.common"})
public class MarketingServiceApplication {
	public static void main(String[] args) {

		SpringApplication.run(MarketingServiceApplication.class, args);
	}
}