package com.integral.boot.messagequeue;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class BuyShopAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public void send(ApplicationEvent applicationEvent){
        applicationContext.publishEvent(applicationEvent);
    }
}
