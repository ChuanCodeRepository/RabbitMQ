package com.chuan.mq.service;

import com.chuan.mq.config.MqConfigProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties({MqConfigProperties.class})
public class MessageService {
    @RabbitListener(queues = {"queue.topic.a", "queue.topic.b"})    public void receiveMessage(Message msg) {
        System.out.println("receiveMessage: " + new String(msg.getBody()));
    }



}
