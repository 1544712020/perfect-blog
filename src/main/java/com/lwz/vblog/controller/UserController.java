package com.lwz.vblog.controller;

import com.lwz.vblog.bean.RespBean;
import com.lwz.vblog.service.UserService;
import com.lwz.vblog.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api("操作用户接口")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取当前用户的昵称
     *
     * @return String
     */
    @ApiOperation(value = "获取当前登录用户姓名")
    @RequestMapping(value = "/currentUserName", method = RequestMethod.GET)
    public String currentUserName() {
        return Util.getCurrentUser().getNickname();
    }

    /**
     * 获取当前用户id
     *
     * @return Long
     */
    @ApiOperation(value = "获取当前登录用户ID")
    @RequestMapping(value = "/currentUserId", method = RequestMethod.GET)
    public Long currentUserId() {
        return Util.getCurrentUser().getId();
    }

    /**
     * 获取当前用户的邮箱
     *
     * @return String
     */
    @ApiOperation(value = "获取当前登录用户邮箱")
    @RequestMapping(value = "/currentUserEmail", method = RequestMethod.GET)
    public String currentUserEmail() {
        return Util.getCurrentUser().getEmail();
    }

    /**
     * 判断当前用户是否为超级管理员
     * 1：通过工具类获取当前登录用户
     * 2：通过getAuthorities方法获取该用户被授予的角色权限
     * 2：遍历查找是否存在超级管理员的角色
     *
     * @return boolean
     */
    @ApiOperation(value = "判断当前用户是否为超级管理员")
    @RequestMapping(value = "/isAdmin", method = RequestMethod.GET)
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
     *
     * @param email
     * @return RespBean
     */
    @ApiOperation(value = "更新用户的邮箱")
    @ApiImplicitParam(name = "email", value = "String类型(新的邮箱)")
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
