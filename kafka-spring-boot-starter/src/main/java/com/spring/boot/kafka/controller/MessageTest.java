package com.spring.boot.kafka.controller;

import com.spring.boot.kafka.model.Message;
import com.spring.boot.kafka.producer.Sender;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * kafka测试
 * @author wangb
 * @create 2019/5/9
 * @since 1.0.0
 */
@RestController
@RequestMapping("/messgae")
@Slf4j
public class MessageTest {

    @Autowired
    private  Sender sender;
    private final static String topic = "vp_cds";

    @PostMapping("/send")
    public void send(){
        Message message = new Message();
        message.setId(UUID.randomUUID().getLeastSignificantBits());
        message.setMsg("hello kafka");
        message.setSendTime(System.currentTimeMillis());
        sender.send(topic, message.toString());
    }

    @KafkaListener(topics = topic)
    public void consumer(ConsumerRecord consumerRecord){
        log.info("消费信息: {}",consumerRecord.value().toString());
    }

}
