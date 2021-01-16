package com.lwz.vblog.controller;

import com.lwz.vblog.bean.RespBean;
import com.lwz.vblog.bean.Tags;
import com.lwz.vblog.mapper.TagsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/tag")
public class TagController {

    @Autowired(required = false)
    private TagsMapper tagsMapper;

    /** 查询所有的标签 */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Tags> getAllTags() {
        return tagsMapper.getAllTags();
    }

    /** 通过id删除标签 */
    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteById(@PathVariable String ids) {
        String[] split = ids.split(",");
        int result = tagsMapper.deleteTagsByAid1(split);
        if (result > 0) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    /** 添加新的标签 */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewTag(Tags tag) {

        if (tag == null) {
            return new RespBean("error", "请输入标签名称!");
        }

        int result = tagsMapper.saveTags1(tag);

        if (result == 1) {
            return new RespBean("success", "添加成功!");
        }
        return new RespBean("error", "添加失败!");
    }

    /** 修改标签 */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public RespBean updateCate(Tags tags) {
        int i = tagsMapper.updateTagById(tags);
        if (i == 1) {
            return new RespBean("success", "修改成功!");
        }
        return new RespBean("error", "修改失败!");
    }

}
