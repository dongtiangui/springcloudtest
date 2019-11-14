package com.integral.boot.messagequeue;

import org.apache.commons.lang.StringUtils;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AlarmConsumer {

//    设置消息接受器
    @JmsListener(destination = "customer-buy-shop")
    public void consumer(String text){
        if(StringUtils.isNotBlank(text)){
            System.out.println("AlarmConsumer收到的报文为:"+text);
            System.out.println("把报警信息["+text+"]发送邮件给xxx");
            System.out.println("把报警信息["+text+"]发送短信给xxx");
        }
    }

    @JmsListener(destination = "customer-buy-shop")
    public void consumer1(String text){
        if (StringUtils.isNotBlank(text)){
            System.out.println("我是第二个消费者："+text);
        }
    }
}
