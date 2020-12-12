package com.lwz.vueblog.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwz.vueblog.common.dto.LoginDto;
import com.lwz.vueblog.common.lang.Result;
import com.lwz.vueblog.entity.User;
import com.lwz.vueblog.service.UserService;
import com.lwz.vueblog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Lw中
 * @date 2020/6/21 9:34
 * <p>
 * 登录接口：
 * 登录的逻辑接受账号密码，然后把用户的id生成jwt，返回给前端，
 * 为了后续的jwt的延期把jwt放在header上。
 */

@RestController
public class AccountController {

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserService userService;

  /**
   * 默认账号密码：lwz / 111111
   *
   * @CrossOrigin：用来处理跨域请求
   * @validated：校验数据
   */
  //@CrossOrigin
  @PostMapping("/login")
  public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
    /** 获取user信息，QueryWrapper用于生成sql中的where条件*/
    /** wrapper为条件查询器，通过在wrapper.isNotNull()、eq()后面添加条件*/
    User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
    /** 断定username是否存在，如果不不存在就抛出异常.*/
    Assert.notNull(user, "用户不存在");

    /**  判断用户密码*/
    if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
      return Result.fail("密码不正确");
    }

    /** 通过jwtUtils工具类生成token*/
    String jwt = jwtUtils.generateToken(user.getId());

    /** 设置响应头，将jwt放在header上*/
    response.setHeader("Authorization", jwt);
    response.setHeader("Access-control-Expose-Headers", "Authorization");

    /**
     * 返回结果集
     *
     * MapUtil：操作集合工具类中的一个方法（hutool工具类中的方法）
     * */
    return Result.succ(MapUtil.builder()
      .put("id", user.getId())
      .put("username", user.getUsername())
      .put("avatar", user.getAvatar())
      .put("email", user.getEmail())
      .map()
    );
  }

  /**
   * 退出登录
   *
   * @RequiresAuthentication：验证用户是否登录,说明需要登录之后才能访问的接口
   */
  @RequiresAuthentication
  @GetMapping("/logout")
  public Result logout() {
    SecurityUtils.getSubject().logout();
    return Result.succ(null);
  }

}
