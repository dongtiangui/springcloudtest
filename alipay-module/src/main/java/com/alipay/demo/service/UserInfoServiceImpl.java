package com.alipay.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipay.demo.dal.UserInfoMapper;
import com.alipay.demo.pojo.UserInfo;
import org.springframework.stereotype.Service;

/** 服务层代码Demo，对用户信息进行操作
 * @author alipay.demo
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper      userInfoMapper;

    @Override
    public int insertOrUpdateByUserId(UserInfo userInfo) {
    	return userInfoMapper.insert(userInfo);
    }

    @Override
    public UserInfo selectByUserId(String userId) {
    	return userInfoMapper.selectByUserId(userId);
    }

}
