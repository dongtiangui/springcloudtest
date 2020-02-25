package com.integral.boot.messagequeue;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 生产者
 */
@Component
public class AlarmProducer {

    @Resource(name = "jmsQueueTemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, String message){
        this.jmsTemplate.convertAndSend(destination,message);
    }
}
