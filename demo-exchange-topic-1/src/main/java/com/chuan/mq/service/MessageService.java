package com.chuan.mq.service;

import com.chuan.mq.config.MqConfigProperties;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MqConfigProperties mqConfigProperties;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(mqConfigProperties.getExchangeName(), "*.orange.rabbit", message);
    }


}
