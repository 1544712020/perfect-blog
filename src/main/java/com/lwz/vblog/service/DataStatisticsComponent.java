package com.lwz.vblog.service;

import com.lwz.vblog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Lw中
 * @date 2020/7/26 19:11
 */

@Component
public class DataStatisticsComponent {

    @Autowired
    ArticleMapper articleMapper;

    /**
     * LWZ TODO : 2020/7/26
     * 此注解入户实现的定时任务
     */
    /** @Scheduled注解执行定时任务*/
    @Scheduled(cron = "1 0 0 * * ?")
    public void pvStatisticsPerDay() {
        articleMapper.pvStatisticsPerDay();
    }

}
