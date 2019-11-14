package com.alipay.demo.dal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alipay.demo.pojo.UserInfo;

/**
 * @author alipay.demo
 *
 */
@Component
public class UserInfoMapper {
	private static Logger       logger = LoggerFactory.getLogger(UserInfoMapper.class);
	
	private Map<String, UserInfo>   userDb = new ConcurrentHashMap<String, UserInfo>();
	
	
    public int insert(UserInfo userInfo){
    	logger.info("模拟数据库插入一个用户:{}", JSON.toJSONString( userInfo) );
    	
    	userDb.put(userInfo.getUserId(), userInfo );
    	return 1;
    }

    public UserInfo selectByUserId(String userId){
    	logger.info("模拟数据库查询一个用户:{}", userId );
    	
    	return userDb.get( userId );
    }

}