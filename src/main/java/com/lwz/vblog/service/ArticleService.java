package com.lwz.vblog.service;

import com.lwz.vblog.bean.Article;
import com.lwz.vblog.mapper.ArticleMapper;
import com.lwz.vblog.mapper.TagsMapper;
import com.lwz.vblog.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/26 19:07
 */

@Service
@Transactional
public class ArticleService {

    @Autowired(required = false)
    ArticleMapper articleMapper;
    @Autowired(required = false)
    TagsMapper tagsMapper;

    /**
     * 添加文章
     * @return
     */
    public int addNewArticle(Article article) {
        //处理文章摘要
        // 如果文章没有摘要
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            //获取html形式的文章内容
            String stripHtml = stripHtml(article.getHtmlContent());
            // 截取文章内容的前面50个字符充当摘要
            article.setSummary(stripHtml.substring(0, stripHtml.length() > 50 ? 50 : stripHtml.length()));
        }
        // 如果文章的id为-1，将文章插入数据库
        if (article.getId() == -1) {
            //添加操作
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            article.setEditTime(timestamp);
            //设置当前用户
            article.setUid(Util.getCurrentUser().getId());
            int i = articleMapper.addNewArticle(article);
            //打标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            }
            return i;
            // 如果文章已存在数据库中，对文章进行更新操作
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (article.getState() == 1) {
                //设置发表日期
                article.setPublishDate(timestamp);
            }
            //更新
            article.setEditTime(new Timestamp(System.currentTimeMillis()));
            int i = articleMapper.updateArticle(article);
            //修改标签
            String[] dynamicTags = article.getDynamicTags();
            if (dynamicTags != null && dynamicTags.length > 0) {
                int tags = addTagsToArticle(dynamicTags, article.getId());
                if (tags == -1) {
                    return tags;
                }
            } else {
                tagsMapper.deleteTagsByAid(article.getId());
            }
            return i;
        }
    }
    private int addTagsToArticle(String[] dynamicTags, Long aid) {
        //1.删除该文章目前所有的标签（中间表）
        tagsMapper.deleteTagsByAid(aid);
        //2.将上传上来的标签全部存入数据库（标签表）
        tagsMapper.saveTags(dynamicTags);
        //3.查询这些标签的id（标签表）
        List<Long> tIds = tagsMapper.getTagsIdByTagName(dynamicTags);
        //4.重新给文章设置标签（中间表）
        int i = tagsMapper.saveTags2ArticleTags(tIds, aid);
        return i == dynamicTags.length ? i : -1;
    }

    /**
     * 将文章内容变成html形式的输出
     * @param content
     * @return
     */
    public String stripHtml(String content) {
        // 将正则表达式中的内容变为空，进而将文章变为html形式
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    /**
     * 通过id获取文章
     * @param aid
     * @return
     */
    public Article getArticleById(Long aid) {
        // 通过id查询文章
        Article article = articleMapper.getArticleById(aid);
        // 给文章添加一个访问量
        articleMapper.pvIncrement(aid);
        return article;
    }

    /**
     * 通过状态获取文章
     * @param state
     * @param count
     * @param page
     * @param keywords
     * @return
     */
    public List<Article> getArticleByState(Integer state,
                                   Integer page, Integer count,
                                   String keywords) {
        int start = (page -1) * count;
        Long uid = Util.getCurrentUser().getId();
        return articleMapper.getArticleByState(state, start, count, uid,keywords);
    }

    /**
     * 更新单个文章状态(将文章从回收站拿回来)
     * @param articleId
     * @return
     */
    public int restoreArticle(Integer articleId) {
        // 文章状态默认为1
        return articleMapper.updateArticleStateById(articleId, 1);
    }

    /**
     * 批量更新文章状态
     * @param aids
     * @param state
     * @return
     */
    public int updateArticleState(Long aids[], Integer state) {
        // 如果文章在回收站，将文章删除
        if (state == 2) {
            return articleMapper.deleteArticleById(aids);
        } else {
            return articleMapper.updateArticleState(aids, 2);
        }
    }

    /**
     * 通过状态获取相同状态文章数量
     * @param state
     * @param uid
     * @param keywords
     * @return
     */
    public int getArticleCountByState(Integer state, Long uid, String keywords) {
        return articleMapper.getArticleCountByState(state, uid, keywords);
    }

    /**
     * 给文章添加每天用户访问量数据
     */
    public void pvStatisticsPerDay() {
        articleMapper.pvStatisticsPerDay();
    }

    /**
     * 通过作者id获取近7次文章访问时间
     * @return
     */
    public List<String> getCategories() {
        return articleMapper.getCategories(Util.getCurrentUser().getId());
    }
    /**
     * 通过用户id获取近7天文章访问数据
     * @return
     */
    public List<Integer> getDataStatistics() {
        return articleMapper.getDataStatistics(Util.getCurrentUser().getId());
    }

}
