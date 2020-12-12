package com.lwz.vblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/21 15:42
 * 因为对文章使用了redis缓存，所以需要进行序列化
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {

    private Long id;
    private String title;
    private String mdContent;
    private String htmlContent;
    private String summary;
    private Long cid;
    private Long uid;
    /**
     * 使用时间戳保存文章编辑、文章公布时间
     */
    private Timestamp publishDate;
    private Integer state;
    /**
     * 文章页面浏览次数
     */
    private Integer pageView;
    private Timestamp editTime;

    /**
     * 为了和mapper文件中的select语句以及resultMap中的元素、字段对应，可以为实体类添加需要匹配的属性
     */
    private String[] dynamicTags;
    private String nickname1;
    private String cateName1;
    private List<Tags> tags;
    private String stateStr;

}
