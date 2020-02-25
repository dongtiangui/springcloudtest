package com.integral.boot.messagequeue;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
public class EventListenerBuyShop implements ApplicationListener<BuyShop> {

    @Autowired
    private AlarmProducer alarmProducer;

    @Override
    public void onApplicationEvent(BuyShop buyShop) {
        Destination destination = new ActiveMQTopic("hik_active");
        alarmProducer.sendMessage(destination,buyShop.toString());
    }
}
