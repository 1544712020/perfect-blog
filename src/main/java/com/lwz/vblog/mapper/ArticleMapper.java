package com.lwz.vblog.mapper;

import com.lwz.vblog.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/22 20:35
 */

@Mapper
public interface ArticleMapper {

    /**
     * 添加文章
     * @param article
     * @return
     */
    int addNewArticle(Article article);

    /**
     * 更新文章
     * @param article
     * @return
     */
    int updateArticle(Article article);

    /**
     * 通过状态获取文章
     * @param state
     * @param start
     * @param count
     * @param uid
     * @param keywords
     * @return
     */
    List<Article> getArticleBySate(@Param("state") Integer state, @Param("start") Integer start,
                                   @Param("count") Integer count, @Param("uid") Long uid,
                                   @Param("keywords") String keywords);

    /**
     * 通过状态获取相同状态文章数量
     * @param state
     * @param uid
     * @param keywords
     * @return
     */
    int getArticleCountByState(@Param("state") Integer state, @Param("uid") Long uid,
                               @Param("keywords") String keywords);

    /**
     * 批量更新文章状态
     * @param aids
     * @param state
     * @return
     */
    int updateArticleState(@Param("aids") Long aids[], @Param("state") Integer state);

    /**
     * 更新单个文章状态
     * @param articleId
     * @param state
     * @return
     */
    int updateArticleStateById(@Param("articleId") Integer articleId, @Param("state") Integer state);

    /**
     * 通过id删除文章
     * @param aids
     * @return
     */
    int deleteArticleById(@Param("aids") Long[] aids);

    /**
     * 通过id获取文章
     * @param aid
     * @return
     */
    Article getArticleById(Long aid);

    /**
     * LWZ TODO : 2020/7/24
     * 此接口有何意义？
     */
    /**
     *
     * @param aid
     */
    void pvIncrement(Long aid);

    /**
     * LWZ TODO : 2020/7/24
     * 此接口有何意义？
     */
    void pvStatisticsPerDay();

    /**
     * 通过作者id获取文章
     * @param uid
     * @return
     */
    List<String> getCategories(Long uid);

    /**
     * 通过用户id获取数据
     * @param uid
     * @return
     */
    List<Integer> getDataStatistics(Long uid);
}
