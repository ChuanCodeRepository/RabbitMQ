package com.chuan.boot.service;

import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        Message msg = MessageBuilder.withBody(message.getBytes())
                .setHeader("color", "red")
                .setHeader("speed", "fast")
                .build();

        rabbitTemplate.send("exchange.header","", msg);
    }

}
