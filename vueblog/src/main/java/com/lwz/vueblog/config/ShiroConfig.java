package com.lwz.vueblog.config;

import com.lwz.vueblog.shiro.AccountRealm;
import com.lwz.vueblog.shiro.JwtFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro启用注解拦截控制器
 */

@Configuration
public class ShiroConfig {

  @Resource
  JwtFilter jwtFilter;

  /**
   * 给SessionManager注入redis，保存session数据至redis中
   */
  @Bean
  public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
    DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

    // inject redisSessionDAO
    sessionManager.setSessionDAO(redisSessionDAO);
    return sessionManager;
  }

  /**
   * 给SecurityManager注入SessionManager、accountRealm、redis缓存，保存权限数据至redis中
   */
  @Bean
  public DefaultWebSecurityManager securityManager(AccountRealm accountRealm,
                                                   SessionManager sessionManager,
                                                   RedisCacheManager redisCacheManager) {

    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(accountRealm);

    //inject sessionManager
    securityManager.setSessionManager(sessionManager);

    // inject redisCacheManager
    securityManager.setCacheManager(redisCacheManager);
    return securityManager;
  }

  /**
   * 设置过滤链，添加被拦截的路径
   */
  @Bean
  public ShiroFilterChainDefinition shiroFilterChainDefinition() {
    DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

    Map<String, String> filterMap = new LinkedHashMap<>();

    filterMap.put("/**", "jwt");
    chainDefinition.addPathDefinitions(filterMap);
    return chainDefinition;
  }

  /**
   * 设置过滤器，添加过滤链以及安全管理
   */
  @Bean("shiroFilterFactoryBean")
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
                                                       ShiroFilterChainDefinition shiroFilterChainDefinition) {
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    shiroFilter.setSecurityManager(securityManager);

    Map<String, Filter> filters = new HashMap<>();
    filters.put("jwt", jwtFilter);
    shiroFilter.setFilters(filters);

    Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();

    shiroFilter.setFilterChainDefinitionMap(filterMap);
    return shiroFilter;
  }

}
