package com.lwz.vblog.service;

import com.lwz.vblog.bean.Category;
import com.lwz.vblog.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/25 17:29
 */

@Service
@Transactional
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 获取所有的文章类别
     * @return
     */
    public List<Category> getAllCategories() {
        return categoryMapper.getAllCategories();
    }

    /**
     * 批量删除文章类别
     * @param ids
     * @return
     */
    public boolean deleteCategoryByIds(String ids) {
        String[] split = ids.split(",");
        int result = categoryMapper.deleteCategoryByIds(split);
        return result == split.length;
    }

    /**
     *  通过id更新文章类别
     * @param category
     * @return
     */
    public int updateCategoryById(Category category) {
        return categoryMapper.updateCategoryById(category);
    }

    /**
     * 添加文章类别
     * @param category
     * @return
     */
    public int addCategory(Category category) {
        /**
         * LWZ TODO : 2020/7/29 设置时间问题
         * 为何这样设置时间
         */
        category.setDate(new Timestamp(System.currentTimeMillis()));
        return categoryMapper.addCategory(category);
    }

}
