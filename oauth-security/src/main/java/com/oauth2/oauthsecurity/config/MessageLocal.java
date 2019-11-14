package com.oauth2.oauthsecurity.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.annotation.Order;

import java.util.Locale;

@Configuration
public class MessageLocal {
    /**
     *	下面为加载自定义消息的properties文件，我放在了maven结构resources下，里面的提示消息可以自己定义，
     *	比如：密码错误是，原文件中提示的是，坏的凭证，我们可以找到它对应的key，修改它的值为 用户名或密码错误。
     */
    @Bean
    @Order(0)
    public MessageSource messageSource() {
        Locale.setDefault(Locale.CHINA);
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:messages_zh_CN");

        return messageSource;
    }
}
