package com.lwz.vueblog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwz.vueblog.common.lang.Result;
import com.lwz.vueblog.entity.Blog;
import com.lwz.vueblog.service.BlogService;
import com.lwz.vueblog.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (MBlog)表控制层
 *
 * @author Lw中
 * @since 2020-06-20 08:31:52
 */
@RestController
public class BlogController {
    /**
     * 服务对象
     */
    @Resource
    private BlogService blogService;

    @GetMapping("/queryAll")
    public List<Blog> getAll(Blog blog) {
        List<Blog> blogs = blogService.queryAll(null);
        for (Blog blog1 : blogs) {
            System.out.println(blog1.getTitle());
        }
        return blogs;
    }

    /**
     * 分页查询
     *
     * @param currentPage
     * @return Result list<blog>
     */
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        /** wrapper为条件查询器，通过在.后面添加条件*/
        /** wrapper为条件查询器，通过在wrapper.isNotNull()、eq()后面添加条件*/
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageData);
    }

    /**
     * 通过id查询博客
     *
     * @param id
     * @return Result blog
     */
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "博客已删除");
        return Result.succ(blog);
    }

    /**
     * 编辑文章
     *
     * @param blog
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        Blog temp = null;
        if (blog.getId() != null) {
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的文章
            System.out.println(ShiroUtil.getProfile().getId());
            // longValue()是Long类的一个方法，用来得到Long类中的数值
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(new Date());
            temp.setStatus(0);
        }
        // copyProperties：用于对象之间属性赋值，避免通过getter、setter一个一个属性赋值，后面跟着的属性是不需要赋值改变的属性（hutool工具类中的方法）
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        // 调用mybatis-plus中的方法进行修改博客
        blogService.saveOrUpdate(temp);
        return Result.succ(null);
    }

}