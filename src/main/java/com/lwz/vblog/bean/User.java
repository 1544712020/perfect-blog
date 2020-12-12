package com.lwz.vblog.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lw中
 * @date 2020/7/21 14:18
 * <p>
 * User继承SpringSecurity包下的UserDetails接口
 * 实现该接口来定义自己认证用户的获取方式
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private Boolean enabled;
    private String email;
    private String userface;
    private Timestamp regTime;
    private List<Role> roles;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 此类实现了UserDetails接口，重写了相应的方法
     * isAccountNonExpired：账户是否过期
     * isAccountNonLocked：账户是否加锁
     * isCredentialsNonExpired：密码是否过期
     * getAuthorities：返回用户信息
     */
    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
