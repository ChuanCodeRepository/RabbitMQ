package com.chuan.boot.service;

import com.chuan.boot.config.MqConfigproperties;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MqConfigproperties mqConfigproperties;

    public void sendMessage(String message) {

        Message msg = new Message(message.getBytes());

        rabbitTemplate.convertAndSend(mqConfigproperties.getNormalExchangeName(), mqConfigproperties.getNormalRoutingKey(), msg);
    }



}
