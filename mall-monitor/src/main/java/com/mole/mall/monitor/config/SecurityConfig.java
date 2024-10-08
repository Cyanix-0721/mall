package com.mole.mall.monitor.config;

import com.mole.mall.monitor.filter.CustomCsrfFilter;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

/**
 * @auther macrozheng
 * @description Spring Boot Admin的Security相关配置
 * @date 2024/1/16
 * @github https://github.com/macrozheng
 *
 * @modified by Cyanix-0721
 * @modification This class has been modified to remove unnecessary SecureConfig and retain existing annotations.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 配置SecurityFilterChain Bean
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 配置登录成功处理器
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl("/");

        // 配置HTTP安全
        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        // 允许访问静态资源
                        .requestMatchers(new AntPathRequestMatcher("/assets/**")).permitAll()
                        // 允许访问健康检查和信息端点
                        .requestMatchers(new AntPathRequestMatcher("/actuator/info")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/actuator/health")).permitAll()
                        // 允许访问登录页面
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        // 允许异步请求
                        .dispatcherTypeMatchers(DispatcherType.ASYNC).permitAll()
                        // 其他请求需要认证
                        .anyRequest().authenticated())
                // 配置表单登录
                .formLogin((formLogin) -> formLogin.loginPage("/login").successHandler(successHandler))
                // 配置注销
                .logout((logout) -> logout.logoutUrl("/logout"))
                // 配置HTTP基本认证
                .httpBasic(Customizer.withDefaults());

        // 添加自定义CSRF过滤器
        http.addFilterAfter(new CustomCsrfFilter(), BasicAuthenticationFilter.class)
                // 配置CSRF保护
                .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/instances", "POST"),
                                new AntPathRequestMatcher("/instances/*", "DELETE"),
                                new AntPathRequestMatcher("/actuator/**")
                        ));

        // 配置记住我功能
        http.rememberMe((rememberMe) -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600));

        return http.build();
    }

    // 配置InMemoryUserDetailsManager Bean
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        // 创建一个内存中的用户
        UserDetails user = User.withUsername("mole")
                .password(passwordEncoder.encode("114514"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // 配置PasswordEncoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}