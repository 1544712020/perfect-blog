package com.lwz.vueblog.util;

import com.lwz.vueblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author Lw中
 * @date 2020/6/20 14:52
 */


public class ShiroUtil {

    public static AccountProfile getProfile() {
        // 获取Subject单例对象
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}
