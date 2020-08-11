package com.lwz.vueblog.controller;

import com.lwz.vueblog.common.lang.Result;
import com.lwz.vueblog.entity.User;
import com.lwz.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MUser)表控制层
 *
 * @author Lw中
 * @since 2020-06-20 08:34:09
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     *
     *  @RequiresAuthentication：验证用户是否登录,说明需要登录之后才能访问的接口
     */

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = this.userService.getById(1L);
        return Result.succ(user);
    }

    /**
     * @Validated：用于校验实体类中的参数是否正确合理
     * */
    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.succ(user);
    }

}