package com.oauth2.oauthsecurity.util;

import com.oauth2.oauthsecurity.domain.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class UsernamePasswordInfo {
    private static UserEntity userEntity;
    private UsernamePasswordInfo(){}
    private static UserEntity getExamples(){
        if (userEntity!=null){
            return userEntity;
        }
        userEntity = new UserEntity();
        return userEntity;
    }
    private Map<String,Object> getUserInfo(){
        Map<String,Object> map = new ConcurrentHashMap<>(20);
        UserEntity entity = getExamples();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        String password = userDetails.getPassword();
        entity.setUsername(username);
        entity.setPassword(password);
        map.put("USERINFO",userEntity);
        map.put("SECURITY",userDetails);
        map.put("LOGIN_TIME",getTime());
        return map;
    }

    /**
     * 获取时间
     * @return String
     */
    private String getTime(){
        LocalDateTime time = LocalDateTime.now();
        return time.format(DateTimeFormatter.ofPattern("yyyy:MM:dd:mm:ss"));
    }
}
