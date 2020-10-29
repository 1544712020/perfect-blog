package com.lwz.vblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lw中
 * @date 2020/7/21 15:37
 * 因为对文章使用了redis缓存，所以需要进行序列化
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tags implements Serializable {

private Long id;
private String tagName;

}
