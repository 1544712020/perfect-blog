package com.lwz.vblog;

import com.lwz.vblog.bean.Article;
import com.lwz.vblog.bean.Category;
import com.lwz.vblog.bean.Role;
import com.lwz.vblog.bean.User;
import com.lwz.vblog.mapper.ArticleMapper;
import com.lwz.vblog.service.ArticleService;
import com.lwz.vblog.service.CategoryService;
import com.lwz.vblog.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
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
    @Autowired
    ArticleMapper articleMapper;

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

        //  删除用户测试成功
//        int i = userService.deleteUserById(21L);
//        System.out.println(i);

    }

    @Before
    public void testCategory() {
        //  获取所有的文章类别测试成功
//        List<Category> allCategories = categoryService.getAllCategories();
//        for (Category category : allCategories) {
//            System.out.println(category.getCateName());
//        }
        // 添加文章类别测试成功
//        Category category = new Category();
//        category.setCateName("励志文摘");
//        int i = categoryService.addCategory(category);
//        System.out.println(i);

        // 通过id更新文章类别测试成功
//        Category category = new Category();
//        category.setId(66L);
//        category.setCateName("励志文章");
//        int i = categoryService.updateCategoryById(category);
//        System.out.println(i);

        // 批量删除文章类别测试成功
//        boolean b = categoryService.deleteCategoryByIds("66,67");
//        System.out.println(b);
    }

    @After
    public void testArticle() {
        // 获取最近七天的数据测试成功
//        List<Integer> dataStatistics = articleService.getDataStatistics();
//        for (int i = 0; i < dataStatistics.size(); i++) {
//            System.out.println(dataStatistics.get(i));
//        }

        // 通过作者id获取近7次文章访问时间测试成功
//        List<String> categories = articleService.getCategories();
//        for (int i = 0; i < categories.size(); i++) {
//            System.out.println(categories.get(i));
//        }

        // 通过状态获取相同状态文章数量测试成功
//        int articleCountByState = articleService.getArticleCountByState(2, 6L, null);
//        System.out.println(articleCountByState);

        //批量更新文章状态
//        Long[] aids = {115L, 113L};
//        int i = articleMapper.updateArticleState(aids, 1);
//        System.out.println(i);

        //更新单个文章状态测试成功
//        int i = articleService.updateArticleStateById(112);
//        System.out.println(i);

        //通过状态获取文章测试成功
//        List<Article> articleBySate = articleService.getArticleBySate(2, 2, 1, null);
//        for (int i = 0; i < articleBySate.size(); i++) {
//            System.out.println(articleBySate.get(0));
//        }

        //通过id获取文章测试成功
//        Article articleById = articleService.getArticleById(108L);
//        System.out.println(articleById.getPageView());

        // 添加文章，修改文章测试成功
//        Article article = new Article();
//        article.setHtmlContent("test1");
//        article.setState(1);
//        article.setId(124L);
////        int i = articleMapper.updateArticle(article);
//        int i = articleMapper.addNewArticle(article);
//        System.out.println(i);
    }

}

