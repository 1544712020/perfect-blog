package com.lwz.vblog;

import com.lwz.vblog.bean.Article;
import com.lwz.vblog.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/10/29 15:15
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    public void testService() {
//        Article article = articleService.getArticleById(108l);
//        System.out.println(article.getTitle());
        List<Article> articles = articleService.getArticleByState(1, 1, 5, "");
        for (Article article : articles) {
            System.out.println(article.getTitle());
        }
    }

}
