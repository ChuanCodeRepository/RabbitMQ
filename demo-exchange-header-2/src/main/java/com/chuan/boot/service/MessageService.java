package com.chuan.boot.service;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @RabbitListener(queues = {"queue.header.a","queue.header.b"})
    public void receiveMessage(Message message) {
        System.out.println("消费队列：" + message.getMessageProperties().getConsumerQueue());
        System.out.println("消息头：" + message.getMessageProperties().getHeaders());
    }

}
