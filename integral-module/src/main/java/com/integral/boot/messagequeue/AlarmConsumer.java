package com.integral.boot.messagequeue;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
@Component
public class AlarmConsumer {
    private final static Logger LOGGER = LoggerFactory.getLogger(AlarmConsumer.class);
//    设置消息接受器
    @JmsListener(destination = "customer-buy-shop")
    public void consumer(String text){
        if(StringUtils.isNotBlank(text)){
            LOGGER.info("AlarmConsumer收到的报文为:"+text);
            LOGGER.info("把报警信息["+text+"]发送邮件给xxx");
            LOGGER.info("把报警信息["+text+"]发送短信给xxx");
        }
    }
    @JmsListener(destination = "customer-buy-shop")
    public void consumer1(String text){
        if (StringUtils.isNotBlank(text)){
            LOGGER.info("我是第二个消费者："+text);
        }
    }
}
