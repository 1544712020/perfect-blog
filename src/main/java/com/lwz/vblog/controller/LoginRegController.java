package com.lwz.vblog.controller;

import com.lwz.vblog.bean.RespBean;
import com.lwz.vblog.bean.User;
import com.lwz.vblog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lw中
 * @date 2020/8/3 23:22
 * 登陆注册Controller
 */

@Api(value = "登录注册结果信息接口")
@RestController
public class LoginRegController {

    @Autowired
    UserService userService;

    /**
     * 登陆失败
     *
     * @return
     */
    @ApiOperation(value = "响应登录失败信息")
    @RequestMapping(value = "/login_error", method = RequestMethod.GET)
    public RespBean loginError() {
        return new RespBean("error", "登录失败!");
    }

    /**
     * 登录成功
     *
     * @return
     */
    @ApiOperation(value = "响应登录成功信息")
    @RequestMapping(value = "/login_success", method = RequestMethod.GET)
    public RespBean loginSuccess() {
        return new RespBean("success", "登录成功!");
    }

    /**
     * 如果自动跳转到这个页面，说明用户未登录
     *
     * @return
     */
    @ApiOperation(value = "响应尚未登录信息")
    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public RespBean loginPage() {
        return new RespBean("error", "尚未登录，请登录!");
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "注册用户")
    @ApiImplicitParam(name = "user", value = "User实体(用户信息)")
    @PostMapping("/reg")
    public RespBean reg(User user) {
        int result = userService.reg(user);
        if (result == 0) {
            //成功
            return new RespBean("success", "注册成功!");
        } else if (result == 1) {
            return new RespBean("error", "用户名重复，注册失败!");
        } else {
            //失败
            return new RespBean("error", "注册失败!");
        }
    }

}
