package com.lwz.vblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/21 15:42
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private Long id;
    private String title;
    private String mdContent;
    private String htmlContent;
    private String summary;
    private Long cid;
    private Long uid;
    private Timestamp publishDate;
    private Integer state;
    /**文章页面浏览次数*/
    private Integer pageView;
    private Timestamp editTime;

    private String[] dynamicTags;
    private String nickname1;
    private String cateName1;
    private List<Tags> tags;
    private String stateStr;

}
