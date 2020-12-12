package com.lwz.vblog.mapper;

import com.lwz.vblog.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/22 20:35
 */

@Mapper
public interface CategoryMapper {

    /**
     * 获取所有的文章类别
     *
     * @return
     */
    List<Category> getAllCategories();

    /**
     * 通过ids数组批量删除文章类别
     *
     * @param ids
     * @return
     */
    int deleteCategoryByIds(@Param("ids") String[] ids);

    /**
     * 通过category更新文章类别
     *
     * @param category
     * @return
     */
    int updateCategoryById(Category category);

    /**
     * 添加文章类别
     *
     * @param category
     * @return
     */
    int addCategory(Category category);

}
