package com.lwz.vueblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwz.vueblog.entity.Blog;
import com.lwz.vueblog.dao.BlogDao;
import com.lwz.vueblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Blog)表服务实现类
 *
 * @author Lw中
 * @since 2020-06-20 08:30:02
 */
@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<BlogDao, Blog> implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Override
    public List<Blog> queryAll(Blog blog) {
        return blogDao.queryAll(null);
    }
}