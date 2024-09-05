package com.chuan.boot.service;

import com.chuan.boot.config.MqConfigProperties;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class MessageSendService {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Resource
    private MqConfigProperties mqConfigProperties;

    public void sendMessage(String message,Integer delay) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("x-delay", delay);

        Message msg = new Message(message.getBytes(), messageProperties);

        amqpTemplate.convertAndSend(mqConfigProperties.getDelayExchangeName(), mqConfigProperties.getDelayRoutingKey(), msg);
        System.out.println("消息发送成功:" + new String(message.getBytes()));
        System.out.println("消息发送时间:" + LocalDateTime.now());
    }

}
