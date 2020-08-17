package com.lwz.vblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * LWZ TODO : 2020/8/10 @EnableScheduling定时任务
 * 定时任务
 */
/**
 * @author 15447
 */
@SpringBootApplication
@EnableScheduling
public class VblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(VblogApplication.class, args);
    }

}
