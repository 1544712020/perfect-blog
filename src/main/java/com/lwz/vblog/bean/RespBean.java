package com.lwz.vblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  响应类，用于对返回结果信息进行封装
 * @author Lw中
 * @date 2020/7/21 15:39
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private String status;
    private String msg;

}
