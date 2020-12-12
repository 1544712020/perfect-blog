package com.lwz.vueblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwz.vueblog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author Lw中
 * @since 2020-06-20 08:34:02
 */
public interface UserDao extends BaseMapper<User> {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  User queryById(Long id);

  /**
   * 查询指定行数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


  /**
   * 通过实体作为筛选条件查询
   *
   * @param user 实例对象
   * @return 对象列表
   */
  List<User> queryAll(User user);


  /**
   * 修改数据
   *
   * @param user 实例对象
   * @return 影响行数
   */
  int update(User user);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Long id);

}
