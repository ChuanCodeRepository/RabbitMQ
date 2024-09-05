package com.chuan.boot.service;

import com.chuan.boot.config.MqConfigproperties;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MqConfigproperties mqConfigproperties;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(mqConfigproperties.getExchangeName(), mqConfigproperties.getRoutingKey() + "error", message);
        System.out.println("send message:" + message);
    }

}
