package com.lwz.vueblog.shiro;

import lombok.Data;
import java.io.Serializable;

/**
 * @author Lw中
 * @date 2020/6/20 14:52
 */

@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

}