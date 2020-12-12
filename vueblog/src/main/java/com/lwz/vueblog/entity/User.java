package com.lwz.vueblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author Lw中
 * @EqualsAndHashCode：此注解会生成equals(Object other) 和 hashCode()方法。
 * @Accessors：用于配置getter和setter方法的生成结果，chain的中文含义是链式的， 设置为true，则setter方法返回当前对象。
 * @since 2020-06-20 08:29:29
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_user")
public class User implements Serializable {
  private static final long serialVersionUID = 247180356803303792L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @NotBlank(message = "昵称不能为空")
  private String username;

  private String avatar;

  @NotBlank(message = "邮箱不同为空")
  @Email(message = "邮箱不正确")
  private String email;

  private String password;

  private Integer status;

  /**
   * 格式化json数据
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date created;

  private Date lastLogin;

}
