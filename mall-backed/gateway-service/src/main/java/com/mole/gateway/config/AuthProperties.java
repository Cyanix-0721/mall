package com.mole.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 这个类用于存储应用程序的认证属性。
 * 它使用Spring的@ConfigurationProperties来绑定应用程序配置文件中前缀为"my.auth"的属性。
 * 这些属性在运行时注入到这个类中。
 *
 * @author wryyyy4444
 */
@Data
@Component
@ConfigurationProperties(prefix = "my.auth")
public class AuthProperties {
	/**
	 * 应该从认证过程中排除的路径列表。
	 * 这些路径将无需任何认证即可访问。
	 */
	private List<String> excludePaths;
}