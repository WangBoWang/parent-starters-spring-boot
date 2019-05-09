package com.spring.boot.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * kafka消息发送者
 */
@Slf4j
@Component
public class Sender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic,String message) {
        try {
            log.info("sending data='{}' to topic='{}'", message, topic);
            ListenableFuture<SendResult<String, String>> future =  kafkaTemplate.send(topic, message);
            future.addCallback(success -> log.info("KafkaMessageProducer 发送消息成功！"),
                    fail -> log.error("KafkaMessageProducer 发送消息失败！"));
        }catch (Exception e){
            log.error("KafkaMessageProducer 发送消息失败！");
        }
    }
}
