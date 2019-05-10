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

    private int consumerAcount = 0;
    private int consumerBcount = 0;
    private int consumerCcount = 0;

    @PostMapping("/send")
    public void send(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10000;i++){
                    Message message = new Message();
                    message.setId(Long.valueOf(i));
                    message.setMsg("hello kafka");
                    message.setSendTime(System.currentTimeMillis());
                    try {
                        Thread.sleep(500);
                        sender.send(topic, message.toString());
                    } catch (InterruptedException e) {
                        log.info("发送异常");
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @KafkaListener(topics = topic)
    public void consumerA(ConsumerRecord consumerRecord){
        log.info("消费者A收到信息: {},总消费量: {}",consumerRecord.value().toString(),++consumerAcount);
    }

    @KafkaListener(topics = topic)
    public void consumerB(ConsumerRecord consumerRecord){
        log.info("消费者B收到信息: {},总消费量: {}",consumerRecord.value().toString(),++consumerBcount);
    }

    @KafkaListener(topics = topic)
    public void consumerC(ConsumerRecord consumerRecord){
        log.info("消费者C收到信息: {},总消费量: {}",consumerRecord.value().toString(),++consumerCcount);
    }

}