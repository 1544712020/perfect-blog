package com.lwz.vblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author Lw中
 * @date 2020/7/21 15:41
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private Long id;
    private String cateName;
    private Timestamp date;

}
