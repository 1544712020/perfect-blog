package com.lwz.vueblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Lw中
 * @date 2020/6/20 14:57
 * <p>
 * 跨域配置
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    /** 为路径注册跨域资源请求*/
    registry.addMapping("/**")
      /** 表示支持的域*/
      .allowedOrigins("*")
      /** 允许的请求方法*/
      .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
      .allowCredentials(true)
      /** 请求的有效期*/
      .maxAge(3600)
      /** 表示允许的请求头*/
      .allowedHeaders("*");
  }

}
