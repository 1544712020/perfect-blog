package com.lwz.vueblog.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Lw中
 * @date 2020/6/20 20:56
 */

@Data
public class LoginDto implements Serializable {

    /** 用于数据校验*/
    @NotBlank(message = "昵称不能为空")
    private String username;

    /** 用于数据校验*/
    @NotBlank(message = "密码不能为空")
    private String password;

}
