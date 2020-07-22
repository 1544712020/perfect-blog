package com.lwz.vblog.mapper;

import com.lwz.vblog.bean.Role;
import com.lwz.vblog.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/22 20:34
 */
public interface UserMapper {

    /**
     * 通过username加载用户信息·
     * @param username
     * @return User
     */
    User loadUserByName(@Param("username") String username);

    /**
     * 添加用户
     * @param user
     * @return Long
     */
    Long reg(User user);

    /**
     * 通过用户id更新用户电子邮件
     * @param email
     * @param id
     * @return int
     */
    int updateUserEmail(@Param("email") String email, @Param("id") Long id);

    /**
     * 通过用户昵称获取用户信息
     * @param nickname
     * @return List<User>
     */
    List<User> getUserByNickname(@Param("nickname") String nickname);

    /**
     * 获取所有用户角色
     * @return List<Role>
     */
    List<Role> getAllRole();

    /**
     * 通过用户id更新用户的权限
     * @param enabled
     * @param uid
     * @return int
     */
    int updateUserEnabled(@Param("enabled") Boolean enabled, @Param("uid") Long uid);

    /**
     * 通过id删除用户
     * @param uid
     * @return int
     */
    int deleteUserById(Long uid);

    /**
     *  通过id删除用户角色
     * @param id
     * @return int
     */
    int deleteUserRolesByUid(Long id);

    /**
     *  通过用户id设置用户角色
     * @param rids
     * @param id
     * @return int
     */
    int setUserRoles(@Param("rids") Long[] rids, @Param("id") Long id);

    /**
     * 通过id获取用户
     * @param id
     * @return
     */
    User getUserById(@Param("id") Long id);
}
