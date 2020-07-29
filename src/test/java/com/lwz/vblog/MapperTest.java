package com.lwz.vblog;

import com.lwz.vblog.bean.Article;
import com.lwz.vblog.bean.Category;
import com.lwz.vblog.service.ArticleService;
import com.lwz.vblog.service.CategoryService;
import com.lwz.vblog.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}

