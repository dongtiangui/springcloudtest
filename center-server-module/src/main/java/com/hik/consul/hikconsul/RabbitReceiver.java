package com.hik.consul.hikconsul;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {
    private final static Logger log = LoggerFactory.getLogger(RabbitReceiver.class);
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "hik_queue",
                    durable="true"),
            exchange = @Exchange(value = "hik_exchange",
                    type= "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "spring.*"))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        log.info("--------------------------------------");
        log.info("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }
}
