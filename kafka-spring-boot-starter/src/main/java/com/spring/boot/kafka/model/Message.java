package com.spring.boot.kafka.model;

import lombok.Data;

@Data
public class Message {

    private Long id;
    private String msg;
    private Long sendTime;
}
