package com.lwz.vblog.service;

import com.lwz.vblog.bean.Article;
import com.lwz.vblog.mapper.ArticleMapper;
import com.lwz.vblog.mapper.TagsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lw中
 * @date 2020/7/26 19:07
 */

@Service
@Transactional
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagsMapper tagsMapper;



}
