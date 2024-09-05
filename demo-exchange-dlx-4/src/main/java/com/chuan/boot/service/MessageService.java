package com.chuan.boot.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageService {


    @RabbitListener(queues = "queue.direct.normal.3")
    public void receive(Message message, Channel channel) {

        MessageProperties messageProperties = message.getMessageProperties();
        long deliveryTag = messageProperties.getDeliveryTag();

        String body = new String(message.getBody());

        try {
            //todo 模拟业务处理异常
            int i = 1 / 0;
            // 业务处理完成后没问题,再进行手动确认,避免消费者自动确认,导致业务失败时,同时消息在队列中删除.
            // deliveryTag相当于消息的唯一标识符,维度是通道
            // false:是否批量确认, true:批量确认, false:只确认当前消息,不对累计的未确认的消息进行确认.
            channel.basicAck(deliveryTag, false);
            System.out.println("消费成功:" + body);
        } catch (Exception e) {
            System.out.println("消费失败,消息重新入队:" + body);
            try {
                // 手动不确认:channel.basicNack(deliveryTag, batch, retry);
                // retry为true,表示重新入队,不会进入死信队列,会重新放入队列,等待再次消费
                // 如果业务逻辑出现异常,则会一直重新消费,直到业务处理成功
                //channel.basicNack(deliveryTag, false, true);
                // retry为false,表示进入死信队列
                //channel.basicNack(deliveryTag, false, false);

                // basicReject:只拒绝当前消息,不对累计的未确认的消息进行拒绝;
                // 参数2为true表示重新入队,false表示进入死信队列
                channel.basicReject(deliveryTag, true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }


    }

}
