package com.lwz.vueblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lwz.vueblog.entity.Blog;
import java.util.List;

/**
 * (Blog)表服务接口
 *
 * @author Lw中
 * @since 2020-06-20 08:30:02
 */
public interface BlogService extends IService<Blog> {

    public List<Blog> queryAll(Blog blog);

}