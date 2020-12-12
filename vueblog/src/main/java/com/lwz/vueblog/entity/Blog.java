package com.lwz.vueblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.io.Serializable;

/**
 * (Blog)实体类
 *
 * @author Lw中
 * @since 2020-06-20 08:28:53
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_blog")
public class Blog implements Serializable {
  private static final long serialVersionUID = -66393507787673840L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private Long userId;

  @NotBlank(message = "标题不能为空")
  private String title;

  @NotBlank(message = "摘要不能为空")
  private String description;

  @NotBlank(message = "内容不能为空")
  private String content;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date created;

  private Integer status;

}
