package com.oauth2.oauthsecurity.securityrealm;

import com.oauth2.oauthsecurity.dao.UserDao;
import com.oauth2.oauthsecurity.domain.PermissionEntity;
import com.oauth2.oauthsecurity.domain.RoleEntity;
import com.oauth2.oauthsecurity.domain.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpringSecurityUser implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SpringSecurityUser.class);
    /**
     *
     自定义认证器 包括用户认证  用户授权  资源分配
     UserDetails 统一认证对象封装器
     加载用户信息
     */
    private final UserDao userDao;

    public SpringSecurityUser(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity sysUser = userDao.findByUsername(username);
        if (null == sysUser) {
            throw new BadCredentialsException("你的账号有误！请重新输入");
        }
        Optional<UserEntity> optional = Optional.of(sysUser);
        UserEntity entity = optional.get();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : entity.getRoleEntity()) {
            assert role!=null;
            for (PermissionEntity permission : role.getPermissionEntityList()) {
                assert permission!=null;
                authorities.add(new SimpleGrantedAuthority(permission.getPermission_name()));
            }
        }
        logger.info(authorities.toString());
        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }
    /**
     * 检查数据校验U盘是否存在
     * @return b
     */
    public boolean CheckUPan(){
        return true;
    }
}
