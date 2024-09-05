package com.chuan.boot.service;

import com.chuan.boot.config.MqConfigProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSendService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MqConfigProperties mqConfigProperties;

    @PostConstruct
    public RabbitTemplate rabbitTemplate() {
        /*
         * ConfirmCallBack: 消息回调,当生产者给交换机发送消息后,无论消息是否到达交换机,都会回调该方法.
         * confirm这一步骤存在于消息生产者和交换机这一步,与后续的消息队列和交换机之间不存在确认步骤.
         * 只要发送了消息就会回调接口.
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {

            System.out.println("correlationData:" + correlationData);
            if (ack) {
                System.out.println("CONFIRM:消息发送成功");
            } else {
                System.out.println("CONFIRM:消息发送失败");
            }
        });

        /*
         * ReturnCallBack: 消息回调,当交换机把消息路由给队列失败时,才会回调该方法.
         */
        rabbitTemplate.setReturnsCallback(returnedMessage -> System.out.println("returnedMessage:" + returnedMessage.getReplyText()));

        return rabbitTemplate;
    }

    public void sendMessage(String message) {

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("1");

        rabbitTemplate.convertAndSend(mqConfigProperties.getExchangeName(), mqConfigProperties.getRoutingKey() + "1", message,correlationData);
        System.out.println("消息发送成功:" + new String(message.getBytes()));

    }

}
