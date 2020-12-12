package com.lwz.vueblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwz.vueblog.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Blog)表数据库访问层
 *
 * @author Lw中
 * @since 2020-06-20 08:30:01
 */
public interface BlogDao extends BaseMapper<Blog> {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  Blog queryById(Long id);

  /**
   * 查询指定行数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  List<Blog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


  /**
   * 通过实体作为筛选条件查询
   *
   * @param blog 实例对象
   * @return 对象列表
   */
  List<Blog> queryAll(Blog blog);

  /**
   * 修改数据
   *
   * @param blog 实例对象
   * @return 影响行数
   */
  int update(Blog blog);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Long id);

}
