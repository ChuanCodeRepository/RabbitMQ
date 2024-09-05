package com.chuan.boot.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("correlationData:" + correlationData);
        if (ack) {
            System.out.println("CONFIRM:消息发送成功");
        } else {
            System.out.println("cause:" + cause);
            System.out.println("消息发送失败");
        }
    }

}
