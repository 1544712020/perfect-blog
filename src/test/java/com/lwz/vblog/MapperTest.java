package com.lwz.vblog;

import com.lwz.vblog.bean.Article;
import com.lwz.vblog.bean.Category;
import com.lwz.vblog.bean.Role;
import com.lwz.vblog.bean.User;
import com.lwz.vblog.service.ArticleService;
import com.lwz.vblog.service.CategoryService;
import com.lwz.vblog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/29 17:12
 *
 * @SpringBootTest：用于注明此测试类是SpringBoot中的测试类
 * @RunWith(SpringRunner.class)：让测试运行于Spring测试环境
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {

    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;

    @Test
    public void testUser() {
        //  通过用户昵称查找用户测试成功
//        UserDetails userDetails = userService.loadUserByUsername("Lw中");
//        System.out.println(userDetails);

        // 注册用户测试成功
//        User user = new User();
//        user.setId(21L);
//        user.setPassword("123");
//        int reg = userService.reg(user);
//        System.out.println(reg);

        // 修改用户邮箱测试成功
//        int i = userService.updateUserEmail("123@qq.com");
//        System.out.println(i);

        // 通过昵称查询用户测试成功
//        List<User> users = userService.getUserByNickname(null);
//        for (int i = 0; i < users.size(); i++) {
//            System.out.println(users.get(i).getUsername());
//        }

        // 获取所有用户角色测试成功
//        List<Role> allRole = userService.getAllRole();
//        for (Role role : allRole) {
//            System.out.println(role.getName());
//        }

        // 设置用户是否可用测试成功
//        int i = userService.updateUserEnabled(true, 17L);
//        System.out.println(i);

        // 通过id查询用户测试成功
//        User userById = userService.getUserById(21L);
//        System.out.println(userById);

        //  根据用户id来更新用户的角色测试成功
//        Long[] ids = {1L};
//        int i = userService.updateUserRoles(ids, 21L);
//        System.out.println(i);

        //  删除用户
        int i = userService.deleteUserById(21L);
        System.out.println(i);

    }

}

