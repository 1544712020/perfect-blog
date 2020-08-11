package com.lwz.vueblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Lw中
 * @date 2020/6/20 14:46
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
