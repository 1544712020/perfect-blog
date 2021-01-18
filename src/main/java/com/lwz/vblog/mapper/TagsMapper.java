package com.lwz.vblog.mapper;

import com.lwz.vblog.bean.Tags;
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
     * 通过id删除单个标签信息
     * @param aid
     * @return
     */
    int deleteTagsByAid(Long aid);

    /**
     * 批量删除标签信息
     * @param ids
     * @return
     */
    int deleteTagsByAid1(@Param("ids") String[] ids);

    /**
     * 批量添加标签
     *
     * @param tags
     * @return
     */
    int saveTags(@Param("tags") String[] tags);

    /**
     * 添加单个标签信息
     * @param tagName
     * @return
     */
    int saveTags1(Tags tagName);

    /**
     * 通过标签名称获取标签id
     *
     * @param tagNames
     * @return 因为查询到的标签id是int类型而且不止一个，所以使用list数组来存储查询到的标签id
     */
    List<Long> getTagsIdByTagName(@Param("tagNames") String[] tagNames);

    /**
     * 为当前文章添加标签
     *
     * @param tagIds
     * @param aid
     * @return
     */
    int saveTags2ArticleTags(@Param("tagIds") List<Long> tagIds, @Param("aid") Long aid);

    /**
     * 查询所有标签
     * @return
     */
    List<Tags> getAllTags();

    /**
     * 通过标签id更新标签信息
     * @param tags
     * @return
     */
    int updateTagById(Tags tags);

}
