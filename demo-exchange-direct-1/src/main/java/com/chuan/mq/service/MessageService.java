package com.chuan.mq.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageService {

    @Resource
    public RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        String message = "direct";
        Message msg = new Message(message.getBytes());
        rabbitTemplate.convertAndSend("exchange.direct", "direct.A", msg);
        //rabbitTemplate.convertAndSend("exchange.direct", "direct.B", msg);


    }

}
