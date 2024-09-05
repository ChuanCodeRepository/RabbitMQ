package com.chuan.boot.service;

import com.chuan.boot.config.MqConfigProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
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
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
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
        });
        return rabbitTemplate;
    }

    /**
     * @ param
     * @ return void
     *
     * ConfirmCallBack: 消息回调,当生产者给交换机发送消息后,无论消息是否到达交换机,都会回调该方法.
     * confirm这一步骤存在于消息生产者和交换机这一步,与后续的消息队列和交换机之间不存在确认步骤.
     * 只要发送了消息就会回调接口.
     */
    public void sendMessage(String message) {

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("1");

        rabbitTemplate.convertAndSend(mqConfigProperties.getExchangeName(), mqConfigProperties.getRoutingKey(), message,correlationData);
        System.out.println("消息发送成功:" + new String(message.getBytes()));

    }

}
