package com.alipay.demo.service;

import com.alipay.demo.pojo.UserInfo;

/** 服务层代码Demo，对用户信息进行操作
 * @author alipay.demo
 *
 */
public interface UserInfoService {

    int insertOrUpdateByUserId(UserInfo userInfo);

    UserInfo selectByUserId(String userId);
}
