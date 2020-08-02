package com.lwz.vblog.utils;

import com.lwz.vblog.bean.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 工具类：用于获取当前登录用户
 * 此类属于SpringSecurity中的类
 * @author 15447
 */
public class Util {
    public static User getCurrentUser() {
        // 使用springSecurity中的方法获取当前登录用户
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
