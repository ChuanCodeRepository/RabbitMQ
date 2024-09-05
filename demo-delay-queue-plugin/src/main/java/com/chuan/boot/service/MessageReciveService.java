package com.chuan.boot.service;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageReciveService {

        @RabbitListener(queues = "${mq.delay-queue-name}")
        public void reciveDeadMessage(Message message){

            message.getMessageProperties().getHeaders().forEach((k,v)-> System.out.println(k+":"+v));
            String body = new String(message.getBody());
            System.out.println("消息接收成功:" + body);
            System.out.println("消息接收时间:" + LocalDateTime.now());


        }














}
