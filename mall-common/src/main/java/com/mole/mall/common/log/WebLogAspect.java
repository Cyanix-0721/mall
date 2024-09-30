package com.mole.mall.common.log;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.mole.mall.common.domain.WebLog;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一日志处理切面
 * Created by macro on 2018/4/26.
 * Modified by Cyanix-0721 on 2024/09/28.
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {
	/**
	 * 定义切点，匹配所有控制器中的公共方法
	 */
	@Pointcut("execution(public * com.mole.mall.*.controller.*.*(..))")
	public void webLog() {
	}

	/**
	 * 在切点方法执行前执行
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 目前不需要实现
	}

	/**
	 * 在切点方法返回后执行
	 */
	@AfterReturning(value = "webLog()", returning = "ret")
	public void doAfterReturning(Object ret) throws Throwable {
		// 目前不需要实现
	}

	/**
	 * 环绕切点方法执行
	 *
	 * @param joinPoint 连接点，表示被拦截的方法
	 * @return 方法执行结果
	 * @throws Throwable 如果方法执行过程中抛出异常
	 */
	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		// 获取当前请求对象
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes != null ? attributes.getRequest() : null;

		// 记录请求信息（通过Logstash传入Elasticsearch）
		WebLog webLog = new WebLog();
		Object result = joinPoint.proceed(); // 执行目标方法
		Signature signature = joinPoint.getSignature(); // 获取方法签名
		MethodSignature methodSignature = (MethodSignature) signature; // 强转为方法签名
		Method method = methodSignature.getMethod(); // 获取方法对象

		// 如果方法上有 @Operation 注解，获取其描述信息
		if (method.isAnnotationPresent(Operation.class)) {
			Operation log = method.getAnnotation(Operation.class);
			webLog.setDescription(log.summary());
		}

		long endTime = System.currentTimeMillis();

		String urlStr = request != null ? request.getRequestURL().toString() : null;

		// 设置 WebLog 对象的属性
		webLog.setBasePath(urlStr != null ? StrUtil.removeSuffix(urlStr, URLUtil.url(urlStr).getPath()) : null);
		webLog.setIp(request != null ? request.getRemoteUser() : null);
		webLog.setMethod(request != null ? request.getMethod() : null);
		webLog.setParameter(getParameter(method, joinPoint.getArgs()));
		webLog.setResult(result);
		webLog.setSpendTime((int) (endTime - startTime));
		webLog.setStartTime(startTime);
		webLog.setUri(request != null ? request.getRequestURI() : null);
		webLog.setUrl(request != null ? request.getRequestURL().toString() : null);

		// 构建日志信息
		Map<String, Object> logMap = new HashMap<>();
		logMap.put("url", webLog.getUrl());
		logMap.put("method", webLog.getMethod());
		logMap.put("parameter", webLog.getParameter());
		logMap.put("spendTime", webLog.getSpendTime());
		logMap.put("description", webLog.getDescription());

		// 记录日志信息
		log.info(Markers.appendEntries(logMap), JSONUtil.parse(webLog).toString());

		return result;
	}

	/**
	 * 根据方法和传入的参数获取请求参数
	 *
	 * @param method 方法对象
	 * @param args   方法参数
	 * @return 请求参数对象
	 */
	private Object getParameter(Method method, Object[] args) {
		List<Object> argList = new ArrayList<>();
		Parameter[] parameters = method.getParameters();
		for (int i = 0; i < parameters.length; i++) {
			// 将 RequestBody 注解修饰的参数作为请求参数
			RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
			if (requestBody != null) {
				argList.add(args[i]);
			}
			// 将 RequestParam 注解修饰的参数作为请求参数
			RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
			if (requestParam != null) {
				Map<String, Object> map = new HashMap<>();
				String key = parameters[i].getName();
				if (StringUtils.hasLength(requestParam.value())) {
					key = requestParam.value();
				}
				map.put(key, args[i]);
				argList.add(map);
			}
		}
		if (argList.isEmpty()) {
			return null;
		} else if (argList.size() == 1) {
			return argList.get(0);
		} else {
			return argList;
		}
	}
}