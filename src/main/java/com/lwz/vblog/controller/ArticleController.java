package com.lwz.vblog.controller;

import com.lwz.vblog.bean.Article;
import com.lwz.vblog.bean.RespBean;
import com.lwz.vblog.service.ArticleService;
import com.lwz.vblog.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "文章操作接口")
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
    @ApiOperation(value = "添加新的文章")
    @ApiImplicitParam(name = "article", value = "Article实体(文章信息)")
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
    @ApiOperation(value = "通过文章的状态获取文章的数量和文章list集合")
    @ApiImplicitParams({@ApiImplicitParam(name = "state", value = "Integer类型(文章状态)"), @ApiImplicitParam(name = "page", value = "Integer类型(当前页数)"),
            @ApiImplicitParam(name = "count", value = "Integer类型(每页显示数量)"), @ApiImplicitParam(name = "keywords", value = "String类型(检索文章关键字)")})
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
    @ApiOperation(value = "通过文章id获取文章信息")
    @ApiImplicitParam(name = "aid", value = "Long类型(文章id)")
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
    @ApiOperation(value = "批量更新文章状态")
    @ApiImplicitParams({@ApiImplicitParam(name = "aids", value = "Long数组(文章ids)"), @ApiImplicitParam(name = "state", value = "Integer类型(更新成什么状态)")})
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
    @ApiOperation(value = "将文章从回收站还原")
    @ApiImplicitParam(name = "articleId", value = "Integer类型(文章id)")
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
    @ApiOperation(value = "获取当前用户的文章访问量")
    @RequestMapping(value = "/dataStatistics", method = RequestMethod.GET)
    public Map<String,Object> dataStatistics() {
        Map<String, Object> map = new HashMap<>();
        List<String> categories = articleService.getCategories();
        List<Integer> dataStatistics = articleService.getDataStatistics();
        map.put("categories", categories);
        map.put("ds", dataStatistics);
        return map;
    }

    /**
     * 图片上传
     */
    @ApiOperation(value = "文章图片上传")
    @ApiImplicitParams({@ApiImplicitParam(name = "request", value = "HttpServletRequest(http请求)"), @ApiImplicitParam(name = "img",value = "MultipartFile(图片)")})
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
         * LWZ TODO : 2020/8/7 下面代码一知半解
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
