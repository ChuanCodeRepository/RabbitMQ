package com.chuan.boot.service;

import com.chuan.boot.config.MqConfigProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MessageService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MqConfigProperties mqConfigProperties;

    public void sendMessage(String message) {

        MessageProperties messageProperties = new MessageProperties();
        //设置过期毫秒数
        messageProperties.setExpiration("100000000000");
        Message msg = new Message(message.getBytes(), messageProperties);

        System.out.println(LocalDateTime.now());
        rabbitTemplate.convertAndSend(mqConfigProperties.getExchangeName(), mqConfigProperties.getRoutingKey(), msg);
        System.out.println(LocalDateTime.now());
    }

}
