package com.hik.common.commonmodule.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * kafka 服务
 */
public interface KafkaServices {

    /**
     * kafka监听器
     * @param consumerRecord
     */
    public void kafkaListener(ConsumerRecord consumerRecord);

}
