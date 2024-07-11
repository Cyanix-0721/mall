package com.mole.gateway.filter;

import com.auth0.jwt.interfaces.Claim;
import com.mole.auth.utils.JwtUtil;
import com.mole.common.exception.CommonException;
import com.mole.gateway.config.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * 这个类是一个全局的认证过滤器。
 * 它检查进来的请求是否有有效的JWT令牌，以及请求路径是否被排除在认证之外。
 * 如果请求路径没有被排除，并且JWT令牌无效，它会返回401未授权状态。
 *
 * @author njslyr
 */
@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

	private final AuthProperties authProperties;
	private final StringRedisTemplate redisTemplate;
	// antPathMatcher是Spring提供的一个用于路径匹配的工具类,无法直接注入
	AntPathMatcher antPathMatcher = new AntPathMatcher();

	/**
	 * 这个方法过滤进来的请求。
	 * 它检查请求路径是否被排除在认证之外。
	 * 如果没有，它会检查请求是否有有效的JWT令牌。
	 * 如果JWT令牌无效，它会返回401未授权状态。
	 *
	 * @param exchange 当前的服务器web交换
	 * @param chain    提供了一种方式来委托给下一个过滤器
	 * @return 一个Mono<Void>，表示请求处理完成时的指示
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 获取请求头
		ServerHttpRequest request = exchange.getRequest();
		// 判断是否是可以放行的路径
		if (isExclude(request)) {
			return chain.filter(exchange);
		}
		// 需要校验权限的路径
		List<String> authorization = request.getHeaders().get("Authorization");
		String token;
		// 获取token
		if (authorization != null && !authorization.isEmpty()) {
			token = authorization.get(0);
			if (token.startsWith("Bearer ")) {
				token = token.substring(7);
				System.out.println("accept token: " + token);
			}
		} else {
			token = null;
		}
		// 如果token在黑名单中，返回401
		if (isTokenInBlacklist(token)) {
			throw new CommonException("Token is in blacklist", 401);
		}
		String username = null;
		try {
			JwtUtil jwtUtil = new JwtUtil(86400000);
			Map<String, Claim> claims = jwtUtil.getClaims(token);
			if (claims.containsKey("username")) {
				Claim claim = claims.get("username");
				username = claim.asString();
			}
			System.out.println(username);
			// 传递 username 和 Authorization
			String finalUsername = username;
			String finalToken = token;
			ServerWebExchange serverWebExchange = exchange.mutate().request(builder -> {
				builder.header("Authorization", "Bearer " + finalToken);
				builder.header("user-info", finalUsername);
			}).build();
			return chain.filter(serverWebExchange);
		} catch (Exception e) {
			System.out.println(e);
			// 如果出错，说明用户没有权限
			ServerHttpResponse response = exchange.getResponse();
			response.setStatusCode(HttpStatus.UNAUTHORIZED);        // 设置401，用户无权限标志位
			return response.setComplete();              // 返回异步调用
		}
	}

	/**
	 * 这个方法检查请求路径是否被排除在认证之外。
	 *
	 * @param request 当前的服务器http请求
	 * @return 一个布尔值，表示请求路径是否被排除在认证之外
	 */
	private boolean isExclude(ServerHttpRequest request) {
		String url = request.getPath().toString();
		for (String excludeUrl : authProperties.getExcludePaths()) {
			if (antPathMatcher.match(excludeUrl, url)) {
				System.out.println("true");
				return true;
			}
		}
		return false;
	}

	/**
	 * 这个方法返回这个过滤器的顺序。
	 * 过滤器的顺序决定了它何时被应用。
	 * 顺序较低的过滤器首先被应用。
	 *
	 * @return 这个过滤器的顺序
	 */
	@Override
	public int getOrder() {
		return 0;
	}

	public boolean isTokenInBlacklist(String token) {
		return Boolean.TRUE.equals(redisTemplate.hasKey(token));
	}
}