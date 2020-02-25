package com.integral.boot.messagequeue;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

@Component
public class BuyShop extends ApplicationEvent {

    private static final long serialVersionUID = -1849361110150968247L;

    public BuyShop(@Qualifier("buyShopAware") Object source) {
        super(source);
    }

    @Resource(name = "jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, String message){
        this.jmsTemplate.convertAndSend(destination,message);
    }
}
