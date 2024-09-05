package com.chuan.boot.service;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReciveService {

        /**
         * @ param
         * @ return void
         * 监听死信队列,查看过期消息,是否会等到队列里最前面也是超时时间最大的消息一起到达死信队列.
         */
        @RabbitListener(queues = "${mq.deadQueueName}")
        public void reciveDeadMessage(Message message){

            message.getMessageProperties().getHeaders().forEach((k,v)-> System.out.println(k+":"+v));
            String body = new String(message.getBody());
            System.out.println("消息接收成功:" + body);

        }














}
