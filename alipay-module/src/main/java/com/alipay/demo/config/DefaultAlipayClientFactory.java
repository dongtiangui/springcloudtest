package com.alipay.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;


/**
 * @author alipay.demo
 *
 */
@Component
public class DefaultAlipayClientFactory implements FactoryBean<AlipayClient>, InitializingBean{
	private static final Logger   logger = LoggerFactory.getLogger(DefaultAlipayClientFactory.class);
	
	private AlipayClient alipayClient = null;

	@Autowired  
    protected Environment config;
	
	@Override
	public AlipayClient getObject() throws Exception {
		return alipayClient;
	}

	@Override
	public Class<?> getObjectType() {
		return AlipayClient.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 请求方式 json
		String FORMAT = "json";
		// 编码格式，目前只支持UTF-8
		String CHARSET = "UTF-8";
		// 签名方式
		String SIGN_TYPE = "RSA2";
		// 网关
		String URL = config.getProperty("application.alipay.gateway");
		// 商户APP_ID
		String APP_ID = config.getProperty("application.appId");
		// 商户RSA 私钥
		String APP_PRIVATE_KEY = config.getProperty("application.privatekey");
		// 支付宝公钥
		String ALIPAY_PUBLIC_KEY = config.getProperty("application.publickey");
		
		alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		
		logger.info("创建支付宝网关访问客户端完成, 网关地址：{}，appId:{}", URL, APP_ID);
	}
}