package com.lwz.vblog.controller;

import com.lwz.vblog.bean.RespBean;
import com.lwz.vblog.service.UserService;
import com.lwz.vblog.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/8/3 23:22
 */

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取当前用户的昵称
     * @return String
     */
    @RequestMapping("/currentUserName")
    public String currentUserName() {
        return Util.getCurrentUser().getNickname();
    }

    /**
     * 获取当前用户id
     * @return Long
     */
    @RequestMapping("/currentUserId")
    public Long currentUserId() {
        return Util.getCurrentUser().getId();
    }

    /**
     * 获取当前用户的邮箱
     * @return String
     */
    @RequestMapping("/currentUserEmail")
    public String currentUserEmail() {
        return Util.getCurrentUser().getEmail();
    }

    /**
     * LWZ TODO : 2020/8/4 SpringSecurity
     * GrantedAuthority属于SpringSecurity范畴
     * 深入下面这个类
     */
    /**
     * 判断当前用户是否为超级管理员
     * @return boolean
     */
    @RequestMapping("/isAdmin")
    public Boolean isAdmin() {
        List<GrantedAuthority> authorities = Util.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("超级管理员")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 更新用户的邮箱
     * @param email
     * @return RespBean
     */
    @RequestMapping(value = "/updateUserEmail", method = RequestMethod.PUT)
    public RespBean updateUserEmail(String email) {
        if (userService.updateUserEmail(email) == 1) {
            // 调用响应类返回操作结果
            return new RespBean("success", "开启成功");
        } else {
            // 调用响应类返回操作结果
            return new RespBean("error", "开启失败");
        }
    }

}
