package com.alipay.demo.controller.rest;

import java.util.Date;

import org.jboss.resteasy.spi.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.alipay.demo.config.RestConstants;
import com.alipay.demo.pojo.UserInfo;
import com.alipay.demo.service.UserInfoService;
import org.springframework.stereotype.Controller;

/**
 * @author alipay.demo
 *
 */
@Controller
public class UserControllerImpl implements UserController {
	private static final Logger   logger = LoggerFactory.getLogger(UserControllerImpl.class);
	
	@Autowired
	private AlipayClient alipayClient;

	@Autowired
	private UserInfoService userInfoService;

	@Override
	public UserInfo authUser(String authCode, HttpResponse response) throws Exception {
		if (isBlank(authCode)) {
			logger.warn("授权编码不能为空!");
			throw new Exception("授权编码不能为空");
		}
		// token and userId
		AlipaySystemOauthTokenResponse alipaySystemOauthTokenResponse = this.getAccessToken(authCode);
		if (!alipaySystemOauthTokenResponse.isSuccess()) {
			logger.warn("换取 AuthToken 失败！错误编码：{}, 错误信息：{}", alipaySystemOauthTokenResponse.getCode(), alipaySystemOauthTokenResponse.getMsg() );
			throw new Exception("换取 AuthToken 失败 code=" + alipaySystemOauthTokenResponse.getCode() + ", msg="
					+ alipaySystemOauthTokenResponse.getMsg());
		}
		String accessToken = alipaySystemOauthTokenResponse.getAccessToken();
		
		logger.info("通过AuthCode:{} 换取AuthToken:{} 成功！", authCode, accessToken );
		
		// get user by accessToken
		AlipayUserInfoShareResponse alipayUserInfoShareResponse = this.getUserInfoFromAlipay(accessToken);
		String userId = alipayUserInfoShareResponse.getUserId();
		if (isBlank(userId)) {
			logger.warn("获取用户信息失败 错误编码：{}, 错误信息：{}", alipaySystemOauthTokenResponse.getCode(), alipaySystemOauthTokenResponse.getMsg() );
			throw new Exception("获取用户信息失败 code=" + alipayUserInfoShareResponse.getCode() + ", msg="
					+ alipayUserInfoShareResponse.getBody());
		}

		UserInfo userInfo = this.convertFromAlipayUserInfoShareResponse(alipayUserInfoShareResponse);
		logger.info("获取用户信息成功！ 当前用户名：{}， 用户图标：{}", userInfo.getNickName(), userInfo.getAvatar() );
		
		userInfo.setAccessToken(accessToken);
		userInfo.setRefreshToken(alipaySystemOauthTokenResponse.getRefreshToken());
		// insert or update userInfo
		this.userInfoService.insertOrUpdateByUserId(userInfo);
		// header added
		response.getOutputHeaders().putSingle(RestConstants.ACCESS_TOKEN_KEY, accessToken);
		response.getOutputHeaders().putSingle(RestConstants.USER_ID_KEY, alipayUserInfoShareResponse.getUserId());
		
		return userInfo;
	}

	private AlipaySystemOauthTokenResponse getAccessToken(String authCode) throws AlipayApiException {
		AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
		request.setGrantType("authorization_code");
		request.setCode(authCode);
		return alipayClient.execute(request);
	}

	private AlipayUserInfoShareResponse getUserInfoFromAlipay(String accessToken) throws AlipayApiException {
		AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
		return alipayClient.execute(request, accessToken);
	}

	private UserInfo convertFromAlipayUserInfoShareResponse(AlipayUserInfoShareResponse alipayUserInfoShareResponse) {
		UserInfo userInfo = new UserInfo();
		String nickName = alipayUserInfoShareResponse.getNickName();
		userInfo.setUserId(alipayUserInfoShareResponse.getUserId());
		userInfo.setNickName(isBlank(nickName) ? "" : nickName);
		String avatar = alipayUserInfoShareResponse.getAvatar();
		userInfo.setAvatar(isBlank(avatar) ? "" : avatar);
		// now
		userInfo.setModifyTime(new Date());
		return userInfo;
	}

	private boolean isBlank(String s) {
		return s == null || s.equals("");
	}
}
