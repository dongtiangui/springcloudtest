package com.alipay.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.alipay.api.AlipayClient;

/** 控制层基础类，承载公共配置信息
 * @author alipay.demo
 *
 */
public class BaseController {
    /**
	 * 配置文件加载
	 */
    @Autowired  
    protected Environment config;

    @Autowired  
    protected AlipayClient alipayClient;
    

	public void setAlipayClient(AlipayClient alipayClient) {
		this.alipayClient = alipayClient;
	}

	public void setConfig(Environment config) {
		this.config = config;
	} 
    
}
