package com.lwz.vblog.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * 此类实现了passwordEncoder：主要用于对密码加密
 * @author Lw中
 * @date 2020/7/29 19:20
 */

@Component
public class MyPasswordEncoder implements PasswordEncoder {

    /**
     *此类将用户传入的明文密码进行加密，返回密文
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
    }

    /**
     * 用户登录时，将用户输入的密码和数据库中的密文密码校对
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes()));
    }


}
