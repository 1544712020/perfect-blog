package com.lwz.vblog.service;

import com.lwz.vblog.bean.Role;
import com.lwz.vblog.bean.User;
import com.lwz.vblog.mapper.RolesMapper;
import com.lwz.vblog.mapper.UserMapper;
import com.lwz.vblog.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/25 16:49
 */

@Service
@CacheConfig(cacheNames = "User")
@Transactional
public class UserService implements UserDetailsService {

    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired(required = false)
    RolesMapper rolesMapper;
    /**
     * 注入此类用于加密
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 通过用户名查询用户
     * UserService实现了UserDetailsService，用于登录认证
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByName(s);
        if (user == null) {
            // 避免返回null，这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
            return new User();
        }
        //查询用户的角色信息，并返回存入user实体中（因为角色信息是用中间表存储的，需要分开查询）
        List<Role> roles = rolesMapper.getRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }

    /**
     * 添加用户
     *
     * @param user
     * @return 0表示成功
     * 1表示用户名重复
     * 2表示失败
     */
    public int reg(User user) {
        User loadUserByUsername = userMapper.loadUserByName(user.getUsername());
        if (loadUserByUsername != null) {
            return 1;
        }
        // 插入用户之前，对用户密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 设置用户可用
        user.setEnabled(true);
        long result = userMapper.reg(user);
        System.out.println(user.getId() + user.getUsername());
        //配置用户的角色，默认都是普通用户
        String[] roles = new String[]{"2"};
        // 向role_user中间表插入关联数据
        int i = rolesMapper.addRoles(roles, user.getId());
        boolean b = i == roles.length && result == 1;
        if (b) {
            return 0;
        } else {
            return 2;
        }
    }

    /**
     * @param email
     * @return
     */
    public int updateUserEmail(String email) {
        return userMapper.updateUserEmail(email, Util.getCurrentUser().getId());
    }

    /**
     * 通过昵称查询用户
     *
     * @param nickname
     * @return
     */
    public List<User> getUserByNickname(String nickname) {
        List<User> list = userMapper.getUserByNickname(nickname);
        return list;
    }

    /**
     * 获取所有用户角色
     *
     * @return
     */
    @Cacheable(cacheNames = "Role")
    public List<Role> getAllRole() {
        return userMapper.getAllRole();
    }

    /**
     * 设置用户是否可用
     *
     * @param enabled
     * @param uid
     * @return
     */
    public int updateUserEnabled(Boolean enabled, Long uid) {
        return userMapper.updateUserEnabled(enabled, uid);
    }

    /**
     * 通过用户id删除用户
     *
     * @param uid
     * @return
     */
    public int deleteUserById(Long uid) {
        return userMapper.deleteUserById(uid);
    }

    /**
     * 根据用户id来更新用户的角色
     *
     * @param rids
     * @param id
     * @return
     */
    public int updateUserRoles(Long[] rids, Long id) {
        int i = userMapper.deleteUserRolesByUid(id);
        return userMapper.setUserRoles(rids, id);
    }

    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

}
