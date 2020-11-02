package com.lwz.vblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lw中
 * @date 2020/7/21 14:23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private Long id;
    private String name;

}
