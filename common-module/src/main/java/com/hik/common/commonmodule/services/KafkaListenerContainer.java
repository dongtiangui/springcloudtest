package com.hik.common.commonmodule.services;
import com.hik.common.commonmodule.utils.KettleUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * kafka 监听器
 */
@Component
@Slf4j
public class KafkaListenerContainer implements KafkaServices{
    @Autowired
    private KettleUtils kettleUtils;
    /**
     * 监听方法 方法入参获取监听信息
     * @param consumerRecord
     */
    @KafkaListener(topics = {"etl-hik"})
    @Override
    public void kafkaListener(ConsumerRecord consumerRecord){
        log.info((String) consumerRecord.key(),consumerRecord.value());
        log.info("监听kafka信息！实现业务");
    }
}
