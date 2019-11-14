package com.oauth2.oauthsecurity.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
/*
 * 用于检验用户信息
 */
public class UserDTO extends LoginSuccessVO implements UserDetails,Serializable {


    private static final long serialVersionUID = -1242685051020041319L;
    /**
     * 登录用户名
     */
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 是否记住
     */
    private boolean remember;


    /**
     * 获取权限信息
     * @return GrantedAuthority
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream()
                .map(roleName->new
                        SimpleGrantedAuthority("ROLE_"+roleName))
                .collect(Collectors.toList());
    }

    /**
     * 获取密码
     * @return String
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 获取用户名
     * @return boolean
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 检查是否过期
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 检查是否锁定
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户凭证是否未过期
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *  指定用是否启用，如果禁用则无法进行验证
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
