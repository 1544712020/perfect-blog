package com.lwz.vblog.controller;

import com.lwz.vblog.bean.Article;
import com.lwz.vblog.bean.RespBean;
import com.lwz.vblog.service.ArticleService;
import com.lwz.vblog.utils.Util;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Lw中
 * @date 2020/8/3 23:21
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    ArticleService articleService;

    /**
     * 添加新文章
     * @param article
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewArticle(Article article) {
        int result = articleService.addNewArticle(article);
        if (result == 1) {
            return new RespBean("success", article.getId() + "");
        } else {
            return new RespBean("error", article.getState() == 0 ? "文章保存失败!" : "文章发表失败!");
        }
    }

    /**
     * 通过文章state获取文章数量和文章List集合
     * @param state
     * @param page
     * @param count
     * @param keywords
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Map<String, Object> getArticleByState(@RequestParam(value = "state", defaultValue = "-1") Integer state,
                                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                 @RequestParam(value = "count", defaultValue = "6") Integer count,
                                                 String keywords) {
        int totalCount = articleService.getArticleCountByState(state, Util.getCurrentUser().getId(),keywords);
        List<Article> articles = articleService.getArticleByState(state, page, count,keywords);
        Map<String, Object> map = new HashMap<>();
        // 将文章总数存储到map种
        map.put("totalCount", totalCount);
        // 将文章List集合放入map种存储
        map.put("articles", articles);
        return map;
    }

    /**
     * 通过id获取文章
     * @param aid
     * @return
     */
    @RequestMapping(value = "/{aid}", method = RequestMethod.GET)
    public Article getArticleById(@PathVariable Long aid) {
        return articleService.getArticleById(aid);
    }

    /**
     * 更新文章状态
     * @param aids
     * @param state
     * @return
     */
    @RequestMapping(value = "/dustbin", method = RequestMethod.PUT)
    public RespBean updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    /**
     * 将文章从回收站还原
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/restore", method = RequestMethod.PUT)
    public RespBean restoreArticle(Integer articleId) {
        if (articleService.restoreArticle(articleId) == 1) {
            return new RespBean("success", "还原成功!");
        }
        return new RespBean("error", "还原失败!");
    }

    /**
     * 获取文章访问数据
     * @return
     */
    @RequestMapping("/dataStatistics")
    public Map<String,Object> dataStatistics() {
        Map<String, Object> map = new HashMap<>();
        List<String> categories = articleService.getCategories();
        List<Integer> dataStatistics = articleService.getDataStatistics();
        map.put("categories", categories);
        map.put("ds", dataStatistics);
        return map;
    }

    /**
     * LWZ TODO : 2020/8/7 文件上传
     * 文件上传代码不熟悉
     */
    /**
     * 图片上传
     */
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public RespBean uploadImg(HttpServletRequest request, MultipartFile img) {
        // StringBuffer用于拼接字符串
        StringBuffer url =new StringBuffer();
        // 设置文件路径
        String filePath = "/blogimg/" + sdf.format(new Date());
        //设置图片存储目录路径
        String imgFolderPath = request.getServletContext().getRealPath(filePath);
        // 创建文件夹
        File imgFolder = new File(imgFolderPath);
        if (!imgFolder.exists()) {
            // 如果文件夹不存在添加新文件夹
            imgFolder.mkdirs();
        }
        /**
         * LWZ TODO : 2020/8/7 图片上传bug
         * 下面代码一知半解
         */
        url.append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append(request.getContextPath())
                .append(filePath);
        System.out.println(img.getOriginalFilename());
        String imgName = UUID.randomUUID() + "_" + img.getOriginalFilename().replaceAll(" ", "");
        try {
            IOUtils.write(img.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            url.append("/").append(imgName);
            return new RespBean("success", url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RespBean("error", "上传失败!");
    }

}
