package com.mole.common.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 这个类提供了负载均衡器的自定义配置。
 * 它定义了一个创建随机负载均衡器的bean。
 */
@Configuration
public class CustomLoadBalancerConfiguration {

	/**
	 * 这个方法创建了一个ReactorLoadBalancer类型的bean。
	 * 负载均衡器使用随机策略进行负载均衡。
	 *
	 * @param environment               从中获取负载均衡器名称的环境。
	 * @param loadBalancerClientFactory 用于创建负载均衡器的工厂。
	 * @return 一个ReactorLoadBalancer实例。
	 */
	@Bean
	ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
	                                                        LoadBalancerClientFactory loadBalancerClientFactory) {
		String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		System.out.println("======== 负载均衡策略：随机 ========");
		return new RandomLoadBalancer(loadBalancerClientFactory
				.getLazyProvider(name, ServiceInstanceListSupplier.class),
				name);
	}

}