package com.mole.mall.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解，有该注解的缓存方法会抛出异常。
 * <p>
 * 该注解可用于标记缓存相关的异常方法。它应当用于方法上，并且在运行时保留。
 * <p>
 * 使用示例：
 *
 * <pre>
 * {@code
 * @CacheException
 * public void someCacheMethod() {
 *     // 方法实现
 * }
 * }
 * </pre>
 *
 * @Documented - 表示该注解会被javadoc等工具记录。
 * @Target(ElementType.METHOD) - 表示该注解只能用于方法。
 * @Retention(RetentionPolicy.RUNTIME) - 表示该注解会在运行时保留。
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheException {
}