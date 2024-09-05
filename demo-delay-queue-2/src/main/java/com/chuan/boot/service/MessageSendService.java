package com.chuan.boot.service;

import com.chuan.boot.config.MqConfigProperties;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

@Service
public class MessageSendService {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Resource
    private MqConfigProperties mqConfigProperties;

    public void sendMessage(String message,String routingKey) {
        amqpTemplate.convertAndSend(mqConfigProperties.getExchangeName(), routingKey, message);
        System.out.println("消息发送成功:" + new String(message.getBytes()));
    }








}
