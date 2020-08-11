package com.lwz.vueblog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lw中
 * @date 2020/6/20 8:14
 *
 * mybatis-plus配置类
 */

@Configuration
@EnableAutoConfiguration
@MapperScan("com.lwz.vueblog.dao")
public class MybatisPlusConfig {

    /**
     * 配置分页拦截器
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        /** 添加分页插件*/
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
