package com.lwz.vblog.controller;

import com.lwz.vblog.bean.Category;
import com.lwz.vblog.bean.RespBean;
import com.lwz.vblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lw中
 * @date 2020/8/3 23:22
 */

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 获取所有文章类别
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * 批量删除文章类别
     * @param ids
     * @return
     */
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteById(@PathVariable String ids) {
        boolean result = categoryService.deleteCategoryByIds(ids);
        if (result) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    /**
     * 添加新的文章类别
     * @param category
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewCate(Category category) {
        if ("".equals(category.getCateName()) || category.getCateName() == null) {
            return new RespBean("error", "请输入栏目名称!");
        }
        int result = categoryService.addCategory(category);
        if (result == 1) {
            return new RespBean("success", "添加成功!");
        }
        return new RespBean("error", "添加失败!");
    }

    /**
     * 更新文章类别
     * @param category
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateCate(Category category) {
        int i = categoryService.updateCategoryById(category);
        if (i == 1) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }

}
