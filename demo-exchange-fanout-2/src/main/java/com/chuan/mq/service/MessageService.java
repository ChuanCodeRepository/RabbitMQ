package com.chuan.mq.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageService {

    @RabbitListener(queues = {"queue.fanout.A", "queue.fanout.B"})
    public void receiveMessage(Message msg) {
        System.out.println("receiveMessage: " + new String(msg.getBody()));
    }

}
