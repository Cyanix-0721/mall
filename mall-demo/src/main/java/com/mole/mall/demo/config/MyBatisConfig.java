package com.mole.mall.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis相关配置
 * Created by macro on 2019/4/8.
 * Modified by Cyanix-0721 on 2024/09/29.
 */
@Configuration
@MapperScan("com.mole.mall.mbg.mapper")
public class MyBatisConfig {
}
