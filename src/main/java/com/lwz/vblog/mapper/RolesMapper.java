package com.lwz.vblog.mapper;

import com.lwz.vblog.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/22 20:34
 */

@Mapper
public interface RolesMapper {

    /**
     * 通过用户id给用户添加角色
     *
     * @param roles
     * @param uid
     * @return
     */
    int addRoles(@Param("roles") String[] roles, @Param("uid") Long uid);

    /**
     * 通过用户id获取用户的角色
     *
     * @param uid
     * @return
     */
    List<Role> getRolesByUid(Long uid);

}
