package com.lwz.vblog.service;

import com.lwz.vblog.bean.Category;
import com.lwz.vblog.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/25 17:29
 */

@Service
@CacheConfig(cacheNames = "Category")
@Transactional
public class CategoryService {

    @Autowired(required = false)
    CategoryMapper categoryMapper;

    /**
     * 获取所有的文章类别
     * @return
     */
    @Cacheable(cacheNames = "All")
    public List<Category> getAllCategories() {
        return categoryMapper.getAllCategories();
    }

    /**
     * 批量删除文章类别（当执行删除操作之后会对名称为“All”的缓存进行清除）
     * @param ids
     * @return
     */
    @CacheEvict(value = "All", allEntries = true)
    public boolean deleteCategoryByIds(String ids) {
        String[] split = ids.split(",");
        int result = categoryMapper.deleteCategoryByIds(split);
        return result == split.length;
    }

    /**
     *  通过id更新文章类别（当执行修改操作之后会对名称为“All”的缓存进行清除）
     * @param category
     * @return
     */
    @CacheEvict(value = "All", allEntries = true)
    public int updateCategoryById(Category category) {
        return categoryMapper.updateCategoryById(category);
    }

    /**
     * 添加文章类别
     * @param category
     * @return
     */
    @CacheEvict(value = "All", allEntries = true)
    public int addCategory(Category category) {
        /**
         * Timestamp：是时间戳，将存储在数据库中的时间精确到秒（将获取到的秒数转换为精度到秒的时间戳）
         * System.currentTimeMillis()：获取当前时间秒数
         */
        category.setDate(new Timestamp(System.currentTimeMillis()));
        return categoryMapper.addCategory(category);
    }

}
