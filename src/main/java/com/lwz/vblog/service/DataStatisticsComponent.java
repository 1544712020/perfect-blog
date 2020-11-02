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

    @Autowired(required = false)
    ArticleMapper articleMapper;

    /**
     * @Scheduled：此注解入户实现的定时任务
     * cron有6-7个字符组成(秒 分钟 一天的第几个小时 某月的第几天 月 星期几 年)
     * 0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
     * 下面的是每天的零点，第一秒执行下面的定时任务
     */
    @Scheduled(cron = "1 0 0 * * ?")
    public void pvStatisticsPerDay() {
        articleMapper.pvStatisticsPerDay();
    }

}
