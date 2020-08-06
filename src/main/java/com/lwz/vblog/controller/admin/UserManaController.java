package com.lwz.vblog.controller.admin;

import com.lwz.vblog.bean.RespBean;
import com.lwz.vblog.bean.Role;
import com.lwz.vblog.bean.User;
import com.lwz.vblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/8/3 23:23
 * 超级管理员权限
 * 用户管理控制层类
 */

@RestController
@RequestMapping("/admin")
public class UserManaController {

    @Autowired
    UserService userService;

    /**
     * 通过用户昵称查找用户信息
     * @param nickname
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> getUserByNickname(String nickname) {
        return userService.getUserByNickname(nickname);
    }

    /**
     * 通过用户id查找用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * 获取所有角色信息
     * @return
     */
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public List<Role> getAllRole() {
        return userService.getAllRole();
    }

    /**
     * 通过用户id和权限布尔值更新用户的权限状态
     * @param enabled
     * @param uid
     * @return RespBean对象(返回封装后的结果信息类)
     */
    @RequestMapping(value = "/user/enabled", method = RequestMethod.PUT)
    public RespBean updateUserEnabled(Boolean enabled, Long uid) {
        if (userService.updateUserEnabled(enabled, uid) == 1) {
            return new RespBean("success", "更新成功!");
        } else {
            return new RespBean("error", "更新失败!");
        }
    }

    /**
     * 通过用户id删除用户信息
     * @param uid
     * @return RespBean(返回结果信息封装对象)
     */
    @RequestMapping(value = "/user/{uid}", method = RequestMethod.DELETE)
    public RespBean deleteUserById(@PathVariable Long uid) {
        if (userService.deleteUserById(uid) == 1) {
            return new RespBean("success", "删除成功!");
        } else {
            return new RespBean("error", "删除失败!");
        }
    }

    /**
     * 通过用户id以及角色id数组批量更新用户的角色信息
     * @param rids
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/role", method = RequestMethod.PUT)
    public RespBean updateUserRoles(Long[] rids, Long id) {
        if (userService.updateUserRoles(rids, id) == rids.length) {
            return new RespBean("success", "更新成功!");
        } else {
            return new RespBean("error", "更新失败!");
        }
    }

}
