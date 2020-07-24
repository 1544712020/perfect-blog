package com.lwz.vblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/22 20:34
 */

@Mapper
public interface TagsMapper {

    /**
     * 通过id删除标签
     * @param aid
     * @return
     */
    int deleteTagsByAid(Long aid);

    /**
     * 添加标签
     * @param tags
     * @return
     */
    int saveTags(@Param("tags") String[] tags);

    /**
     * 通过标签名称获取标签id
     * @param tagNames
     * @return
     */
    List<Long> getTagsIdByTagName(@Param("tagNames") String[] tagNames);

    /**
     * 为当前文章添加标签
     * @param tagIds
     * @param aid
     * @return
     */
    int saveTags2ArticleTags(@Param("tagIds") List<Long> tagIds, @Param("aid") Long aid);

}
