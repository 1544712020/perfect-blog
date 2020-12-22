package com.lwz.vblog.config;

import com.lwz.vblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 15447
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }

    /**
     * 注入自定义的AccessDeniedHandler（认证被拒绝）
     */
    @Bean
    AccessDeniedHandler getAccessDeniedHandler() {
        return new AuthenticationAccessDeniedHandler();
    }

    /**
     * 使用userDetailsService来配置用户信息
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 保证用户登录时使用md5对密码进行处理再与数据库中的密码比对
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 用于给某些路径添加上需要登录或者相关登录的时候才可以访问
                .antMatchers("/admin/category/all").authenticated()
                // /admin/**的URL都需要有超级管理员角色，如果使用.hasAuthority()方法来配置，需要在参数中加上ROLE_,
                // 如下.hasAuthority("ROLE_超级管理员")
                .antMatchers("/admin/**", "/reg").hasRole("超级管理员")
                .anyRequest().authenticated()//其他的路径都是登录后即可访问
                .and()
                .formLogin()
                .loginPage("/login_page")
                //  登录成功处理方式
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
                        out.flush();
                        out.close();
                    }
                })
                // 登录失败处理方式
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
                        out.flush();
                        out.close();
                    }
                })
                // 设置请求登录接口路径
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling()
                // getAccessDeniedHandler()获取自定义的AccessDeniedHandler处理类
                .accessDeniedHandler(getAccessDeniedHandler());
        /**
         * accessDeniedHandler(getAccessDeniedHandler())有何用？
         * AccessDeniedException 主要是在用户在访问受保护资源时被拒绝而抛出的异常。
         * AccessDeniedException 的子类比较少，主要是 CSRF 相关的异常和授权服务异常。
         */
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/blogimg/**", "/index.html", "/static/**");
        /* 配置Spring Security以允许无需身份验证即可访问Swagger URL */
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

}
