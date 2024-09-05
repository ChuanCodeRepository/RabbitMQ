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

    public void sendMessage(String message,String expiration) {
        // 发送消息时候,单独指定消息的过期时间,模拟消息阻塞问题.
        // 当后发消息比前面消息的过期时间短,则会被阻塞,直到前面的消息过期,才会一同转发到死信队列.
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration(expiration);

        Message msg = new Message(message.getBytes(), messageProperties);

        amqpTemplate.convertAndSend(mqConfigProperties.getNormalExchangeName(), mqConfigProperties.getNormalRoutingKey(), msg);
        System.out.println("消息发送成功:" + new String(message.getBytes()));
    }








}
