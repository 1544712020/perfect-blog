package com.lwz.vblog.controller.admin;

import com.lwz.vblog.bean.Article;
import com.lwz.vblog.bean.RespBean;
import com.lwz.vblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lw中
 * @date 2020/8/3 23:23
 * 超级用户专属Controller：用于管理文章
 */

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ArticleService articleService;

    /**
     * 获取回收站中的文章
     * @param page
     * @param count
     * @param keywords
     * @return
     */
    @RequestMapping(value = "/article/all", method = RequestMethod.GET)
    public Map<String, Object> getArticleByStateByAdmin(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
        // 获取回收站中的文章
        List<Article> articles = articleService.getArticleByState(-2, page, count, keywords);
        // 将回收站的文章放入map中，将未放入回收站的文章总数放入map中
        Map<String, Object> map = new HashMap<>();
        map.put("articles", articles);
        map.put("totalCount", articleService.getArticleCountByState(1, null, keywords));
        return map;
    }

    /**
     * 批量更新文章状态
     * @param aids
     * @param state
     * @return
     */
    @RequestMapping(value = "/article/dustbin", method = RequestMethod.PUT)
    public RespBean updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

}
