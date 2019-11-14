package com.alipay.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author alipay.demo
 *
 */
@SpringBootApplication
@ComponentScan( basePackages="com.alipay.demo" )
public class Application extends SpringBootServletInitializer {
	//demo版本号
	public static final String ALIPAY_DEMO_VERSION = "alipay_demo_hello_20181207";
	private static final Logger   logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
    	logger.info("启动alipayDemo-hello系统...");

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
    	/*
        JettyEmbeddedServletContainerFactory factory =
                new JettyEmbeddedServletContainerFactory();
        */
        return new TomcatEmbeddedServletContainerFactory();
    }

}
