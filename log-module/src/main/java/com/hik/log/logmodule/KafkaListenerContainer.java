package com.hik.log.logmodule;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;
/**
 * kafka 监听器
 */
@Component
@Slf4j
public class KafkaListenerContainer {
    /**
     * 监听方法 方法入参获取监听信息
     * @param consumerRecord
     */
    @KafkaListener(groupId = "hik",topicPartitions = {
            @TopicPartition(topic = "hik-topic",partitions = {"1","3"}),
            @TopicPartition(topic = "hik-topic",partitions = {"0","4"})
    })
    public void listener(ConsumerRecord consumerRecord){
        System.out.println(consumerRecord.value().toString());
    }
}
